import java.util.Random;
import java.util.Scanner;

/**
 * This class represents a simple conversation simulator,
 * taking user-specified number of rounds.
 */
class Conversation {

  // Attributes
  /**
   * Defines the data type of variables
   * and initializes array of canned responses and word pairs for replacements
   */
  private int round;
  private String[] transcript;
  private static String[] responses = { "Aha", "Mmm-hm", "Oh, okay", "I see", "Cool" };
  private int num = 0;
  private String[][] replacements = { { "I'm", "You" }, { "I am", "You are" }, { "I", "You" }, { "me", "you" },
      { "am", "are" }, { "you", "I" }, { "my", "your" }, { "your", "my" }, { "are", "am" } };

  // Constructor
  public Conversation() {
  }

  // Methods

  /**
   * Asks how many rounds of conversation would the user play
   * and initializes the transcript array based on the number of rounds
   */
  public void askRound() {
    Scanner myObj = new Scanner(System.in);
    System.out.println("How many rounds?");
    this.round = myObj.nextInt();
    this.transcript = new String[this.round * 2 + 2];
  }

  /**
   * Prints the introduction message and stores it in the transcript
   */
  public void intro() {
    System.out.println("Hi there! What's on your mind?");
    this.transcript[num] = "Hi there! What's on your mind?";
  }

  /**
   * Takes user input and generates a response,
   * replacing certain words or selecting random canned responses
   */
  public void converse() {
    Scanner yourAnswer = new Scanner(System.in);
    for (int t = 1; t <= this.round; t = t + 1) {
      String input = yourAnswer.nextLine();
      this.transcript[++num] = input;

      String[] wordOfInput = input.split(" ");
      for (int i = 0; i < wordOfInput.length; i++) {
        for (int f = 0; f < replacements.length; f++) {
          if (wordOfInput[i].equals(replacements[f][0])) {
            wordOfInput[i] = replacements[f][1];
            break;
          }
        }
      }
      String finalAns = String.join(" ", wordOfInput);
      if (finalAns.equals(input)) {
        Random random = new Random();
        int randomIndex = random.nextInt(responses.length);
        System.out.println(responses[randomIndex]);
        this.transcript[++num] = responses[randomIndex];
      } else {
        System.out.println(finalAns + "?");
        this.transcript[++num] = finalAns + "?";
      }
    }
  }

  /**
   * Prints the outro message and stores it in the transcript
   */
  public void outro() {
    System.out.println("See ya!");
    this.transcript[this.round * 2 + 1] = "See ya!";
  }

  public static void main(String[] arguments) {

    Conversation myGame = new Conversation();

    myGame.askRound();
    myGame.intro();
    myGame.converse();
    myGame.outro();

    System.out.println("\n----This is your transcript----");
    for (int a = 0; a < myGame.transcript.length; a++) {
      System.out.println(myGame.transcript[a]);
    }
  }
}