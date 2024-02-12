package ru.tigran.epidemforecast.susceptible

import ru.tigran.epidemforecast.base.behaviour.LoopBehaviour

class BreatheBehaviour(
    private val agent: Susceptible,
) : LoopBehaviour(agent, 100) {

    override fun doAction() {
        agent.receive()?.apply {
            agent.getInfected()
        }
    }

}