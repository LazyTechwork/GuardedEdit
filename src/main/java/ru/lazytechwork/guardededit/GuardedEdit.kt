package ru.lazytechwork.guardededit

import com.sk89q.worldedit.WorldEdit
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.primesoft.asyncworldedit.AsyncWorldEditBukkit
import org.primesoft.asyncworldedit.AsyncWorldEditMain

class GuardedEdit : JavaPlugin() {

    companion object {
        var instance: GuardedEdit? = null
            private set;
    }

    override fun onEnable() {
        Bukkit.getLogger().info("Guarded Edit successfully loaded")
        instance = this
        WorldEdit.getInstance().eventBus.register(WEListener())
        if (this.server.pluginManager.getPlugin("AsyncWorldEdit") !== null)
            AsyncWorldEditMain.getInstance().worldEditIntegrator.we.eventBus.register(WEListener())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}