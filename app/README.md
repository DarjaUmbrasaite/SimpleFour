# Simple 4

This is the repository for my project on the GitHub:

[GitHub Repository](https://github.com/DarjaUmbrasaite/SimpleFour.git)

## An Android Mobile cooking recipe application developed in Android Studio using Kotlin programming language that allows users to use search features to easier access custom recipes.


## Overview:
This is an android application that can be used to search for recipes by various filters such as ingredients and categories and favourites can be saved

## Features:

The android app lets you:

* Save recipes to favourites.
* Use filtered search to find those ingredient based recipes.
* App allows you to delete recipes from favourites list.
* App allows you to UNDO the deletion of the recipe in favourites.
* User can create an account.
* User can login using registered credentials.
* You can can log out from the app by using the top right corner menu.
* The log out feature will navigate back to the log in page.
* User can search for recipes in categories section.
* User can watch video of the meal's preparation if preferred over reading.
* App allows you to see recipe's description and the list of ingredients needed to cook the meal.
* The home page allows user to discover new recipes by randomly displaying the top recipe as meal on the day, and below and sliding down list from a random category.
* Password creation is minimum at least 6 characters.
* During registration process user can see message if registration password is already in use.
* User can not proceed in the search section of the recipes before he enters the valid ingredient for the first ingredient of four.

## Folder Structure:

- **/app**: Project application module where all code and resources are stored.
- **/manifests**: Directory that contains AndroidManifest.xml file which contains this app's essential information about components and permissions.
- **/kotlin+java**: Kotlin source code files (the first top 'com' subfolder).
- **/java**: Generated code directory, which does not need an edit.
- **/res**: Directory with all resources such as drawable for images and icons, font folder, layout for UI, menu for home screen top menu and bottom navigation, mipmap for launcher icons, values for colours abd style.    
- **/Gradle Scripts**: Contains build.gradle (Module: app) for the app module settings and dependencies, as well as other Gradle-related scripts.

## Installation Instructions

1.

## Requirements:
Android Studio HedgeHog 2023.1.1 (latest or earlier versions possibly okay too)
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o. (android studio will automatically acquire the appropriate JDKs)
Online Access

## How to Build and Run:
1. Clone the repository or download the ZIP file.
2. Navigate to the project directory.
3. Open the project folder in android studio (file->open).
4. Set up an emulator device in android studio: (device manager -> add -> choose a phone, e.g. 'Medium Phone').
5. Choose a system image for the emulator (e.g. UpsideDownCake (API level 34) Android 14.0. Also tested on Android 11.0.
6. Run the app (Run -> Run App) - it should build and then run.

* Note you must be online so gradle can download required files. The app also requires online access to access the meal database.

### Trouble Shooting:
Try deleting the .gradle folder and .idea folder and retrying the above steps (in case of any gradle related issues).

## How to Test the Product

While this project does not include automated test scripts, comprehensive manual testing has been conducted to ensure each feature performs as expected. Below are detailed steps and scenarios that were manually tested, which can be followed to verify the functionality of the application:

### General Testing Steps:
1. **Launch the Application:**
    - Ensure the application loads without crashes or significant delays.
    - Verify the initial splash screen appears as designed with all logo elements visible.

2. **Navigation:**
    - Navigate through all sections of the app using the bottom menu and buttons.
    - Ensure that navigation buttons/links take you to the correct pages.

3. **Functionality Tests:**
    - **Sign In/Out:**
      - Attempt to log in with correct and incorrect credentials.
      - Ensure that logging out redirects to the login page.

    - **Add Recipes to Favourites:**
       - **Adding/Deleting [Click on recipe on the Home page/press on black heart on the bottom to add it to favourites]:**
          - Navigate to Favourites on the bottom bar.
          - Check that saved recipe is in the Favourites list.
          - Swipe the chosen recipe to the left or right to delete it.
          - The Message will appear on the bottom of notifying deletion with "UNDO" option.
          - By clicking on "UNDO" deleted recipe will return back to the Favourites list.

      - **Search Functionality:**
         - Use the search feature with various keywords and check if the results are appropriate.
         - Try typing a word with lower/upper cases.
         - Try typing numbers.
         - Check if the message of "No Recipes found..." appears when no such a combination on ingredients in a meal exist.

      - **Meal Categories:**
         - Check that meal categories correspond to the name of the main page [Beef/Beef and Mustard Pie, Beef and Oyster pie, Beef Asado etc.]

      - **Meal Recipe Steps/Ingredients:**
        - Click on the desired recipe's picture.
        - Check that recipe's page displays "Recipe Steps"
        - Check that swipe down function works to allow to see the full recipe steps description.
        - Check that ingredient list is available to view at the bottom of recipe's page.
        - Check that by clicking on "Youtube" icon you are being navigated to the youtube video of that recipe (Note: some meals might not have video available)





