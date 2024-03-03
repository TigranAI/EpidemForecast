package ru.tigran.epidemforecast.rma

import jade.core.Agent
import jade.wrapper.ContainerController

class RmaRefreshAgent : Agent() {
    companion object {
        fun create(container: ContainerController) {
            container.createNewAgent("rra", RmaRefreshAgent::class.qualifiedName, emptyArray()).start()
        }
    }

    init {
        addBehaviour(ConnectRemotePlatformBehaviour(RMA.INSTANCE))
    }

}