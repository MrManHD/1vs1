package net.mrmanhd.onevsone.minigame.plugin.command

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.extension.sendConfigMessage
import net.mrmanhd.onevsone.minigame.game.kit.GameKit
import net.mrmanhd.onevsone.minigame.game.kit.GameKitLoader
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayOutputStream

/**
 * Created by MrManHD
 * Class create at 23.07.2021 14:12
 */

class KitSetupCommand : CommandExecutor, TabCompleter {

    var kitName: String? = null
    var materialName: String? = null

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        sender as Player

        when (args.size) {

            0 -> this.sendHelpMessage(sender)

            1 -> {

                when (args[0].lowercase()) {

                    "finish" -> {

                        if (this.kitName == null || this.materialName == null) {
                            sender.sendConfigMessage("setup.kit.command.finish.failed")
                            return false
                        }

                        val items = hashMapOf<Int, String>()
                        for (i in 0.. 40) {
                            items[i] = this.itemStackToBase64(sender.inventory.getItem(i) ?: ItemStack(Material.AIR))
                        }

                        val gameKit = GameKit(this.kitName!!, this.materialName!!, items)
                        GameKitLoader().save(gameKit)

                        sender.sendConfigMessage("setup.kit.command.finished", this.kitName!!)

                        this.kitName = null
                        this.materialName = null
                    }

                    else -> this.sendHelpMessage(sender)

                }

            }

            2 -> this.sendHelpMessage(sender)

            3 -> {

                when (args[0].lowercase()) {

                    "create" -> {
                        val availableKits = Minigame.instance.gameExecutor.availableKits
                        val gameKit = availableKits.firstOrNull { it.kitName.lowercase() == args[1].lowercase() }

                        gameKit?.let {
                            sender.sendConfigMessage("setup.kit.command.create.failed")
                            return false
                        }

                        this.kitName = args[1]
                        this.materialName = args[2]

                        sender.inventory.clear()

                        sender.sendConfigMessage("setup.kit.command.created", args[1])
                    }

                    else -> this.sendHelpMessage(sender)

                }

            }

        }

        return false
    }

    private fun sendHelpMessage(sender: Player) {
        sender.sendConfigMessage("setup.kit.command.usage.help")
        sender.sendConfigMessage("setup.kit.command.usage.help.create")
        sender.sendConfigMessage("setup.kit.command.usage.help.finish")
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if (args.size == 3) {
            return Material.values().map { it.name }.toMutableList()
        }
        if (args.size != 1) {
            return arrayListOf()
        }
        return arrayListOf("create", "finish")
    }

    private fun itemStackToBase64(item: ItemStack): String {
        return try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)

            dataOutput.writeObject(item)
            dataOutput.close()

            Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (exception: Exception) {
            throw IllegalStateException("Unable to save item stacks.", exception)
        }
    }

}