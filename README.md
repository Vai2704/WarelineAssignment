# Wareline Assignment

This is an Android application that fetches images from the Pexels API and displays them in a RecyclerView using Glide for image loading and caching.
This project is developed on the latest Android version and supports compile SDK 34. 

## Features

- Fetches images from the Pexels API.
- Displays images in a grid using RecyclerView.
- Implements infinite scrolling to load more images as the user scrolls.
- Uses Glide for image loading and caching (both memory and disk).

## Prerequisites

- Android Studio
- Java JDK
- A Pexels API key

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Vai2704/WarelineAssignment.git
    ```
2. Open the project in Android Studio.

3. Add your Pexels API key in `MainActivity.java`:
    
    ```java
    params.put("Authorization", "YOUR_PEXELS_API_KEY");
    ```

5. Sync the project to resolve dependencies.

## Usage

- Run the application on an Android device or emulator.
- Scroll through the images. More images will load as you reach the bottom.

## Dependencies

- [Glide](https://github.com/bumptech/glide) for image loading and caching.
- [Volley](https://developer.android.com/training/volley) for network requests.

Add these dependencies in your `build.gradle` file:
```gradle
implementation ("com.github.bumptech.glide:glide:4.16.0")
annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
implementation ("com.android.volley:volley:1.2.1")
```


## Contributing
- Feel free to fork this repository and contribute by submitting a pull request.
