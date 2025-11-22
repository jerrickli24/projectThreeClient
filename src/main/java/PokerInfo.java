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
        // initialize everything to default values
        this.playerHand = null;
        this.dealerHand = null;
        this.anteBet = 0;
        this.pairPlusBet = 0;
        this.playBet = 0;
        this.totalWinnings = 0;
        this.gameResult = "";
        this.pairPlusWinnings = 0;
        this.anteWinnings = 0;
        this.messageToClient = "";
    }
    
    // all the getters
    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
    
    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }
    
    public int getAnteBet() {
        return anteBet;
    }
    
    public int getPairPlusBet() {
        return pairPlusBet;
    }
    
    public int getPlayBet() {
        return playBet;
    }
    
    public int getTotalWinnings() {
        return totalWinnings;
    }
    
    public String getGameResult() {
        return gameResult;
    }
    
    public int getPairPlusWinnings() {
        return pairPlusWinnings;
    }
    
    public int getAnteWinnings() {
        return anteWinnings;
    }
    
    public String getMessageToClient() {
        return messageToClient;
    }
    
    // all the setters
    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }
    
    public void setDealerHand(ArrayList<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }
    
    public void setAnteBet(int anteBet) {
        this.anteBet = anteBet;
    }
    
    public void setPairPlusBet(int pairPlusBet) {
        this.pairPlusBet = pairPlusBet;
    }
    
    public void setPlayBet(int playBet) {
        this.playBet = playBet;
    }
    
    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }
    
    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }
    
    public void setPairPlusWinnings(int pairPlusWinnings) {
        this.pairPlusWinnings = pairPlusWinnings;
    }
    
    public void setAnteWinnings(int anteWinnings) {
        this.anteWinnings = anteWinnings;
    }
    
    public void setMessageToClient(String messageToClient) {
        this.messageToClient = messageToClient;
    }
}