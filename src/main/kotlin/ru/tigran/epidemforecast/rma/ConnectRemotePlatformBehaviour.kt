package ru.tigran.epidemforecast.rma

import jade.core.AID
import jade.domain.FIPAAgentManagement.APDescription
import jade.domain.FIPAAgentManagement.APService
import jade.tools.rma.rma
import ru.tigran.epidemforecast.base.behaviour.LoopBehaviour

class ConnectRemotePlatformBehaviour(
    private val agent: rma,
    private val platformName: String = "Docker:8080/JADE",
    private val platformHosts: Array<String> = arrayOf(
        "http://host.docker.internal:7778/acc",
        "http://localhost:7778/acc",
    ),
) : LoopBehaviour(agent, 10000) {
    private var connected: Boolean = false

    override fun doAction() {
        if (!connected) connect()
        agent.refreshRemoteAgent(getPlatformDescription(), getPlatformAmsAID())
    }

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