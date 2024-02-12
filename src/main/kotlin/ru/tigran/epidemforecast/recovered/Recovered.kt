package ru.tigran.epidemforecast.recovered

import jade.wrapper.AgentContainer
import ru.tigran.epidemforecast.base.behaviour.BaseAgent

class Recovered : BaseAgent() {

    companion object {
        fun create(name: String, container: AgentContainer) {
            container.createNewAgent(
                "[R] $name",
                Recovered::class.qualifiedName,
                emptyArray(),
            ).start()
        }
    }

}