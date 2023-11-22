/**
 * Name: Rusho Binnabi
 * Date: 9/15/2023
 * Assignment: Parser 1
 * Class: ICSI 311 - Fall 2023
 */

import java.util.LinkedList;
import java.util.Optional;

public class BlockNode extends Node {

    // this BlockNode class gets used when the Parser creates BlockNodes for statements using a linked list
    // and an Optional for conditions which decides when to run this block.

    private LinkedList<StatementNode> statements;
    private Optional<Node> condition;

    /**
     * this setStatements() method sets the linked list of StatementNode statements.
     * @param statement the linked list of StatementNode statements to be set.
     */
    public void setStatements(LinkedList<StatementNode> statement) {
        statements = statement;
    }

    /**
     * this getStatements() method gets the linked list of StatementNode statements.
     * @return the linked list of StatementNode statements.
     */

    public LinkedList<StatementNode> getStatements() {
        return statements;
    }

    /**
     * this setCondition() method sets the Optional<Node> condition for deciding when to run the block
     * @param condition the Optional<Node> condition for deciding when to run the block gets set.
     */

    public void setCondition(Optional<Node> condition) {
        this.condition = condition;
    }

    /**
     * this getCondition() method gets the Optional<Node> condition for deciding when to run the block.
     * @return the Optional<Node> condition for deciding when to run the block.
     */

    public Optional<Node> getCondition() {
        return condition;
    }

    /**
     * this BlockNode() constructor initializes all the objects.
     * @param statement the linked list of statements.
     * @param bool the boolean condition of when to run the block.
     */

    public BlockNode(LinkedList<StatementNode> statement, Optional<Node> bool) {
        statements = statement;
        condition = bool;
    }

    /**
     * this toString() method creates a string representation of the block node.
     * @return a string representation of the block node.
     */

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (StatementNode state : getStatements()) {
            output.append("StatementNode( ").append(state).append(")").append("+");
        }
        Optional<Node> conditionOptional = getCondition();
        if (conditionOptional.isPresent()) {
            Node node = conditionOptional.get();
            output.append("Condition(").append(node).append(")").append("+");
        }
        return output.toString();
    }
}