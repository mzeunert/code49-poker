package pokergame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {
  public static HandScore evaluateHand(List<Card> handIn) {
    if (handIn.size() != 5) throw new RuntimeException("handsize must be 5 cards");

    var hand = new ArrayList<>(handIn);
    //ordering hand by value high ot low
    hand.sort((a, b) -> b.value().compareTo(a.value()));
    //grouping by value to easily extract same value groups
    var groupedByValues = hand.stream().collect(Collectors.groupingBy(Card::value));
    var pairs = groupedByValues.values().stream().filter((c) -> c.size() == 2).collect(Collectors.toList());
    var threes = groupedByValues.values().stream().filter((c) -> c.size() == 3).collect(Collectors.toList());
    var fours = groupedByValues.values().stream().filter((c) -> c.size() == 4).collect(Collectors.toList());
    //check if we have a flush
    var isFlush = hand.stream().allMatch((c) -> c.suit().equals(hand.get(0).suit()));
    //check for straight
    var handIterator = hand.listIterator();
    var prev = handIterator.next();
    boolean isStraight = true;
    while (handIterator.hasNext()) {
      var current = handIterator.next();
      if (current.value().ordinal() + 1 != prev.value().ordinal()) {
        isStraight = false;
      }
      prev = current;
    }
    //calculate secondary scores
    List<Integer> secondaryScores = hand.stream().map((c) -> c.value().ordinal()).collect(Collectors.toList());
    List<Integer> pairValues = pairs.stream().map((p) -> p.get(0).value().ordinal()).sorted(Comparator.reverseOrder()).toList();
    List<Integer> threeValues = threes.stream().map((p) -> p.get(0).value().ordinal()).toList();
    List<Integer> fourValues = fours.stream().map((p) -> p.get(0).value().ordinal()).toList();
    //now use early return to return highest handvalue
    if (isStraight && isFlush) {
      return new HandScore(HandValue.STRAIGHT_FLUSH, secondaryScores);
    }
    if (fours.size() > 0) {
      secondaryScores.addAll(0, fourValues);
      return new HandScore(HandValue.FOUR_OF_A_KIND, secondaryScores);
    }
    if (threes.size() > 0 && pairs.size() > 0) {
      secondaryScores.addAll(0, pairValues);
      secondaryScores.addAll(0, threeValues);
      return new HandScore(HandValue.FULL_HOUSE, secondaryScores);
    }
    if (isFlush) {
      return new HandScore(HandValue.FLUSH, secondaryScores);
    }
    if (isStraight) {
      return new HandScore(HandValue.STRAIGHT, secondaryScores);
    }
    if (threes.size() > 0) {
      secondaryScores.addAll(0, threeValues);
      return new HandScore(HandValue.THREE_OF_A_KIND, secondaryScores);
    }
    if (pairs.size() > 1) {
      secondaryScores.addAll(0, pairValues);
      return new HandScore(HandValue.TWO_PAIRS, secondaryScores);
    }
    if (pairs.size() > 0) {
      secondaryScores.addAll(0, pairValues);
      return new HandScore(HandValue.PAIR, secondaryScores);
    }
    return new HandScore(HandValue.HIGH_CARD, secondaryScores);
  }


  public static int evaluateGame(List<List<Card>> hands) {
    if (hands.size()<1) throw new RuntimeException("At least 2 hands are required for a valid game");
    //TODO: not handled duplicate cards issue, occurring is hands are created manually
    if (hands.stream().flatMap(Collection::stream).collect(Collectors.toSet()).size()<hands.size()*5) System.err.println("WARN: There seems to be a duplicated card");

    int winner = 0;
    var winnerScore = evaluateHand(hands.get(0));

    for (int playerIdx = 1; playerIdx < hands.size(); playerIdx++) {
      var playerScore = evaluateHand(hands.get(playerIdx));
      if (playerScore.compareTo(winnerScore) > 0) {
        winner = playerIdx;
        winnerScore = playerScore;
      }
    }
    return winner;
  }
}
