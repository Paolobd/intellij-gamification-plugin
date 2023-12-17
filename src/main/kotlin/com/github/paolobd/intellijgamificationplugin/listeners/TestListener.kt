package com.github.paolobd.intellijgamificationplugin.listeners

import com.github.paolobd.intellijgamificationplugin.communication.Server
import com.github.paolobd.intellijgamificationplugin.dataClasses.TestStatus
import com.github.paolobd.intellijgamificationplugin.enums.DailyAchievement
import com.github.paolobd.intellijgamificationplugin.enums.GlobalAchievement
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.services.AchievementService
import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import com.github.paolobd.intellijgamificationplugin.services.ProjectStatePersistence
import com.github.paolobd.intellijgamificationplugin.userInterface.MyNotifier
import com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener
import com.intellij.execution.testframework.sm.runner.SMTestProxy
import com.intellij.openapi.project.Project
import java.time.LocalTime

class TestListener(private val project: Project) : SMTRunnerEventsListener {
    private lateinit var server: Server
    override fun onTestingStarted(testsRoot: SMTestProxy.SMRootTestProxy) {
        ApplicationStatePersistence.getInstance().checkAndUpdateDailyGUI()
        server = Server()
        server.start()
        println("Server started!")
    }

    override fun onTestingFinished(testsRoot: SMTestProxy.SMRootTestProxy) {
        server.stop()
        println("Server stopped!")
    }

    override fun onTestsCountInSuite(count: Int) {
    }

    override fun onTestStarted(test: SMTestProxy) {
    }

    override fun onTestFinished(test: SMTestProxy) {
        println("Test finished!")
        val eventList = Server.events
        println("Events:")
        for (event in eventList) {
            println(event)
        }
        val service = AchievementService.getInstance()

        if (eventList.isNotEmpty()) {
            AchievementService().addExp(
                global = true, daily = false,
                GlobalAchievement.FIRST_SELENIUM.achievement, 1
            )

            val daily = ApplicationStatePersistence.getInstance().state.dailyAchievement

            val projectState = ProjectStatePersistence.getInstance(project).state

            if (!projectState.testState.contains(test.name)) {
                //Test added to map
                projectState.testState[test.name] = TestStatus()

                val currentHour = LocalTime.now().hour

                if (currentHour in 6..8) {
                    AchievementService().addExp(
                        global = true, daily = false,
                        GlobalAchievement.EARLY_TEST.achievement, 1
                    )

                    val achEarly = DailyAchievement.DAILY_EARLY.achievement
                    if (daily.state.id == achEarly.id) {
                        service.addExp(global = true, daily = true, achEarly, 1)
                    }

                } else if (currentHour >= 23 || currentHour <= 3) {
                    service.addExp(global = true, daily = false, GlobalAchievement.LATE_TEST.achievement, 1)

                    val achLate = DailyAchievement.DAILY_LATE.achievement
                    if (daily.state.id == achLate.id) {
                        service.addExp(global = true, daily = true, achLate, 1)
                    }
                }
            }

            if (test.isPassed) {
                //New test passed
                if (!projectState.testState[test.name]!!.passedOnce) {
                    projectState.testState[test.name]!!.passedOnce = true

                    service.addExp(
                        global = true, daily = false,
                        GlobalAchievement.NUM_TEST_PASSED.achievement, 1
                    )
                    service.addExp(
                        global = false, daily = false,
                        ProjectAchievement.NUM_TEST_PASSED.achievement, 1
                    )

                    val achTest = DailyAchievement.DAILY_TEST_PASSED.achievement

                    if (daily.state.id == achTest.id) {
                        service.addExp(
                            global = true, daily = true,
                            DailyAchievement.DAILY_TEST_PASSED.achievement, 1
                        )
                    }
                } else {
                    //Test executed in the past but now passes
                    if (!projectState.testState[test.name]!!.lastPassed) {
                        service.addExp(
                            global = true, daily = false,
                            GlobalAchievement.NUM_TEST_FIXED.achievement, 1
                        )
                        service.addExp(
                            global = false, daily = false,
                            ProjectAchievement.NUM_TEST_FIXED.achievement, 1
                        )
                    }
                }
                projectState.testState[test.name]!!.lastPassed = true
            } else {
                //Test not passed for the first time
                service.addExp(
                    global = true, daily = false,
                    GlobalAchievement.FIRST_FAILED.achievement, 1
                )

                projectState.testState[test.name]!!.lastPassed = false
            }
            service.analyzeEvents(eventList)
        } else {
            MyNotifier.notifyWarning(project)
        }
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