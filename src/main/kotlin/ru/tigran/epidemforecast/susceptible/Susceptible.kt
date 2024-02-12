package ru.tigran.epidemforecast.susceptible

import jade.wrapper.AgentContainer
import ru.tigran.epidemforecast.base.behaviour.BaseAgent
import ru.tigran.epidemforecast.infected.Infected

class Susceptible : BaseAgent() {

    companion object {
        @get:Synchronized
        val INSTANCES: HashSet<Susceptible> = hashSetOf()

        fun create(name: String, container: AgentContainer) {
            container.createNewAgent(
                "[S] $name",
                Susceptible::class.qualifiedName,
                emptyArray(),
            ).start()
        }
    }

    init {
        INSTANCES += this
    }

    override fun onSetup() {
        addBehaviour(BreatheBehaviour(this))
    }

    fun getInfected() {
        Infected.create(name.substring(4), containerController)
        doDelete()
    }

}