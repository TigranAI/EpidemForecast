package ru.tigran.epidemforecast.rma

import jade.tools.rma.rma

class RMA : rma() {
    companion object {
        lateinit var INSTANCE: rma
    }

    init {
        INSTANCE = this
        addBehaviour(ConnectRemotePlatformBehaviour(this))
    }
}