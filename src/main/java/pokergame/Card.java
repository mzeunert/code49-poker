package pokergame;

public record Card(CardSuit suit, CardValue value) {
  @Override
  public String toString() {
    return String.format("Card{suit=%s, value=%s}", suit, value);
  }
}
