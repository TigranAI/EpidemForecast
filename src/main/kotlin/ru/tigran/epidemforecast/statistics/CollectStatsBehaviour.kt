package ru.tigran.epidemforecast.statistics

import ru.tigran.epidemforecast.base.behaviour.BaseAgent
import ru.tigran.epidemforecast.base.behaviour.LoopBehaviour
import java.time.Clock
import java.util.logging.Level
import java.util.logging.Logger

class CollectStatsBehaviour(
    agent: StatisticsAgent,
) : LoopBehaviour(agent, 1000) {
    private val logger: Logger = Logger.getLogger(CollectStatsBehaviour::class.qualifiedName)

    override fun doAction() {
        val stats = BaseAgent.INSTANCES.values.groupingBy { it.javaClass.simpleName }.eachCount()
        logger.log(Level.INFO, "Agent stats at ${Clock.systemUTC().millis()} is $stats")
    }

}