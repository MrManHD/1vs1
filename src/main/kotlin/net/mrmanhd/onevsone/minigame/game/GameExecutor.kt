package net.mrmanhd.onevsone.minigame.game

import net.mrmanhd.onevsone.minigame.game.map.GameMap
import net.mrmanhd.onevsone.minigame.game.map.GameMapLoader

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:11
 */

class GameExecutor {

    val availableMaps = GameMapLoader().loadAll()

    fun getMapByName(mapName: String): GameMap? {
        return this.availableMaps.firstOrNull { it.mapName == mapName }
    }

}