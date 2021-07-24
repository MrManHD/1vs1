package net.mrmanhd.onevsone.minigame.extension

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.game.arena.Arena
import net.mrmanhd.onevsone.minigame.message.MessageType
import org.bukkit.entity.Player
import java.text.MessageFormat

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:42
 */

fun Player.sendConfigMessage(messageKey: String, vararg arguments: String) {
    val config = Minigame.instance.configLoader.loadConfig()
    val messageConfig = Minigame.instance.messageConfigLoader.loadConfig()
    val message = messageConfig.messages[messageKey]
        ?: throw NullPointerException("Cannot found messageKey: $messageKey")

    val messageFormat = MessageFormat.format(message.message, *arguments)

    when (message.type) {
        MessageType.CHAT -> this.sendMessage("${config.chatPrefix} " + messageFormat)

        MessageType.ACTIONBAR -> this.spigot().sendMessage(ChatMessageType.ACTION_BAR,
            TextComponent("§7${message.message}"))
    }
}

fun Player.getArena(): Arena? {
    return Minigame.instance.gameExecutor.getArenaByPlayer(this)
}