/**
 * Name: Rusho Binnabi
 * Date: 10/6/2023
 * Assignment: Parser 3
 * Class: ICSI 311 - Fall 2023
 */

public class TernaryNode extends Node {

    // this TernaryNode class handles the ternary's for the operations.

    private Node condition;
    private Node consequent; // true case.
    private Node alternate; // false case.

    /**
     * this TernaryNode() constructor initializes the objects and variables.
     * @param condition the condition for the ternary operation.
     * @param consequent the consequent for the ternary operation.
     * @param alternate the alternate for the ternary operation.
     */

    public TernaryNode(Node condition, Node consequent, Node alternate) {
        this.condition = condition;
        this.consequent = consequent;
        this.alternate = alternate;
    }

    /**
     * this setCondition() method sets the condition for the ternary operation.
     * @param node the condition for the ternary operation.
     */

    public void setCondition(Node node) {
        condition = node;
    }

    /**
     * this setConsequent() method sets the consequent for the ternary operation.
     * @param node the consequent for the ternary operation.
     */
    public void setConsequent(Node node) {
        consequent = node;
    }

    /**
     * this setAlternate() method sets the alternate for the ternary operation.
     * @param node the alternate for the ternary operation.
     */
    public void setAlternate(Node node) {
        alternate = node;
    }

    /**
     * this getCondition() method gets the condition for the ternary operation.
     * @return the condition for the ternary operation.
     */
    public Node getCondition() {
        return condition;
    }

    /**
     * this getConsequent() method gets the consequent for the ternary operation.
     * @return the consequent for the ternary operation.
     */
    public Node getConsequent() {
        return consequent;
    }

    /**
     * this getAlternate() method gets the alternate for the ternary operation.
     * @return the alternate for the ternary operation.
     */
    public Node getAlternate() {
        return alternate;
    }

    /**
     * this toString() method creates a string representation of the condition,
     * consequent and alternate of the ternary operation.
     * @return a string representation of the condition, consequent and alternate ternary operations.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getConsequent().toString().charAt(i); i++) {
            output.append("Consequent(").append(getConsequent()).append(")").append("+");
        }
        for (int i = 0; i < getCondition().toString().charAt(i); i++) {
            output.append("Condition(").append(getCondition()).append(")").append("+");
        }
        for (int i = 0; i < getAlternate().toString().charAt(i); i++) {
            output.append("Alternate(").append(getAlternate()).append(")").append("+");
        }
        return output.toString();

    }
}
