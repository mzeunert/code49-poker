package pokergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandGenerator {

  public static final int NUMBER_OF_CARDS = 5;

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
    if (numberOfHands<1) throw new RuntimeException("At least 2 hands are required for a valid game");

    List<List<Card>> hands = new ArrayList<>();
    var deck = generateDeck();
    if (numberOfHands * NUMBER_OF_CARDS > deck.size()) throw new RuntimeException("Too many players for this deck size");
    Collections.shuffle(deck);
    for (int i = 0; i < numberOfHands; i++) {
      hands.add(deck.subList(i * NUMBER_OF_CARDS, (i + 1) * NUMBER_OF_CARDS));
    }
    return hands;
  }
}
