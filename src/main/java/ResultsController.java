import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;

public class ResultsController {
    
    @FXML private Label resultMessageLabel;
    @FXML private Label gameAmountLabel;
    @FXML private VBox breakdownArea;
    @FXML private Button playAgainButton;
    @FXML private Button exitButton;
    
    private ClientConnection clientConnection;
    private int totalWinnings;
    
    public void setClientConnection(ClientConnection conn) {
        this.clientConnection = conn;
    }
    
    public void setTotalWinnings(int winnings) {
        this.totalWinnings = winnings;
    }
    
    public void displayResults(PokerInfo info) {
        // figure out how much won/lost this game
        int anteResult = info.getAnteWinnings();
        int pairPlusResult = info.getPairPlusWinnings();
        int gameTotal = anteResult + pairPlusResult;
        
        // update total winnings
        totalWinnings += gameTotal;
        
        // display if they won or lost
        if(gameTotal > 0) {
            resultMessageLabel.setText("You Won!");
            resultMessageLabel.setStyle("-fx-text-fill: green;");
        } else if(gameTotal < 0) {
            resultMessageLabel.setText("You Lost");
            resultMessageLabel.setStyle("-fx-text-fill: red;");
        } else {
            resultMessageLabel.setText("Push");
            resultMessageLabel.setStyle("-fx-text-fill: orange;");
        }
        
        gameAmountLabel.setText("This game: $" + gameTotal);
        
        // show breakdown of ante/play result and pair plus result
        Label breakdownLabel = new Label();
        String breakdownText = "Ante/Play Wager Result: $" + anteResult + "\n";
        breakdownText += "Pair Plus Wager Result: $" + pairPlusResult + "\n";
        breakdownText += "\n" + info.getMessageToClient() + "\n";
        breakdownText += "\nTotal Winnings: $" + totalWinnings;
        
        breakdownLabel.setText(breakdownText);
        breakdownLabel.setStyle("-fx-font-size: 16px;");
        
        breakdownArea.getChildren().add(breakdownLabel);
    }
    
    @FXML
    public void handlePlayAgain() {
        try {
            // go back to gameplay screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameplay.fxml"));
            Parent root = loader.load();
            
            // need to pass the clientConnection to the new controller
            GamePlayController gameController = loader.getController();
            gameController.setClientConnection(clientConnection);
            
            Stage stage = ClientMain.getPrimaryStage();
            Scene scene = new Scene(root, 900, 700);
            stage.setScene(scene);
            
        } catch(Exception e) {
            System.out.println("Error going back to game: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleExit() {
        // close connection
        clientConnection.closeConnection();
        
        // exit program
        Platform.exit();
    }
}