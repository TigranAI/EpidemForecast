package ru.tigran.epidemforecast.base

import jade.core.Specifier
import jade.tools.rma.rma
import jade.util.leap.ArrayList
import jade.util.leap.Properties
import ru.tigran.epidemforecast.rma.RMA

class ApplicationProperties : Properties() {

    companion object {
        val JADE_RMA = rma::class.qualifiedName
        val APPLICATION_RMA = RMA::class.qualifiedName
    }

    @Synchronized
    override fun put(key: Any?, value: Any?): Any? {
        if (key == "agents" && value is ArrayList) {
            value.toList().forEach {
                if (it is Specifier && it.className == JADE_RMA) it.className = APPLICATION_RMA
            }
        }

        return super.put(key, value)
    }
}