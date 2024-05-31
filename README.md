# WELCOME TO FILL IT UP GAME!


The player will start the game by entering their name, choosing game difficulty modes
(easy – moderate – challenging). This setting changes the number of carts and the amount of the corresponding resource it takes to fill each one.
The player then selects the number of rounds to play from 5 to 15 rounds range and 3 types of Towers to start playing.
There are 5 different towers. Each tower has a different resource amount, reloading speed, resource type, level, and cost.
Next, the player will select a round difficulty before beginning the round. There are 3 round difficulty modes, which are Easy, Moderate, and Challenging.
This setting changes the speed of the carts on the track.

Now the gameplay begins.
To fill a running cart, the player must first use the Tower buttons at the bottom of the screen
to select a tower to shoot resources from. Next, the player will click on a cart that takes the same resource with the previously chosen tower. 
This will add an amount of resource into the cart. Be aware that it takes a few seconds for the tower to recover as
each resource has different recovery time, so that it can fire again.

After winning the first round, the player will be rewarded points and can be converted to coins, The player then can spend it in the Shop.
There are multiple buy Towers and upgrade options in the Shop. After winning the first round, there is a possibility that the player's towers
will randomly break and disappear from their inventory. 

## Authors
Team 82

Ngoc Le – ID: 46909266 

Anh Le - ID: 51227573
## Prerequisites
- JDK >= 17 [click here to get the latest stable OpenJDK release (as of writing this README)](https://jdk.java.net/18/)
- *(optional)* Gradle [Download](https://gradle.org/releases/) and [Install](https://gradle.org/install/)


## What's Included
This project comes with some basic examples of the following (including dependencies in the build.gradle file):
- JavaFX
- Junit 5

We have also included a basic setup of the Gradle project and Tasks required for the course including:
- Required dependencies for the functionality above
- Test Coverage with JaCoCo
- Build plugins:
    - JavaFX Gradle plugin for working with (and packaging) JavaFX applications easily

You are expected to understand the content provided and build your application on top of it. If there is anything you
would like more information about please reach out to the tutors.

## Importing Project (Using IntelliJ)
IntelliJ has built-in support for Gradle. To import your project:

- Launch IntelliJ and choose `Open` from the start-up window.
- Select the project and click open
- At this point in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load

**Note:** *If you run into dependency issues when running the app or the Gradle pop up doesn't appear then open the Gradle sidebar and click the Refresh icon.*

## Run Project 
1. Open a command line interface inside the project directory and run `./gradlew run` to run the app.
2. The app should then open a new window, this may not be displayed over IntelliJ but can be easily selected from the taskbar

## Build and Run Jar
1. Open a command line interface inside the project directory and run `./gradlew jar` to create a packaged Jar. The Jar file is located at build/libs/seng201_team0-1.0-SNAPSHOT.jar
2. Navigate to the build/libs/ directory (you can do this with `cd build/libs`)
3. Run the command `java -jar seng201_team0-1.0-SNAPSHOT.jar` to open the application.

## Run Tests
1. Open a command line interface inside the project directory and run `./gradlew test` to run the tests.
2. Test results should be printed to the command line

**Note:** *This Jar is **NOT** cross-platform, so you **must** build the jar you submit on Linux.* 
