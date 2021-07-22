package net.mrmanhd.onevsone.minigame.config.default

import net.mrmanhd.onevsone.minigame.config.default.settings.ArenaCountdown
import net.mrmanhd.onevsone.minigame.config.default.settings.ArenaSettings
import net.mrmanhd.onevsone.minigame.message.ConfigMessage
import net.mrmanhd.onevsone.minigame.message.MessageType

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:30
 */

object DefaultConfig {

    fun get(): Config {
        return Config(
            "§8[§6§l1vs1§8]§7",
            null,
            this.getArenaSettings()
        )
    }

    private fun getArenaSettings(): ArenaSettings {
        return ArenaSettings(
            arrayListOf(1,2,3,5,10),
            5,
            arrayListOf(
                ArenaCountdown(5, "§45", "", 0, 100, 0),
                ArenaCountdown(4, "§c4", "", 0, 100, 0),
                ArenaCountdown(3, "§63", "", 0, 100, 0),
                ArenaCountdown(2, "§e2", "", 0, 100, 0),
                ArenaCountdown(1, "§a1", "", 0, 100, 0)
            )
        )
    }

}