package ru.tigran.epidemforecast

import ru.tigran.epidemforecast.base.ApplicationProperties
import ru.tigran.epidemforecast.base.ApplicationRuntime

class EpidemForecast

fun main(args: Array<String>) {
    ApplicationRuntime.startUp(ApplicationProperties.getProfile(args))
}
