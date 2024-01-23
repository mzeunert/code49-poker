package pokergame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {
  public static HandScore evaluateHand(List<Card> handIn){
    assert(handIn.size()==5);
    var hand = new ArrayList<>(handIn);
    //ordering hand by value high ot low
    hand.sort((a,b)->b.value().compareTo(a.value()));
    //grouping by value to easily extract same value groups
    var groupedByValues = hand.stream().collect(Collectors.groupingBy(Card::value));
    var pairs = groupedByValues.values().stream().filter((c)->c.size()==2).collect(Collectors.toList());
    var threes = groupedByValues.values().stream().filter((c)->c.size()==3).collect(Collectors.toList());
    var fours = groupedByValues.values().stream().filter((c)->c.size()==4).collect(Collectors.toList());
    //check if we have a flush
    var isFlush = hand.stream().allMatch((c)->c.suit().equals(hand.get(0).suit()));
    //check for straight
    var handIterator = hand.listIterator();
    var prev = handIterator.next();
    boolean isStraight = true;
    while (handIterator.hasNext()){
      var current = handIterator.next();
      if(current.value().ordinal()+1!=prev.value().ordinal()){
        isStraight = false;
      }
      prev = current;
    }
    //now use early return to return highest handvalue
    if(isStraight && isFlush) return new HandScore(HandValue.STRAIGHT_FLUSH,List.of(0));
    if(fours.size()>0) return new HandScore(HandValue.FOUR_OF_A_KIND, List.of(0));
    if(threes.size()>0 && pairs.size()>0) return new HandScore(HandValue.FULL_HOUSE, List.of(0));
    if(isFlush) return new HandScore(HandValue.FLUSH, List.of(0));
    if(isStraight) return new HandScore(HandValue.STRAIGHT, List.of(0));
    if(threes.size()>0) return new HandScore(HandValue.THREE_OF_A_KIND, List.of(0));
    if(pairs.size()>1) return new HandScore(HandValue.TWO_PAIRS, List.of(0));
    if(pairs.size()>0) return new HandScore(HandValue.PAIR, List.of(0));
    return new HandScore(HandValue.HIGH_CARD, hand.stream().map((c)->c.value().ordinal()).toList());
  }


  public static int evaluateGame(List<List<Card>> hands){
    assert (hands.size()>0);

    int winner = 0;
    var winnerScore = evaluateHand(hands.get(0));

    for(int playerIdx = 1;playerIdx<hands.size();playerIdx++){
      var playerScore = evaluateHand(hands.get(playerIdx));
      if(playerScore.compareTo(winnerScore)>0){
        winner = playerIdx;
        winnerScore = playerScore;
      }
    }
    return winner;
  }
}
