import java.util.*;
public class PlayBlackjack {
    public static void playBlackjack(Scanner scanner) {
        System.out.println("\nWELCOME TO BLACKJACK!!\n");
        
        boolean keepPlaying = true;

        while (keepPlaying){
             List<Integer> deck = new ArrayList<>();
             // Initialize deck
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }
        Collections.shuffle(deck);
        int deckIndex = 0;

        List<Integer> playerHand = new ArrayList<>();
        List<Integer> dealerHand = new ArrayList<>();

        // Deal initial two cards each
        playerHand.add(deck.get(deckIndex++));
        dealerHand.add(deck.get(deckIndex++));
        playerHand.add(deck.get(deckIndex++));
        dealerHand.add(deck.get(deckIndex++));

        boolean playerBust = false;

         // Player turn
         while(true){
            int playerTotal = getHandValue(playerHand);
            System.out.println("\nPlayer has the following cards:");
            System.out.println(getHandString(playerHand));
            System.out.println("\nTOTAL: " + playerTotal);

            if(playerTotal > 21){
                System.out.println("\nPlayer went over 21. Dealer wins!");
                playerBust = true;
                break;
            }
            System.out.println("\nWould you like to be dealt another card? (Y/N): ");
            String choice = scanner.nextLine().trim().toUpperCase();
            if (choice.equals("Y")){
                playerHand.add(deck.get(deckIndex++));
            }
            else{
                break;
            }

        } 
            // Dealer turn
             boolean dealerBust = false;
             if(!playerBust){
                while(getHandValue(dealerHand) <= 17){
                    dealerHand.add(deck.get(deckIndex++));
                }
                int dealerTotal = getHandValue(dealerHand);
                if(dealerTotal > 21){
                    System.out.println("\nDealer has the following cards:");
                    System.out.println(getHandString(dealerHand));
                    System.out.println("\nTOTAL: " + dealerTotal);
                    System.out.println("\nDealer went over 21. Player wins!");
                    dealerBust = true;
                }
             }

              // Determine winner
              if(!playerBust && !dealerBust){
                int playerTotal = getHandValue(playerHand);
                int dealerTotal = getHandValue(dealerHand);

                System.out.println("\nPlayer has the following cards:");
                System.out.println(getHandString(playerHand));
                System.out.println("\nTOTAL: " + playerTotal);

                System.out.println("\nDealer has the following cards:");
                System.out.println(getHandString(dealerHand));
                System.out.println("\nTOTAL: " + dealerTotal);

                if(playerTotal > dealerTotal){
                    System.out.println("\n!!!Player wins!!! :) ");
                }
                else if(dealerTotal > playerTotal){
                    System.out.println("\n!!!Player loses!!! :( ");
                }
                else{
                    System.out.println("\n!!!Push!!! Tie game :| ");
                }
              }

            String again = "";
            while (true) {
                System.out.print("\nWould you like to play again? (Y/N): ");
                again = scanner.nextLine().trim().toUpperCase();
                if (again.equals("Y") || again.equals("N")) {
                    break; // valid input
                }
                System.out.println("\nInvalid choice. Please enter Y or N.");
            }
            keepPlaying = again.equals("Y");

         }
         System.out.println("\nThanks for playing!");
    
        }
    // Get Blackjack value of a card (1–13 -> 1–11, all face cards are worth 10)
    public static int getCardValue(int cardNumber) {
        int faceValue = (cardNumber - 1) % 13 + 1;
        if (faceValue >= 10) return 10;
        return faceValue;
    }

    // Get the string of a hand for display
    public static String getHandString(List<Integer> hand) {
        List<String> cards = new ArrayList<>();
        for (int card : hand) {
            cards.add(cardToString(card));
        }
        return String.join(", ", cards);
    }

    // Convert a card number to suit and face (e.g., "Ace of Hearts")
    public static String cardToString(int cardNumber) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] faces = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        int faceIndex = (cardNumber - 1) % 13;
        int suitIndex = (cardNumber - 1) / 13;

        return faces[faceIndex] + " of " + suits[suitIndex];
    }

    // Compute Blackjack hand value with Ace handling (1 or 11)
    public static int getHandValue(List<Integer> hand) {
        int total = 0;
        int aceCount = 0;

        for(int i = 0; i < hand.size(); i++){
            int card = hand.get(i);
            int value = getCardValue(card);
            total += value;
            if((card - 1) % 13 + 1 == 1){
                aceCount++;
                total += 10;
            }

        }

        while(total > 21 && aceCount > 0){
            total -= 10;
            aceCount--;
        }
        
        return total;
    }
}