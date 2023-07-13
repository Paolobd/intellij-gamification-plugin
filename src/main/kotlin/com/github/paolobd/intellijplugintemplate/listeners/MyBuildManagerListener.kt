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

        var driverField: PsiField? = null
        var driverVariable: PsiLocalVariable? = null

        val globalSearchScope = GlobalSearchScope.allScope(project)

        ApplicationManager.getApplication().runReadAction {
            val file =
                FilenameIndex.getVirtualFilesByName("MainPageTest.java", globalSearchScope).firstOrNull()

            val psiFile = PsiManager.getInstance(project).findFile(file!!)

            //println("File type: ${file.fileType}")
            //println("File content: ${file.readText()}")

            println("File name: ${file.name}")

            psiFile!!.accept(object : JavaRecursiveElementVisitor() {
                /*override fun visitLocalVariable(variable: PsiLocalVariable) {
                    super.visitLocalVariable(variable)
                    println("Found a variable '${variable.name}' at offset ${variable.textRange.startOffset}")
                    variables.add(variable.name)
                }*/

                override fun visitTypeElement(type: PsiTypeElement) {
                    super.visitTypeElement(type)

                    if(type.text == "WebDriver"){

                        if(type.context is PsiLocalVariable){
                            driverVariable = type.context as PsiLocalVariable
                            println("Found a variable '${driverVariable!!.name}' at offset ${driverVariable!!.textRange.startOffset}")
                        } else if(type.context is PsiField) {
                            driverField = type.context as PsiField
                            println("Found a field '${driverField!!.name}' at offset ${driverField!!.textRange.startOffset}")
                        }
                    }
                }

            })

            //percorrere ricorsivamente TUTTO il file
            /*psiFile.accept(object : PsiRecursiveElementWalkingVisitor(){
                override fun visitElement(element: PsiElement) {
                    super.visitElement(element)
                    if(element.text == "WebDriver" && element.elementType == JavaTokenType.IDENTIFIER){
                        println("element WebDriver: $element")

                    }
                }
            })*/

            //psiFile.javaClass.fields.forEach { println(it)}
        }



        WriteCommandAction.runWriteCommandAction(project){
            if(driverVariable != null){
                //do something
                //driverVariable!!.name = "nutzDriver"
                println("WebDriver Variable references:")
                //ReferencesSearch.search(driverVariable!!).findAll().forEach{println(it.element)}
            }
            if(driverField != null){
                //do something
                //driverField!!.name = "nutzDriver"
                println("WebDriver Field references:")
                //ReferencesSearch.search(driverField!!).forEach{println(it)}

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