package net.mrmanhd.onevsone.minigame.plugin.listener

import net.mrmanhd.onevsone.minigame.extension.getArena
import net.mrmanhd.onevsone.minigame.extension.sendConfigMessage
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

/**
 * Created by MrManHD
 * Class create at 23.07.2021 21:25
 */

class PlayerDeathListener : Listener {

    @EventHandler
    fun handleDeath(event: PlayerDeathEvent) {
        val player = event.entity

        player.getArena()?.let { arena ->
            event.deathMessage = null
            event.keepInventory = true
            event.drops.clear()
            event.droppedExp = 0

            player.health = 20.0

            val otherLivingPlayer = arena.players.first { it != player }

            player.playSound(player.location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 10F, 10F)

            arena.addPlayerWin(otherLivingPlayer)

            player.sendConfigMessage("death.entity.message", otherLivingPlayer.name)

            otherLivingPlayer.sendConfigMessage("death.killer.message", player.name)
            otherLivingPlayer.playSound(player.location, Sound.ENTITY_BLAZE_DEATH, 10F, 10F)

            arena.nextRound()
        }

    }

}