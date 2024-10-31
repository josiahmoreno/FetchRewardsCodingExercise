# Description
Native Android App in Kotlin that retrieves and displays data from a sample api.
# Requirements
-   Display all the items grouped by "listId"
-   Sort the results first by "listId" then by "name" when displaying.
-   Filter out any items where "name" is blank or null.
- The final result should be displayed to the user in an easy-to-read list.

# Features
- MVI
- Dependency Integration via Koin
- ViewModels
- Jetpack Compose.
- Retrofit2
- Error handling for network exceptions
- Pull to Refresh and a Snackbar notification to retry fetching
- Modules project structure
- `data`, `domain`, `ui` layer seperation
- Clean Archticture with Use Cases and Repository pattern
- Unit test to verify koin configuration

# Demo

https://github.com/user-attachments/assets/c92e1726-d85b-484f-b4d8-58699c9836ee


### Airplane Mode Test

https://github.com/user-attachments/assets/2fe3faa9-398e-48c1-a4d8-ed2170ec2fe6


#  Future Improvements
- Add a LocalDataSource to the ListRepository for offline use.
  Perhaps Room or SQLite.
- Look into a better way of connecting the `app` module to `:features:list:app` since it took some refactoring to get them working together.
- Setup koin and the FetchListViewModel for Composable Previews
- Think of better names for each of the layer's model classes like ItemDomainModel and `FetchNetworkItem`.
-  Maybe use `groupBy` in the FetchListUseCase instead of `foreach`
- Find an alternative to LaunchedEffect for one time effects like SnackBar or PullToRefresh because their state management was tricky to set up and caused extra recompositions initially.
- Utilize more Unit tests specifically for ViewModel or the UseCase. So far the only one is `ExampleUnitTest.checkKoinModule` which verifies Koin config


# Notes throughout development
Here are my stream of consciousness notes taken during development. Excuse their messiness!

> I haven’t used a libs version toml so that was fun
>
> Start making the modules , Started with app, then app requires for ui module, then
> ui asks for domain, and then domain asks for data
>
>  I started work on UseCases
>
> Added in
> implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
> Added coroutine core
>
> // I wonder I I should trim the dependcies of coroutines later
>  Added domain and data modules
> Refreshed knowledge of up LazyColumn component
>
> Did a first test run, made sure the koin worked
>
> It took a couple of changes of the gradles dependencies to get the
> unit test to work!
>
> Committed work for dummy api
>
> I started forming FetchRewardsDomainModel, I formed it based on how i
> wanted to look with a group id and list of items  
> Functions like mapToUiModel and mapToDomain model were added  
> Fleshed out ItemDomainEntity
>
> I worked on grouping and sorting  part of UseCase
> started forming out the FetchListScreen and the column content, that will be put it another
> file soon.
> Start work on enforcing the null and blank check
>  Did some name changes   corrected listId in Item.kt to id
>
> then i started working on retrofit   
> then i created interface for theapi, swapping out dummy data
> then i started working on using kotlin Result.kt, converting flow to result,
> Cleaned up files and comments  
> Result.kt work required some refactoring
> fixed up koin for remote data source
>
> added in with ItemId sorting
> i worked on the header for Groups
> I worked on the keys for Lazy column
>
> then i added in the networking CoroutineDispatcher for api calls (dispatchers.io)
>
> Added internet permission
> Cleaned up files and comments again
> pull to refresh and snackbar integration
> ran into some problems with material3 version so had to update.
> refreshed LaunchedEffect knowledge to get state management for PullToRefresh and Snackbar to
> work correctly.
> Cleaned up files and comments again again...
> modifications to add feature:list:app to :app and get rid of mainactivity
