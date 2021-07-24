package net.mrmanhd.onevsone.minigame.game.arena

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.extension.sendConfigMessage
import net.mrmanhd.onevsone.minigame.game.kit.GameKit
import net.mrmanhd.onevsone.minigame.game.map.GameMap
import net.mrmanhd.onevsone.minigame.game.state.GameState
import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:13
 */

class Arena(
    val gameMap: GameMap,
    val gameKit: GameKit,
    val players: List<Player>,
    val rounds: Int,
    val uniqueId: UUID = UUID.randomUUID()
) {

    private var playerToWinRoundsMap = hashMapOf<Player, Int>()
    private val arenaSettings = Minigame.instance.arenaConfigLoader.loadConfig()

    var currentRound = 1
    var gameState = GameState.COUNTDOWN

    fun startGame() {
        this.teleportPlayersToLocation()
        this.startCountdown()

        this.players.forEach {
            Minigame.instance.scoreboardHandler.setArenaScoreboard(this, it)
        }

        this.players.forEach { this.gameKit.equipPlayer(it) }
    }

    private fun setMinigameWinner(player: Player) {
        this.spawnFirework(player)

        this.players.forEach {
            it.sendConfigMessage("winner.player.message", player.name)
            it.playSound(it.location, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 10F, 10F)
        }
    }

    private fun spawnFirework(player: Player) {
        val entity = player.world.spawnEntity(player.location, EntityType.FIREWORK) as Firework
        val fireworkMeta = entity.fireworkMeta

        fireworkMeta.power = 1
        fireworkMeta.addEffect(
            FireworkEffect.builder().withColor(Color.ORANGE)
                .with(FireworkEffect.Type.BALL_LARGE).withColor(Color.WHITE).flicker(true).build()
        )

        entity.fireworkMeta = fireworkMeta
    }

    fun stopMinigame(winnerPlayer: Player) {
        this.gameState = GameState.ENDING

        val config = Minigame.instance.configLoader.loadConfig()
        val spawnLocation = config.spawnLocation

        this.setMinigameWinner(winnerPlayer)

        object : BukkitRunnable() {
            override fun run() {
                players.forEach {
                    val scoreboardHandler = Minigame.instance.scoreboardHandler
                    scoreboardHandler.scoreBoards[it]?.delete()
                    scoreboardHandler.setSpawnScoreboard(it)

                    it.inventory.clear()
                    it.health = 20.0
                    it.foodLevel = 20
                    it.teleport(spawnLocation!!)

                    it.gameMode = GameMode.valueOf(config.defaultGamemode.uppercase())
                }

                Minigame.instance.gameExecutor.arenas.removeIf { it.uniqueId == uniqueId }
            }
        }.runTaskLater(Minigame.instance.javaPlugin, (20 * 5))
    }

    fun nextRound() {
        if (this.currentRound == this.rounds) {
            this.stopMinigame(this.playerToWinRoundsMap.entries.maxByOrNull { it.value }!!.key)
            return
        }

        this.currentRound++
        this.startCountdown()
        this.resetPlayers()
    }

    private fun startCountdown() {
        var countdownTimer = this.arenaSettings.countdownTimer

        object : BukkitRunnable() {
            override fun run() {

                if (countdownTimer == 0) {
                    cancel()

                    players.forEach {
                        it.resetTitle()
                        it.sendConfigMessage("next.round.message", currentRound.toString())
                    }

                    gameState = GameState.INGAME
                }

                arenaSettings.getArenaCountdownByCount(countdownTimer)?.sendTitle()
                countdownTimer--

            }
        }.runTaskTimer(Minigame.instance.javaPlugin, 0, 20)
    }

    private fun resetPlayers() {
        this.players.forEach {
            it.gameMode = GameMode.SURVIVAL
            it.health = 20.0
            it.inventory.clear()

            this.gameKit.equipPlayer(it)
            Minigame.instance.scoreboardHandler.updateArenaScoreboard(this, it)
        }

        this.teleportPlayersToLocation()
    }

    private fun teleportPlayersToLocation() {
        this.players.shuffled()
            .forEachIndexed { index, player -> player.teleport(this.gameMap.getLocationList()[index]) }
    }

    fun addPlayerWin(player: Player) {
        this.playerToWinRoundsMap[player] = this.playerToWinRoundsMap.getOrDefault(player, 0) + 1
    }

}