import pokergame.HandGenerator;

public class Application {


  public static void main(String[] args) {
    System.out.println("Test");
    var deck = HandGenerator.generateDeck();
    deck.stream().forEach((c) -> System.out.println(c));
  }


}
