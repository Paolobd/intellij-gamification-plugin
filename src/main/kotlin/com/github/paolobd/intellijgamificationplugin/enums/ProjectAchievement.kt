package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class ProjectAchievement(
    val achievement: Achievement
) {
    NUM_TEST_PASSED(
        Achievement(
            1,
            "Can't believe it worked!",
            "Selenium tests passed",
            "test_passed.svg",
            listOf(5, 10, 20),
            listOf(10, 20, 30)
        )
    ),
    NUM_TEST_FIXED(
        Achievement(
            2,
            "Bug Finder",
            "Selenium tests fixed<br/>after not passing",
            "test_fixed.svg",
            listOf(1, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR(
        Achievement(
            3,
            "Inspector Gadget",
            "WebElements found using<br/>any locator strategy",
            "locator.svg",
            listOf(10, 25, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_ID(
        Achievement(
            4,
            "Inspector Gadget: ID Expert",
            "WebElements found using<br/>the 'ID' locator strategy",
            "locator_id.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_NAME(
        Achievement(
            5,
            "Inspector Gadget: Name Expert",
            "WebElements found using<br/>the 'Name' locator strategy",
            "locator_name.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_CSS(
        Achievement(
            6,
            "Inspector Gadget: CSS Expert",
            "WebElements found using<br/>the 'CSS Selector' locator strategy",
            "locator_css.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_XPATH(
        Achievement(
            7,
            "Inspector Gadget: XPath Expert",
            "WebElements found using<br/>the 'XPath' locator strategy",
            "locator_xpath.svg",
            listOf(5, 15, 30),
            listOf(10, 20, 30)
        )
    ),
    NUM_NAVIGATION(
        Achievement(
            8,
            "The Web Surfer",
            "Websites visited",
            "navigation.svg",
            listOf(5, 10, 15),
            listOf(10, 20, 30)
        )
    ),
    NUM_NAVIGATION_BACK(
        Achievement(
            9,
            "Back to...",
            "WebDriver's back<br/>method executed",
            "navigation_back.svg",
            listOf(5),
            listOf(20)
        )
    ),
    NUM_NAVIGATION_FORWARD(
        Achievement(
            10,
            "...the Future",
            "WebDriver's forward<br/>method executed",
            "navigation_forward.svg",
            listOf(5),
            listOf(20)
        )
    ),
    NUM_NAVIGATION_REFRESH(
        Achievement(
            11,
            "F5 master",
            "WebDriver's refresh<br/>method executed",
            "navigation_refresh.svg",
            listOf(5),
            listOf(20)
        )
    ),
    NUM_ELEMENT_CLICK(
        Achievement(
            12,
            "Click Addicted",
            "Click method called<br/>on WebElements",
            "element_click.svg",
            listOf(5, 15, 30),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_SEND_KEYS(
        Achievement(
            13,
            "Keyboard Master",
            "Send_keys method called<br/>on WebElements",
            "element_send_keys.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_DISPLAYED(
        Achievement(
            14,
            "Display Detective",
            "IsDisplayed method called<br/>on WebElements",
            "element_displayed.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_SELECTED(
        Achievement(
            15,
            "Checkmark Champion",
            "IsSelected method called<br/>on WebElements",
            "element_selected.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_ENABLED(
        Achievement(
            16,
            "The Validator",
            "IsEnabled method called<br/>on WebElements",
            "element_enabled.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_TEXT(
        Achievement(
            17,
            "Textual Treasure Hunter",
            "GetText method called<br/>on WebElements",
            "element_text.svg",
            listOf(5, 15, 30),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_ATTRIBUTE(
        Achievement(
            18,
            "Attribute Archaeologist",
            "GetAttribute method called<br/>on WebElements",
            "element_attribute.svg",
            listOf(5, 10, 15),
            listOf(10, 20, 30)
        )
    ),
    NUM_ELEMENT_CSS(
        Achievement(
            19,
            "Styling Virtuoso",
            "GetCssValue method called<br/>on WebElements",
            "element_css.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOGIN(
        Achievement(
            20,
            "Keyblade Wielder",
            "Attempted logins",
            "login.svg",
            listOf(1),
            listOf(20)
        )
    ),
    NUM_SUBMIT(
        Achievement(
            21,
            "Form Filler",
            "Submit method called<br/>or submit buttons clicked",
            "submit.svg",
            listOf(3, 5, 10),
            listOf(10, 20, 30)
        )
    ),
    NUM_TITLE(
        Achievement(
            22,
            "Title Tracker",
            "WebDriver's getTitle method<br/>executed",
            "title.svg",
            listOf(5, 10, 15),
            listOf(10, 20, 30)
        )
    ),
    NUM_ALERT(
        Achievement(
            23,
            "Click-and-Dismiss",
            "Interaction with an alert",
            "alert.svg",
            listOf(1, 3, 5),
            listOf(10, 20, 30)
        )
    )
}