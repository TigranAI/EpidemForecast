package ru.tigran.epidemforecast.base

import jade.core.Profile
import jade.core.Runtime
import jade.wrapper.AgentContainer
import ru.tigran.epidemforecast.rma.RmaRefreshAgent
import ru.tigran.epidemforecast.susceptible.Susceptible
import ru.tigran.epidemforecast.util.getEnv
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

object ApplicationRuntime {
    private lateinit var mainContainer: AgentContainer
    private val logger: Logger = Logger.getLogger(ApplicationRuntime::class.qualifiedName)

    fun startUp(profile: Profile) {
        logger.log(Level.INFO, "Starting with profile environment ${profile.getEnv()}")

        val rt = Runtime.instance()
        mainContainer = rt.createMainContainer(profile)

        //StatisticsAgent.create(mainContainer)
        if (profile.getEnv() == Environment.LOCAL) RmaRefreshAgent.create(mainContainer)
        if (profile.getEnv() == Environment.DOCKER) initializeAgents()

        mainContainer.start()
    }

    private fun initializeAgents() {
        logger.log(Level.INFO, "Initialize Susceptible agents")
        for (i in 1..1000) {
            Susceptible.create(UUID.randomUUID().toString(), mainContainer)
        }
    }
}