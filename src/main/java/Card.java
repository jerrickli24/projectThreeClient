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
        String valueStr = "";
        
        if(value == 14) {
            valueStr = "A";
        }
        else if(value == 13) {
            valueStr = "K";
        }
        else if(value == 12) {
            valueStr = "Q";
        }
        else if(value == 11) {
            valueStr = "J";
        }
        else {
            valueStr = String.valueOf(value);
        }
        
        return valueStr + suit;
    }
}