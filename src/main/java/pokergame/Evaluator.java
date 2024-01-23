package pokergame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {
  public static HandValue evaluateHand(List<Card> handIn){
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
    if(isStraight && isFlush) return HandValue.STRAIGHT_FLUSH;
    if(fours.size()>0) return HandValue.FOUR_OF_A_KIND;
    if(threes.size()>0 && pairs.size()>0) return HandValue.FULL_HOUSE;
    if(isFlush) return HandValue.FLUSH;
    if(isStraight) return HandValue.STRAIGHT;
    if(threes.size()>0) return HandValue.THREE_OF_A_KIND;
    if(pairs.size()>1) return HandValue.TWO_PAIRS;
    if(pairs.size()>0) return HandValue.PAIR;
    return HandValue.HIGH_CARD;

  }
  record HandScore(HandValue handvalue, int secondaryScore) implements  Comparable<HandScore>{

    @Override
    public int compareTo(HandScore o) {
      return Comparator.comparing(HandScore::handvalue).thenComparing(HandScore::secondaryScore).compare(this,o);
    }
  }
  public static int evaluateGame(List<List<Card>> hands){
    assert (hands.size()>0);

    int winner = 0;
    var winnerScore = new HandScore(evaluateHand(hands.get(0)), 0);

    for(int playerIdx = 1;playerIdx<hands.size();playerIdx++){
      var playerScore = new HandScore(evaluateHand(hands.get(playerIdx)), 0);
      if(playerScore.compareTo(winnerScore)>0){
        winner = playerIdx;
        winnerScore = playerScore;
      }
    }
    return winner;
  }
}
