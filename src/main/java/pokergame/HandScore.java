package pokergame;

import java.util.Comparator;
import java.util.List;

public record HandScore(HandValue handvalue, List<Integer> secondaryScores) implements Comparable<HandScore> {

  @Override
  public int compareTo(HandScore o) {
    var valueCompare = Comparator.comparing(HandScore::handvalue).compare(this, o);
    if (valueCompare != 0) return valueCompare;
    for (int i = 0; i < secondaryScores.size(); i++) {
      var comp = secondaryScores.get(i).compareTo(o.secondaryScores.get(i));
      if (comp != 0) return comp;
    }
    //should not be possible when hands are distinct
    return 0;
  }
}
