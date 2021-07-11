Project Overview
Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids given a period of time with data such as the size, velocity, distance to earth and if they are potentially hazardous. In this project, I am showing will skills such as fetching data from the internet, saving data to a database, and display the data in a clear, compelling UI.

You will need the NEoWs API which is a free, open source API provided by NASA JPL Asteroid team, as they explain it: “Is a RESTful web service for near earth Asteroid information. With NeoWs a user can: search for Asteroids based on their closest approach date to Earth, lookup a specific Asteroid with its NASA JPL small body id, as well as browse the overall data-set.”

The resulting output of the project will be two screens: a Main screen with a list of all the detected asteroids and a Details screen that is going to display the data of that asteroid once it´s selected in the Main screen list. The main screen will also show the NASA image of the day to make the app more striking.

Getting Started
The first step is to get an API Key from NASA. Follow the instructions as listed.

Got to the following URL - https://api.nasa.gov/ :

Fill the required fields, click the Signup button and you will get a API key (the API Key is also going to be sent to your email ).

You will use the API key to send requests to NASA servers to get data about asteroids - to view how it works, scroll down a little more until you see the NeoWs data.

Take your API Key and paste it in the Constants.kt file.

Below are the application Screenshots :

![Screenshot_20210711-140452](https://user-images.githubusercontent.com/7738156/125194384-b374fa80-e26e-11eb-8fb7-cade0b0b379a.jpg)
![Screenshot_20210711-140505](https://user-images.githubusercontent.com/7738156/125194464-12d30a80-e26f-11eb-8514-fa5b29723bfa.jpg)
![Screenshot_20210711-140515](https://user-images.githubusercontent.com/7738156/125194470-1797be80-e26f-11eb-9050-430cc53e53d0.jpg)
![Screenshot_20210711-140538](https://user-images.githubusercontent.com/7738156/125194475-19618200-e26f-11eb-8700-c57285e51c96.jpg)
![Screenshot_20210711-140553](https://user-images.githubusercontent.com/7738156/125194481-22eaea00-e26f-11eb-9689-a919230d209d.jpg)

<img src="https://user-images.githubusercontent.com/7738156/125194384-b374fa80-e26e-11eb-8fb7-cade0b0b379a.jpg" width="250"> | <img src="https://user-images.githubusercontent.com/7738156/125194464-12d30a80-e26f-11eb-8514-fa5b29723bfa.jpg" width="250">
