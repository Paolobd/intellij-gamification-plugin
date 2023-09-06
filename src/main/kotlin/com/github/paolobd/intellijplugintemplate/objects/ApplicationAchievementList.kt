package com.github.paolobd.intellijplugintemplate.objects

enum class ApplicationAchievementList (
    val title: String,
    val description: String,
    val iconUrl: String,
    val total: Int,
    val userExp: Int
) {
    NUM_CLICKS(
        "Night Owl",
        "Execute a test between 11 p.m and 3 a.m",
        "/userInterface/global/owl.svg",
        1,
        100
    ),
    NUM_SITES(
        "Slow Testing",
        "A test took more than 1 minute",
        "/userInterface/global/slow.svg",
        5,
        20
    ),
    NUM_LOCATOR_ALL(
        "Fast Testing",
        "A test took more than 1 second",
        "/userInterface/global/fast.svg",
        200,
        30
    ),
    NUM_LOCATOR_ID(
        "High Success!",
        "Number of tests passed",
        "/userInterface/global/success.svg",
        100,
        30
    ),
    NUM_LOCATOR_XPATH(
        "Failure",
        "Number of tests failed",
        "/userInterface/global/failed.svg",
        100,
        30
    ),
    NUM_LOCATOR_CSS(
        "More Testing!",
        "Executed a suite with more than 10 test",
        "/userInterface/global/test.svg",
        100,
        30
    ),
}
