package com.github.paolobd.intellijplugintemplate.listeners

import com.intellij.execution.ExecutionListener
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment

class MyProva : ExecutionListener{
    override fun processStarting(executorId: String, env: ExecutionEnvironment) {
        super.processStarting(executorId, env)

    }

    override fun processStarted(executorId: String, env: ExecutionEnvironment, handler: ProcessHandler) {
        super.processStarted(executorId, env, handler)
    }
}