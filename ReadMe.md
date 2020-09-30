About the Project

Project is using an API to load information to a homepage.

Architecture:
1. MVVM Architecture with ViewState
2. Repository pattern is used for fetching data

Libraries Used:
1. Lifecycle : For ViewModel and LiveData
2. Glide: For ImageLoading
3. Retrofit: For API Calling
4. GsonConverter: To convert response into kotlin object
5. RxJava: To do the API call on io thread

Future Improvements:
1. Unit testing with Junit and MockK
2. RxJava could be replaced with Coroutine