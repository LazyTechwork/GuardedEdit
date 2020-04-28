package ru.lazytechwork.guardededit

import com.sk89q.worldedit.EditSession
import com.sk89q.worldedit.entity.Player
import com.sk89q.worldedit.event.extent.EditSessionEvent
import com.sk89q.worldedit.util.eventbus.Subscribe

class WEListener {
    @Subscribe
    fun onEditSessionChange(ev: EditSessionEvent) {
        if (ev.stage == EditSession.Stage.BEFORE_CHANGE)
            ev.extent = WEGuarder(extent = ev.extent, player = ev.actor as Player, weWorld = ev.world!!)
    }
}