/**
 * Name: Rusho Binnabi
 * Date: 10/9/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */
public class DoWhileNode extends StatementNode {

    // similar to the while node statement type, this DoWhileNode class has the do-while loop statement type for parsing do-while statements by the parser.

    private Node parseOperationCondition;
    private BlockNode parseDoWhileStatements;

    /**
     * this DoWhileNode() constructor initializes the objects and variables.
     * @param statements the node of do-while statement types.
     * @param condition the condition from the parseOperation() method on whether it succeeded.
     */

    public DoWhileNode(BlockNode statements, Node condition) {
        parseDoWhileStatements = statements;
        parseOperationCondition = condition;
    }

    /**
     * this setParseDoWhileStatements() method sets the do-while statement type for parsing do-while statements.
     * @param statements the node of do-while statement types.
     */

    public void setParseDoWhileStatements(BlockNode statements) {
        parseDoWhileStatements = statements;
    }

    /**
     * this getParseDoWhileStatements() method gets the do-while statement types for parsing do-while statements.
     * @return the node of do-while statement types.
     */
    public BlockNode getParseDoWhileStatements() {
        return parseDoWhileStatements;
    }

    /**
     * this setParseOperationCondition() method sets the condition for parsing do-while statements based on the result of the parseOperation() method.
     * @param condition the condition for parsing do-while statements based on the result of the parseOperation() method.
     */

    public void setParseOperationCondition(Node condition) {
        parseOperationCondition = condition;
    }

    /**
     * this getParseOperationCondition() method gets the condition for parsing do-while statements based on the result of the parseOperation() method.
     * @return the condition for parsing do-while statements based on the result of the parseOperation() method.
     */
    public Node getParseOperationCondition() {
        return parseOperationCondition;
    }

    /**
     * this toString() method creates a string representation of the values of the condition for parsing
     * do-while statements based on the result of the parseOperation() method and the do-while statement types.
     * @return a string representation of the values of the condition for parsing do-while statements based
     * on the result of the parseOperation() method and the do-while statement types.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        while (getParseOperationCondition() != null) {
            if (getParseOperationCondition() == null){
                break;
            }
            output.append("ParseOperationCondition(").append(getParseOperationCondition()).append(")").append("+");
        }
        for (int i = 0; i < getParseDoWhileStatements().toString().charAt(i); i++) {
            output.append("DoWhileStatements(").append(getParseDoWhileStatements()).append(")").append("+");
        }
        return output.toString();
    }
}
