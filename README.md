# BringThemHome 🎗️

BringThemHome updates on individuals kidnapped in the 2023 attack on Israel, aiming to maintain awareness and community support. It enables users to engage with each story and contribute to advocacy efforts, strengthening the collective call for action and resolution.

## Features

- **Up-to-date Information**: The status and details of individuals are always updated and synchronized.
- **Advanced Filtering**: Filter individuals by various criteria such as first name, last name, city of residence, status (returned/kidnapped/all), and age.
- **Time Counter**: Displays the number of days passed since October 7, 2023, to keep track of the time elapsed.
- **Status Summary**: Provides a summary of the current statuses (kidnapped/returned).
- **Status Color Coding**: Each card's background is colored according to the status (red for kidnapped, green for returned).

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/kroi4/BringThemHome.git
   cd BringThemHome
   ```

2. Open the project in Android Studio.

3. Build and run the project on an Android device or emulator.

## Download

You can download the latest APK from the following link:
[Download APK](https://drive.google.com/file/d/1_XGEF2490HsFdpQvW9BlE0iBxNthSu-T/view?usp=drive_link)

## Project Structure

- **app**: The main application code, including Activity and Fragment files.
- **gradle/wrapper**: Gradle files for project management.
- **.idea**: IntelliJ IDEA/Android Studio configuration files.

## Architecture

The project is built using the MVVM (Model-View-ViewModel) architecture pattern. This ensures a clean separation of concerns and allows for easier testing and maintenance.

- **Model**: Represents the data and business logic of the app. It includes the data classes, database setup, and API interfaces.
- **View**: The UI layer that displays the data to the user. This includes Activities, Fragments, and Adapters.
- **ViewModel**: Acts as a bridge between the Model and the View. It holds the data and state of the UI and handles the logic to interact with the Model.

## User Interface

- **Main Screen**: The app's main screen is the kidnapped individuals' screen. Swiping left (or right, depending on the device language) will navigate to the returned individuals' screen. Another swipe will take you to the search screen, where all individuals are displayed and can be filtered based on various criteria.
- **Scrolling**: You can scroll up and down on each screen to display more cards.
- **Card Interaction**: Tapping on a card will display the details of the individual, allowing you to scroll down to read all the information. Long-pressing a card will copy the individual's details to the smartphone's clipboard.

## Demo

Watch the demo video to see BringThemHome in action:

[![BringThemHome Demo](https://img.youtube.com/vi/yydZU4BZFRY/0.jpg)](https://youtu.be/yydZU4BZFRY)

## Screenshots

<img src="https://github.com/user-attachments/assets/cf6a0864-ff5e-4328-8fa0-a5706d27a53c" width="400"/>
<img src="https://github.com/user-attachments/assets/3f1d0559-f5e9-4944-a6ff-4b0ad21df21e" width="400"/>
<img src="https://github.com/user-attachments/assets/b10a18fe-a3ed-46d4-b7c2-24e5fc121bc0" width="400"/>
<img src="https://github.com/user-attachments/assets/0cc416f1-d573-40cb-920d-be75e4dcf120" width="400"/>

## Contribution

Contributions to the development of the app are welcome.

## Personal Note

We pray for the physical and mental well-being of the kidnapped individuals, and most importantly, for their swift and safe return to their families.
