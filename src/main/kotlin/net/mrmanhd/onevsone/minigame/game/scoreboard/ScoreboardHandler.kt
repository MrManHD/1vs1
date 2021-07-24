package net.mrmanhd.onevsone.minigame.game.scoreboard

import fr.mrmicky.fastboard.FastBoard
import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.game.arena.Arena
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 22.07.2021 17:42
 */

class ScoreboardHandler {

    val scoreBoards = hashMapOf<Player, FastBoard>()

    fun setSpawnScoreboard(player: Player) {
        val scoreboardSettings = Minigame.instance.arenaConfigLoader.loadConfig().scoreboardSettings
        val fastBoard = FastBoard(player)

        fastBoard.updateTitle(scoreboardSettings.title)
        this.updateSpawnLines(fastBoard)

        this.scoreBoards[player] = fastBoard
    }

    fun setArenaScoreboard(arena: Arena, player: Player) {
        val scoreboardSettings = Minigame.instance.arenaConfigLoader.loadConfig().scoreboardSettings
        val fastBoard = FastBoard(player)

        fastBoard.updateTitle(scoreboardSettings.title)
        this.updateArenaLines(arena, player, fastBoard)

        this.scoreBoards[player] = fastBoard
    }

    fun updateArenaScoreboard(arena: Arena, player: Player) {
        this.scoreBoards[player]?.let { this.updateArenaLines(arena, player, it) }
    }

    fun updateSpawnScoreboard(player: Player) {
        this.scoreBoards[player]?.let { this.updateSpawnLines(it) }
    }

    private fun updateArenaLines(arena: Arena, player: Player, fastBoard: FastBoard) {
        val scoreboardSettings = Minigame.instance.arenaConfigLoader.loadConfig().scoreboardSettings
        val enemyPlayer = arena.players.firstOrNull { it.name != player.name }

        scoreboardSettings.scores.forEachIndexed { index, text ->
            if (index != 0) {
                fastBoard.updateLine(
                    index, text
                        .replace("%FIGHTER%", enemyPlayer?.name ?: "?")
                        .replace("%TARGET_ROUND%", arena.currentRound.toString())
                        .replace("%MAX_ROUNDS%", arena.rounds.toString())
                        .replace("%MAP%", arena.gameMap.mapName)
                        .replace("%KIT%", arena.gameKit.kitName)
                )
            }
        }
    }

    private fun updateSpawnLines(fastBoard: FastBoard) {
        val spawnScoreboardSettings = Minigame.instance.configLoader.loadConfig().spawnScoreboardSettings

        spawnScoreboardSettings.scores.forEachIndexed { index, text ->
            if (index != 0) {
                fastBoard.updateLine(
                    index, text
                        .replace("%MAX_PLAYERS%", Bukkit.getMaxPlayers().toString())
                        .replace("%ONLINE_PLAYERS%", Bukkit.getOnlinePlayers().size.toString())
                        .replace("%INGAME_ROUNDS%", Minigame.instance.gameExecutor.arenas.size.toString())
                )
            }
        }
    }

}