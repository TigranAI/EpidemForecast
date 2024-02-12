package ru.tigran.epidemforecast.infected

import jade.wrapper.AgentContainer
import ru.tigran.epidemforecast.base.behaviour.BaseAgent
import ru.tigran.epidemforecast.recovered.Recovered

class Infected : BaseAgent() {

    companion object {
        fun create(name: String, container: AgentContainer) {
            container.createNewAgent(
                "[I] $name",
                Infected::class.qualifiedName,
                emptyArray(),
            ).start()
        }
    }

    override fun onSetup() {
        addBehaviour(CoughBehaviour(this))
        addBehaviour(RecoveryBehaviour(this))
    }

    fun doRecovery() {
        Recovered.create(name.substring(4), containerController)
        doDelete()
    }

}