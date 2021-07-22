package net.mrmanhd.onevsone.minigame.config.default.settings

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 22.07.2021 17:18
 */

class ArenaCountdown(
    val timer: Int,
    val title: String,
    val subtitle: String,
    val fadeIn: Int,
    val stay: Int,
    val fadeOut: Int,
) {

    fun sendTitle() {
        Bukkit.getOnlinePlayers().forEach { this.sendTitle(it) }
    }

    private fun sendTitle(player: Player) {
        player.sendTitle(this.title, this.subtitle, this.fadeIn, this.stay, this.fadeOut)
    }

}