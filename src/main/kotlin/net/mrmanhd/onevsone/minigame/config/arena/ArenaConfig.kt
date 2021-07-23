package net.mrmanhd.onevsone.minigame.config.arena

import net.mrmanhd.onevsone.minigame.config.arena.scoreboard.ScoreboardSettings

/**
 * Created by MrManHD
 * Class create at 23.07.2021 10:07
 */

class ArenaConfig(
    val availableRounds: List<Int>,
    val scoreboardSettings: ScoreboardSettings,
    val countdownTimer: Int,
    val arenaCountdowns: List<ArenaCountdown>
) {

    fun getArenaCountdownByCount(countdownTimer: Int): ArenaCountdown? {
        return this.arenaCountdowns.firstOrNull { it.timer == countdownTimer }
    }

}