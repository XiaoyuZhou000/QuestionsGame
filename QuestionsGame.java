import java.util.*;
import java.io.*;

// Xiaoyu Zhou
// 03/05/2021
// CSE143
// Section: AP
// TA: Hitesh Boinpally
// Assignment #7
// This program represents the 20 questions game and store the computer's current answer 
// tree for the game of 20 Questions.

public class QuestionsGame {
    private QuestionNode overallQuestion;
    private Scanner console;

    // Post: This method will construct and initialize a new tree root of game answer
    //       It is initialized to the deflaut root which is "computer".
    public QuestionsGame() {
        overallQuestion = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    // Post: This method will replace the current answer tree with a new answer tree 
    //       reading from a given file in a standard/pre-order format.
    // Parameter: 
    //      Scanner input - the given file want to be read in a standard/pre-order format. 
    public void read(Scanner input) {
        overallQuestion = readHelper(input);
    }

    // Post: This method will help to replace the current answer tree with a new answer
    //       tree reading from a given file in a standard/pre-order format. 
    // Parameter:
    //      Scanner input - the given file want to be read in a standard/pre-order format. 
    // Return: It will return the updated each level of branch of the answer tree.
    private QuestionNode readHelper(Scanner input) {
        String type = input.nextLine();
        String question = input.nextLine();
        QuestionNode root = new QuestionNode(question);
        if (!type.equals("A:")) {
            root.yesNode = readHelper(input);
            root.noNode = readHelper(input);
        } 
        return root;
    }

    // Post: This method will store and write out the current answer tree to an output file 
    //       by using given tool in standard format/pre-order format. This file can also be 
    //       used in future gameplay.
    // Parameter:
    //      PrintStream output - the given tool to output a file.
    public void write(PrintStream output) {
        write(output, overallQuestion);
    }

    // Post: This method will help to store and write out the current answer tree to an 
    //       output file by using given tool in standard format/pre-order format. 
    //       This file can also be used in future gameplay.
    // Parameter:
    //      PrintStream output - the given tool to output a file in standard 
    //                           format/pre-order format.
    //      QuestionNode root - the branch of the answer tree that need to be outputed 
    //                          to a file.
    private void write(PrintStream output, QuestionNode root) {
        if (root.yesNode == null) {
            output.println("A:\n" + root.question);
        } else {
            output.println("Q:\n" + root.question);
            write(output, root.yesNode);
            write(output, root.noNode);
        }
    }

    // Post: This method will ask yes or no questions to play the 20 questions game with 
    //       user. The correct or incorrect answer will be decided by the current answer 
    //       tree of the game.
    //       If the computer win the game (track down to the corrent answer successfully),
    //       this method will print out the winning words; otherwise, this method will 
    //       update the new object to the current answer tree by asking names, a question
    //       to indicate the object, and the yes or no answer to that question.
    public void askQuestions() {
        overallQuestion = askQuestions(overallQuestion, console);
    }

    // Post: This method will help to ask yes or no questions to play the 20 questions game 
    //       with user. The correct or incorrect answer will be decided by the current 
    //       answer tree of the game.
    //       If the computer win the game (track down to the corrent answer successfully),
    //       this method will print out the winning words; otherwise, this method will 
    //       update the new object to the current answer tree by asking names, a question
    //       to indicate the object, and the yes or no answer to that question.
    // Parameter: 
    //      QuestionNode root - the specific level of branch of the current answer tree 
    //      Scanner console - the tool to get user keyboard input
    // Return: It will return the updated each level of branch of the answer tree.
    private QuestionNode askQuestions(QuestionNode root, Scanner console) {
        if (root.yesNode == null) {
            if (yesTo("Would your object happen to be " + root.question + "?")) {
                System.out.println("Great, I got it right!");
            } else {
                QuestionNode falseAnswer = new QuestionNode(root.question);
                System.out.print("What is the name of your object? ");
                QuestionNode name = new QuestionNode(console.nextLine());
                System.out.println("Please give me a yes/no question that");
                System.out.println("distinguishes between your object");
                System.out.print("and mine--> ");
                String questionString = console.nextLine();
                if (yesTo("And what is the answer for your object? ")) {
                    return new QuestionNode(questionString, name, falseAnswer);
                } else {
                    return new QuestionNode(questionString, falseAnswer, name);
                }
            }
        } else if (yesTo(root.question)) {
            root.yesNode = askQuestions(root.yesNode, console);
        } else {
            root.noNode = askQuestions(root.noNode, console);
        }
        return root;
    }

    // Do not modify this method in any way
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}
