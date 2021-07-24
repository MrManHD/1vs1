package net.mrmanhd.onevsone.minigame.config.arena

import net.mrmanhd.onevsone.minigame.config.arena.scoreboard.ScoreboardSettings

object DefaultArenaConfig {

    fun get(): ArenaConfig {
        return ArenaConfig(
            arrayListOf(1,2,3,5,10),
            this.getScoreboardSettings(),
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

    private fun getScoreboardSettings(): ScoreboardSettings {
        return ScoreboardSettings(
            "§6§l1vs1",
            arrayListOf(
                "",
                "§eMap §8»§7§o %MAP%",
                "",
                "§eKit §8»§7§o %KIT%",
                "§eGegner §8»§7§o %FIGHTER%",
                "§eRunde §8»§7§o %TARGET_ROUND%§8§o/§7§o%MAX_ROUNDS%",
                ""
            )
        )
    }
}