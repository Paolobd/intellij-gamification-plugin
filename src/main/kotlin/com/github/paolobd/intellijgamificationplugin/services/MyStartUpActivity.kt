package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.UserState
import com.github.paolobd.intellijgamificationplugin.library.Server
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
class MyStartUpActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        println("Hello world!")
        /*val server = Server()
        server.start()

        println("Server started!")*/
        //ProjectStatePersistence.getInstance(project).resetState()
        //ApplicationStatePersistence.getInstance().resetState()
        /*ApplicationStatePersistence.getInstance().state.userState = UserState(
            "Test", 1, 1,5, 50, listOf(1, 2, 3, 4, 5)
        )*/
    }
}