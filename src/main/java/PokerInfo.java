import java.io.Serializable;
import java.util.ArrayList;

public class PokerInfo implements Serializable {
    
    // hands
    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealerHand;
    
    // bets
    private int anteBet;
    private int pairPlusBet;
    private int playBet;
    
    // results
    private int totalWinnings;
    private String gameResult;
    private int pairPlusWinnings;
    private int anteWinnings;
    private String messageToClient;
    
    public PokerInfo() {
        // initialize everything
    }
    
    // need getters and setters for everything
    
}