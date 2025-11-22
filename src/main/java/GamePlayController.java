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
        updateTotalWinnings();
        
        // disable play and fold buttons at first
        playButton.setDisable(true);
        foldButton.setDisable(true);
        
        // set some default bet values
        anteField.setText("5");
        pairPlusField.setText("0");
        
        // clear the cards
        playerCard1Label.setText("");
        playerCard2Label.setText("");
        playerCard3Label.setText("");
        dealerCard1Label.setText("??");
        dealerCard2Label.setText("??");
        dealerCard3Label.setText("??");
        
        gameInfoArea.appendText("Welcome! Place your bets to start.\n");
    }
    
    @FXML
    public void handlePlaceBets() {
        try {
            // get ante and pair plus from text fields
            int ante = Integer.parseInt(anteField.getText());
            int pairPlus = Integer.parseInt(pairPlusField.getText());
            
            // validate bets (5-25 for ante, 0 or 5-25 for pair plus)
            if(ante < 5 || ante > 25) {
                gameInfoArea.appendText("Ante bet must be between $5 and $25!\n");
                return;
            }
            
            if(pairPlus != 0 && (pairPlus < 5 || pairPlus > 25)) {
                gameInfoArea.appendText("Pair Plus must be $0 or between $5 and $25!\n");
                return;
            }
            
            // create PokerInfo with the bets
            currentGameInfo = new PokerInfo();
            currentGameInfo.setAnteBet(ante);
            currentGameInfo.setPairPlusBet(pairPlus);
            
            gameInfoArea.appendText("Placing bets: Ante $" + ante + ", Pair Plus $" + pairPlus + "\n");
            
            // send to server on a separate thread so GUI doesn't freeze
            Thread networkThread = new Thread(() -> {
                clientConnection.sendToServer(currentGameInfo);
                
                // get response from server (will have cards dealt)
                PokerInfo response = clientConnection.receiveFromServer();
                
                if(response != null) {
                    currentGameInfo = response;
                    
                    // update GUI on JavaFX thread
                    Platform.runLater(() -> {
                        // show the cards on screen
                        displayCards(response.getPlayerHand(), response.getDealerHand());
                        
                        // disable place bet button, enable play and fold
                        placeBetButton.setDisable(true);
                        playButton.setDisable(false);
                        foldButton.setDisable(false);
                        
                        playWagerLabel.setText("Play wager will be: $" + ante);
                        gameInfoArea.appendText("Cards dealt! Do you want to Play or Fold?\n");
                    });
                }
            });
            
            networkThread.start();
            
        } catch(NumberFormatException e) {
            gameInfoArea.appendText("Please enter valid numbers for bets!\n");
        } catch(Exception e) {
            gameInfoArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }
    
    @FXML
    public void handlePlay() {
        // set play bet equal to ante bet
        currentGameInfo.setPlayBet(currentGameInfo.getAnteBet());
        
        gameInfoArea.appendText("You chose to play! Play wager: $" + currentGameInfo.getPlayBet() + "\n");
        
        // disable buttons while waiting
        playButton.setDisable(true);
        foldButton.setDisable(true);
        
        // send to server on separate thread
        Thread networkThread = new Thread(() -> {
            try {
                clientConnection.sendToServer(currentGameInfo);
                
                // get result from server
                PokerInfo response = clientConnection.receiveFromServer();
                
                if(response != null) {
                    // update GUI on JavaFX thread
                    Platform.runLater(() -> {
                        // show dealer cards
                        revealDealerCards(response.getDealerHand());
                        
                        gameInfoArea.appendText("Dealer's cards revealed!\n");
                        gameInfoArea.appendText(response.getMessageToClient() + "\n");
                        
                        // wait a bit so player can see the cards
                        try {
                            Thread.sleep(2000);
                        } catch(Exception e) {}
                        
                        // switch to results screen
                        switchToResultsScene(response);
                    });
                }
            } catch(Exception e) {
                Platform.runLater(() -> {
                    gameInfoArea.appendText("Error communicating with server: " + e.getMessage() + "\n");
                });
            }
        });
        
        networkThread.start();
    }
    
    @FXML
    public void handleFold() {
        // player loses ante and pair plus bets
        int loss = currentGameInfo.getAnteBet() + currentGameInfo.getPairPlusBet();
        totalWinnings -= loss;
        
        currentGameInfo.setTotalWinnings(totalWinnings);
        currentGameInfo.setGameResult("You folded. Lost $" + loss);
        currentGameInfo.setAnteWinnings(-loss);
        currentGameInfo.setPairPlusWinnings(0);
        currentGameInfo.setMessageToClient("You folded.");
        
        gameInfoArea.appendText("You folded. Lost $" + loss + "\n");
        
        // go to results screen
        switchToResultsScene(currentGameInfo);
    }
    
    private void displayCards(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
        // show dealer cards as face down (??)
        dealerCard1Label.setText("??");
        dealerCard2Label.setText("??");
        dealerCard3Label.setText("??");
        
        // show player cards
        if(playerHand != null && playerHand.size() == 3) {
            playerCard1Label.setText(playerHand.get(0).toString());
            playerCard2Label.setText(playerHand.get(1).toString());
            playerCard3Label.setText(playerHand.get(2).toString());
        }
    }
    
    private void revealDealerCards(ArrayList<Card> dealerHand) {
        // flip over dealer cards and display them
        if(dealerHand != null && dealerHand.size() == 3) {
            dealerCard1Label.setText(dealerHand.get(0).toString());
            dealerCard2Label.setText(dealerHand.get(1).toString());
            dealerCard3Label.setText(dealerHand.get(2).toString());
        }
    }
    
    private void updateTotalWinnings() {
        // update the label
        totalWinningsLabel.setText("Total Winnings: $" + totalWinnings);
    }
    
    // menu options
    @FXML
    public void handleMenuExit() {
        // close the program
        clientConnection.closeConnection();
        Platform.exit();
    }
    
    @FXML
    public void handleFreshStart() {
        // reset winnings to 0
        totalWinnings = 0;
        updateTotalWinnings();
        
        // clear game info
        gameInfoArea.clear();
        gameInfoArea.appendText("Fresh start! Place your bets.\n");
        
        // reset buttons
        placeBetButton.setDisable(false);
        playButton.setDisable(true);
        foldButton.setDisable(true);
        
        // clear cards
        playerCard1Label.setText("");
        playerCard2Label.setText("");
        playerCard3Label.setText("");
        dealerCard1Label.setText("??");
        dealerCard2Label.setText("??");
        dealerCard3Label.setText("??");
        
        playWagerLabel.setText("");
    }
    
    @FXML
    public void handleNewLook() {
        // change the css or colors or something
        // for now just switch between two different styles
        Scene scene = menuBar.getScene();
        
        if(scene.getStylesheets().contains(getClass().getResource("/gameplay.css").toExternalForm())) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/gameplay2.css").toExternalForm());
            gameInfoArea.appendText("New look applied! (Style 2)\n");
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/gameplay.css").toExternalForm());
            gameInfoArea.appendText("New look applied! (Style 1)\n");
        }
    }
    
    private void switchToResultsScene(PokerInfo info) {
        try {
            // load results.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/results.fxml"));
            Parent root = loader.load();
            
            // pass the client connection and winnings to results controller
            ResultsController resultsController = loader.getController();
            resultsController.setClientConnection(clientConnection);
            resultsController.setTotalWinnings(totalWinnings);
            
            // tell it to display the results
            resultsController.displayResults(info);
            
            // switch scenes
            Stage stage = ClientMain.getPrimaryStage();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            
        } catch(Exception e) {
            System.out.println("Error switching to results: " + e.getMessage());
            e.printStackTrace();
        }
    }
}