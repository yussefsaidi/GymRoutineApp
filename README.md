# GymRoutine
App to keep track of your gym routine.
Start by picking from my 3 templates, and then modify your routine according to your personal preference.
Add, Remove, or update Reps and Sets for your exercises.

## Screenshots
<p>
  <img src="/screenshots/Screenshot_1589407112.png" width="270" height="450"/>
  <img src="/screenshots/Screenshot_1589407130.png" width="270" height="450"/>
  <img src="/screenshots/Screenshot_1589407134.png" width="270" height="450"/>
</p>

## Installation
Git clone the repository, import project in Android Studio, and run on an android device.

## Architecture Overview
This application makes use of the MVVM architectural pattern. 

## Future AddOns
This application will make use of Retrofit2 and a third party API to show Users a list of exercises ordered by category to pick from and add to their routines.

## External Dependencies
- [Dagger2](https://dagger.dev/): Framework for compile-time dependency injection.
- [Room](https://developer.android.com/training/data-storage/room): Used to setup a local data storage.
- [RxJava](https://github.com/ReactiveX/RxJava): Asynchronous calls to interact with data storage.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel)

## Tests

## License
© [Yussef Saidi](https://yussefsaidi.me/)
