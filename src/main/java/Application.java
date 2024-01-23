import pokergame.Evaluator;
import pokergame.HandGenerator;

public class Application {

  public static void main(String[] args) {
    System.out.println("Hello Pokerplayers...");
    var game = HandGenerator.generateRandomHands(2);
    for (int i = 0; i < game.size(); i++) {
      System.out.println(String.format("Player %d hand:", i));
      System.out.println(game.get(i));
      var playerScore = Evaluator.evaluateHand(game.get(i));
      System.out.println(String.format("Player %s hand:", playerScore));
    }
    int winner = Evaluator.evaluateGame(game);
    var winnerScore = Evaluator.evaluateHand(game.get(winner));
    System.out.println(String.format("Player %d won with %s", winner, winnerScore));
  }
}
