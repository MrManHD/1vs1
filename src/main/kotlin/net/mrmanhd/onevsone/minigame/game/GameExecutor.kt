package net.mrmanhd.onevsone.minigame.game

import net.mrmanhd.onevsone.minigame.game.arena.Arena
import net.mrmanhd.onevsone.minigame.game.map.GameMap
import net.mrmanhd.onevsone.minigame.game.map.GameMapLoader
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:11
 */

class GameExecutor {

    val arenas = arrayListOf<Arena>()
    val availableMaps = GameMapLoader().loadAll()

    fun getMapByName(mapName: String): GameMap? {
        return this.availableMaps.firstOrNull { it.mapName == mapName }
    }

    fun getArenaByMapName(mapName: String): Arena? {
        return this.arenas.firstOrNull { it.gameMap.mapName == mapName }
    }

    fun getArenaByPlayer(player: Player): Arena? {
        return this.arenas.firstOrNull { it.players.contains(player) }
    }
}