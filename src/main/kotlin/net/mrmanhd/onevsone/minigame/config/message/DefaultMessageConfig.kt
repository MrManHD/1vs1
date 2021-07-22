package net.mrmanhd.onevsone.minigame.config.message

import net.mrmanhd.onevsone.minigame.message.ConfigMessage
import net.mrmanhd.onevsone.minigame.message.MessageType

object DefaultMessageConfig {

    fun get(): MessageConfig {
        val messages = hashMapOf<String, ConfigMessage>()

        messages["setup.first.failed.spawn"] = ConfigMessage("§cDu hast noch keinen Spawn eingerichtet! Nutze dafür den Command /setup spawn", MessageType.CHAT)
        messages["setup.first.failed.map"] = ConfigMessage("§cDu hast noch keine Map eingerichtet! Nutze dafür den Command /mapsetup", MessageType.CHAT)

        messages["setup.command.usage.help"] = ConfigMessage("Informationen über das §6§lSetup§8:", MessageType.CHAT)
        messages["setup.command.usage.help.prefix"] = ConfigMessage("§6§l1.§7§o /setup prefix <Prefix>", MessageType.CHAT)
        messages["setup.command.usage.help.spawn"] = ConfigMessage("§6§l2.§7§o /setup spawn", MessageType.CHAT)

        messages["setup.command.prefix.update"] = ConfigMessage("Der Prefix wurde erfolgreich aktualisiert§8!", MessageType.CHAT)

        messages["setup.map.command.usage.help"] = ConfigMessage("Informationen über das §6§lMap-Setup§8:", MessageType.CHAT)
        messages["setup.map.command.usage.help.create"] = ConfigMessage("§6§l1.§7§o /mapsetup create <MapName>", MessageType.CHAT)
        messages["setup.map.command.usage.help.locationone"] = ConfigMessage("§6§l2.§7§o /mapsetup locationOne", MessageType.CHAT)
        messages["setup.map.command.usage.help.locationtwo"] = ConfigMessage("§6§l3.§7§o /mapsetup locationTwo", MessageType.CHAT)
        messages["setup.map.command.usage.help.spectatorlocation"] = ConfigMessage("§6§l4.§7§o /mapsetup spectatorLocation", MessageType.CHAT)
        messages["setup.map.command.usage.help.finish"] = ConfigMessage("§6§l5.§7§o /mapsetup finish", MessageType.CHAT)

        messages["setup.map.command.create.failed"] = ConfigMessage("§cDieser Name wird bereits genutzt!", MessageType.CHAT)
        messages["setup.map.command.created"] = ConfigMessage("Die Map §6§l{0}§7 wird nun eingerichtet§8!", MessageType.CHAT)

        messages["setup.map.command.finish.failed"] = ConfigMessage("§cDie Map ist noch nicht fertig eingerichtet!", MessageType.CHAT)
        messages["setup.map.command.finished"] = ConfigMessage("Die Map §6§l{0}§7 wurde erfolgreich eingerichtet§8!", MessageType.CHAT)

        messages["setup.map.command.save.location"] = ConfigMessage("Die Location §6§l{0}§7 wurde gesetzt§8!", MessageType.CHAT)

        return MessageConfig(messages)
    }

}