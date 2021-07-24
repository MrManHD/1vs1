package net.mrmanhd.onevsone.minigame.plugin.listener

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.config.default.Config
import net.mrmanhd.onevsone.minigame.extension.sendConfigMessage
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:46
 */

class PlayerJoinListener : Listener {

    @EventHandler
    fun handleJoin(event: PlayerJoinEvent) {
        val player = event.player
        val gameExecutor = Minigame.instance.gameExecutor

        val config = Minigame.instance.configLoader.loadConfig()
        player.gameMode = GameMode.valueOf(config.defaultGamemode.uppercase())

        this.handleSpawnLocation(player, config)

        if (gameExecutor.availableMaps.isEmpty()) {
            player.sendConfigMessage("setup.first.failed.map")
        }

        if (gameExecutor.availableKits.isEmpty()) {
            player.sendConfigMessage("setup.first.failed.kit")
        }

        Minigame.instance.scoreboardHandler.setSpawnScoreboard(player)
    }

    private fun handleSpawnLocation(player: Player, config: Config) {
        config.spawnLocation?.let {
            player.teleport(it)
            return
        }
        player.sendConfigMessage("setup.first.failed.spawn")
    }

}