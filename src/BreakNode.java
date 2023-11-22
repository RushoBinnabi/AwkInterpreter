/**
 * Name: Rusho Binnabi
 * Date: 10/8/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */
public class BreakNode extends StatementNode {

    // this BreakNode class has the break statement type for parsing break statements by the parser.

    private Node canBreak;

    /**
     * this BreakNode() constructor initializes the objects and variables.
     */
    public BreakNode(Node node) {
        canBreak = node;
    }

    /**
     * this getCanContinue() method gets the break statement type when it's used by the parser for parsing break statements.
     * @return the node of break statement types.
     */
    public Node getCanBreak() {
        return canBreak;
    }

    /**
     * this setCanBreak() method sets the break statement type when it's used by the parser for parsing break statements.
     * @param canBreak the break statement type for parsing break statements.
     */
    public void setCanBreak(Node canBreak) {
        this.canBreak = canBreak;
    }

    /**
     * this toString() method creates a string representation of the values of the break statement types when it's used by the parser.
     * @return a string representation of the values of the break statement types when it's used by the parser.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getCanBreak().toString().charAt(i); i++) {
            output.append("BreakNode(").append(getCanBreak()).append(")").append("+");
        }
        return output.toString();
    }
}
