package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class GlobalAchievement(
    val achievement: Achievement
) {
    NUM_TEST_PASSED(
        Achievement(
            1,
            "Can't believe it worked",
            "Different Selenium tests passed",
            "test_passed.svg",
            listOf(10, 20, 30),
            listOf(10, 20, 30)
        )
    ),
    NUM_TEST_FIXED(
        Achievement(
            2,
            "Bug Finder",
            "Different Selenium tests fixed<br/>after not passing",
            "test_fixed.svg",
            listOf(10),
            listOf(10)
        )
    ),
    NUM_LOCATOR(
        Achievement(
            3,
            "Inspector Gadget",
            "Different WebElements found using<br/>any locator strategy",
            "locator.svg",
            listOf(10),
            listOf(10)
        )
    ),
    NUM_NAVIGATION(
        Achievement(
            4,
            "The Web Surfer",
            "Different websites visited",
            "navigation.svg",
            listOf(10),
            listOf(10)
        )
    ),
    NUM_ELEMENT_CLICK(
        Achievement(
            5,
            "Click Addicted",
            "Times the 'click()' method<br/>was called on different WebElements",
            "element_click.svg",
            listOf(10),
            listOf(10)
        )
    ),
    NUM_ELEMENT_SEND_KEYS(
        Achievement(
            6,
            "Keyboard Master",
            "Times the 'send_keys(...)' method<br/>was called on different WebElements",
            "element_send_keys.svg",
            listOf(10),
            listOf(10)
        )
    ),
    NUM_ELEMENT_TEXT(
        Achievement(
            7,
            "Textual Treasure Hunter",
            "Times the 'getText()' method<br/>was called on different WebElements",
            "element_text.svg",
            listOf(10),
            listOf(10)
        )
    ),
    NUM_LOGIN(
        Achievement(
            8,
            "Keyblade Wielder",
            "Times of attemped logins",
            "login.svg",
            listOf(10),
            listOf(10)
        )
    ),
    NUM_ALERT(
        Achievement(
            9,
            "Click-and-Dismiss",
            "Times interacted with an alert",
            "alert.svg",
            listOf(10),
            listOf(10)
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
            "Execute a test between 6 a.m. and 8 a.m.",
            "early.svg",
            listOf(10),
            listOf(10)
        )
    ),
    LATE_TEST(
        Achievement(
            15,
            "Night Owl",
            "Execute a test between 11 p.m. and 3 a.m.",
            "late.svg",
            listOf(10),
            listOf(10)
        )
    )
}
