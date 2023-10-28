package com.github.paolobd.intellijgamificationplugin.listeners

import com.github.paolobd.intellijgamificationplugin.communication.Server
import com.github.paolobd.intellijgamificationplugin.services.AchievementService
import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener
import com.intellij.execution.testframework.sm.runner.SMTestProxy
import com.intellij.openapi.project.Project

class TestListener(private val project: Project) : SMTRunnerEventsListener {
    private lateinit var server: Server
    override fun onTestingStarted(testsRoot: SMTestProxy.SMRootTestProxy) {
        server = Server()
        server.start()
        ApplicationStatePersistence.getInstance().checkAndUpdateDailyGUI()
    }

    override fun onTestingFinished(testsRoot: SMTestProxy.SMRootTestProxy) {
        server.stop()
    }

    override fun onTestsCountInSuite(count: Int) {
    }

    override fun onTestStarted(test: SMTestProxy) {
    }

    override fun onTestFinished(test: SMTestProxy) {
        val eventList = Server.events
        val service = project.getService(AchievementService::class.java)

        service.analyzeEvents(eventList)
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