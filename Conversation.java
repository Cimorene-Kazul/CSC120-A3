
class Conversation implements Chatbot {

  // Attributes 
  int rounds; // number of rounds of converstaion remaining
  String[] greetings = {"Hi!", "Hello!", "Hey!"}; // list of greetings
  String[] starters = {"What's up?", "What is on your mind?", "Whatcha thinking about?"}; // list of starters for conversation
  // list of mirror words. first word in each list is a the word with the second being its mirror. Replace top words first.
  String[][] replacements = {
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
  // list of mirror words. make a generator for them, maybe, but for now do by hand
  String[] mirrorWords = {"i", "me", "am", "you", "my", "your", "yours", "mine", "i'm", "you're", "yourself", "myself"};

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
    String[] outputWords;
    String intermediateString;
    String[] sentences;
    String returnString;
    // figure out to use canned or mirrored response
    for (String word: this.mirrorWords){
      if (inputString.contains(word)){
        mirror = true;
      };
    };
    if (mirror){
      //if mirror, make a mirrored response
      inputString = inputString.toLowerCase();
      outputWords = inputString.split(" ");
      // replaces words in the list
      for (int i = 0; i<outputWords.length; i++){
        for (String[] pair: this.replacements){
          if (outputWords[i] == pair[0]){
            outputWords[i] = pair[1];
            break;
          }
        };
      };
      // joins words into a string
      intermediateString = "".join(" ", outputWords);
      // capitalizes first sentence and all sentences after .
      sentences = intermediateString.split(".");
      for (int i = 0; i<sentences.length; i++){
        sentences[i].replaceFirst(sentences[i].substring(0,1), sentences[i].substring(0,1).toUpperCase());
      }
      // turns statements into questions
      intermediateString = "".join("?", sentences);
      // capitalizes all sentences after !
      sentences = intermediateString.split("!");
      for (int i = 0; i<sentences.length; i++){
        sentences[i].replaceFirst(sentences[i].substring(0,1), sentences[i].substring(0,1).toUpperCase());
      }
      intermediateString = "".join("!", sentences);
      // capitalizes all sentences after ?
      sentences = intermediateString.split("?");
      for (int i = 0; i<sentences.length; i++){
        sentences[i].replaceFirst(sentences[i].substring(0,1), sentences[i].substring(0,1).toUpperCase());
      }
      // turns questions into statements
      intermediateString = "".join(".", sentences);
      returnString = intermediateString;
    } else {
      //otherwise, make a canned response
      if (rounds>0){
        //if there are still lines left, try and draw out more thoughts
        int index = (int)(Math.random()*cannedContinues.length);
        returnString = cannedContinues[index];
      } else {
        //otherwise, end noncommitally
        int index = (int)(Math.random()*cannedEnds.length);
        returnString = cannedEnds[index];
      }
    };
    return returnString; 
  }

  public static void main(String[] arguments) {
    Conversation myConversation = new Conversation();
    myConversation.chat();
  }
}
