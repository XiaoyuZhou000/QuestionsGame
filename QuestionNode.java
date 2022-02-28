// Xiaoyu Zhou
// 03/05/2021
// CSE143
// Section: AP
// TA: Hitesh Boinpally
// Assignment #7
// This program represents a single branch in the tree

public class QuestionNode {
    public String question;
    public QuestionNode yesNode;
    public QuestionNode noNode;

    // This class will constructs a node with the given string and a null link
    // Parameter:
    //      String question - the given string to constructs a new node
    public QuestionNode(String question) {
        this(question, null, null);
    }

    // This class will constructs a node with the given string and link
    // Parameter:
    //      String question - the given string to constructs a new node
    //      QuestionNode yesNode - the given node to constructs a new link to correct answer
    //      QuestionNode noNode - the given node to constructs a new link to incorrect answer
    public QuestionNode(String question, QuestionNode yesNode, QuestionNode noNode) {
        this.question = question;
        this.yesNode = yesNode;
        this.noNode = noNode;
    }
}
