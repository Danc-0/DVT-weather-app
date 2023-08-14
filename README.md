# DVT-weather-app

This is a Android App Developed for DVT Mobile Developer Test

It's a modern Weather App built with Open Weather APIs with the following key features.

### Features

###### Forecast

The Weather Forecast is to provide timely information about Weather based on Current Location of the
user, and also with several options.
The following are the key responsibilities,

* Get Forecast Current Location.
* Add New Location.
* Save Favourite Location
* View List of Favourite Locations
* Detailed information for a specific Location
* Offline Mode.

### Requirements
* Weather forecast must be based on the user’s current location.
* The background image changes depending on the type of weather (Cloudy, Sunny and Rainy).
* Saving different weather locations as favourites
* Ability to view a list of favourites
* Getting extra information about a specific location
* Using GOOGLE PLACES API [Places](https://developers.google.com/places/web-service/intro)
* Offline capability and show time the app or weather were last updated.
* Users ability to view saved locations on a map
* Users ability to access a map and see all the weather locations they have saved as favourites and also where they are now.

## Project Characteristics
The project uses best practices tools and solutions.
* Kotlin
* MVVM architecture.
* Clean architecure
* Architecture Components
* Dependency Injection
* APIs
  * https://openweathermap.org/forecast5
 
* Technologies/Libraries Used
  * [Jetpack](https://developer.android.com/jetpack)🚀 - Libraries that help follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices so that developers can focus on the code they care about.
  * [Dagger-Hilt](https://dagger.dev/hilt/) - For dependency injection
  * [Compose Navigation](https://developer.android.com/guide/navigation/navigation-getting-started](https://developer.android.com/jetpack/compose/navigation)
  * [Coroutines](https://developer.android.com/kotlin/coroutines?gclid=CjwKCAjwk_WVBhBZEiwAUHQCmdx8rjojm7dxpQ2EGOYQydzDN3DbqnzZBC0nq-GGzvdmCvnnFYvgFRoCyPEQAvD_BwE&gclsrc=aw.ds) - Is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously
  * [Flow](https://developer.android.com/kotlin/flow) - Is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
  * [Room](https://developer.android.com/training/data-storage/room) - Provide an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite
  * [Retrofit](https://square.github.io/retrofit/) - Class through which API Interfaces turn callable objects
  * [Material UI]()
  * [Google Location API](https://developers.google.com/maps/documentation/geolocation/overview)
  * [Lottie](https://lottiefiles.com/featured) - For Animations
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAjwjJmIBhA4EiwAQdCbxrvUiq3wgakPX8sop8Kp8irusL4bi_9xCnaiZkUJqBzTbOTB2FB4XRoCujoQAvD_BwE&gclsrc=aw.ds) - Manage UI related data in a lifecycle conscious way and act as a channel between use cases and ui
 
## CI/CD
  * [Fastlane](https://docs.fastlane.tools/) -  is the easiest way to automate beta deployments and releases for your Android apps
