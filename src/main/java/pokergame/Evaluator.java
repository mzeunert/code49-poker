package pokergame;

import java.util.ArrayList;
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

    if(isStraight && isFlush) return HandValue.STRAIGHT_FLUSH;
    return HandValue.HIGH_CARD;

  }
}
