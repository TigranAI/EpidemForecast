package ru.tigran.epidemforecast.infected

import jade.lang.acl.ACLMessage
import ru.tigran.epidemforecast.base.behaviour.BaseAgent
import ru.tigran.epidemforecast.base.behaviour.LoopBehaviour
import ru.tigran.epidemforecast.susceptible.Susceptible

class CoughBehaviour(
    private val agent: Infected,
) : LoopBehaviour(agent, 2000) {

    @Synchronized override fun doAction() {
        BaseAgent.INSTANCES.values.randomOrNull()?.takeIf { it is Susceptible }?.apply {
            val message = ACLMessage(ACLMessage.INFORM)
            message.addReceiver(this.aid)
            agent.send(message)
        }
    }

    override fun done() = false

}