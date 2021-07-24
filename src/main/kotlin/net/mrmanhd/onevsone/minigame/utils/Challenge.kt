package net.mrmanhd.onevsone.minigame.utils

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.game.kit.GameKit
import net.mrmanhd.onevsone.minigame.game.map.GameMap

/**
 * Created by MrManHD
 * Class create at 24.07.2021 03:51
 */

class Challenge(
    val challenger: String,
    val target: String,
    val mapName: String,
    val kitName: String,
    val rounds: Int
) {

    fun getMap(): GameMap {
        val gameExecutor = Minigame.instance.gameExecutor
        return gameExecutor.getMapByName(this.mapName)!!
    }

    fun getKit(): GameKit {
        val gameExecutor = Minigame.instance.gameExecutor
        return gameExecutor.getKitByName(this.kitName)!!
    }

}