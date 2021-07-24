package net.mrmanhd.onevsone.minigame.game

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.game.arena.Arena
import net.mrmanhd.onevsone.minigame.game.kit.GameKit
import net.mrmanhd.onevsone.minigame.game.kit.GameKitLoader
import net.mrmanhd.onevsone.minigame.game.map.GameMap
import net.mrmanhd.onevsone.minigame.game.map.GameMapLoader
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:11
 */

class GameExecutor {

    val arenas = arrayListOf<Arena>()
    val availableKits = GameKitLoader().loadAll()
    val availableMaps = GameMapLoader().loadAll()

    fun getSpawnPlayers(): List<Player> {
        return Bukkit.getOnlinePlayers().filter { it.world.name ==
                Minigame.instance.configLoader.loadConfig().spawnLocation?.world?.name }
            .filter { arenas.firstOrNull { it2 -> it2.players.contains(it) } == null }
    }

    fun getMapByName(mapName: String): GameMap? {
        return this.availableMaps.firstOrNull { it.mapName == mapName }
    }

    fun getKitByName(kitName: String): GameKit? {
        return this.availableKits.firstOrNull { it.kitName == kitName }
    }

    fun getArenaByMapName(mapName: String): Arena? {
        return this.arenas.firstOrNull { it.gameMap.mapName == mapName }
    }

    fun getArenaByPlayer(player: Player): Arena? {
        return this.arenas.firstOrNull { it.players.contains(player) }
    }
}