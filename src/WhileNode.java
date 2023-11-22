/**
 * Name: Rusho Binnabi
 * Date: 10/9/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */
public class WhileNode extends StatementNode {

    // simpler than the if node statement type, this WhileNode class has the while loop statement type for parsing while statements by the parser.

    private Node parseOperationCondition;
    private BlockNode parseWhileStatements;

    /**
     * this WhileNode() constructor initializes the objects and variables.
     * @param statements the node of while statement types.
     * @param condition the condition from the parseOperation() method on whether it succeeded.
     */

    public WhileNode(BlockNode statements, Node condition) {
        parseWhileStatements = statements;
        parseOperationCondition = condition;
    }

    /**
     * this setParseWhileStatements() method sets the while statement type for parsing while statements.
     * @param statements the node of while statement types.
     */

    public void setParseWhileStatements(BlockNode statements) {
        this.parseWhileStatements = statements;
    }

    /**
     * this getParseBlockStatements() method gets the while statement types for parsing while statements.
     * @return the node of while statement types.
     */
    public BlockNode getParseWhileStatements() {
        return parseWhileStatements;
    }

    /**
     * this setParseOperationCondition() method sets the condition for parsing while statements based on the result of the parseOperation() method.
     * @param condition the condition for parsing while statements based on the result of the parseOperation() method.
     */
    public void setParseOperationCondition(Node condition) {
        parseOperationCondition = condition;
    }

    /**
     * this getParseOperationCondition() method gets the condition for parsing while statements based on the result of the parseOperation() method.
     * @return the condition for parsing while statements based on the result of the parseOperation() method.
     */
    public Node getParseOperationCondition() {
        return parseOperationCondition;
    }

    /**
     * this toString() method creates a string representation of the values of the condition for parsing
     * while statements based on the result of the parseOperation() method and the while statement types.
     * @return a string representation of the values of the condition for parsing while statements based
     * on the result of the parseOperation() method and the while statement types.
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
        for (int i = 0; i < getParseWhileStatements().toString().charAt(i); i++) {
            output.append("WhileNodeStatements(").append(getParseWhileStatements()).append(")").append("+");
        }
        return output.toString();
    }
}
