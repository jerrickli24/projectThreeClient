import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {
    
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        
        // load the welcome screen fxml file
        Parent root = FXMLLoader.load(getClass().getResource("/welcome.fxml"));
        
        // make the scene
        Scene scene = new Scene(root, 800, 600);
        
        // set title and show
        primaryStage.setTitle("3 Card Poker - Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}