package ru.tigran.epidemforecast.base.behaviour

import jade.core.Agent
import jade.core.behaviours.SimpleBehaviour

abstract class LoopBehaviour(
    agent: Agent,
    private val delayMs: Long,
    private var skipStart: Boolean = true
) : SimpleBehaviour(agent) {

    abstract fun doAction()

    override fun done() = false

    override fun action() {
        block(delayMs)
        if (skipStart) {
            skipStart = false
        } else {
            doAction()
        }
    }
}