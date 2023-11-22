import java.util.Optional;

/**
 * Name: Rusho Binnabi
 * Date: 10/9/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */

public class IfNode extends StatementNode {

    // this IfNode class has the if statement type for parsing if statements by the parser.
    private Node parseOperationCondition;
    private BlockNode parseBlockStatements;
    private Optional<Node> next;

    /**
     * this IfNode() constructor initializes the objects and variables.
     * @param parseBlockStatements the if statement types from the parseBlock() method.
     * @param condition the condition from the parseOperation() method on whether it succeeded.
     * @param nextNode the next if statement type.
     */
    public IfNode(BlockNode parseBlockStatements, Node condition, Node nextNode) {
        this.parseBlockStatements = parseBlockStatements;
        parseOperationCondition = condition;
        next = Optional.ofNullable(nextNode);
    }

    /**
     * this setNext() method sets the next node when parsing if statement types.
     * @param nextNode the next node.
     */

    public void setNext(Node nextNode) {
        next = Optional.ofNullable((IfNode) nextNode);
    }

    /**
     * this getNext() method gets the next node when parsing if statement types.
     * @return the next node.
     */

    public Optional<Node> getNext() {
        return next;
    }

    /**
     * this setParseBlockStatements() method sets the if statement type for parsing a block of statements.
     * @param parseBlockStatements the block of if statement types from the parseBlock() method.
     */

    public void setParseBlockStatements(BlockNode parseBlockStatements) {
        this.parseBlockStatements = parseBlockStatements;
    }

    /**
     * this getParseBlockStatements() method gets the if statement types for parsing a block of statements.
     * @return the block of if statement types from the parseBlock() method.
     */

    public BlockNode getParseBlockStatements() {
        return parseBlockStatements;
    }

    /**
     * this setParseOperationCondition() method sets the condition for parsing a
     * block of statements based on the result of the parseOperation() method.
     * @param condition the condition for parsing a block of statements based on the result
     * of the parseOperation() method.
     */
    public void setParseOperationCondition(Node condition) {
        parseOperationCondition = condition;
    }

    /**
     * this getParseOperationCondition() method gets the condition for parsing a
     * block of statements based on the result of the parseOperation() method.
     * @return the condition for parsing a block of statements based on the result of the parseOperation() method.
     */
    public Node getParseOperationCondition() {
        return parseOperationCondition;
    }

    /**
     * this toString() method creates a string representation of the values of the condition for parsing a block of
     * statements based on the result of the parseOperation() method and the if statement types from the parseBlock() method.
     * @return a string representation of the values of the condition for parsing a block of statements based on the result
     * of the parseOperation() method and the if statement types from the parseBlock() method.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        while (getParseOperationCondition() != null) {
            if (getParseOperationCondition() == null) {
                break;
            }
            output.append("ParseOperationCondition(").append(getParseOperationCondition()).append(")").append("+");
        }
        for (int i = 0; i < getParseBlockStatements().toString().charAt(i); i++) {
            output.append("ParseIfBlockStatements(").append(getParseBlockStatements()).append(")").append("+");
        }
        Optional<Node> nextIfNode = getNext();
        if (nextIfNode.isPresent()) {
            Node node = nextIfNode.get();
            output.append("NextIfNode(").append(node).append(")").append("+");
        }
        return output.toString();
    }
}
