package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

class MyNotifier {

    companion object {
        fun notifyAchievementProgress(
            project: Project?,
            global: Boolean,
            daily: Boolean,
            achievement: Achievement,
            milestoneIndex: Int,
            percentage: Int
        ) {
            val title = "Achievement progress update"
            var content = if (daily) {
                "Daily achievement"
            } else {
                if (!global) {
                    "Project achievement"
                } else {
                    "Global achievement"
                }
            }

            content += " '${achievement.name}' - Completed $percentage%"
            if (achievement.milestone.size != 1) {
                content += " of Milestone ${milestoneIndex + 1}"
            }

            NotificationGroupManager.getInstance()
                .getNotificationGroup("NotificationAchievementProgressGameGui")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }

        fun notifyAchievementMilestone(
            project: Project?,
            global: Boolean,
            daily: Boolean,
            achievement: Achievement,
            milestoneIndex: Int
        ) {
            val title = if (milestoneIndex == achievement.milestone.size - 1) {
                "Achievement completed"
            } else {
                "New Milestone reached"
            }

            var content = if (daily) {
                "Daily achievement"
            } else {
                if (!global) {
                    "Project achievement"
                } else {
                    "Global achievement"
                }
            }

            content += if (milestoneIndex == achievement.milestone.size - 1) {
                " '${achievement.name}' completed! Gained ${achievement.userExperience[milestoneIndex]} xp"
            } else {
                " '${achievement.name}' Milestone ${milestoneIndex + 1} completed! " +
                        "Gained ${achievement.userExperience[milestoneIndex]} xp"
            }
            NotificationGroupManager.getInstance()
                .getNotificationGroup("NotificationAchievementCompletedGameGui")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }

        fun notifyLevelUp(project: Project?, level: Int) {
            val title = "Level $level reached!"
            val content = "New icons and titles have been unlocked in your profile"
            NotificationGroupManager.getInstance()
                .getNotificationGroup("NotificationLevelUpGameGui")
                .createNotification(title, content, NotificationType.INFORMATION)
                .notify(project)
        }

        fun notifyWarning(project: Project?) {
            val title = "No events received"
            val content = "Check that you are using the Game GUI library in your Selenium project"
            NotificationGroupManager.getInstance()
                .getNotificationGroup("NotificationWarningGameGui")
                .createNotification(title, content, NotificationType.WARNING)
                .notify(project)
        }
    }
}