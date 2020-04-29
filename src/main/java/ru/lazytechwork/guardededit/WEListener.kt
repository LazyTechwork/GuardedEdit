package ru.lazytechwork.guardededit

import com.sk89q.worldedit.event.extent.EditSessionEvent
import com.sk89q.worldedit.extension.platform.Actor
import com.sk89q.worldedit.util.eventbus.Subscribe

class WEListener {
    @Subscribe
    fun onEditSessionChange(ev: EditSessionEvent) {
        val actor: Actor? = ev.actor
        if (actor != null && actor.isPlayer) {
            ev.extent = WEGuarder(extent = ev.extent, actor = ev.actor!!, weWorld = ev.world!!)
        }
    }
}