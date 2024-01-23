package pokergame;

import java.util.List;

public class Evaluator {
  public static HandValue evaluateHand(final List<Card> hand){
    return HandValue.HIGH_CARD;
  }
}
