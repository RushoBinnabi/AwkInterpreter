/**
 * Name: Rusho Binnabi
 * Date: 10/6/2023
 * Assignment: Parser 3
 * Class: ICSI 311 - Fall 2023
 */
public class AssignmentNode extends StatementNode {

    // this AssignmentNode class handles all the assignments for the operations.
    private Node target;
    private Node expression;

    /**
     * this AssignmentNode() constructor initializes the objects and variables.
     * @param target the target for the assignment operation.
     * @param expression the expression for the assignment operation.
     */

    public AssignmentNode(Node target, Node expression) {
        this.target = target;
        this.expression = expression;
    }

    /**
     * this setTarget() method sets the target for the assignment operation.
     * @param node the target for the assignment operation.
     */
    public void setTarget(Node node) {
        target = node;
    }

    /**
     * this setExpression() method sets the expression for the assignment operation.
     * @param node the expression for the assignment operation.
     */
    public void setExpression(Node node) {
        expression = node;
    }

    /**
     * this getTarget() method gets the target for the assignment operation.
     * @return the target for the assignment operation.
     */
    public Node getTarget() {
        return target;
    }

    /**
     * this getExpression() method gets the expression for the assignment operation.
     * @return the expression for the assignment operation.
     */
    public Node getExpression() {
        return expression;
    }

    /**
     * this toString() method creates a string representation of the target and expression of the assignment operation.
     * @return a string representation of the target and expression of the assignment operation.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getTarget().toString().charAt(i); i++) {
            output.append("Target(").append(getTarget()).append(")").append("+");
        }
        for (int i = 0; i < getExpression().toString().charAt(i); i++) {
            output.append("Expression(").append(getExpression()).append(")").append("+");
        }
        return output.toString();
    }
}
