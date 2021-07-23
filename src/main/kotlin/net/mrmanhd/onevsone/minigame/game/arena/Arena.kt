package net.mrmanhd.onevsone.minigame.game.arena

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.game.map.GameMap
import net.mrmanhd.onevsone.minigame.game.state.GameState
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:13
 */

class Arena(
    val gameMap: GameMap,
    val players: List<Player>,
    val rounds: Int
) {

    private var playerToWinRoundsMap = hashMapOf<Player, Int>()
    private val arenaSettings = Minigame.instance.arenaConfigLoader.loadConfig()

    var currentRound = 1
    var gameState = GameState.COUNTDOWN

    fun startGame() {
        this.teleportPlayersToLocation()
        this.startCountdown()

        Bukkit.getOnlinePlayers().forEach {
            Minigame.instance.scoreboardHandler.setArenaScoreboard(this, it)
        }
    }

    fun stopMinigame(winnerPlayer: Player) {
        this.gameState = GameState.ENDING
        val spawnLocation = Minigame.instance.configLoader.loadConfig().spawnLocation

        object : BukkitRunnable() {
            override fun run() {
                players.forEach { it.teleport(spawnLocation!!) }
                //TODO: reset World
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

                    gameState = GameState.INGAME
                    Bukkit.getOnlinePlayers().forEach {
                        it.playSound(it.location, Sound.BLOCK_BEACON_ACTIVATE, 10F, 10F)
                        it.resetTitle()
                    }
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

            Minigame.instance.scoreboardHandler.updateScoreboard(this, it)
        }
        this.teleportPlayersToLocation()

        //TODO: update kits
    }

    private fun teleportPlayersToLocation() {
        val playerIterator = this.players.shuffled().iterator()
        playerIterator.next().teleport(this.gameMap.locationOne)
        playerIterator.next().teleport(this.gameMap.locationTwo)
    }

    fun addPlayerWin(player: Player) {
        this.playerToWinRoundsMap[player] = this.playerToWinRoundsMap.getOrDefault(player, 0) + 1
    }

}