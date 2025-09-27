import java.util.Random;
import java.util.Scanner;

public class Games {
   
    public static void lotteryGame(Scanner input) {
        System.out.println("\nWELCOME TO THE LOTTERY!!\n");

        // Create random number generator and scanner
        Random rand = new Random();
        boolean keepPlaying = true;

        while(keepPlaying){

        
        
        // Step 1: Generate a random two-digit lottery number (10 to 99)
        //         && set the 2 digits of lottery number 
        int lotto = rand.nextInt(90) + 10;
        // Step 2: Prompt the user to enter a two-digit number
        // Step 3:Ensure it's a valid two-digit number
        int user;
        while(true){
            System.out.println("\nPlease enter a 2 digit number: \n");
            user = input.nextInt();
            input.nextLine();
            if(user >= 10 && user <= 99){
                break;
            }

            System.out.println("\nInvalid number. Please choose a two digit number.\n");

        }

        // Step 4: get the 2 digits of userGuess
            int lotto1 = lotto/10;
            int lotto2 = lotto%10;
            int user1 = user/10;
            int user2 = user%10;
        // Step 5: Display lottery number
        System.out.println("\nThe lottery number is: " + lotto);

        // Step 6: Check for types of matches & print results
        // exact match
        if(lotto == user){
                System.out.println("\nyou won big!! $10,000!!\n");
            }
        // Step 7: Check for all digits match (in different order)
         else if (user1 == lotto2 && user2 == lotto1) {
                System.out.println("\nYou won $3000\n");
            }
        // Step 8: Check for one digit match
        else if (user1 == lotto1 || user1 == lotto2 || user2 == lotto1 || user2 == lotto2){
                System.out.println("\nYou won $1000\n");
            }

        // Step 9: No match
        else {
                System.out.println("\nSorry, you did not win today.\n");
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

        System.out.println("Thanks for playing the Lottery!");

    }

    public static void playCraps(Scanner input) {
        System.out.println("\nWELCOME TO CRAPS!!\n");
        double netWorth = 50;
        Random rand = new Random();
        boolean playAgain = true;

        while(netWorth > 0 && playAgain){
            System.out.println("Your net worth is: " + netWorth + ".\n");
        
        double userBet;
        while (true){
            System.out.print("\nPlease enter a valid bet: \n");
            userBet = input.nextDouble();
            input.nextLine();
            if(userBet > 0 && userBet <= netWorth){
                break;
            }
            System.out.println("\nYour bet was invalid. Enter a valid bet.");
        }
        int roll = rollDice(rand);
        
        if (roll == 7 || roll == 11) {
            System.out.println("\nYou win!");
            netWorth += userBet;

        }
            else if (roll == 2 || roll == 3 || roll == 12) {
                System.out.println("\nYou lose!");
                netWorth -= userBet;

            }
            else{
                int point = roll;
                System.out.println("\nPoint is set to: " + point);
                roll = rollDice(rand);
                while (roll != 7 && roll != point){
                roll = rollDice(rand);
                }
                if (roll == 7) {
                    System.out.println("\nYou lost!");
                    netWorth -= userBet;
                }
                else {
                    System.out.println("\nYou win!");
                    netWorth += userBet;
                }
            }
        if(netWorth <= 0){
            System.out.println("\nYour funds are insufficient, thanks for playing.");
            break;
        }
        String choice = "";
        while (true) {
            System.out.print("\nYour net worth is: $" + netWorth + "Do you want to roll again? (Y/N): ");
            choice = input.nextLine().trim().toUpperCase();
            if (choice.equals("Y") || choice.equals("N")) {
                break; // valid input, exit loop
            }
            System.out.println("\nInvalid choice. Please enter Y or N.");
        }
        playAgain = choice.equals("Y");
    }
}


    
    

    // Helper method to roll two six-sided dice
    private static int rollDice(Random rand) {
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        int total = die1 + die2;
        System.out.println("\nYou rolled: " + die1 + " + " + die2 + " = " + total);
        return total;
    }


    public static void playScraps(Scanner input) {
        
        System.out.println("\nWELCOME TO SCRAPS!!\n");

        Random random = new Random();
        double worth = 50.00;
        boolean playAgain = true;

        while(worth > 0 && playAgain){
            System.out.println("Your net worth is: " + worth + ".\n");
        

        double userBet;
        while (true){
            System.out.println("Please enter your bet. (You have $" + worth + "): ");
            userBet = input.nextDouble();
            input.nextLine();
            if(userBet > 0 && userBet <= worth){
                break;
            }
            System.out.println("Invalid bet entered, please try again.");
        }
        int[] dice = rollThreeDice(random);
        int diceSum = dice[0] + dice[1] + dice[2];
        boolean hasEight = dice[0] == 8 || dice[1] == 8 || dice[2] == 8;
        boolean hasOne = dice[0] == 1 || dice[1] == 1 || dice[2] == 1;

        System.out.println("\nYou rolled: " + dice[0] + " , " + dice[1] + " , " + dice[2] + " (Total: " + diceSum + ")");

        if(hasEight || diceSum == 9 || diceSum == 10 || diceSum == 14) {
            System.out.println("\nYou win!");
            worth += userBet;
        }
        else if(hasOne || diceSum == 8 || diceSum == 20 || diceSum == 23 || diceSum == 24){
            System.out.println("\nYou lose!");
            worth -= userBet;
        }
        else{
            int point = diceSum;
            System.out.println("\nPoint is set to: " + point + ". Rolling until point is hit (win), or until either 8 or a sum of 15 is hit (lose) .");

            while(diceSum != point && !hasEight && diceSum != 15){
                dice = rollThreeDice(random);
                diceSum = dice[0] + dice[1] + dice[2];
                hasEight = dice[0] == 8 || dice[1] == 8 || dice[2] == 8;

                System.out.println("\nYou rolled: " + dice[0] + " , " + dice[1] + " , " + dice[2] + " (Total: " + diceSum + ")");
            }
            if(diceSum == point){
                System.out.println("\nYou hit the point! You win!");
                worth += userBet;
            }
            else if(hasEight || diceSum == 15){
                System.out.println("\nYou rolled an 8 or a sum of 15. You lose!");
                worth -= userBet;
            }
        }
            if(worth <= 0){
                System.out.println("\nInsufficient funds. Thank you for playing.");
                break;
            }

            System.out.println("\nYour new worth is $" + worth + ".");


            String choice = "";
            while (true) {
                System.out.print("\nDo you want to play again? (Y/N): ");
                choice = input.nextLine().trim().toUpperCase();
                if (choice.equals("Y") || choice.equals("N")) {
                    break; // exit loop if valid
                }
                System.out.println("\nInvalid choice. Please enter Y or N.");
            }
            playAgain = choice.equals("Y");

            
        }
        


        

        System.out.println("\nThanks for playing! You ended with $" + worth);

    }
    
    private static int[] rollThreeDice(Random rand){
        int die1 = rand.nextInt(8) + 1;
        int die2 = rand.nextInt(8) + 1;
        int die3 = rand.nextInt(8) + 1;
        return new int[] {die1, die2, die3};
    }

    public static void playRockPaperScissors(Scanner input){
        System.out.println("\nWelcome to Rock, Paper, Scissors!");

        Random rand = new Random();
        boolean keepPlaying = true;

        while(keepPlaying){
            System.out.println("\nEnter Rock, Paper, or Scissors: ");
            String userChoice = input.nextLine().toLowerCase();

            int user = -1;
            if(userChoice.equals("rock")){
                user = 0;
            }
            else if(userChoice.equals("paper")){
                user = 1;
            }
            else if(userChoice.equals("scissors")){
                user = 2;
            }
            else{
                System.out.println("\nInvalid input, try again.");
                continue;
            }
            
            int oppChoice = rand.nextInt(3);
            String[] names = {"rock", "paper", "scissors"};
            System.out.println("\nOpponent chose: " + names[oppChoice]);

            if(user == oppChoice){
                System.out.println("\nThe game is a draw.");
            }
            else if((user == 0 && oppChoice == 2) || (user == 1 && oppChoice == 0) || (user == 2 && oppChoice == 1)){
                System.out.println("\n!!You've won!!");
            }
            else{
                System.out.println("\nYou lose.");
            }

            String again = "";
            while (true) {
                System.out.print("\nWould you like to play again? (Y/N): ");
                again = input.nextLine().trim().toUpperCase();
                if (again.equals("Y") || again.equals("N")) {
                    break; // valid input, exit loop
                }
                System.out.println("\nInvalid choice. Please enter Y or N.");
            }
            keepPlaying = again.equals("Y");


        }
    }

    public static void playRockPaperScissorsSpock(Scanner input){
        System.out.println("\nWelcome to Rock,Paper,Scissors,Spock!");

        Random rand = new Random();
        boolean keepPlaying = true;

        while(keepPlaying){
            System.out.println("\nChoose rock, paper, scissors, or spock: ");
            String userChoice = input.nextLine().toLowerCase();

            int user = -1;
            if(userChoice.equals("rock")){
                user = 0;
            }
            else if(userChoice.equals("paper")){
                user = 1;
            }
            else if(userChoice.equals("scissors")){
                user = 2;
            }
            else if(userChoice.equals("spock")){
                user = 3;
            }
            else{
                System.out.println("\nInvalid input, try again.");
                continue; // skip rest of loop
            }

            int oppChoice = rand.nextInt(4);
            String names[] = {"rock", "paper", "scissors", "spock"};
            System.out.println("\nOpponent chose: " + names[oppChoice]);

            if(user == oppChoice){
                System.out.println("\nThe game is a draw.");
            }
            else if((user == 0 && oppChoice == 2) || (user == 1 && oppChoice == 0) || (user == 2 && oppChoice == 1) ||
                    (user == 1 && oppChoice == 3) || (user == 3 && oppChoice == 0) || (user == 3 && oppChoice == 2)){
                System.out.println("\n!!You won!!");
            }
            else{
                System.out.println("\nYou lose.");
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
}
    

}
 

