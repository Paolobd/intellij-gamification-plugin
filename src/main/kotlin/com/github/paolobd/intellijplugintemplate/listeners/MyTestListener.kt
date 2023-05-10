package com.github.paolobd.intellijplugintemplate.listeners

import com.github.paolobd.intellijplugintemplate.views.MyNotifier
import com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener
import com.intellij.execution.testframework.sm.runner.SMTestProxy
import com.intellij.openapi.project.BaseProjectDirectories.Companion.getBaseDirectories
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager

internal class MyTestListener(private val project: Project) : SMTRunnerEventsListener {
    override fun onTestingStarted(testsRoot: SMTestProxy.SMRootTestProxy) {
        MyNotifier.notifyAchievement(null, "Testing started!")
    }

    override fun onTestingFinished(testsRoot: SMTestProxy.SMRootTestProxy) {
        val directory = project.getBaseDirectories()
        println("Directory: $directory")
    }

    override fun onTestsCountInSuite(count: Int) {
    }

    override fun onTestStarted(test: SMTestProxy) {
        //var driver:WebDriver = ChromeDriver()
        //ApplicationManager.getApplication().runReadAction {
        //    driver = WebDriverRunner.getWebDriver()
        //}
        //println("driver: $driver")
    }

    override fun onTestFinished(test: SMTestProxy) {

    }

    override fun onTestFailed(test: SMTestProxy) {
    }

    override fun onTestIgnored(test: SMTestProxy) {
    }

    override fun onSuiteFinished(suite: SMTestProxy) {
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