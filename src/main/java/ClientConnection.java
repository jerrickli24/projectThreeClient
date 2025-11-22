import java.io.*;
import java.net.Socket;

public class ClientConnection {
    
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String ipAddress;
    private int port;
    private boolean isConnected;
    
    public ClientConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.isConnected = false;
    }
    
    public boolean connect() {
        try {
            // create socket connection to server
            
            // create output stream first!!
            
            // then create input stream
            
            // set connected to true
            
            return true;
        } catch(Exception e) {
            System.out.println("Failed to connect");
            return false;
        }
    }
    
    public void sendToServer(PokerInfo info) {
        try {
            // write the object to the output stream
            // don't forget to flush!
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public PokerInfo receiveFromServer() {
        try {
            // read PokerInfo object from input stream
            
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void closeConnection() {
        try {
            // close streams and socket
            
            isConnected = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean isConnected() {
        return isConnected;
    }
}