import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pokergame.Card;
import pokergame.Evaluator;
import pokergame.HandGenerator;
import pokergame.HandValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pokergame.CardSuit.*;
import static pokergame.CardValue.*;

public class EvaluatorTest {
  public static final List<Card> HIGH_CARD = List.of(new Card(C, FIVE), new Card(D, SEVEN), new Card(C, TWO), new Card(C, THREE), new Card(C, FOUR));
  public static final List<Card> HIGH_CARD_ACE = List.of(new Card(C, ACE), new Card(D, SEVEN), new Card(C, TWO), new Card(C, THREE), new Card(C, FOUR));
  public static final List<Card> STRAIGHT_FLUSH = List.of(new Card(C, TWO), new Card(C, THREE), new Card(C, FOUR), new Card(C, FIVE), new Card(C, SIX));
  public static final List<Card> FOUR_OF_A_KIND = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(H, FIVE), new Card(S, FIVE), new Card(C, THREE));
  public static final List<Card> FULL_HOUSE = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(H, FIVE), new Card(C, THREE), new Card(C, THREE));
  public static final List<Card> FLUSH = List.of(new Card(C, TWO), new Card(C, THREE), new Card(C, NINE), new Card(C, FIVE), new Card(C, SIX));
  public static final List<Card> STRAIGHT = List.of(new Card(C, TWO), new Card(D, THREE), new Card(H, FOUR), new Card(C, FIVE), new Card(C, SIX));
  public static final List<Card> THREE_OF_A_KIND = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(H, FIVE), new Card(C, THREE), new Card(C, FOUR));
  public static final List<Card> TWO_PAIRS = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(C, TWO), new Card(D, TWO), new Card(C, FOUR));
  public static final List<Card> PAIR = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(C, TWO), new Card(C, THREE), new Card(C, FOUR));

  public static final List<List<Card>> ORDERED_CONFIGURED_HANDS = List.of(HIGH_CARD, HIGH_CARD_ACE, PAIR, TWO_PAIRS, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH);

  @Test
  public void testValidDeck() {
    assertEquals(52, HandGenerator.generateDeck().size());
  }

  @Test
  public void testHighCardHand() {
    assertEquals(HandValue.HIGH_CARD, Evaluator.evaluateHand(HIGH_CARD).handvalue());
  }

  @Test
  public void testPairHand() {
    assertEquals(HandValue.PAIR, Evaluator.evaluateHand(PAIR).handvalue());
  }

  @Test
  public void testTwoPairs() {
    assertEquals(HandValue.TWO_PAIRS, Evaluator.evaluateHand(TWO_PAIRS).handvalue());
  }

  @Test
  public void testThreeOfAKindHand() {
    assertEquals(HandValue.THREE_OF_A_KIND, Evaluator.evaluateHand(THREE_OF_A_KIND).handvalue());
  }

  @Test
  public void testStraightHand() {
    assertEquals(HandValue.STRAIGHT, Evaluator.evaluateHand(STRAIGHT).handvalue());
  }

  @Test
  public void testFlushHand() {
    assertEquals(HandValue.FLUSH, Evaluator.evaluateHand(FLUSH).handvalue());
  }


  @Test
  public void testFullHouseHand() {
    assertEquals(HandValue.FULL_HOUSE, Evaluator.evaluateHand(FULL_HOUSE).handvalue());
  }

  @Test
  public void testFourOfAKindHand() {
    assertEquals(HandValue.FOUR_OF_A_KIND, Evaluator.evaluateHand(FOUR_OF_A_KIND).handvalue());
  }

  @Test
  public void testStraightFlushHand() {
    assertEquals(HandValue.STRAIGHT_FLUSH, Evaluator.evaluateHand(STRAIGHT_FLUSH).handvalue());
  }

  @Test
  public void testExampleGame() {
    var playerOneHand = HIGH_CARD;
    var playerTwoHand = PAIR;
    var winner = Evaluator.evaluateGame(List.of(playerOneHand, playerTwoHand));
    assertEquals(1, winner);
  }

  @Test
  public void testBothHighCardGame() {
    var playerOneHand = HIGH_CARD;
    var playerTwoHand = HIGH_CARD_ACE;
    var winner = Evaluator.evaluateGame(List.of(playerOneHand, playerTwoHand));
    assertEquals(1, winner);
  }

  //finally a nice usage for ParameterizedTest
  @ParameterizedTest
  @MethodSource("providePairsOfHands")
  void testPairsOfHands(List<List<Card>> hands, int expectedWinner) {
    var winner = Evaluator.evaluateGame(hands);
    assertEquals(1, winner);
  }

  private static Stream<Arguments> providePairsOfHands() {
    List<List<List<Card>>> pairs = new ArrayList<>();

    for (int i = 0; i < ORDERED_CONFIGURED_HANDS.size(); i++) {
      for (int j = i + 1; j < ORDERED_CONFIGURED_HANDS.size(); j++) {
        pairs.add(List.of(ORDERED_CONFIGURED_HANDS.get(i), ORDERED_CONFIGURED_HANDS.get(j)));
      }
    }
    return pairs.stream().map(g -> Arguments.of(g, 1));
  }
}
