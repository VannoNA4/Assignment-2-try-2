import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n\nWELCOME TO NICK'S GAMES!!\n");
        Scanner scanInput = new Scanner(System.in);
        char choice;
        choice = menu(scanInput);
        
        while (choice != 'Q'){
           switch(choice){
            case 'L':
                System.out.println("\nEntering Lottery!");
                Games.lotteryGame(scanInput);
                break;
            case 'C':
                System.out.println("\nEntering Craps!");
                Games.playCraps(scanInput);
                break;
            case 'S':
                System.out.println("\nEntering Scraps!");
                Games.playScraps(scanInput);
                break;
            case 'R':
                System.out.println("\nEntering Rock, Paper, Scissors!");
                Games.playRockPaperScissors(scanInput);
                break;
            case 'E':
                System.out.println("\nEntering Rock, Paper, Scissors, Spock!");
                Games.playRockPaperScissorsSpock(scanInput);
                break;
            case 'B':
                System.out.println("\nEntering Blackjack!");
                PlayBlackjack.playBlackjack(scanInput);
                break;
            case 'H':
                System.out.println("\nEntering Hangman!");
                PlayHangman.playHangman(scanInput);
                break;
            default:
                System.out.println("\nInvalid input. Choose again.");

           }
          
           choice = menu(scanInput);
           
        
        }
        System.out.println("\nThanks for playing!");

        scanInput.close();

    }

    public static char menu(Scanner input){
        char choice = 'Z';
        
       System.out.println("\nL   Lottery");
        System.out.println("C   Craps");
        System.out.println("S   Scraps");
        System.out.println("R   Rock, Paper, Scissors");
        System.out.println("E   Rock, Paper, Scissors, Spock");
        System.out.println("B   Blackjack");
        System.out.println("H   Hangman");
        System.out.println("Q   Quit");
        System.out.print("\nChoose your game: \n");

        String inpuString = input.nextLine().toUpperCase();

        if(inpuString.length() > 0){
            choice = inpuString.charAt(0);

        }

 
        return choice;
    }
}

