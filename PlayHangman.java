//import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PlayHangman {

    public static void playHangman(Scanner input) {
        System.out.println("\nWELCOME TO HANGMAN!!\n");
        Random random = new Random();
        ArrayList<String> wordList = new ArrayList<>();

        //load words into the ArrayList
        try (Scanner fileScanner = new Scanner(new File("words.txt"))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim().toLowerCase();
                if (!word.isEmpty()) {
                    wordList.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nError: words.txt file not found.");
            return;
        }

        if (wordList.isEmpty()) {
            System.out.println("\nThe words.txt file is empty.");
            return;
        }            

        boolean keepPlaying = true;

        System.out.println("\nWelcome to Hangman!");

        while (keepPlaying) {
          String chosenWord = wordList.get(random.nextInt(wordList.size()));

          //initialize array for guess
          char[] guessWord = new char[chosenWord.length()];
          Arrays.fill(guessWord, '?');
          
          int incorrectGuesses = 0;
          final int MAX_INCORRECT_GUESSES = 6;
          ArrayList<Character> guessedLetters = new ArrayList<>();
          
          while(incorrectGuesses < MAX_INCORRECT_GUESSES && !String.valueOf(guessWord).equals(chosenWord)){
            System.out.println("\nWord: " + String.valueOf(guessWord));
            System.out.println("\nGuesses left: " + (MAX_INCORRECT_GUESSES - incorrectGuesses));
            System.out.println("\nGuessed letters: " + guessedLetters);
            System.out.print("\nEnter a letter: ");

            char guess = input.next().toLowerCase().charAt(0);

            if(guessedLetters.contains(guess)){
                System.out.println("\nLetter has already been used for a guess.");
                continue;
            }

            guessedLetters.add(guess);

            if(chosenWord.indexOf(guess) >= 0){
                for(int i = 0; i < chosenWord.length(); i++){
                    if(chosenWord.charAt(i) == guess){
                        guessWord[i] = guess;
                    }
                }

                System.out.println("\nYou guessed correctly, nice job!");
            }

            else{
                incorrectGuesses++;
                System.out.println("\nIncorrect guess.");
            }

          }

          if(String.valueOf(guessWord).equals(chosenWord)){
            System.out.println("\n!!You got it!! The word was: " + chosenWord);
          }
          else{
            System.out.println("\nGood try, but not quite. The word you were looking for was: " + chosenWord);
          }
            
          String again = "";
          while (true) {
                System.out.print("\nWould you like to play again? (Y/N): ");
                again = input.nextLine().trim().toUpperCase();
                if (again.equals("Y") || again.equals("N")) {
                    break; // valid input
                }
                System.out.println("\nInvalid choice. Please enter Y or N.");
            }
            keepPlaying = again.equals("Y");
        
        }

        System.out.println("\nThanks for playing Hangman!");
    }
}