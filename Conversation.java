import java.util.Scanner;

class Conversation implements Chatbot {

  // Attributes 
  int rounds; // number of rounds of converstaion remaining
  String[] greetings = {"Hi!", "Hello!", "Hey!"}; // list of greetings
  String[] starters = {"What's up?", "What is on your mind?", "Whatcha thinking about?"}; // list of starters for conversation
  String[] punctuation = {"", "\\.",",", ";", "\\!", "\\?"}; // list of punctuation to watch for at the end of words
  // list of mirror words. first word in each list is a the word with the second being its mirror. Replace top words first.
  String[][] replacementsBase = {
    {"i", "you"},
    {"me", "you"},
    {"am", "are"},
    {"you", "I"},
    {"my", "your"},
    {"your", "my"},
    {"mine", "yours"},
    {"yours", "mine"},
    {"i'm", "you're"},
    {"you're", "I'm"},
    {"myself", "yourself"},
    {"yourself", "myself"}
  };
  String[][] replacements = new String[replacementsBase.length * punctuation.length][2];
  String[] mirrorWords = new String[replacementsBase.length * punctuation.length];

  //  canned responses that draw a response
  String[] cannedContinues = {
    "Is that so?", 
    "Why?", 
    "Would you please elaborate?", 
    "Please tell me more."
  }; 
  String[] cannedEnds = {"Hmm.", "Ok.", "Yeah."}; // canned responses for end of conversation, meaning they do not try to draw a response
  // ways to end the conversation smoothly
  String[] exits = {
  "That's interesting, but I need to go.", 
  "It was fun talking to you, but you probably have to go now.",
  "That is cool! I hope we can talk more about it later, but I have to go now.",
  "I'm afraid we are out of time to talk, but this was a fun conversation."
  }; 
  String[] goodbyes = {"Bye!", "Goodbye!", "See you later!", "Seeya later!"}; // goodbyes
  String transcript = "TRANSCRIPT:"; // save transcript here. starting line is TRANSCRIPT:

  /**
   * Constructor 
   */
  Conversation() {
    Scanner input = new Scanner(System.in);

    for (int i = 0; i < this.replacementsBase.length; i++){
      for (int j = 0; j < this.punctuation.length; j++){
        this.replacements[i*this.punctuation.length + j][0] = " " + this.replacementsBase[i][0] + this.punctuation[j] + " ";
        this.replacements[i*this.punctuation.length + j][1] = " " + this.replacementsBase[i][1] + this.punctuation[j] + " ";
      }
    }

    for (int i = 0; i < this.replacements.length; i++){
      this.mirrorWords[i] = this.replacements[i][0];
    }
    
    System.out.println("How many rounds would you like to talk? ");
    this.rounds = input.nextInt();
    System.out.println("\n");
    this.chat();
    System.out.println("\n");
    this.printTranscript();
  }
  
  /**
   * Prints and adds to this.transcript the statement
   * @param thing the statement you want the chatbot to say
   */
  public void say(String thing){
    System.out.println(thing); // print the thing to say
    this.transcript+="\n"+thing; // add the thing to say to the transcript on a new line
  }  
  /**
  * Picks and says a random statement in a list of statements
  * @param choices the list of statements you want the chatbot to pick from to say
  */
 public void pick(String[] choices){
    int index = (int)(Math.random()*choices.length); // random number between 0 and the length of the list minus 1
    this.say(choices[index]); // say the chosen thing
 }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner input = new Scanner(System.in);
    this.pick(this.greetings);
    this.pick(this.starters);
    while (this.rounds > 0){
      this.rounds -= 1;
      String userStatement = input.nextLine();
      this.transcript += "\n" + userStatement;
      this.say(this.respond(userStatement));
    };
    this.pick(this.exits);
    this.pick(this.goodbyes);
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println(this.transcript);
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    boolean mirror = false;
    String intermediateString;
    String[] sentences;
    String returnString;
    // figure out to use canned or mirrored response
    inputString = " "+ inputString.toLowerCase();
    for (String word: this.mirrorWords){
      if (inputString.contains(word)){
        mirror = true;
      };
    };
    if (mirror){
      inputString.replace("?", "\\?");
      //if mirror, make a mirrored response
      intermediateString = inputString;
      for (String[] pair: this.replacements){
        intermediateString = intermediateString.replaceAll(pair[0], pair[1]);
      };
      // capitalizes first sentence and all sentences after .
      sentences = intermediateString.split("\\.");
      for (int i = 0; i<sentences.length; i++){
        if (sentences[i].length() >= 1){
        sentences[i].replaceFirst(sentences[i].substring(0,1), sentences[i].substring(0,1).toUpperCase());
        };
      };
      // turns statements into questions
      intermediateString = "".join("\\?", sentences);
      // capitalizes all sentences after !
      sentences = intermediateString.split("\\!");
      for (int i = 0; i<sentences.length; i++){
        if (sentences[i].length() >= 1){
        sentences[i].replaceFirst(sentences[i].substring(0,1), sentences[i].substring(0,1).toUpperCase());
        };
      };
      intermediateString = "".join("\\!", sentences);
      // capitalizes all sentences after ?
      sentences = intermediateString.split("\\?");
      for (int i = 0; i<sentences.length; i++){
        if (sentences[i].length() >= 1){
          sentences[i].replaceFirst(sentences[i].substring(0,1), sentences[i].substring(0,1).toUpperCase());
        };
      };
      // turns questions into statements
      intermediateString = "".join("\\.", sentences);
      returnString = intermediateString;
    } else {
      //otherwise, make a canned response
      if (this.rounds>0){
        //if there are still lines left, try and draw out more thoughts
        int index = (int)(Math.random()*this.cannedContinues.length);
        returnString = this.cannedContinues[index];
      } else {
        //otherwise, end noncommitally
        int index = (int)(Math.random()*this.cannedEnds.length);
        returnString = this.cannedEnds[index];
      };
    };
    return returnString; 
  }

  public static void main(String[] arguments) {
    Conversation myConversation = new Conversation();
  }
}
