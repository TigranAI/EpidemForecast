package ru.tigran.epidemforecast.base.behaviour

import jade.core.Agent
import java.util.concurrent.ConcurrentHashMap

open class BaseAgent : Agent() {

    companion object {
        val INSTANCES: ConcurrentHashMap<String, BaseAgent> = ConcurrentHashMap()
    }

    open fun onSetup() {

    }

    override fun setup() {
        onSetup()
        INSTANCES[name] = this
    }

    @Synchronized override fun doDelete() {
        INSTANCES.remove(name)
        super.takeDown()
        super.doDelete()
    }

}