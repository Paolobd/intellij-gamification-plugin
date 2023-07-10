package com.github.paolobd.intellijplugintemplate.listeners

import com.intellij.compiler.server.BuildManagerListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
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

        val variables: MutableList<PsiLocalVariable> = mutableListOf()

        ApplicationManager.getApplication().runReadAction {
            val file =
                FilenameIndex.getVirtualFilesByName("MainPageTest.java", GlobalSearchScope.projectScope(project)).firstOrNull()

            val psiFile = PsiManager.getInstance(project).findFile(file!!)

            //println("File type: ${file.fileType}")
            //println("File content: ${file.readText()}")

            println("File name: ${file.name}")

            psiFile!!.accept(object : JavaRecursiveElementVisitor() {
                override fun visitLocalVariable(variable: PsiLocalVariable) {
                    super.visitLocalVariable(variable)
                    println("Found a variable '${variable.name}' at offset ${variable.textRange.startOffset}")
                    variables.add(variable)
                }
            })

            //percorrere ricorsivamente TUTTO il file
            psiFile.accept(object : PsiRecursiveElementWalkingVisitor(){
                override fun visitElement(element: PsiElement) {
                    super.visitElement(element)
                    //if (element.elementType == WebDriver::javaClass)
                }
            })
        }

        WriteCommandAction.runWriteCommandAction(project){
            variables.forEach{
               //do nothing
            }
        }



//        ProjectFileIndex.getInstance(project).iterateContent{
//            println("File type: ${it.fileType}")
//            println("File content: ${it.readText()}")
//            true
//        }
    }

    override fun buildFinished(project: Project, sessionId: UUID, isAutomake: Boolean) {
        super.buildFinished(project, sessionId, isAutomake)

        println("Build Finished!")
    }
}