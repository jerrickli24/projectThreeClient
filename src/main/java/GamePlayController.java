import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.util.ArrayList;

public class GamePlayController {
    
    @FXML private MenuBar menuBar;
    @FXML private Label totalWinningsLabel;
    
    // dealer cards
    @FXML private Label dealerCard1Label;
    @FXML private Label dealerCard2Label;
    @FXML private Label dealerCard3Label;
    
    // player cards
    @FXML private Label playerCard1Label;
    @FXML private Label playerCard2Label;
    @FXML private Label playerCard3Label;
    
    // betting stuff
    @FXML private TextField anteField;
    @FXML private TextField pairPlusField;
    @FXML private Label playWagerLabel;
    
    // buttons
    @FXML private Button placeBetButton;
    @FXML private Button playButton;
    @FXML private Button foldButton;
    
    // game info display
    @FXML private TextArea gameInfoArea;
    
    private ClientConnection clientConnection;
    private int totalWinnings;
    private PokerInfo currentGameInfo;
    
    public void setClientConnection(ClientConnection conn) {
        this.clientConnection = conn;
    }
    
    @FXML
    public void initialize() {
        // set starting values
        totalWinnings = 0;
        
        // disable play and fold buttons at first
        
        // set some default bet values maybe
    }
    
    @FXML
    public void handlePlaceBets() {
        try {
            // get ante and pair plus from text fields
            
            // validate bets (5-25 for ante, 0 or 5-25 for pair plus)
            
            // create PokerInfo with the bets
            
            // send to server
            
            // get response from server (will have cards dealt)
            
            // show the cards on screen
            
            // disable place bet button, enable play and fold
            
        } catch(Exception e) {
            // show error in game info
        }
    }
    
    @FXML
    public void handlePlay() {
        try {
            // set play bet equal to ante bet
            
            // send to server
            
            // get result from server
            
            // show dealer cards
            
            // switch to results screen
            
        } catch(Exception e) {
            // handle error
        }
    }
    
    @FXML
    public void handleFold() {
        // player loses ante and pair plus bets
        
        // update total winnings
        
        // go to results screen
    }
    
    private void displayCards(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
        // show dealer cards as face down (??)
        
        // show player cards
        
    }
    
    private void revealDealerCards(ArrayList<Card> dealerHand) {
        // flip over dealer cards and display them
        
    }
    
    private void updateTotalWinnings() {
        // update the label
    }
    
    // menu options
    @FXML
    public void handleMenuExit() {
        // close the program
    }
    
    @FXML
    public void handleFreshStart() {
        // reset winnings to 0
        // clear game info
        // reset buttons
    }
    
    @FXML
    public void handleNewLook() {
        // change the css or colors or something
    }
    
    private void switchToResultsScene(PokerInfo info) {
        try {
            // load results.fxml
            
            // pass the client connection and winnings to results controller
            
            // tell it to display the results
            
            // switch scenes
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}