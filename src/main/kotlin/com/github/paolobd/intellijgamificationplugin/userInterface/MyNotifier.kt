package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

class MyNotifier {

    companion object {
        fun notifyAchievementProgress(
            project: Project?,
            daily: Boolean,
            achievement: Achievement,
            milestoneIndex: Int,
            percentage: Int
        ) {
            val title = "Achievement progress update"
            var content = if (daily) {
                "Daily achievement"
            } else {
                if (project != null) {
                    "Project achievement"
                } else {
                    "Global achievement"
                }
            }

            content += " '${achievement.name}' - Completed $percentage%"
            if (achievement.milestone.size != 1) {
                content += " of milestone ${milestoneIndex + 1}"
            }

            NotificationGroupManager.getInstance()
                .getNotificationGroup("Game GUI Achievement Progress")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }

        fun notifyAchievementMilestone(
            project: Project?,
            daily: Boolean,
            achievement: Achievement,
            milestoneIndex: Int
        ) {
            val title = if (milestoneIndex == achievement.milestone.size - 1) {
                "Achievement completed"
            } else {
                "New milestone reached"
            }

            var content = if (daily) {
                "Daily achievement"
            } else {
                if (project != null) {
                    "Project achievement"
                } else {
                    "Global achievement"
                }
            }

            content += if (milestoneIndex == achievement.milestone.size - 1) {
                " '${achievement.name}' completed! Gained ${achievement.userExperience[milestoneIndex]} xp"
            } else {
                " '${achievement.name} milestone ${milestoneIndex + 1} completed! " +
                        "Gained ${achievement.userExperience[milestoneIndex]} xp"
            }
            NotificationGroupManager.getInstance()
                .getNotificationGroup("Game GUI Achievement Completed")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }

        fun notifyLevelUp(project: Project?, level: Int) {
            val title = "Level $level reached!"
            val content = "New icons and titles have been unlocked in your profile"
            NotificationGroupManager.getInstance()
                .getNotificationGroup("Game GUI Level Up")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }
    }
}