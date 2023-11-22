/**
 * Name: Rusho Binnabi
 * Date: 10/8/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */

public class ContinueNode extends StatementNode {

    // this ContinueNode class has the continue statement type for parsing continue statements by the parser.

    private Node canContinue;

    /**
     * this getCanContinue() method gets the continue statement type when it's used by the parser for parsing continue statements.
     * @return the node of continue statement types.
     */
    public Node getCanContinue() {
        return canContinue;
    }

    /**
     * this setCanContinue() method sets the continue statement type when it's used by the parser for parsing continue statements.
     * @param canContinue the continue statement type for parsing continue statements.
     */
    public void setCanContinue(Node canContinue) {
        this.canContinue = canContinue;
    }

    /**
     * this ContinueNode() constructor initializes the objects and variables.
     */
    public ContinueNode(Node node) {
        canContinue = node;
    }

    /**
     * this toString() method creates a string representation of the values of the continue statement types when it's used by the parser.
     * @return a string representation of the values of the continue statement types when it's used by the parser.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getCanContinue().toString().charAt(i); i++) {
            output.append("ContinueNode(").append(getCanContinue()).append(")").append("+");
        }
        return output.toString();
    }

}
