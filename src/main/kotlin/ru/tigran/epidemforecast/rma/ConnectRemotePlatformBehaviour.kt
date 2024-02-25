package ru.tigran.epidemforecast.rma

import jade.core.AID
import jade.core.behaviours.Behaviour
import jade.domain.FIPAAgentManagement.APDescription
import jade.domain.FIPAAgentManagement.APService

class ConnectRemotePlatformBehaviour(
    private val agent: RMA,
    private val platformName: String = "Docker:8080/JADE",
    private val platformHosts: Array<String> = arrayOf(
        "http://host.docker.internal:7778/acc",
        "http://localhost:7778/acc",
    ),
) : Behaviour(agent) {
    private var connected: Boolean = false

    override fun action() {
        if (!connected) {
            connect()
            agent.refreshRemoteAgent(getPlatformDescription(), getPlatformAmsAID())
        }
    }

    override fun done() = connected

    private fun connect() {
        agent.addRemotePlatform(getPlatformAmsAID())
        connected = true
    }

    private fun getPlatformAmsAID() = AID("ams@$platformName", true).apply {
        for (host in platformHosts) addAddresses(host)
    }

    private fun getPlatformDescription() = APDescription().apply {
        name = "\"$platformName\""
        addAPServices(APService("fipa.mts.mtp.http.std", platformHosts))
    }
}