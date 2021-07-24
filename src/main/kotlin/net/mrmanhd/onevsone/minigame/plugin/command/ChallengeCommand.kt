package net.mrmanhd.onevsone.minigame.plugin.command

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.extension.sendConfigMessage
import net.mrmanhd.onevsone.minigame.game.arena.Arena
import net.mrmanhd.onevsone.minigame.utils.Challenge
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 24.07.2021 03:21
 */

class ChallengeCommand : CommandExecutor, TabCompleter {

    val challenges = arrayListOf<Challenge>()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        sender as Player

        when (args.size) {

            0 -> this.sendHelpMessage(sender)

            1 -> this.sendHelpMessage(sender)

            2 -> {


                when (args[0].lowercase()) {

                    "accept" -> {
                        val playerName = args[1]

                        Bukkit.getPlayer(playerName)?.let { player ->
                            val challenge = this.challenges.filter { it.challenger == player.name }
                                .firstOrNull { it.target == sender.name }

                            if (challenge == null) {
                                sender.sendConfigMessage("challenge.command.request.failed")
                                return false
                            }

                            val arenas = Minigame.instance.gameExecutor.arenas
                            val gameMap = challenge.getMap()

                            if (arenas.firstOrNull { it.gameMap == gameMap } != null) {
                                sender.sendConfigMessage("challenge.command.accept.failed.already.map", gameMap.mapName)
                                player.sendConfigMessage("challenge.command.accept.failed.already.map", gameMap.mapName)
                                return false
                            }

                            sender.sendConfigMessage("challenge.command.accept.target", playerName)
                            player.sendConfigMessage("challenge.command.accept.challenger", sender.name)

                            val gameExecutor = Minigame.instance.gameExecutor
                            val arena = Arena(
                                gameMap,
                                challenge.getKit(),
                                arrayListOf(sender, player),
                                challenge.rounds
                            )

                            arena.startGame()
                            gameExecutor.arenas.add(arena)

                            this.challenges.removeIf { it.challenger == player.name && it.target == sender.name }

                            return false
                        }

                        sender.sendConfigMessage("challenge.command.request.failed")
                    }

                    "deny" -> {

                    }

                    else -> this.sendHelpMessage(sender)

                }

            }

            3 -> this.sendHelpMessage(sender)

            4 -> {
                val playerName = args[0]

                when (playerName) {

                    "accept" -> this.sendHelpMessage(sender)
                    "deny" -> this.sendHelpMessage(sender)

                    else -> {
                        val gameExecutor = Minigame.instance.gameExecutor

                        Bukkit.getPlayer(playerName)?.let { targetPlayer ->
                            if (this.challenges.firstOrNull { it.target == targetPlayer.name } != null) {
                                sender.sendConfigMessage("challenge.command.challenge.failed.alreadysend")
                                return false
                            }

                            if (gameExecutor.getMapByName(args[1]) == null) {
                                sender.sendConfigMessage("challenge.command.challenge.failed.wrong.map")
                                return false
                            }

                            if (gameExecutor.getKitByName(args[2]) == null) {
                                sender.sendConfigMessage("challenge.command.challenge.failed.wrong.kit")
                                return false
                            }

                            if (!Minigame.instance.arenaConfigLoader.loadConfig().availableRounds.contains(args[3].toInt())) {
                                sender.sendConfigMessage("challenge.command.challenge.failed.wrong.round")
                                return false
                            }

                            val arenas = Minigame.instance.gameExecutor.arenas
                            if (arenas.firstOrNull { it.players.contains(targetPlayer) } != null) {
                                sender.sendConfigMessage("challenge.command.challenge.failed.player.inround", targetPlayer.name)
                                return false
                            }

                            sender.sendConfigMessage("challenge.command.challenge.start", targetPlayer.name)

                            targetPlayer.sendConfigMessage("challenge.command.challenge.start.target", sender.name)
                            targetPlayer.sendConfigMessage("challenge.command.challenge.start.target.info", sender.name)

                            this.challenges.add(
                                Challenge(
                                    sender.name,
                                    targetPlayer.name,
                                    args[1],
                                    args[2],
                                    args[3].toInt()
                                )
                            )
                            return false
                        }
                    }

                }

                sender.sendConfigMessage("challenge.command.challenge.failed")
            }

        }

        return false
    }

    private fun sendHelpMessage(sender: Player) {
        sender.sendConfigMessage("challenge.command.usage.help")
        sender.sendConfigMessage("challenge.command.usage.help.challenge")
        sender.sendConfigMessage("challenge.command.usage.help.accept")
        sender.sendConfigMessage("challenge.command.usage.help.deny")
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {

        when (args.size) {

            1 -> {
                val spawnPlayers = Minigame.instance.gameExecutor.getSpawnPlayers().map { it.name }
                    .filter { it != sender.name } as ArrayList<String>
                spawnPlayers.add("accept")
                spawnPlayers.add("deny")

                return spawnPlayers
            }

            2 -> {

                if (args[0] == "accept" || args[0] == "deny") {
                    return this.challenges.filter { it.target == sender.name }.map { it.challenger }.toMutableList()
                }

                return Minigame.instance.gameExecutor.availableMaps.map { it.mapName }.toMutableList()
            }

            3 -> {
                return Minigame.instance.gameExecutor.availableKits.map { it.kitName }.toMutableList()
            }

            4 -> {
                return Minigame.instance.arenaConfigLoader
                    .loadConfig().availableRounds.map { it.toString() }.toMutableList()
            }
        }

        return arrayListOf()
    }

}