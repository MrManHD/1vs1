package net.mrmanhd.onevsone.minigame.game.kit

import net.mrmanhd.onevsone.minigame.utils.Nameable
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.IOException

/**
 * Created by MrManHD
 * Class create at 23.07.2021 14:04
 */

class GameKit(
    val kitName: String,
    val material: String,
    private val items: Map<Int, String>
) : Nameable {

    fun equipPlayer(player: Player) {
        player.inventory.clear()
        this.getItems().filter { it.value.type != Material.AIR }
            .forEach { (slot, itemStack) -> player.inventory.setItem(slot, itemStack) }
    }

    private fun getItems(): Map<Int, ItemStack> {
        return this.items.mapValues { this.itemStackFromBase64(it.value) }
    }

    private fun itemStackFromBase64(data: String): ItemStack {
        try {
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
            val dataInput = BukkitObjectInputStream(inputStream)

            return dataInput.readObject() as ItemStack
            dataInput.close()
        } catch (exception: ClassNotFoundException) {
            throw IOException("Unable to decode class type.", exception)
        }
    }

    override fun getName(): String = this.kitName

}