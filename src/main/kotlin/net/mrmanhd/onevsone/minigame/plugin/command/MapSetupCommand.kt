package net.mrmanhd.onevsone.minigame.plugin.command

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.extension.sendConfigMessage
import net.mrmanhd.onevsone.minigame.game.map.GameMap
import net.mrmanhd.onevsone.minigame.game.map.GameMapLoader
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 22.07.2021 11:16
 */

class MapSetupCommand : CommandExecutor, TabCompleter {

    private var locationOne: Location? = null
    private var locationTwo: Location? = null
    private var spectatorLocation: Location? = null
    private var mapName: String? = null

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        sender as Player

        when (args.size) {

            0 -> {
                this.sendHelpMessage(sender)
            }

            1 -> {

                when (args[0].lowercase()) {

                    "locationone" -> {
                        this.locationOne = sender.location
                        sender.sendConfigMessage("setup.map.command.save.location", "locationOne")
                    }

                    "locationtwo" -> {
                        this.locationTwo = sender.location
                        sender.sendConfigMessage("setup.map.command.save.location", "locationTwo")
                    }

                    "spectatorlocation" -> {
                        this.spectatorLocation = sender.location
                        sender.sendConfigMessage("setup.map.command.save.location", "spectatorLocation")
                    }

                    "finish" -> {

                        if (this.mapName == null || this.locationOne == null || this.locationTwo == null || this.spectatorLocation == null) {
                            sender.sendConfigMessage("setup.map.command.finish.failed")
                            return false
                        }

                        val gameMap = GameMap(
                            this.mapName!!,
                            this.locationOne!!,
                            this.locationTwo!!,
                            this.spectatorLocation!!
                        )
                        GameMapLoader().save(gameMap)

                        sender.sendConfigMessage("setup.map.command.finished", this.mapName!!)

                        this.mapName = null
                        this.locationOne = null
                        this.locationTwo = null
                        this.spectatorLocation = null
                    }

                    else -> this.sendHelpMessage(sender)

                }

            }

            2 -> {

                when (args[0].lowercase()) {

                    "create" -> {
                        val availableMaps = Minigame.instance.gameExecutor.availableMaps
                        val gameMap = availableMaps.firstOrNull { it.mapName.lowercase() == args[1].lowercase() }

                        gameMap?.let {
                            sender.sendConfigMessage("setup.map.command.create.failed")
                            return false
                        }

                        this.mapName = args[1]
                        sender.sendConfigMessage("setup.map.command.created", args[1])
                    }

                    else -> this.sendHelpMessage(sender)

                }

            }

        }

        return false
    }

    private fun sendHelpMessage(sender: Player) {
        sender.sendConfigMessage("setup.map.command.usage.help")
        sender.sendConfigMessage("setup.map.command.usage.help.create")
        sender.sendConfigMessage("setup.map.command.usage.help.locationone")
        sender.sendConfigMessage("setup.map.command.usage.help.locationtwo")
        sender.sendConfigMessage("setup.map.command.usage.help.spectatorlocation")
        sender.sendConfigMessage("setup.map.command.usage.help.finish")
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if (args.size != 1) {
            return arrayListOf()
        }
        return arrayListOf("create", "locationOne", "locationTwo", "spectatorLocation", "finish")
    }

}