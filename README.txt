# 450HackAlong

Features implemented:
  login with email address and password (and verification)
  register by clicking the register button to go to the edit profile screen
  create account with verification by either logging in or creating account, the user is sent to the inital app event page
  List of events page using cards presented. user can click and open a new fragment for event details
  List of people / users presented. user can select a card to get details about user 
  Setings page allows users to log out of the application
  App info allows users to view information about developers and the app

Suggestions from Phase 1:
  we updated the login page to allow users to hide or show their passwords
  We didnt implement the sharing of the login and registration screen. We had a suggestion to have it be on screen that once a user           presses the register button, a new fragment is pre populated with the text user enter on a login fragment 
  We never got around to solve the on brack pressed dont inflate the register page issue
  We did implement confimation toast notifications on succesful register and login

Missing Features
  The Search feature for events or people
  The intrests tags for events and users.
 
Saving Data
  Data persisted through the use of the CSS database and the SharedPreferences file.
  We used the shared Preferences file to store the loged in user data locally.
  Data for events and othe people was stored remotely on the database and was fetched on Events fragment interaction.

WebService
   As we had previously talked to the professor prior, we have a script that runs on the CSS server to update the database and input the values there. We made this choice to offload some data costs of the users onto the server. 
   The original web service that we were going to use was dead and nolonger had usful data <https://github.com/mikachoow21/mlh-api>
   We pivoted quickly with another API to populate our database <http://www.hackathonwatch.com/>

Meeting Notes:
   https://docs.google.com/document/d/1bQ1wXr1nGE9wGlvt4Jho8RLK-gLFOTkDM-Dl8LEp6C4/edit
