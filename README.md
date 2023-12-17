# Game GUI: An IntelliJ IDEA Plugin
Game GUI is an IntelliJ IDEA plugin developed in Kotlin with the aim of creating a gamified environment to support scripted GUI testing techniques. It has been developed as a **Master Thesis** work at _Politecnico di Torino_ by Paolo Stefanut Bodnarescul with the aid of the [Software Engineering Research Group](https://softeng.polito.it/).<br/><br/>
To take full advantage of the plugin's gamified environment, it must be used along with the [Game GUI Java Library](https://github.com/Paolobd/gamification-library) in a project that performs scripted GUI testing by exploiting _Selenium WebDriver_.
The plugin features:
- Achievements that track the user actions with the Selenium WebDriver. Each time an achievement is completed some points are awarded to the user.
- A user profile page that can be customized at will. The user can unlock new icons and titles to equip and they can also display some of the unlocked achievements.

## Documentation
This section will highlight the most important parts of the plugin but will not explain in detail how to develop a plugin. In case of doubts please refer to the official [IntelliJ Platform SDK](https://plugins.jetbrains.com/docs/intellij/welcome.html) documentation.</br>

The project is divided into several folders to better separate the different concepts.

The "dataClasses" folder contains all the Kotlin data classes used in this project. The most important classes here are the **ProjectState** and **ApplicationState**, both used to store user information at the project level and application level.

The "dataProviders" folder contains some objects used to retrieve some static information about levels, titles, and user icons. Since the amount of data is small, this solution has been implemented instead of using an internal database.

In the "enums" folder there are the **DailyAchievement**, **GlobalAchievement**, and **ProjectAchievement** classes that contain all the defined achievements used in this project. These can be modified to edit the given experience, description, name, and steps of the achievements or to add new ones.

The "listeners" folder contains the listeners used in this project. **StartActivity** is used to run the plugin's tool window at the start of the project to correctly initialize all the logics, meanwhile **TestListener** is used to keep track of the tests executed by the user. When a test is finished the plugin checks the activities performed during the GUI testing and awards the corresponding achievements. Additionally, it initializes a server at the start of the user testing session to allow communication with the **Game GUI Java Library**, which will be closed at the end of the testing session. 

The "services" folder contains the services exploited in this project. **ApplicationStatePersistence** and **ProjectStatePersistence** both implement the [PersistingStateComponent](https://plugins.jetbrains.com/docs/intellij/persisting-state-of-components.html) interface to save the user information. ApplicationStatePersistence saves the ApplicationState data class with info such as name, title, icon, level, progress, achievements, achievements displayed, and the daily task. ProjectStatePersistence saves the ProjectState data class that contains info about the  project achievements and activities performed by using the Selenium WebDriver. There is another service, **AchievementService** that contains the logic to increase the progress of the appropriate achievements. 

The "actions" folder contains only the **Reset Action**. It is available in the settings of the plugin tool window and it is used to reset the user progress.

The "communication" folder allows the exchange of information between the **Game GUI Java Library** and this plugin. It contains the **Event** and **EventType** data classes, both shared with the library. There is also a [Ktor](https://ktor.io/) server that is initialized by the TestListener at the start of the user testing session and closed at the end of the user testing session. It has only a POST endpoint through which the library can send the events. 

The "userInterface" folder contains all the classes that implement the tool window user interface using Java Swing. **UserInterfaceFactory** initializes the tool window of the plugin. In particular **UserInterface** initializes the two tabs of the tool window: **UserTab** and **AchievementsTab**. This folder also contains the **MyNotifier** class that contains all the notifications sent by the plugin to the user through the IDE. 

The resources folder contains the plugin.xml file and all the icons. The icons are available both for the light and dark theme, and the user icons are also available in a smaller size to show them during the editing phase. The plugin configuration file contains all the definitions of the notifications, services, and listeners used by the plugin. 

Modify <kbd>build.gradle.kts</kbd> and <kbd>gradle.properties</kbd> to change the target IDE of the plugin and its version, change the dependencies or the plugin version.

### Summary
In this section will be briefly summarized how the plugin works. 

The **TestListener** creates a server at the start of the testing suite and closes it at the end of the testing suite. While the server is opened, at the end of each test the events sent by the library are analyzed by the **AchievementService**. This service correctly increases the progress of the corresponding achievements by checking the received events and comparing them with the project events stored in **ProjectStatePersistence**. Then it calls the UI methods in **AchievementsTab** to also update the progress visually. If an achievement is completed, the experience will be added to the user profile in the **ApplicationStatePersistence** service, which will calculate if the user has leveled up or not. Additionally, at every suitable moment, the corresponding method in the **MyNotifier** class is called to show notifications to the user. 

## Installation
The plugin has not yet been released in the JetBrains marketplace. It can be manually installed in the IntelliJ IDEA IDE: <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd><br/>

To create the plugin zip file from the project one of the following Gradle tasks can be run:
- <kbd>build</kbd> > <kbd>build</kbd>
- <kbd>intellij</kbd> > <kbd>buildPlugin</kbd>

At that point, the zip file is found in the <kbd>build/distributions</kbd> folder.

Remember also to add the [Game GUI Java Library](https://github.com/Paolobd/gamification-library) to the Selenium WebDriver project by following the instructions.

## Example
A project example with the library already imported and ready to use can be found [here](https://github.com/Paolobd/gamification-plugin-example). The plugin still needs to be installed in your IDE.

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
