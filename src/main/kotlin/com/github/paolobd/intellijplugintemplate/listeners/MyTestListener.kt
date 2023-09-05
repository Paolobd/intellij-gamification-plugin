package com.github.paolobd.intellijplugintemplate.listeners

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.paolobd.intellijplugintemplate.library.Event
import com.github.paolobd.intellijplugintemplate.library.EventType
import com.github.paolobd.intellijplugintemplate.objects.ProjectAchievementList
import com.github.paolobd.intellijplugintemplate.services.ProjectStatePersistence
import com.github.paolobd.intellijplugintemplate.views.MyNotifier
import com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener
import com.intellij.execution.testframework.sm.runner.SMTestProxy
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.readText
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope

internal class MyTestListener(private val project: Project) : SMTRunnerEventsListener {
    override fun onTestingStarted(testsRoot: SMTestProxy.SMRootTestProxy) {
        MyNotifier.notifyAchievement(null, "Testing started!")

        //Aprire server
    }

    override fun onTestingFinished(testsRoot: SMTestProxy.SMRootTestProxy) {
        //Chiudere server
    }

    override fun onTestsCountInSuite(count: Int) {
    }

    override fun onTestStarted(test: SMTestProxy) {
        /*var driver: WebDriver? = null
        ApplicationManager.getApplication().runReadAction {
            driver = WebDriverRunner.getAndCheckWebDriver()
        }
        println("driver: $driver , current url${driver?.currentUrl}")*/
    }

    override fun onTestFinished(test: SMTestProxy) {
        val text: String
        var file: VirtualFile? = null
        ApplicationManager.getApplication().runReadAction {
            file =
                FilenameIndex.getVirtualFilesByName("stats.json", GlobalSearchScope.projectScope(project)).firstOrNull()
        }

        if (file == null) return

        file!!.refresh(false, false)
        text = file!!.readText()

        WriteCommandAction.runWriteCommandAction(project){
            file!!.delete(null)
        }

        val persistence = ProjectStatePersistence.getInstance(project)

        val expUpdate = MutableList(ProjectAchievementList.values().size){0}

        val objectMapper = ObjectMapper()

        val eventList = objectMapper.readValue(text, object: TypeReference<List<Event>>() {})

        val newEvents = mutableListOf<Event>()

        println("EventList: $eventList")

        for (event in eventList){
            val found = persistence.state.eventList.contains(event)

            if(!found){
                newEvents.add(event)
                persistence.state.eventList.add(event)

                when(event.eventType){
                    EventType.CLICK -> expUpdate[ProjectAchievementList.NUM_CLICKS.ordinal] ++
                    EventType.LOCATOR -> expUpdate[ProjectAchievementList.NUM_LOCATOR_ALL.ordinal]++
                    EventType.NAVIGATION -> expUpdate[ProjectAchievementList.NUM_SITES.ordinal]++
                    EventType.LOCATOR_ID -> {
                        expUpdate[ProjectAchievementList.NUM_LOCATOR_ID.ordinal] ++
                        expUpdate[ProjectAchievementList.NUM_LOCATOR_ALL.ordinal] ++
                    }
                    EventType.LOCATOR_CSS -> {
                        expUpdate[ProjectAchievementList.NUM_LOCATOR_CSS.ordinal] ++
                        expUpdate[ProjectAchievementList.NUM_LOCATOR_ALL.ordinal] ++
                    }
                    EventType.LOCATOR_XPATH -> {
                        expUpdate[ProjectAchievementList.NUM_LOCATOR_XPATH.ordinal] ++
                        expUpdate[ProjectAchievementList.NUM_LOCATOR_ALL.ordinal] ++
                    }
                }

            }
        }

        /*for (event in eventList){
            when(event.eventType){
                EventType.CLICK -> expUpdate[AchievementList.NUM_CLICKS.ordinal] ++
                EventType.LOCATOR_ID -> {
                    expUpdate[AchievementList.NUM_LOCATOR_ID.ordinal] ++
                    expUpdate[AchievementList.NUM_LOCATOR_ALL.ordinal] ++
                }
                EventType.LOCATOR_CSS -> {
                    expUpdate[AchievementList.NUM_LOCATOR_CSS.ordinal] ++
                    expUpdate[AchievementList.NUM_LOCATOR_ALL.ordinal] ++
                }
                EventType.LOCATOR_XPATH -> {
                    expUpdate[AchievementList.NUM_LOCATOR_XPATH.ordinal] ++
                    expUpdate[AchievementList.NUM_LOCATOR_ALL.ordinal] ++
                }
                else -> {}
            }
        }*/

        for ((index, exp) in expUpdate.withIndex()){
            if (exp != 0) persistence.addExp(ProjectAchievementList.values()[index], exp)
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