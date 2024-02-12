package ru.tigran.epidemforecast.infected

import ru.tigran.epidemforecast.base.behaviour.LoopBehaviour

class RecoveryBehaviour(
    private val agent: Infected,
) : LoopBehaviour(agent, 20000) {

    override fun doAction() {
        agent.doRecovery()
    }

}