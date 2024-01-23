import org.junit.jupiter.api.Test;
import pokergame.Evaluator;
import pokergame.HandGenerator;
import pokergame.HandValue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluatorTest {
  @Test
  public void testValidDeck() {
    assertEquals(HandGenerator.generateDeck().size(), 52);
  }

  @Test
  public void testHighCardHand() {
    var hand = HandGenerator.generateRandomHand();
    assertEquals(Evaluator.evaluateHand(hand), HandValue.HIGH_CARD);
  }
}
