package net.mrmanhd.onevsone.minigame.config.default

import net.mrmanhd.onevsone.minigame.config.arena.scoreboard.ScoreboardSettings
import org.bukkit.Location

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:28
 */

class Config(
    var chatPrefix: String,
    var defaultGamemode: String,
    var spawnLocation: Location?,
    val spawnScoreboardSettings: ScoreboardSettings
)