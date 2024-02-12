package ru.tigran.epidemforecast

import jade.core.Profile
import jade.core.ProfileImpl
import jade.core.Runtime
import ru.tigran.epidemforecast.infected.Infected
import ru.tigran.epidemforecast.susceptible.Susceptible
import java.util.*

class EpidemForecast

fun main(args: Array<String>) {
    val runtime = Runtime.instance()
    val profile = ProfileImpl().apply {
        setParameter(Profile.MAIN_HOST, "localhost")
        setParameter(Profile.GUI, "true")
    }
    val containerController = runtime.createMainContainer(profile)
    for (i in 1..1000) {
        Susceptible.create(UUID.randomUUID().toString(), containerController)
    }
    Infected.create(UUID.randomUUID().toString(), containerController)
}
