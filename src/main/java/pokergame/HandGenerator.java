package pokergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandGenerator {
    public static List<Card> generateDeck() {
    List<Card> allPossibleCards = new ArrayList<>();
    for (CardSuit cs : CardSuit.values()) {
      for (CardValue cv : CardValue.values()) {
        allPossibleCards.add(new Card(cs, cv));
      }
    }
    return allPossibleCards;
  }
  public static List<Card> generateRandomHand(){
    var deck = generateDeck();
    Collections.shuffle(deck);
    return deck.subList(0,5);
  }
}
