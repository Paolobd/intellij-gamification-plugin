package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class ProjectAchievement(
    val achievement: Achievement
) {
    NUM_TEST_PASSED(
        Achievement(
            1,
            "Can't believe it worked!",
            "Different Selenium tests passed",
            "test_passed.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_TEST_FIXED(
        Achievement(
            2,
            "Bug Finder",
            "Different Selenium tests fixed<br/>after not passing",
            "test_fixed.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR(
        Achievement(
            3,
            "Inspector Gadget",
            "Different WebElements found using<br/>any locator strategy",
            "locator.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_ID(
        Achievement(
            4,
            "Inspector Gadget: ID Expert",
            "Different WebElements found using<br/>the 'ID' locator strategy",
            "locator_id.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_NAME(
        Achievement(
            5,
            "Inspector Gadget: Name Expert",
            "Different WebElements found using<br/>the 'Name' locator strategy",
            "locator_name.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_CSS(
        Achievement(
            6,
            "Inspector Gadget: CSS Expert",
            "Different WebElements found using<br/>the 'CSS Selector' locator strategy",
            "locator_css.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_XPATH(
        Achievement(
            7,
            "Inspector Gadget: XPath Expert",
            "Different WebElements found using<br/>the 'XPath' locator strategy",
            "locator_xpath.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_NAVIGATION(
        Achievement(
            8,
            "The Web Surfer",
            "Different websites visited",
            "navigation.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_NAVIGATION_BACK(
        Achievement(
            9,
            "Back to...",
            "Times the WebDriver's 'back()'<br/>method was executed",
            "navigation_back.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_NAVIGATION_FORWARD(
        Achievement(
            10,
            "...the Future",
            "Times the WebDriver's 'forward()'<br/>method was executed",
            "navigation_forward.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_NAVIGATION_REFRESH(
        Achievement(
            11,
            "F5 master",
            "Times the WebDriver's 'refresh()'<br/>method was executed",
            "navigation_refresh.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_CLICK(
        Achievement(
            12,
            "Click Addicted",
            "Times the 'click()' method was called<br/>on different WebElements",
            "element_click.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_SEND_KEYS(
        Achievement(
            13,
            "Keyboard Master",
            "Times the 'send_keys(...)' method<br/>was called on different WebElements",
            "element_send_keys.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_DISPLAYED(
        Achievement(
            14,
            "Display Detective",
            "Times the 'isDisplayed()' method<br/>was called on different WebElements",
            "element_displayed.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_SELECTED(
        Achievement(
            15,
            "Checkmark Champion",
            "Times the 'isSelected()' method<br/>was called on different WebElements",
            "element_selected.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_ENABLED(
        Achievement(
            16,
            "The Validator",
            "Times the 'isEnabled()' method<br/>was called on different WebElements",
            "element_enabled.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_TEXT(
        Achievement(
            17,
            "Textual Treasure Hunter",
            "Times the 'getText()' method<br/>was called on different WebElements",
            "element_text.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_ATTRIBUTE(
        Achievement(
            18,
            "Attribute Archaeologist",
            "Times the 'getAttribute(...)' method<br/>was called on different WebElements",
            "element_attribute.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_CSS(
        Achievement(
            19,
            "Styling Virtuoso",
            "Times the 'getCssValue(...)' method<br/>was called on different WebElements",
            "element_css.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOGIN(
        Achievement(
            20,
            "Keyblade Wielder",
            "Times of attempted logins",
            "login.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_SUBMIT(
        Achievement(
            21,
            "Form Filler",
            "Times the 'submit()' method<br/>was called on different WebElements",
            "submit.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_TITLE(
        Achievement(
            22,
            "Title Tracker",
            "Times the WebDriver's 'getTitle()' method<br/>was executed",
            "title.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_ALERT(
        Achievement(
            23,
            "Click-and-Dismiss",
            "Times interacted with an alert",
            "alert.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    )
}