package model;


// Represents a password for the user to guess
public class AlphaPassword extends Password {
    //EFFECTS: creates a new password
    //         charGuessed set to 0
    //         charNotGuessed set to LENGTH
    //         isGuessed set to false
    // random char generator from: https://programming.guide/java/generate-random-character.html
    public AlphaPassword() {
        super();
        for (int i = 0; i < LENGTH; i++) {
            Character randChar = (char) (rand.nextInt(26) + 'a');
            content.add(randChar);
        }
    }
}
