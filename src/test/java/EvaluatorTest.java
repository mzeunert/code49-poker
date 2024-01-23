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
  @Test
  public void testValidDeck() {
    assertEquals(52, HandGenerator.generateDeck().size());
  }

  @Test
  public void testHighCardHand() {
    var hand = List.of(new Card(C,FIVE),new Card(D,SEVEN),new Card(C,TWO),new Card(C,THREE),new Card(C,FOUR));
    assertEquals(HandValue.HIGH_CARD,Evaluator.evaluateHand(hand));
  }

  @Test
  public void testPairHand() {
    var hand = List.of(new Card(C,FIVE),new Card(D,FIVE),new Card(C,TWO),new Card(C,THREE),new Card(C,FOUR));
    assertEquals(HandValue.PAIR, Evaluator.evaluateHand(hand));
  }

  @Test
  public void testTwoPairs() {
    var hand = List.of(new Card(C,FIVE),new Card(D,FIVE),new Card(C,TWO),new Card(D,TWO),new Card(C,FOUR));
    assertEquals( HandValue.TWO_PAIRS,Evaluator.evaluateHand(hand));
  }

  @Test
  public void testThreeOfAKindHand() {
    var hand = List.of(new Card(C,FIVE),new Card(D,FIVE),new Card(H,FIVE),new Card(C,THREE),new Card(C,FOUR));
    assertEquals(HandValue.THREE_OF_A_KIND, Evaluator.evaluateHand(hand));
  }

  @Test
  public void testStraightHand() {
    var hand = List.of(new Card(C,TWO),new Card(D,THREE),new Card(H,FOUR),new Card(C,FIVE),new Card(C,SIX));
    assertEquals(HandValue.STRAIGHT, Evaluator.evaluateHand(hand));
  }

  @Test
  public void testFlushHand() {
    var hand = List.of(new Card(C,TWO),new Card(C,THREE),new Card(C,NINE),new Card(C,FIVE),new Card(C,SIX));
    assertEquals(HandValue.FLUSH, Evaluator.evaluateHand(hand));
  }


  @Test
  public void testFullHouseHand() {
    var hand = List.of(new Card(C,FIVE),new Card(D,FIVE),new Card(H,FIVE),new Card(C,THREE),new Card(C,THREE));
    assertEquals(HandValue.FULL_HOUSE, Evaluator.evaluateHand(hand));
  }

  @Test
  public void testFourOfAKindHand() {
    var hand = List.of(new Card(C,FIVE),new Card(D,FIVE),new Card(H,FIVE),new Card(S,FIVE),new Card(C,THREE));
    assertEquals(HandValue.FOUR_OF_A_KIND, Evaluator.evaluateHand(hand));
  }

  @Test
  public void testStraightFlushHand() {
    var hand = List.of(new Card(C,TWO),new Card(C,THREE),new Card(C,FOUR),new Card(C,FIVE),new Card(C,SIX));
    assertEquals(HandValue.STRAIGHT_FLUSH, Evaluator.evaluateHand(hand));
  }

}
