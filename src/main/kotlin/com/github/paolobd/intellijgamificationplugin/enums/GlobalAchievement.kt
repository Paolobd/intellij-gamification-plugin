package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class GlobalAchievement(
    val achievement: Achievement
) {
    NUM_TEST_PASSED(
        Achievement(
            1,
            "Can't believe it worked!",
            "Selenium tests passed",
            "test_passed.svg",
            listOf(10, 50, 100),
            listOf(10, 20, 30)
        )
    ),
    NUM_TEST_FIXED(
        Achievement(
            2,
            "Bug Finder",
            "Selenium tests fixed<br/>after not passing",
            "test_fixed.svg",
            listOf(5, 25, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR(
        Achievement(
            3,
            "Inspector Gadget",
            "WebElements found using<br/>any locator strategy",
            "locator.svg",
            listOf(25, 50, 100),
            listOf(10, 20, 30)
        )
    ),
    NUM_NAVIGATION(
        Achievement(
            4,
            "The Web Surfer",
            "Websites visited",
            "navigation.svg",
            listOf(10, 25, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_CLICK(
        Achievement(
            5,
            "Click Addicted",
            "Click method called<br/>on WebElements",
            "element_click.svg",
            listOf(15, 30, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_SEND_KEYS(
        Achievement(
            6,
            "Keyboard Master",
            "Send_keys method called<br/>on WebElements",
            "element_send_keys.svg",
            listOf(5, 15, 30),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_TEXT(
        Achievement(
            7,
            "Textual Treasure Hunter",
            "GetText method called<br/>on WebElements",
            "element_text.svg",
            listOf(15, 30, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOGIN(
        Achievement(
            8,
            "Keyblade Wielder",
            "Attempted logins",
            "login.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_ALERT(
        Achievement(
            9,
            "Click-and-Dismiss",
            "Interaction with an alert",
            "alert.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    FIRST_EDIT_PROFILE(
        Achievement(
            10,
            "Suit Up",
            "Edit your profile information<br/>for the first time",
            "edit_user.svg",
            listOf(1),
            listOf(10)
        )
    ),
    FIRST_EDIT_SHOWCASE(
        Achievement(
            11,
            "Showcase Legend",
            "Edit your achievement showcase<br/>for the first time",
            "edit_showcase.svg",
            listOf(1),
            listOf(10)
        )
    ),
    FIRST_SELENIUM(
        Achievement(
            12,
            "Baby steps",
            "Run your first Selenium test",
            "first_test.svg",
            listOf(1),
            listOf(10)
        )
    ),
    FIRST_FAILED(
        Achievement(
            13,
            "Buggy beginnings",
            "Fail your first Selenium test",
            "first_error.svg",
            listOf(1),
            listOf(10)
        )
    ),
    EARLY_TEST(
        Achievement(
            14,
            "Morning Person",
            "Execute a new test between<br/>6 a.m. and 8 a.m.",
            "early.svg",
            listOf(5, 10, 15),
            listOf(10, 20, 30)
        )
    ),
    LATE_TEST(
        Achievement(
            15,
            "Night Owl",
            "Execute a new test between<br/>11 p.m. and 3 a.m.",
            "late.svg",
            listOf(5, 10, 15),
            listOf(10, 20, 30)
        )
    )
}
