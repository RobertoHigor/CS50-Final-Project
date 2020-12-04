# About The Project

Anki Dictionary Creator is a web application built with Spring, using WebClient as a REST Client and Thymeleaf. The goal of the project was to make a easier way to get a definition to an English Word and create a Anki.

![Project Diagram](src\main\resources\static\img\project_diagram.png)

This was made using Merrian-Webster dictionary API, to get the definitions of a given word, and Ankiconnect to expose Anki as a API.

# How to use

The first step is setting up Anki's remote address.
![Setting up Anki's address](src\main\resources\static\img\project_url.png)

Then, the use is redirected to a page where He can search a word definition. After sending a word, the application will query the Dictionary API and return each possible definition for the world, including It's word class e.g. Noun, Verb etc.

![Card definitions](src\main\resources\static\img\project_definitions.png)

Finally, after editing the card and choosing the deck which to send the word, the user can see that it was added to Anki, using It's word class as a tag.

![Showing the card on Anki](src\main\resources\static\img\project_anki.jpg)