# 450HackAlong

Features implemented:
  Login with email address and password (and verification)
  Register by clicking the register button to go to the edit profile screen
  Create account with verification by either logging in or creating account, the user is sent to the initial app event page
  List of events page using cards presented. user can click and open a new fragment for event details
  List of people / users presented. user can select a card to get details about user 
  Settings page allows users to log out of the application
  App info allows users to view information about developers and the app

Suggestions from Phase 1:
  We updated the login page to allow users to hide or show their passwords
  We didn’t implement the sharing of the login and registration screen. We had a suggestion to have it be on screen that once a user           presses the register button, a new fragment is pre-populated with the text user enter on a login fragment 
  We never got around to solve the on back pressed to not inflate the register page issue
  We did implement confirmation toast notifications on successful register and login

Missing Features
  The Search feature for events or people
  The interests tag for events and users.
  
Future Features
  Set / Search by location
  Following / Saving events
  Notification of events
  Follow people
  Messaging people
  Different Skin settings
  News Feed section
  Profile Images
  Event images
    

Known Issues
  OnBackPress the navigation drawer has incorrect selection
  The update profile sometimes throws a toast notification error but still saves data (only happened twice)  
 
Saving Data
  Data persisted through the use of the CSS database and the SharedPreferences file.
  We used the shared Preferences file to store the logged in user data locally.
  Data for events and other people was stored remotely on the database and was fetched on Events fragment interaction.

WebService
  As we had previously talked to the professor prior, we have a script that runs on the CSS server to update the database and input the      values there. We made this choice to offload some data costs of the users onto the server. 
  The original web service that we were going to use was dead and no longer had useful data <https://github.com/mikachoow21/mlh-api>
  We pivoted quickly with another API to populate our database <http://www.hackathonwatch.com/>

Meeting Notes:
  https://docs.google.com/document/d/1bQ1wXr1nGE9wGlvt4Jho8RLK-gLFOTkDM-Dl8LEp6C4/edit

