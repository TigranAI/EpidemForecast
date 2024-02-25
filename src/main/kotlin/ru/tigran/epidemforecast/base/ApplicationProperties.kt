package ru.tigran.epidemforecast.base

import jade.core.Profile
import jade.core.ProfileImpl
import jade.core.Specifier
import jade.mtp.http.MessageTransportProtocol
import jade.tools.rma.rma
import jade.util.leap.ArrayList
import jade.util.leap.Properties
import ru.tigran.epidemforecast.rma.RMA
import ru.tigran.epidemforecast.util.getEnv

object ApplicationProperties : Properties() {
    private val JADE_RMA = rma::class.qualifiedName
    private val APPLICATION_RMA = RMA::class.qualifiedName

    @Synchronized
    override fun put(key: Any?, value: Any?): Any? {
        if (key == "agents" && value is ArrayList) {
            value.toList().forEach {
                if (it is Specifier && it.className == JADE_RMA) it.className = APPLICATION_RMA
            }
        }

        return super.put(key, value)
    }

    fun getProfile(args: Array<String>) = ProfileImpl(this).apply {
        val argsMap = args.map { it.split('=') }.associate { it[0] to it[1] }
        setParameter(Environment.ENV.name, System.getenv(Environment.ENV.name))
        setParameter(Profile.GUI, "${getEnv() == Environment.LOCAL}")
        setParameter(Profile.CONTAINER_NAME, argsMap.getOrDefault("-containerName", "Local"))
        setParameter(Profile.EXPORT_HOST, argsMap.getOrDefault("-containerName", "Local"))
        setParameter(Profile.EXPORT_PORT, argsMap.getOrDefault("-mainPort", "8081"))
        setParameter(Profile.MAIN_PORT, argsMap.getOrDefault("-mainPort", "8081"))

        val mtpHost = argsMap.getOrDefault("-mtpHost", "127.0.0.1")
        val dockerHost = argsMap.getOrDefault("-dockerHost", "host.docker.internal")
        val mtpPort = argsMap.getOrDefault("-mtpPort", "7779")
        setParameter(
            Profile.MTPS, buildList {
                add("${MessageTransportProtocol::class.qualifiedName}(http://$mtpHost:$mtpPort/acc)")
                if (getEnv() == Environment.LOCAL) {
                    add("${MessageTransportProtocol::class.qualifiedName}(http://$dockerHost:$mtpPort/acc)")
                }
            }.joinToString(";"))
    }

    private fun readResolve(): Any = ApplicationProperties
}