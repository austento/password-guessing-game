# My Personal Project

## Proposal
I would like to create a game application that would create a randomly generated "password" each round and then have the
user continually guess the "password" until it matches - and a score is generated based on how many guesses it took. 
The "password" could be a sequence of characters, numbers, or colours of a certain length. After a user makes a guess, the application 
would provide some representation of a hint that tells the user how close of a match the guess was to the real "password". 
For example, if the "password" were **abcde** and the user guesses *ibceg* the response from the application would be 
"3 letters are correct and 2 are in the correct position". Ideally I would also like to add a game version where 
the password is a recognized word in the english dictionary and each of the user's guesses must also be a word.

This application would mostly be for entertainment purposes, but it also helps to practice logical inference skills. I
am interested in exploring how to randomly generate information using Java, and understanding how to specify the random 
information I am generating (i.e. generating a six character sequence of numbers vs a six-letter word). I am also interested
in understanding how I am matching each individual part of the sequence to the user's guess and then translating that to
a hint that is helpful to the user.

## User Stories
- As a user, I want to generate a password to guess
- As a user, I want to be able to add a guess to my list of guesses
- As a user, I want to receive a hint after each of my guesses that helps me to guess the password
- As a user, I want to be able to view all of my previous guesses and hints while I am still guessing
- As a user, I want to receive a score after I guess the password correctly

- As a user, I want to choose between kinds of passwords to generate (alpha, numeric, or both)

- As a user, I want to be able to save my progress on guessing a password
- As a user, I want to be able to reload my progress on guessing an existing password

## Instructions for Grader
1. Run program from main
2. Choose "start new game" to start a new game - I suggest to choose the "numeric" password type for simplicity but feel free to pick any type
3. Once the game window opens, click the "menu" button and click "make a guess"
4. You can generate the first required event by entering a guess into the text field and pressing ok - you will see your guess appear in the game window
5. Click "menu" again and click "check accuracy" - this will generate the second required event
6. My visual component appears when you guess the password correctly. 
7. You can save your progress guessing a password by clicking "menu" and then clicking "save your progress"
8. You can load your previous progress from the "load game" button located in the main menu - to return to the main menu from an active game
click "menu" and then click "return to main menu"