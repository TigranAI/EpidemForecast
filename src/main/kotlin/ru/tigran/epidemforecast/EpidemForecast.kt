package ru.tigran.epidemforecast

import jade.core.Profile
import jade.core.ProfileImpl
import jade.core.Runtime
import jade.mtp.http.MessageTransportProtocol
import ru.tigran.epidemforecast.base.ApplicationProperties

class EpidemForecast

fun main(args: Array<String>) {
    val rt = Runtime.instance()
    val profile = getProfile(args)
    rt.startUp(profile)
    Thread.sleep(2000)

    //val containerController = runtime.createMainContainer(profile)
    /*for (i in 1..1000) {
        Susceptible.create(UUID.randomUUID().toString(), containerController)
    }
    Infected.create(UUID.randomUUID().toString(), containerController)*/
}

fun getProfile(args: Array<String>) = ProfileImpl(ApplicationProperties()).apply {
    println("Args is ${args.joinToString()}")
    val argsMap = args.map { it.split('=') }.associate { it[0] to it[1] }
    setParameter(Profile.GUI, (System.getenv("ENV") != "DOCKER").toString())
    setParameter(Profile.CONTAINER_NAME, argsMap.getOrDefault("-containerName", "Local"))
    setParameter(Profile.EXPORT_HOST, argsMap.getOrDefault("-containerName", "Local"))
    setParameter(Profile.EXPORT_PORT, argsMap.getOrDefault("-mainPort", "8081"))
    setParameter(Profile.MAIN_PORT, argsMap.getOrDefault("-mainPort", "8081"))

    val mtpHost = argsMap.getOrDefault("-mtpHost", "127.0.0.1")
    val dockerHost = argsMap.getOrDefault("-dockerHost", "host.docker.internal")
    val mtpPort = argsMap.getOrDefault("-mtpPort", "7779")
    setParameter(Profile.MTPS, arrayListOf(
        "${MessageTransportProtocol::class.qualifiedName}(http://$mtpHost:$mtpPort/acc)",
        "${MessageTransportProtocol::class.qualifiedName}(http://$dockerHost:$mtpPort/acc)",
    ).joinToString(";"))
}
