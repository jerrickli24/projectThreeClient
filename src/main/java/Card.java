import java.io.Serializable;

public class Card implements Serializable {
    
    private char suit; // C, D, H, or S
    private int value; // 2-14
    
    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }
    
    public char getSuit() {
        return suit;
    }
    
    public int getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        // make a string like "AH" for ace of hearts or "5D" for 5 of diamonds
        // need to convert 11, 12, 13, 14 to J, Q, K, A
        
        return null;
    }
}