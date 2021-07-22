package net.mrmanhd.onevsone.minigame

import net.mrmanhd.onevsone.minigame.config.default.ConfigLoader
import net.mrmanhd.onevsone.minigame.config.message.MessageConfigLoader
import net.mrmanhd.onevsone.minigame.game.GameExecutor
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:08
 */

class Minigame(
    val javaPlugin: JavaPlugin
) {

    val gameExecutor = GameExecutor()
    val configLoader = ConfigLoader()
    val messageConfigLoader = MessageConfigLoader()

    init {
        instance = this
        this.configLoader.loadConfig()
        this.messageConfigLoader.loadConfig()
    }

    companion object {
        lateinit var instance: Minigame
    }

}