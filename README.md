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

## Phase 4: Task 2
Fri Dec 02 17:38:52 PST 2022
Password of type ALPHABETIC and content cqmscv created

Fri Dec 02 17:38:59 PST 2022
New guess added: abceef

Fri Dec 02 17:38:59 PST 2022
Element: a0 changes colour to RED

Fri Dec 02 17:38:59 PST 2022
Element: b1 changes colour to RED

Fri Dec 02 17:38:59 PST 2022
Element: c2 changes colour to YELLOW

Fri Dec 02 17:38:59 PST 2022
Element: e3 changes colour to RED

Fri Dec 02 17:38:59 PST 2022
Element: e4 changes colour to RED

Fri Dec 02 17:38:59 PST 2022
Element: f5 changes colour to RED

Fri Dec 02 17:38:59 PST 2022
Guess: abceef added to past guesses

Fri Dec 02 17:39:03 PST 2022
Password of type NUMERIC and content 637314 created

Fri Dec 02 17:39:03 PST 2022
Game progress saved

# Phase 4: Part 3
- The biggest issue I noticed is that the PasswordGamePanel (PGP) class is not very cohesive - it has functions to display a graphical ui, as well as saving progress, and keeping a list of Guesses
- I would refactor the Password and PGP classes so that Password would have a list field of type Sequence instead of PGP having a list field of type Guess
- This would allow PGP to not have to rely on Guess or be doing any work outside displaying the game panel
- The save and load functions could now be moved from the PGP and PasswordAppGUI classes respectively to the Password class which would again let these classes focus on displaying graphics
- Additionally, after making this change the Sequence, Password, and Guess classes would somewhat follow the composite design pattern - where Sequence is the component, Password is the composite, and Guess is the leaf
- Although it doesn't necessarily make sense that a password could have both guesses and other passwords associated with it within the context of my current application, I think it could be useful for future implementations