package ru.lazytechwork.guardededit

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class GuardedEdit : JavaPlugin() {

    companion object {
        var instance: GuardedEdit? = null
        private set;
    }

    override fun onEnable() {
        Bukkit.getLogger().info("Guarded Edit successfully loaded")
        instance = this
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}