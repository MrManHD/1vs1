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

    fun setArenaScoreboard(arena: Arena, player: Player) {
        val scoreboardSettings = Minigame.instance.arenaConfigLoader.loadConfig().scoreboardSettings
        val fastBoard = FastBoard(player)

        fastBoard.updateTitle(scoreboardSettings.title)
        this.updateLines(arena, player, fastBoard)

        this.scoreBoards[player] = fastBoard
    }

    fun updateScoreboard(arena: Arena, player: Player) {
        this.scoreBoards[player]?.let { this.updateLines(arena, player, it) }
    }

    private fun updateLines(arena: Arena, player: Player, fastBoard: FastBoard) {
        val scoreboardSettings = Minigame.instance.arenaConfigLoader.loadConfig().scoreboardSettings
        val enemyPlayer = arena.players.firstOrNull { it.name != player.name }

        scoreboardSettings.scores.forEach { (score, text) ->
            fastBoard.updateLine(score, text
                .replace("%FIGHTER%", enemyPlayer?.name ?: "Unbekannt")
                .replace("%TARGET_ROUND%", arena.currentRound.toString())
                .replace("%MAX_ROUNDS%", arena.rounds.toString())
                .replace("%MAP%", arena.gameMap.mapName))
        }
    }

}