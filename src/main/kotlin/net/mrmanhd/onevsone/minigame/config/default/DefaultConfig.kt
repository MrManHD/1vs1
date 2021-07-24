package net.mrmanhd.onevsone.minigame.config.default

import net.mrmanhd.onevsone.minigame.config.arena.scoreboard.ScoreboardSettings

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:30
 */

object DefaultConfig {

    fun get(): Config {
        return Config(
            "§8[§6§l1vs1§8]§7",
            "ADVENTURE",
            null,
            this.getScoreboardSettings()
        )
    }

    private fun getScoreboardSettings(): ScoreboardSettings {
        return ScoreboardSettings(
            "§6§l1vs1",
            arrayListOf(
                "",
                "§eSpieler §8»§7§o %ONLINE_PLAYERS%§8§o/§7§o%MAX_PLAYERS%",
                "§eRunden §8»§7§o %INGAME_ROUNDS% Online",
                ""
            )
        )
    }

}