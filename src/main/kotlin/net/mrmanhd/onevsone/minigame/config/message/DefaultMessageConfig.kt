package net.mrmanhd.onevsone.minigame.config.message

import net.mrmanhd.onevsone.minigame.message.ConfigMessage
import net.mrmanhd.onevsone.minigame.message.MessageType

object DefaultMessageConfig {

    fun get(): MessageConfig {
        val messages = hashMapOf<String, ConfigMessage>()

        messages["setup.first.failed.spawn"] = ConfigMessage("§cDu hast noch keinen Spawn eingerichtet! Nutze dafür den Command /setup spawn", MessageType.CHAT)
        messages["setup.first.failed.map"] = ConfigMessage("§cDu hast noch keine Map eingerichtet! Nutze dafür den Command /mapsetup", MessageType.CHAT)
        messages["setup.first.failed.kit"] = ConfigMessage("§cDu hast noch kein Kit eingerichtet! Nutze dafür den Command /kitsetup", MessageType.CHAT)

        messages["setup.command.usage.help"] = ConfigMessage("Informationen über das §6§lSetup§8:", MessageType.CHAT)
        messages["setup.command.usage.help.prefix"] = ConfigMessage("§6§l1.§7§o /setup prefix <Prefix>", MessageType.CHAT)
        messages["setup.command.usage.help.spawn"] = ConfigMessage("§6§l2.§7§o /setup spawn", MessageType.CHAT)
        messages["setup.command.usage.help.defaultgamemode"] = ConfigMessage("§6§l1.§7§o /setup defaultGamemode <Gamemode>", MessageType.CHAT)

        messages["setup.command.prefix.update"] = ConfigMessage("Der §6§lPrefix§7 wurde erfolgreich aktualisiert§8!", MessageType.CHAT)
        messages["setup.command.defaultgamemode.update"] = ConfigMessage("Der §6§lGamemode§7 wurde erfolgreich aktualisiert§8!", MessageType.CHAT)


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


        messages["setup.kit.command.usage.help"] = ConfigMessage("Informationen über das §6§lKit-Setup§8:", MessageType.CHAT)
        messages["setup.kit.command.usage.help.create"] = ConfigMessage("§6§l1.§7§o /kitsetup create <KitName> <Material>", MessageType.CHAT)
        messages["setup.kit.command.usage.help.finish"] = ConfigMessage("§6§l2.§7§o /kitsetup finish", MessageType.CHAT)

        messages["setup.kit.command.create.failed"] = ConfigMessage("§cDieser Name wird bereits genutzt!", MessageType.CHAT)
        messages["setup.kit.command.created"] = ConfigMessage("Das Kit §6§l{0}§7 wird nun eingerichtet§8!§7 Dein Inventar wird mit §6§l/kitsetup finish§7 abgespeichert§8.", MessageType.CHAT)
        messages["setup.kit.command.finished"] = ConfigMessage("Das Kit §6§l{0}§7 wurde erfolgreich erstellt§8!", MessageType.CHAT)


        messages["challenge.command.usage.help"] = ConfigMessage("Informationen über §6§lChallenge§8:", MessageType.CHAT)
        messages["challenge.command.usage.help.challenge"] = ConfigMessage("§7§o /challenge <Spielername> <Map> <Kit> <Runden>", MessageType.CHAT)
        messages["challenge.command.usage.help.accept"] = ConfigMessage("§7§o /challenge accept <Spielername>", MessageType.CHAT)
        messages["challenge.command.usage.help.deny"] = ConfigMessage("§7§o /challenge deny <Spielername>", MessageType.CHAT)

        messages["challenge.command.challenge.start"] = ConfigMessage("Du hast §6§l{0}§7 Herausgefordert§8.", MessageType.CHAT)
        messages["challenge.command.challenge.start.target"] = ConfigMessage("Du hast von §6§l{0}§7 eine Herausgeforderung bekommen§8.", MessageType.CHAT)
        messages["challenge.command.challenge.start.target.info"] = ConfigMessage("Nutze §6§l/challenge accept {0}§8,§7 um die Herausforderung zu akzeptieren§8!", MessageType.CHAT)

        messages["challenge.command.challenge.failed"] = ConfigMessage("§cDu kannst diesen Spieler nicht Herausfordern!", MessageType.CHAT)
        messages["challenge.command.challenge.failed.player.inround"] = ConfigMessage("§cDieser Spieler ist bereits in einer Runde!", MessageType.CHAT)
        messages["challenge.command.challenge.failed.alreadysend"] = ConfigMessage("§cDu hat diesen Spieler bereits eine Herausforderung gesendet!", MessageType.CHAT)
        messages["challenge.command.challenge.failed.wrong.map"] = ConfigMessage("§cDiese Map existiert nicht!", MessageType.CHAT)
        messages["challenge.command.challenge.failed.wrong.kit"] = ConfigMessage("§cDieses Kit existiert nicht!", MessageType.CHAT)
        messages["challenge.command.challenge.failed.wrong.round"] = ConfigMessage("§cDiese Rundenanzahl existiert nicht!", MessageType.CHAT)

        messages["challenge.command.accept.challenger"] = ConfigMessage("Der Spieler §6§l{0}§7 hat deine Herausforderung akzeptiert§8!", MessageType.CHAT)
        messages["challenge.command.accept.target"] = ConfigMessage("Du hast die Herausforderung von §6§l{0}§7 akzeptiert§8!", MessageType.CHAT)
        messages["challenge.command.accept.failed.already.map"] = ConfigMessage("§cDie Herausforderung wurde abgebrochen, da die Map {0} bereits genutzt wird!", MessageType.CHAT)

        messages["challenge.command.request.failed"] = ConfigMessage("§cDieser Spieler hat dir keine Herausforderung gesendet!", MessageType.CHAT)


        messages["death.entity.message"] = ConfigMessage("Du wurdest von §6§l{0}§7 getötet§8.", MessageType.CHAT)
        messages["death.killer.message"] = ConfigMessage("Du hast den Spieler §6§l{0}§7 getötet§8.", MessageType.CHAT)

        messages["winner.player.message"] = ConfigMessage("Der Spieler §6§l{0}§7 hat 1vs1 gewonnen§8.", MessageType.CHAT)
        messages["next.round.message"] = ConfigMessage("Die Runde §6§l{0}§7 beginnt jetzt§8.", MessageType.CHAT)

        return MessageConfig(messages)
    }

}