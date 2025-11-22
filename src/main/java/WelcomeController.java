import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WelcomeController {
    
    @FXML
    private TextField ipAddressField;
    
    @FXML
    private TextField portField;
    
    @FXML
    private Button connectButton;
    
    @FXML
    private Label statusLabel;
    
    private ClientConnection clientConnection;
    
    @FXML
    public void initialize() {
        // set default values
        // maybe localhost and 5555?
    }
    
    @FXML
    public void handleConnect() {
        // get ip and port from text fields
        
        // make sure port is a valid number
        
        // create ClientConnection object
        
        // try to connect
        
        // if it works, switch to game screen
        // if not, show error message
    }
    
    private void switchToGameScene() {
        try {
            // load the gameplay.fxml
            
            // get the controller and give it the clientConnection
            
            // switch scenes
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}