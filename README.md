# GymRoutineDagger
App to keep track of your gym routine. Room for local data storage.

## Screenshots
<p>
</p>

## Installation
Git clone the repository, import project in Android Studio, and run on an android device.

## Architecture Overview
This application makes use of the MVVM architectural pattern. 
Our project follows: Activity -> ViewModel -> Repository -> Remote Data Source -> Retrofit -> Webservice.
LiveData is retrieved from the Remote Data Source into our repository, then it trickles down all the way to our ViewModel. The activities do not hold data.

## External Dependencies
- [Dagger2](https://dagger.dev/): Framework for compile-time dependency injection.
- [Room](https://developer.android.com/training/data-storage/room): Used to setup a local data storage.
- [RxJava](https://github.com/ReactiveX/RxJava): Asynchronous calls to interact with data storage.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel)

## Tests

## License
© [Yussef Saidi](https://yussefsaidi.me/)
