package ru.lazytechwork.guardededit

import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.entity.Player
import com.sk89q.worldedit.extent.AbstractDelegateExtent
import com.sk89q.worldedit.extent.Extent
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldedit.world.World
import com.sk89q.worldedit.world.block.BlockStateHolder
import com.sk89q.worldguard.LocalPlayer
import com.sk89q.worldguard.WorldGuard
import org.bukkit.Bukkit

class WEGuarder(private val weWorld: World, extent: Extent, private val player: Player) : AbstractDelegateExtent(extent) {
    private var world: org.bukkit.World

    init {
        if (weWorld is BukkitWorld)
            this.world = weWorld.world
        else
            this.world = Bukkit.getWorld(weWorld.name)!!


    }

    override fun <T : BlockStateHolder<T>?> setBlock(location: BlockVector3?, block: T): Boolean {
        (this.player as LocalPlayer).sendTitle("dubug", "${location?.x} ${location?.y} ${location?.z}")
        val regions = WorldGuard.getInstance().platform.regionContainer.get(weWorld)?.getApplicableRegions(location)
        return if (player.hasPermission("guardededit.guardpass") || regions!!.isMemberOfAll(this.player as LocalPlayer))
            super.setBlock(location, block)
        else
            false
    }
}