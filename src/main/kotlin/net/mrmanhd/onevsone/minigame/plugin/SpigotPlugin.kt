package net.mrmanhd.onevsone.minigame.plugin

import net.mrmanhd.onevsone.minigame.Minigame
import net.mrmanhd.onevsone.minigame.plugin.command.ChallengeCommand
import net.mrmanhd.onevsone.minigame.plugin.command.KitSetupCommand
import net.mrmanhd.onevsone.minigame.plugin.command.MapSetupCommand
import net.mrmanhd.onevsone.minigame.plugin.command.SetupCommand
import net.mrmanhd.onevsone.minigame.plugin.listener.PlayerDeathListener
import net.mrmanhd.onevsone.minigame.plugin.listener.PlayerJoinListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:08
 */

class SpigotPlugin : JavaPlugin() {

    override fun onEnable() {
        val minigame = Minigame(this)

        this.registerEvents()

        object : BukkitRunnable() {
            override fun run() {
                minigame.gameExecutor.getSpawnPlayers().forEach {
                    minigame.scoreboardHandler.updateSpawnScoreboard(it)
                }
            }
        }.runTaskTimer(this, 100, 40)
    }

    override fun onDisable() {

    }

    private fun registerEvents() {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(PlayerJoinListener(), this)
        pluginManager.registerEvents(PlayerDeathListener(), this)

        getCommand("mapsetup")!!.setExecutor(MapSetupCommand())
        getCommand("kitsetup")!!.setExecutor(KitSetupCommand())
        getCommand("setup")!!.setExecutor(SetupCommand())
        getCommand("challenge")!!.setExecutor(ChallengeCommand())
    }

}