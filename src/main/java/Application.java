import java.util.List;

public class Application {
  public enum CardSuit {C, D, H, S}

  public enum CardValue {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, JACK, QUEEN, KING, ACE}

  record Card(CardSuit suit, CardValue value) {
  }

  public static void main(String[] args) {
    System.out.println("Test");
  }

}
