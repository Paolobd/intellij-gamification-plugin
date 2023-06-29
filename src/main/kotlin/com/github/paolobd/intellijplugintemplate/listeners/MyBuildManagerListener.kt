package com.github.paolobd.intellijplugintemplate.listeners

import com.intellij.compiler.server.BuildManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.vfs.readText
import java.util.*

class MyBuildManagerListener(private val project: Project) : BuildManagerListener {
    override fun beforeBuildProcessStarted(project: Project, sessionId: UUID) {
        super.beforeBuildProcessStarted(project, sessionId)

        println("Before Build!")


        //ReferencesSearch.search(PsiLocalVariable)
    }

    override fun buildStarted(project: Project, sessionId: UUID, isAutomake: Boolean) {
        super.buildStarted(project, sessionId, isAutomake)

        println("Build Started!")

        ProjectFileIndex.getInstance(project).iterateContent{
            println("File type: ${it.fileType}")
            println("File content: ${it.readText()}")
            true
        }
    }

    override fun buildFinished(project: Project, sessionId: UUID, isAutomake: Boolean) {
        super.buildFinished(project, sessionId, isAutomake)

        println("Build Finished!")
    }
}