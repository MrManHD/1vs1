package net.mrmanhd.onevsone.minigame.plugin

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.plugin.command.MapSetupCommand
import net.mrmanhd.onevsone.minigame.plugin.command.SetupCommand
import net.mrmanhd.onevsone.minigame.plugin.listener.PlayerJoinListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:08
 */

class SpigotPlugin : JavaPlugin() {

    override fun onEnable() {
        Minigame(this)

        this.registerEvents()
    }

    override fun onDisable() {

    }

    private fun registerEvents() {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(PlayerJoinListener(), this)

        getCommand("mapsetup")!!.setExecutor(MapSetupCommand())
        getCommand("setup")!!.setExecutor(SetupCommand())
    }

}