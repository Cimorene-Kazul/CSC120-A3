import java.util.Scanner;
import java.util.Dictionary;
import java.util.Hashtable;

class Conversation implements Chatbot {

  // Attributes 
  int rounds; // number of rounds of converstaion remaining
  String[] greetings = {"Hi!", "Hello!", "Hey!"}; // list of greetings
  String[] starters = {"What's up?", "What is on your mind?", "Whatcha thinking about?"}; // list of starters for conversation
  String[] punctuation = {"", ".",",", ";", "!", "?"}; // list of punctuation to watch for at the end of words
  // list of mirror words. first word in each list is a the word with the second being its mirror. Replace top words first.
  String[][] replacementsBase = {
    {"i", "you"},
    {"me", "you"},
    {"am", "are"},
    {"are", "am"},
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
  Dictionary<String, String> replacements = new Hashtable<>(); // Dictionary to put keys as words to mirror and the mirrored result as the value
  String[] mirrorWords = new String[replacementsBase.length * punctuation.length]; // list to keep all the mirror words in
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
   * Constructor for a chatbot
   */
  Conversation() {
    Scanner input = new Scanner(System.in);

    // fill up the list of mirror words and mirror word dictionary
    for (int i = 0; i < this.replacementsBase.length; i++){
      for (int j = 0; j < this.punctuation.length; j++){
        // add each punctuation to the end of the mirror word and it's mirror and then put them as a key-value pair in the dictionary
        this.replacements.put(this.replacementsBase[i][0] + this.punctuation[j] , this.replacementsBase[i][1] + this.punctuation[j]);
        // put the mirror word with punctuation in the list of mirror words
        this.mirrorWords[i*this.punctuation.length + j] = this.replacementsBase[i][0] + this.punctuation[j];
      }
    }
    
    // get the number of rounds
    System.out.println("How many rounds would you like to talk? ");
    this.rounds = input.nextInt();
    System.out.println("\n");
    // have a conversation
    this.chat();
    System.out.println("\n");
    // print the transcript
    this.printTranscript();
    // close the Scanner - don't forget this!!!
    input.close();
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
    int index = (int)(Math.random()*choices.length); // random number between 0 and the length of the list minus 1 to be a random index
    this.say(choices[index]); // say the chosen thing
 }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner input = new Scanner(System.in);
    // greet the user
    this.pick(this.greetings);
    this.pick(this.starters);
    // do rounds many rounds of converation
    while (this.rounds > 0){
      this.rounds -= 1;
      String userStatement = input.nextLine();
      this.transcript += "\n" + userStatement;
      this.say(this.respond(userStatement));
    };
    // leave the conversation
    this.pick(this.exits);
    this.pick(this.goodbyes);
    input.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println(this.transcript);
  }
  
  /**
   * Capitalizes the first letter of the input
   * @param inputString 
   * @return inputString with the first letter capitalized
   */
  public String capitalizeFirstLetter(String inputString){
    if (inputString.substring(0,1)=="." || inputString.substring(0,1)=="!" || inputString.substring(0,1)=="?"){
      return inputString; // don't do anything if the first value is punctuation - it will mess up the regex and not change anyways
    } else {
    return inputString.replaceFirst(inputString.substring(0,1), inputString.substring(0,1).toUpperCase()); // otherwise capitalize the first letter
    }
  }

  /**
   * Gives a mirrored version of the input parameter.
   * @param inputString string to mirror, all in lowercase
   * @return mirrored version of inputString
   */
  public String mirror(String inputString){
    String mirroredString;
    String [] sentences;
    // replace words with their mirrors
    String[] inputWords = inputString.split(" ");
    for (int i=0; i<inputWords.length; i++){
      if (this.replacements.get(inputWords[i])== null){ // the case where there is no value in the dictionary with key inputWords[i]
        inputWords[i] = inputWords[i];
      } else { // when inputWords[i] is actually a key
        inputWords[i] = replacements.get(inputWords[i]); // replace the word with it's mirror
      }
    }
    mirroredString = "".join(" ", inputWords) + "  ";
    // capitalize sentences
    // first sentence and anything after a "."
    sentences = mirroredString.split("\\. ");
    for (int i = 0; i<sentences.length; i++){
      sentences[i] = this.capitalizeFirstLetter(sentences[i]);
    }
    // turn comments into questions
    mirroredString = "".join("? ", sentences);
    // anything after a "!"
    sentences = mirroredString.split("! ");
    for (int i = 0; i<sentences.length; i++){
      sentences[i] = this.capitalizeFirstLetter(sentences[i]);
    }
    mirroredString = "".join("! ", sentences);
    // anything after a "?"
    sentences = mirroredString.split("\\? ");
    for (int i = 0; i<sentences.length; i++){
      sentences[i] = this.capitalizeFirstLetter(sentences[i]);
    }
    // turn questions into comments
    mirroredString = "".join(". ", sentences);
    return mirroredString;
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    boolean mirror = false;
    String returnString;
    // figure out to use canned or mirrored response
    inputString = inputString.toLowerCase(); // make input lowercase to ease checking
    // if it has a mirror word, make mirror true
    for (String word: this.mirrorWords){
      if ((" "+inputString).contains(word)){ 
        mirror = true;
      };
    };
    if (mirror){ // if there are mirror words
      returnString = this.mirror(inputString); // return a mirrored version of the string
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

  public static void main(String[] args) {
    Conversation myConversation = new Conversation(); // make a new conversation, which will chat on creation
  }
}
