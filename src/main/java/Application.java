import java.util.ArrayList;
import java.util.List;

public class Application {
  public enum CardSuit {C, D, H, S}

  public enum CardValue {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, JACK, QUEEN, KING, ACE}

  record Card(CardSuit suit, CardValue value) {
    @Override
    public String toString() {
      return String.format("Card{suit=%s, value=%s}", suit, value);
    }
  }

  public static List<Card> generateDeck() {
    List<Card> allPossibleCards = new ArrayList<>();
    for (CardSuit cs : CardSuit.values()) {
      for (CardValue cv : CardValue.values()) {
        allPossibleCards.add(new Card(cs, cv));
      }
    }
    return allPossibleCards;
  }

  public static void main(String[] args) {
    System.out.println("Test");
    var deck = generateDeck();
    generateDeck().stream().forEach((c)->System.out.println(c));
  }

}
