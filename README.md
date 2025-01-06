# Cook Fresh App
This is a simple recipe finding app which fetches recipes from all around the world, and shows you the detailed steps to prepare it.

The frameworks and tools it uses are:
1) Jetpack Compose- UI framework
2) Ktor Client- API fetching
3) Koin- Dependency Inejction Library
4) Firebase and Google Credential manager- Real time Authentication

It implements clean architecture (app implementation is separated into Data, Domain, and Presentation layers per feature), and the app contains two features:
## 1) Auth
It handles real-time authenrication using Firebase and Google Credential Manager. Users can either sign-up using Google accounts or Email and Passowrd. 
After signup, the user can proceed with Sign-In. The user can also Sign-out anytime.

## 2) CookFresh
It handles the core functionality of the app. It displays all recipe categories to search meals from. The meal categories can be clicked to search through the meals.
Each meal can be further searched to see its origin, instructions to prepare it, and its ingredient list. 

## Note:
If cloning the repository, link it with firebase and your own SHA-1 keys for both debug and release variants.
