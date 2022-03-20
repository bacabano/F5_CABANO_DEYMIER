# F5_CABANO_DEYMIER

***
## Table of Contents
  - [Presentation](#presentation)
  - [Functionnalities](#functionnalities)
  - [Storage and API](#storage-and-api)
  - [Appendix](#appendix)

<a name="pres"></a>
## Presentation
***
Lyriczz is an application which allows you to search and find the lyrics from any song using the lyrics.ovh API (https://lyricsovh.docs.apiary.io/#?ref=apilist.fun). You just need to enter the artist and the name of the song.

***Note that to develop this application, we used the emulator Nexus 5X API 30.***

<a name="func"></a>
## Functionnalities
***
* The first page is the home page :

![Home page](/images_readme/home.png?raw=true "Home page")

* If you click on the "Rechercher" button, you can see the lyrics of your requested song (If you didn't provide the artist or the name of the song, you cannot search the lyriczz and a toast appears to warn you).

![Lyriczz](/images_readme/lyriczz.png?raw=true "Lyriczz")

If the song is not found, you have a error message informing you of this issue.

* You also have access to your search history thanks to the "Recherche récente" button :

![Historic](/images_readme/historique.png?raw=true "Historic")

Every item is clickable and redirect you to the Lyriczz page.

* Finally, you can access to a settings page :
  
![Settings](/images_readme/settings.png?raw=true "Settings")

Here, you can erase your recent searches or activate the dark mode.

![Dark mode](/images_readme/home_darkmode.png?raw=true "Dark mode")

The screenshots of the landscape app can be found in the [appendix](#appendix).


<a name="st&api"></a>
## Storage and API
***
When we search for a song, we check if the lyrics of the song is already in the database. If not, we call the API (https://lyricsovh.docs.apiary.io/#?ref=apilist.fun) and we store it in the database. Otherwise, we use the database and update the historic.
***The application only needs permission to INTERNET in order to work***

<a name="appendix"></a>
## Appendix
***

* Screenshots of the landscape version of the application :
  
![Home page land](/images_readme/home_landscape.png?raw=true "Home page land")
![Lyriczz land](/images_readme/lyriczz_land.png?raw=true "Lyriczz land")
![Historic land](/images_readme/historique_land.png?raw=true "Historic land")
![Settings land](/images_readme/settings_land.png?raw=true "Settings land")