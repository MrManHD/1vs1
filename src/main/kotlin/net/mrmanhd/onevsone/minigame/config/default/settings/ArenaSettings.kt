package net.mrmanhd.onevsone.minigame.config.default.settings

/**
 * Created by MrManHD
 * Class create at 22.07.2021 17:15
 */

class ArenaSettings(
    val availableRounds: List<Int>,
    val countdownTimer: Int,
    val arenaCountdowns: List<ArenaCountdown>
) {

    fun getArenaCountdownByCount(countdownTimer: Int): ArenaCountdown? {
        return this.arenaCountdowns.firstOrNull { it.timer == countdownTimer }
    }

}