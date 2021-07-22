package net.mrmanhd.onevsone.minigame.game.arena

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.game.map.GameMap
import net.mrmanhd.onevsone.minigame.game.state.GameState
import org.bukkit.Bukkit
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

    private val arenaSettings = Minigame.instance.configLoader.loadConfig().arenaSettings

    var gameState = GameState.COUNTDOWN

    fun startCountdown() {
        var countdownTimer = this.arenaSettings.countdownTimer

        object : BukkitRunnable() {
            override fun run() {

                if (countdownTimer == 0) {
                    Bukkit.getOnlinePlayers().forEach { it.resetTitle() }
                }

                arenaSettings.getArenaCountdownByCount(countdownTimer)?.sendTitle()
                countdownTimer--

            }
        }.runTaskTimer(Minigame.instance.javaPlugin, 0, 20)
    }

    fun startGame() {
        this.teleportPlayersToLocation()
    }

    private fun teleportPlayersToLocation() {
        val playerIterator = this.players.iterator()
        playerIterator.next().teleport(this.gameMap.locationOne)
        playerIterator.next().teleport(this.gameMap.locationTwo)
    }

}