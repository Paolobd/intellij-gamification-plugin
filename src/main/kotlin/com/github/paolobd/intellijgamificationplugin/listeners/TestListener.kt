package com.github.paolobd.intellijgamificationplugin.listeners

import com.github.paolobd.intellijgamificationplugin.library.Event
import com.github.paolobd.intellijgamificationplugin.library.EventType
import com.github.paolobd.intellijgamificationplugin.library.Server
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.services.ProjectStatePersistence
import com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener
import com.intellij.execution.testframework.sm.runner.SMTestProxy
import com.intellij.openapi.project.Project

internal class TestListener(private val project: Project) : SMTRunnerEventsListener {
    private lateinit var server: Server
    override fun onTestingStarted(testsRoot: SMTestProxy.SMRootTestProxy) {
        server = Server()
        server.start()
    }

    override fun onTestingFinished(testsRoot: SMTestProxy.SMRootTestProxy) {
        server.stop()
    }

    override fun onTestsCountInSuite(count: Int) {
    }

    override fun onTestStarted(test: SMTestProxy) {
    }

    override fun onTestFinished(test: SMTestProxy) {
        val persistence = ProjectStatePersistence.getInstance(project)

        val expUpdate = MutableList(ProjectAchievement.values().size) { 0 }

        val eventList = Server.events

        val newEvents = mutableListOf<Event>()

        println("EventList: $eventList")


        for (event in eventList) {
            val found = persistence.state.eventList.contains(event)

            if (!found) {
                newEvents.add(event)
                persistence.state.eventList.add(event)

                when (event.eventType) {
                    EventType.CLICK -> expUpdate[ProjectAchievement.NUM_CLICKS.ordinal]++
                    EventType.LOCATOR -> expUpdate[ProjectAchievement.NUM_LOCATOR_ALL.ordinal]++
                    EventType.NAVIGATION -> expUpdate[ProjectAchievement.NUM_SITES.ordinal]++
                    EventType.LOCATOR_ID -> {
                        expUpdate[ProjectAchievement.NUM_LOCATOR_ID.ordinal]++
                        expUpdate[ProjectAchievement.NUM_LOCATOR_ALL.ordinal]++
                    }

                    EventType.LOCATOR_CSS -> {
                        expUpdate[ProjectAchievement.NUM_LOCATOR_CSS.ordinal]++
                        expUpdate[ProjectAchievement.NUM_LOCATOR_ALL.ordinal]++
                    }

                    EventType.LOCATOR_XPATH -> {
                        expUpdate[ProjectAchievement.NUM_LOCATOR_XPATH.ordinal]++
                        expUpdate[ProjectAchievement.NUM_LOCATOR_ALL.ordinal]++
                    }
                }

            }
        }

        for ((index, exp) in expUpdate.withIndex()) {
            if (exp != 0) persistence.addExp(ProjectAchievement.values()[index], exp)
        }
    }

    override fun onTestFailed(test: SMTestProxy) {
        println("Test failed ${test.name}")
    }

    override fun onTestIgnored(test: SMTestProxy) {
    }

    override fun onSuiteFinished(suite: SMTestProxy) {
        println("Suite of ${suite.children.size} finished")
    }

    override fun onSuiteStarted(suite: SMTestProxy) {
    }

    override fun onCustomProgressTestsCategory(categoryName: String?, testCount: Int) {
    }

    override fun onCustomProgressTestStarted() {
    }

    override fun onCustomProgressTestFailed() {
    }

    override fun onCustomProgressTestFinished() {
    }

    override fun onSuiteTreeNodeAdded(testProxy: SMTestProxy?) {
    }

    override fun onSuiteTreeStarted(suite: SMTestProxy?) {
    }


}