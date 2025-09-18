
class Conversation implements Chatbot {

  // Attributes 
  int rounds; // number of rounds of converstaion remaining
  String[] greetings = {"Hi!", "Hello!", "Hey!"}; // list of greetings
  String[] starters = {"What's up?", "What is on your mind?", "Whatcha thinking about?"}; // list of starters for conversation
  // list of mirror words. first word in each list is a the word with the second being its mirror.
  String[][] replacements;
  String[] questonResponses; // for when asked a question
  String[] cannedContinues; //  canned responses for conversations with many rounds
  String[] cannedEnds; // canned responses for end of conversation
  // ways to end the conversation smoothly
  String[] exits = {"That's interesting, but I need to go.", 
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
    String returnString = "";
    return returnString; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.say("hi");
    myConversation.printTranscript();
  }
}
