// this is a file to let me test random numbers and other things that I might need.
// I am leaving this here so that you can see my thought processes.
public class Test {
    String testing = "10";
    Test(){
    }
    public static void main(String[] args) {
        System.out.println((int)(Math.random()*3+1)); // Testing the random nunber built in that I had to look up

        // testing saving transcripts
        // A couple of forgetting ; bugs appeared
        String test_string = "";
        test_string += "Hi! \n";
        System.out.println(test_string);
        test_string += "Again.";
        System.out.println(test_string);

        // checking to see if values can be preset
        Test myTest = new Test();
        System.out.println(myTest.testing);
        // Answer affirmative! no need to define variables all Tests have in the declaration. :)

        //how do I work with lists test
        String[] testlist = {"1", "2", "3"}; // Discovered lists use curly braces?
        testlist[1] = "new";
        System.out.println(testlist[1]); // but you index the Python way
        // figuring out lists of lists
        String[][] testlist2 = {{"1", "2"}};
        System.out.println(testlist2[0][1]);

        for (String x: testlist){
            System.out.println(x);
        }
    
        // list lengths - upon googling, I uncovered that the attribute .length is  the length of a String[] object.
        System.out.println(testlist.length);

        //testing string methods
        String testString = "hi, how are you?";
        System.out.println(testString.replaceFirst(testString.substring(0,1), testString.substring(0,1).toUpperCase()));

        String[] sentences = {"12", "45"};
        System.out.println("".join(".", sentences));

        System.out.println("12hh33".length());
    }
}
