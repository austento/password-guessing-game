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


