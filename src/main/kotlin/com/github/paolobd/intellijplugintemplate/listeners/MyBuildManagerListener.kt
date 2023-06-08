package com.github.paolobd.intellijplugintemplate.listeners

import com.intellij.compiler.server.BuildManagerListener
import com.intellij.openapi.project.Project
import java.util.*

class MyBuildManagerListener(private val project: Project) : BuildManagerListener {
    override fun beforeBuildProcessStarted(project: Project, sessionId: UUID) {
        super.beforeBuildProcessStarted(project, sessionId)
    }
}