package com.github.paolobd.intellijplugintemplate.services

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
class MyStartUpActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        println("Hello world!")
    }
}