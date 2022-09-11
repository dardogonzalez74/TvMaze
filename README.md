
Features included in this app
=============================

This application contains all mandatory features:
- List all of the series contained in the API used by the paging scheme provided by the API
- Allow users to search series by name
- The listing and search views must show at least the name and poster image of the series
- After clicking on a series, the application should show the details of the series
- After clicking on an episode, the application should show the episode’s information


This application contains this optional features:
- Allow the user to save a series as a favorite
- Allow the user to delete a series from the favorites list
- Allow the user to browse their favorite series in alphabetical order, and click on one to see its details



How the app was design
======================

When the user opens the app, he/she will see a bottom navigation with this functionality:
- First tob: a paged list of all series
- Second tab: a place to do searches
- Third tab: the list of favorites series

One thing that I hate about streaming apps like Netflix, Star+, HBO, etc. is that when
you are in one tab, scrolling vertically to a group and horizontally to a certain movie 
in it, and then you switch to another tab, the state of the first one is sometimes lost.
When you go back to the first one, you need to scroll again vertically and horizontally
to the movie you were seeing.

To overcome this situation, I decided to keep the three fragments alive and the switching
between them is done by making the appropriate one visible.



Project structure
=================

The project is designed in layers. Each layer has a certain responsibility, and the classes that
conforms each one have minimum logic and a very specific goal. This way the project results in a 
clear and fully understandable code, and suitable for implementing a comprehensive set of unit
tests with a high rate of coverage.

The project is fully based on coroutines and depends on this libraries:
- Retrofit for networking
- Room for database
- Moshi for serialization
- Paging3 for paged lists
- Glide for image managing
- Material for user interface
- Koin for dependency injection
- Mockito for unit testing

The user interface layer
------------------------
This layer is implemented using the MVVM pattern. 
The app has only one activity, called MainActivity, that hosts three fragments, one for each tab:
- ListFragment
- SearchFragment
- FavoritesFragment

The activity and the three fragments holds a shared view model, so the information you see in 
them are fully synced.

The rest of the UI consists of these two fragments
- ShowDetailFragment: shows a series details and its episodes, and works with its own view model
- EpisodeFragment: shows the details of an episode and doesn't need a view model.

The view models interacts with the lower layers through use cases.

The use cases layer
-------------------
Each action that the user does in this app is wrapped in a use case. 

Each one represents a unique action. This way, it has only one responsibility and its logic is
simplified to a minimum extent. This allows us to write a comprehensive set of unit tests in a
sensitive part of the application.

The use cases interacts with the lower layers through repositories.

The repositories layer
----------------------
Each repository is entity-oriented. This means that any action that needs to be done 
with a particular entity is achieved through the corresponding repository. 
It exposes the what-to-do to the upper layers and hides the how-it-is-done.

The repositories depend on the app's most lower layers:
- Network: for retrieving the series list, the episodes, and for doing the searches
- Database: for storing the favorites series
- Cache: for caching the list of episodes of the viewed series



Unit testing
============

I've written this code in a way that it could be fully testable through unit test.

Writing comprehensive and high coverage unit tests is very time consuming. It's take
more time to write unit tests than to write the actual code.

Considering:
- Unit tests takes a lot of time to write.
- Unit testing was not a mandatory part of the assessment.
- I had limited time to complete the project.
- I prioritized the optional functionality of favorites that unit test.
- I didn't know in advanced if I would have time to write them.

Resulted in:
- I didn't write all the testable classes through interfaces needed for the mocking part  
  of unit testing, because it would have taken more time of coding.
- Once I finished the favorites functionality, I had some time left.
- This time left was not enough to write all test cases.

So I decided to:
- Take one class of each layer (view models, use cases, repositories and cache) as an
  example of how a unit test is written in each one.
- Separate the interface and the implementation for mocking purposes only for them.
- Write their test cases


Important note
==============

- I created this project on September 7. At that time the stable version of core-ktx
  library was 1.8.0 and I developed the app using it.
  Today (September 11), when I was reviewing the code, I found out that a new version
  (1.9.0) was released the same day. After updating it, a lot of deprecated warnings
  appeared. Because I don't have time left to review this changes and test again the
  whole application, I decided to go back to the previous version. That's why if you
  open the build.gradle file you will find it as outdated.

- The app icon (shown also in the launch screen) and the app image shown at the top 
  of the application when running are different because I'm not a graphic designer
  and I didn't find an image of Tv Maze that suited well in both cases, but in a
  real app they should be the same.




















