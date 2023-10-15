package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

class MyNotifier {

    companion object {
        fun notifyAchievementProgress(
            project: Project?,
            achievement: Achievement,
            milestoneIndex: Int,
            percentage: Int
        ) {
            val title = "'${achievement.name}': $percentage% completed"
            var content = "Completed $percentage%"
            if (achievement.milestone.size != 1) {
                content += " of milestone ${milestoneIndex + 1}"
            }
            NotificationGroupManager.getInstance()
                .getNotificationGroup("GameGUI Achievement Progress")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }

        fun notifyAchievementMilestone(project: Project?, achievement: Achievement, milestoneIndex: Int) {
            val title = if (milestoneIndex == achievement.milestone.size - 1) {
                "'${achievement.name}' completed"
            } else {
                "'${achievement.name}' update"
            }
            val content = if (milestoneIndex == achievement.milestone.size - 1) {
                "Achievement completed! Gained ${achievement.userExperience[milestoneIndex]} xp"
            } else {
                "Milestone ${milestoneIndex + 1} completed! Gained ${achievement.userExperience[milestoneIndex]} xp"
            }
            NotificationGroupManager.getInstance()
                .getNotificationGroup("GameGUI Achievement Completed")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }

        fun notifyLevelUp(project: Project?, level: Int) {
            val title = "Level $level reached!"
            val content = "New icons and titles have been unlocked in your profile"
            NotificationGroupManager.getInstance()
                .getNotificationGroup("GameGUI Level Up")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }
    }
}