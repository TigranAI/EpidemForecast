package ru.tigran.epidemforecast.susceptible

import ru.tigran.epidemforecast.base.behaviour.LoopBehaviour
import java.util.logging.Logger

class BreatheBehaviour(
    private val agent: Susceptible,
) : LoopBehaviour(agent, 100) {
    private val logger: Logger = Logger.getLogger(BreatheBehaviour::class.qualifiedName)

    override fun doAction() {
        agent.receive()?.apply {
            agent.getInfected()
        }
    }

}