package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.library.Server
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
class MyStartUpActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        println("Hello world!")
        /*val server = Server()
        server.start()

        println("Server started!")*/
    }
}