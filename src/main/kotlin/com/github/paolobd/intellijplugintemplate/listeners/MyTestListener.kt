package com.github.paolobd.intellijplugintemplate.listeners

import com.github.paolobd.intellijplugintemplate.objects.AchievementList
import com.github.paolobd.intellijplugintemplate.services.MyStatePersistence
import com.github.paolobd.intellijplugintemplate.views.MyNotifier
import com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener
import com.intellij.execution.testframework.sm.runner.SMTestProxy
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.readText
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import java.util.*

internal class MyTestListener(private val project: Project) : SMTRunnerEventsListener {
    override fun onTestingStarted(testsRoot: SMTestProxy.SMRootTestProxy) {
        MyNotifier.notifyAchievement(null, "Testing started!")
    }

    override fun onTestingFinished(testsRoot: SMTestProxy.SMRootTestProxy) {
        val text: String
        var file: VirtualFile? = null
        ApplicationManager.getApplication().runReadAction {
            file =
                FilenameIndex.getVirtualFilesByName("stats.txt", GlobalSearchScope.projectScope(project)).firstOrNull()
        }

        if (file == null) return

        file!!.refresh(false, false)
        text = file!!.readText()

        /*ApplicationManager.getApplication().runWriteAction{
            file!!.delete(null)
        }*/

        var countClicks = 0
        val scanner = Scanner(text)
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()

            if (line.contains("CLICK")) {
                countClicks++
            }
        }

        println("text stats: $text")
        println("NUM CLICKS: $countClicks")
        MyStatePersistence.getInstance(project).addExp(AchievementList.NUM_CLICKS, countClicks)
        //MyStatePersistence.getInstance(project).addExp(AchievementValues.NUM_CLICKS, countClicks)
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