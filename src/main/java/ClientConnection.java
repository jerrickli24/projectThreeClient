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
            socket = new Socket(ipAddress, port);
            
            // create output stream first!! this is important
            out = new ObjectOutputStream(socket.getOutputStream());
            
            // then create input stream
            in = new ObjectInputStream(socket.getInputStream());
            
            // set connected to true
            isConnected = true;
            
            System.out.println("Connected to server!");
            return true;
        } catch(Exception e) {
            System.out.println("Failed to connect: " + e.getMessage());
            return false;
        }
    }
    
    public void sendToServer(PokerInfo info) {
        try {
            // write the object to the output stream
            out.writeObject(info);
            out.flush(); // don't forget to flush!
            out.reset(); // helps prevent issues with object caching
            
        } catch(Exception e) {
            System.out.println("Error sending to server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public PokerInfo receiveFromServer() {
        try {
            // read PokerInfo object from input stream
            PokerInfo info = (PokerInfo) in.readObject();
            return info;
        } catch(Exception e) {
            System.out.println("Error receiving from server: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public void closeConnection() {
        try {
            // close streams and socket
            if(out != null) {
                out.close();
            }
            if(in != null) {
                in.close();
            }
            if(socket != null) {
                socket.close();
            }
            
            isConnected = false;
            System.out.println("Connection closed");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean isConnected() {
        return isConnected;
    }
}