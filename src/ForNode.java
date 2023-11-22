/**
 * Name: Rusho Binnabi
 * Date: 10/9/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */
public class ForNode extends StatementNode {

    // this ForNode class has the for and for-each loop statement types for parsing for and for-each statements by the parser.

    private StatementNode forNode;
    private StatementNode forEachNode;

    /**
     * this ForNode() constructor initializes the objects and variables.
     * @param forNode for node statement type.
     * @param forEachNode for-each statement type.
     */

    public ForNode(StatementNode forNode, StatementNode forEachNode) {
        this.forNode = forNode;
        this.forEachNode = forEachNode;
    }

    /**
     * this setForNode() method sets the for statement type when it's used by the parser.
     * @param setNode the for statement type for parsing for statements.
     */

    public void setForNode(StatementNode setNode) {
        forNode = setNode;
    }

    /**
     * this setForEachNode() method sets the for-each statement type when it's used by the parser.
     * @param setNode the for-each statement type for parsing for-each statements.
     */

    public void setForEachNode(StatementNode setNode) {
        forEachNode = setNode;
    }

    /**
     * this getForNode() method gets the for statement type when it's used by the parser.
     * @return the for statement type for parsing for statements.
     */
    public StatementNode getForNode() {
        return forNode;
    }

    /**
     * this getForEachNode() method gets the for-each statement type when it's used by the parser.
     * @return the for-each statement type for parsing for statements.
     */
    public StatementNode getForEachNode() {
        return forEachNode;
    }

    /**
     * this toString() method creates a string representation of the values for the for and for-each
     * statement types when it's used by the parser.
     * @return a string representation of the values for the for and for-each statement types when it's
     * used by the parser.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getForNode().toString().charAt(i); i++) {
            output.append("ForNode(").append(getForNode()).append(")").append("+");
        }
        for (int i = 0; i < getForEachNode().toString().charAt(i); i++) {
            output.append("ForEachNode(").append(getForNode()).append(")").append("+");
        }
        return output.toString();
    }
}
