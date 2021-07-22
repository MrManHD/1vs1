package net.mrmanhd.onevsone.minigame.plugin.listener

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.extension.sendConfigMessage
import net.mrmanhd.onevsone.minigame.message.MessagePlaceholder
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

        if (Minigame.instance.configLoader.loadConfig().spawnLocation == null) {
            player.sendConfigMessage("setup.first.failed.spawn")
        }

        if (gameExecutor.availableMaps.isEmpty()) {
            player.sendConfigMessage("setup.first.failed.map")
        }

    }

}