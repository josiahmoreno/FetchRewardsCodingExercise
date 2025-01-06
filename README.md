
# Description
Native Android App in Kotlin that retrieves and displays data from a sample api.

# Requirements
* Please consume this [JSON feed](https://raw.githubusercontent.com/phunware-services/dev-interview-homework/master/feed.json).  
  * Note: You should do this by performing any necessary network calls in the app.  Do not store this file locally in the app.  
* Display the feed in a master/detail type app  
  * Scrollable view of all events, when tapped leads to a detail view.  
  * Designs \- [Clickable Prototype](http://soltanstudios.com/phunware/codingtest/android/prototype/index.html#/pages/145530220/simulate/sitemap)  
    * Note: back button not working in the prototype. Please use the Sitemap on the left to return to Home view  
* Each Event may or may not have an image associated with it.    
* Display the date of the event using the event’s **timestamp**, which is in GMT, and should be represented in the user’s timezone.  
* While viewing an event:  
  * user can share via SMS and Email.  
    * Note: Sharing via social outlets is not required for this test.   
  * user can place a call with the number associated with the event (design/placement up to you)  
* Support phone and tablet in all orientations.  
* Should work on API level 26 and above.  
* Should not crash when network connection is not available.  
* App Icon and Placeholder Image assets can be found [here](https://github.com/phunware-services/dev-interview-homework/tree/master/Android/assets).

# Features
- MVI
- ListDetailPaneScaffold to handle adaptive layouts like tablets/foldables
- Dependency Integration via Koin
- ViewModel
- Jetpack Compose.
- Retrofit2
- Error handling for network exceptions
- Pull to Refresh and a Snackbar notification to retry fetching
- Modules project structure
- `data`, `domain`, `ui` layer separation
- Clean Architecture with Use Cases and Repository pattern
- Unit test to verify koin configuration
- Tested with Airplane mode to handle network-less scenario

# Challenging Parts
* The adaptive layout library for Tablet was causing some navigation transition weirdness that I had to account for when in Phone/Compact mode. 
* Making sure the Images loaded in the correct aspect ratio even in the weird screen widths like a folded phone.
* Initially had a weird issue where the phone call feature would cause a NPE when navigating to Dialer. Fixed by making EventItemUI parcelable. 

# Future Improvements
- Add a LocalDataSource to the EventsRepository for offline use.
  Perhaps Room or SQLite.
- Look into a better way of connecting the `app` module to `:features:list:app` since it took some refactoring to get them working together.
- Setup koin and the EventsViewModel for Composable Previews
- Find an alternative to LaunchedEffect for one time effects like SnackBar or PullToRefresh because their state management was tricky to set up and caused extra recompositions initially.
- Find a better way to have a "No Event Selected" when using ListDetailPaneScaffold for adaptive layout. Needed a fix to prevent flicker during back navigation.
- Utilize more Unit tests specifically for ViewModel or the UseCase. So far the only one is `ExampleUnitTest.checkKoinModule` which verifies Koin config

# Project Duration
- Worked on and off for about 3 days after New Years

# Possible Discussions
 - There is probably a future discussion about the ellipses requirement for description with the 78 char limit, i think the two line is better and 78 is kinda arbitrary and would look too short.  
 
  - I would to have a discussion about adding the spacing numbers to the mockup or if we had a styling guide I could use that instead of the ol' eyeballs.

# Notes throughout development
Here are my stream of consciousness notes taken during development. Excuse their messiness!

    Added the base url  
    added the get url  
    
    Worked on the eventsample api  
    Created a value class for DateFormatted
      
    works on the spacing a elipisis requirements  
      
    got the styling of list item looking correct,  
      
    added in image loading via coil  
      
    Had to make it so the detail navigation didn't cause flickering  
      
    Looked into how to make LargeAppBar into a Collpasing toolbar, didn't want to use accompanist  
      
    Made the text of detail view look similar to mock styling  
      
    Initially phone dial intent was simply a setting the phone number in dialer, but that didn't use permissions so i wanted to do an actual phone call , added a manifest permission and a runtime permission, these actions could be refactored?  
      
    share intent was interesting because it combined two intents, i wanted it to look more like amazons share intent that shows select recipient instead of new conversation How do we do that?  
      
    added in the app icon  
      
    changed the theme colors to blue and text light blue, thanks to color picker  
      
    Ran into a error when navigating away when phone diler  
      
    worked on getting pull to refresh to respect the scaffodling  
      
    working on place holder images  
      
    there can be discussion about error images or loading indicators  
    
    I didn't see any requirements about a specific error message, so i would asked for some more detail in the Jira Ticket or added it myself if the project has other examples of error images.  
      
    There is probably a discussion for ellipses with the 78 char requirement, i think the two line is better and 78 is kinda arbitrary and would look too short.  
      
    i think i would to have a discussion about adding the spacing to the mockup or if we had a styling guide i would of use that instead of the ol' eyeballs
