package net.mrmanhd.onevsone.minigame.plugin.command

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.extension.sendConfigMessage
import net.mrmanhd.onevsone.minigame.game.arena.Arena
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 22.07.2021 11:42
 */

class SetupCommand : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        sender as Player

        val configLoader = Minigame.instance.configLoader
        val config = configLoader.loadConfig()

        when (args.size) {

            0 -> this.sendHelpMessage(sender)

            1 -> {

                when (args[0].lowercase()) {

                    "spawn" -> {
                        config.spawnLocation = sender.location
                        configLoader.saveConfig(config)

                        sender.sendConfigMessage("setup.map.command.save.location", "spawnLocation")
                    }

                    else -> this.sendHelpMessage(sender)

                }

            }

            2 -> {

                when (args[0].lowercase()) {

                    "prefix" -> {
                        config.chatPrefix = args[1].replace("&", "§") + "§7"
                        configLoader.saveConfig(config)

                        sender.sendConfigMessage("setup.command.prefix.update")
                    }

                    "defaultgamemode" -> {
                        config.defaultGamemode = args[1].uppercase()
                        configLoader.saveConfig(config)

                        sender.sendConfigMessage("setup.command.defaultgamemode.update")
                    }

                    else -> this.sendHelpMessage(sender)

                }

            }

        }

        return false
    }

    private fun sendHelpMessage(sender: Player) {
        sender.sendConfigMessage("setup.command.usage.help")
        sender.sendConfigMessage("setup.command.usage.help.prefix")
        sender.sendConfigMessage("setup.command.usage.help.spawn")
        sender.sendConfigMessage("setup.command.usage.help.defaultgamemode")
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if (args.size == 2 && args[0].lowercase() == "defaultgamemode") {
            return GameMode.values().map { it.name.uppercase() }.toMutableList()
        }

        if (args.size != 1) {
            return arrayListOf()
        }
        return arrayListOf("prefix", "spawn", "defaultGamemode")
    }

}