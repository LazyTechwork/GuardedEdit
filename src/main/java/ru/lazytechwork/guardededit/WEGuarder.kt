package ru.lazytechwork.guardededit

import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.extension.platform.Actor
import com.sk89q.worldedit.extent.AbstractDelegateExtent
import com.sk89q.worldedit.extent.Extent
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldedit.world.World
import com.sk89q.worldedit.world.block.BlockStateHolder
import com.sk89q.worldguard.LocalPlayer
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class WEGuarder(private val weWorld: World, extent: Extent, private val actor: Actor) : AbstractDelegateExtent(extent) {
    private var world: org.bukkit.World
    private var player: Player
    private var wgPlayer: LocalPlayer

    init {
        if (weWorld is BukkitWorld)
            this.world = weWorld.world
        else
            this.world = Bukkit.getWorld(weWorld.name)!!

        this.player = Bukkit.getPlayerExact(this.actor.name)!!
        this.wgPlayer = WorldGuardPlugin.inst().wrapPlayer(this.player)
    }

    override fun <T : BlockStateHolder<T>?> setBlock(location: BlockVector3?, block: T): Boolean {
        GuardedEdit.instance!!.logger.info("${location?.x} ${location?.y} ${location?.z}")
//        sendTitle("debug", )
        val regions = WorldGuard.getInstance().platform.regionContainer.get(weWorld)?.getApplicableRegions(location)
        return if (player.hasPermission("guardededit.guardpass") || regions!!.isMemberOfAll(wgPlayer))
            super.setBlock(location, block)
        else
            false
    }
}