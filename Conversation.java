import java.util.Random;
import java.util.Scanner;
// import java.util.Arrays;

class Conversation {

  // Attributes
  public int round;
  public String[] transcript;
  public String[] responses = { "Aha", "Mmm-hm", "Oh, okay", "I see", "Cool" };
  public int num = 0;
  public String[][] replacements = { { "I'm", "You" }, { "I am", "You are" }, { "I", "You" }, { "me", "you" },
      { "am", "are" }, { "you", "I" }, { "my", "your" }, { "your", "my" } };

  // Constructors
  public Conversation() {
  }

  // Methods
  public void askRound() {
    Scanner myObj = new Scanner(System.in);
    System.out.println("How many rounds?");
    this.round = myObj.nextInt();
    this.transcript = new String[this.round * 2 + 2];
  }

  public void intro() {
    System.out.println("Hi there! What's on your mind?");
    this.transcript[num] = "Hi there! What's on your mind?";
  }

  public void converse() {

    for (int i = 1; i <= this.round; i = i + 1) {
      Scanner yourAnswer = new Scanner(System.in);
      String input = yourAnswer.nextLine();
      this.transcript[++num] = input;
      String temp = input;

      String[] wordOfInput = temp.split(" ");
      for (String each : wordOfInput) {
        for (String[] replace : replacements) {
          if (each.equals(replace[0])) {
            temp = temp.replace(each, replace[1]);
          }
        }
      }
      if (temp.equals(input)) {
        Random random = new Random();
        int randomIndex = random.nextInt(responses.length);
        temp = responses[randomIndex];
        System.out.println(temp);
        this.transcript[++num] = temp;
      } else {
        System.out.println(temp + "?");
        this.transcript[++num] = temp + "?";
      }
    }
  }

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

    // System.out.println(Arrays.toString(myGame.transcript));
  }
}