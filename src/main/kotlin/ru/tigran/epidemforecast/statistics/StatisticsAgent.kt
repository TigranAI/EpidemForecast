package ru.tigran.epidemforecast.statistics

import jade.core.Agent
import jade.wrapper.ContainerController

class StatisticsAgent : Agent() {
    companion object {
        fun create(container: ContainerController) {
            container.createNewAgent("stats", StatisticsAgent::class.qualifiedName, emptyArray()).start()
        }
    }

    init {
        addBehaviour(CollectStatsBehaviour(this))
    }
}