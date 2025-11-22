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
        
        // update total winnings
        
        // display if they won or lost
        
        // show breakdown of ante/play result and pair plus result
        
        // show total winnings
        
    }
    
    @FXML
    public void handlePlayAgain() {
        try {
            // go back to gameplay screen
            
            // need to pass the clientConnection to the new controller
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleExit() {
        // close connection
        // exit program
    }
}