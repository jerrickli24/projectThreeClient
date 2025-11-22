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
        ipAddressField.setText("localhost");
        portField.setText("5555");
        statusLabel.setText("");
    }
    
    @FXML
    public void handleConnect() {
        // get ip and port from text fields
        String ip = ipAddressField.getText();
        String portText = portField.getText();
        
        // make sure port is a valid number
        int port;
        try {
            port = Integer.parseInt(portText);
        } catch(NumberFormatException e) {
            statusLabel.setText("Port must be a number!");
            return;
        }
        
        // create ClientConnection object
        clientConnection = new ClientConnection(ip, port);
        
        // try to connect
        boolean connected = clientConnection.connect();
        
        // if it works, switch to game screen
        if(connected) {
            statusLabel.setText("Connected! Starting game...");
            switchToGameScene();
        } else {
            // if not, show error message
            statusLabel.setText("Connection failed. Is the server running?");
        }
    }
    
    private void switchToGameScene() {
        try {
            // load the gameplay.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameplay.fxml"));
            Parent root = loader.load();
            
            // get the controller and give it the clientConnection
            GamePlayController gameController = loader.getController();
            gameController.setClientConnection(clientConnection);
            
            // switch scenes
            Stage stage = ClientMain.getPrimaryStage();
            Scene scene = new Scene(root, 900, 700);
            stage.setScene(scene);
            
        } catch(Exception e) {
            System.out.println("Error switching scenes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}