package com.github.paolobd.intellijplugintemplate.objects

import com.github.paolobd.intellijplugintemplate.views.MyNotifier
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.JProgressBar

class Achievement(
    val id: Int, val icon: Icon, val title: String,
    val description: String, var currentExp: Int, val maxExp: Int) {

    var progressBar: JProgressBar = JProgressBar()
    var progressLabel: JLabel = JLabel()


    fun addExperience(exp: Int){
        val oldExp = currentExp
        currentExp = if (currentExp + exp > maxExp) maxExp else currentExp + exp
        progressBar.value = currentExp
        progressLabel.text = "$currentExp / $maxExp"

        var notificationText:String? = null
        val oldPercentage = oldExp.toFloat()/maxExp * 100
        val currPercentage = currentExp.toFloat()/maxExp * 100

        intArrayOf(25, 50, 75, 100).forEach{
            if(oldPercentage < it && currPercentage >= it){
                notificationText =
                    if(it == 100) "Congratulations! You've completed '$title'"
                    else "You've reached $it% of '$title'"
            }
        }

        //Passing null instead of project makes it appear in any case, if I manage to pass the project it will show only
        //in the project windows
        if(notificationText != null){
            MyNotifier.notifyAchievement(null, notificationText!!)
        }
    }

    override fun toString(): String {
        return "id: ${this.id} title: ${this.title}"
    }
}