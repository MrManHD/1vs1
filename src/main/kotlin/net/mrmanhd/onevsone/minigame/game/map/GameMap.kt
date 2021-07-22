package net.mrmanhd.onevsone.minigame.game.map

import net.mrmanhd.onevsone.minigame.utils.Nameable
import org.bukkit.Location

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:13
 */

class GameMap(
    val mapName: String,
    val spectatorLocation: Location,
    val locationOne: Location,
    val locationTwo: Location
) : Nameable {

    override fun getName(): String = this.mapName

}