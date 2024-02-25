package ru.tigran.epidemforecast.util

import jade.core.Profile
import ru.tigran.epidemforecast.base.Environment

fun Profile.getEnv(): Environment {
    val environment = this.getParameter(Environment.ENV.name, null)
    return Environment.entries.firstOrNull { it.name == environment } ?: Environment.LOCAL
}