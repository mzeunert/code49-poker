import org.junit.jupiter.api.Test;
import pokergame.Card;
import pokergame.Evaluator;
import pokergame.HandGenerator;
import pokergame.HandValue;
import static pokergame.CardValue.*;
import static pokergame.CardSuit.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluatorTest {
  public static final List<Card> HIGH_CARD = List.of(new Card(C, FIVE), new Card(D, SEVEN), new Card(C, TWO), new Card(C, THREE), new Card(C, FOUR));
  public static final List<Card> STRAIGHT_FLUSH = List.of(new Card(C, TWO), new Card(C, THREE), new Card(C, FOUR), new Card(C, FIVE), new Card(C, SIX));
  public static final List<Card> FOUR_OF_A_KIND = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(H, FIVE), new Card(S, FIVE), new Card(C, THREE));
  public static final List<Card> FULL_HOUSE = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(H, FIVE), new Card(C, THREE), new Card(C, THREE));
  public static final List<Card> FLUSH = List.of(new Card(C, TWO), new Card(C, THREE), new Card(C, NINE), new Card(C, FIVE), new Card(C, SIX));
  public static final List<Card> STRAIGHT = List.of(new Card(C, TWO), new Card(D, THREE), new Card(H, FOUR), new Card(C, FIVE), new Card(C, SIX));
  public static final List<Card> THREE_OF_A_KIND = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(H, FIVE), new Card(C, THREE), new Card(C, FOUR));
  public static final List<Card> TWO_PAIRS = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(C, TWO), new Card(D, TWO), new Card(C, FOUR));
  public static final List<Card> PAIR = List.of(new Card(C, FIVE), new Card(D, FIVE), new Card(C, TWO), new Card(C, THREE), new Card(C, FOUR));
  @Test
  public void testValidDeck() {
    assertEquals(52, HandGenerator.generateDeck().size());
  }

  @Test
  public void testHighCardHand() {
    assertEquals(HandValue.HIGH_CARD,Evaluator.evaluateHand(HIGH_CARD));
  }

  @Test
  public void testPairHand() {
    assertEquals(HandValue.PAIR, Evaluator.evaluateHand(PAIR));
  }

  @Test
  public void testTwoPairs() {
    assertEquals( HandValue.TWO_PAIRS,Evaluator.evaluateHand(TWO_PAIRS));
  }

  @Test
  public void testThreeOfAKindHand() {
    assertEquals(HandValue.THREE_OF_A_KIND, Evaluator.evaluateHand(THREE_OF_A_KIND));
  }

  @Test
  public void testStraightHand() {
    assertEquals(HandValue.STRAIGHT, Evaluator.evaluateHand(STRAIGHT));
  }

  @Test
  public void testFlushHand() {
    assertEquals(HandValue.FLUSH, Evaluator.evaluateHand(FLUSH));
  }


  @Test
  public void testFullHouseHand() {
    assertEquals(HandValue.FULL_HOUSE, Evaluator.evaluateHand(FULL_HOUSE));
  }

  @Test
  public void testFourOfAKindHand() {
    assertEquals(HandValue.FOUR_OF_A_KIND, Evaluator.evaluateHand(FOUR_OF_A_KIND));
  }

  @Test
  public void testStraightFlushHand() {
    assertEquals(HandValue.STRAIGHT_FLUSH, Evaluator.evaluateHand(STRAIGHT_FLUSH));
  }

  @Test
  public void testExampleGame(){
    var playerOneHand = HIGH_CARD;
    var playerTwoHand = PAIR;
    var winner = Evaluator.evaluateGame(List.of(playerOneHand,playerTwoHand));
    assertEquals(1,winner);
  }
}
