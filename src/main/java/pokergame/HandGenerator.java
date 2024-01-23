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

  public static List<List<Card>> generateRandomHands(int numberOfHands) {
    assert (numberOfHands >= 0);
    List<List<Card>> hands = new ArrayList<>();
    var deck = generateDeck();
    Collections.shuffle(deck);
    for (int i = 0; i < numberOfHands; i++) {
      assert (i + 1 * 5 < deck.size());
      hands.add(deck.subList(i * 5, (i + 1) * 5));
    }
    return hands;
  }
}
