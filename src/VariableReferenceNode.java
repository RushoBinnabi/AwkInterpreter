/**
 * Name: Rusho Binnabi
 * Date: 9/29/2023
 * Assignment: Parser 2
 * Class: ICSI 311 - Fall 2023
 */

import java.util.Optional;

public class VariableReferenceNode extends Node {

    // this VariableReferenceNode class holds the values and expressions for a variable reference node.
    private String variableName;
    private Optional<Node> indexExpression;

    /**
     * this VariableReferenceNode() constructor initializes the objects and variables.
     * @param name the name of the variable.
     * @param index the expression for the index.
     */
    public VariableReferenceNode(String name, Optional<Node> index) {
        variableName = name;
        indexExpression = index;
    }

    /**
     * this setIndexExpression() method sets the value for the index expression.
     * @param indexExpression the expression for the index.
     */

    public void setIndexExpression(Optional<Node> indexExpression) {
        this.indexExpression = indexExpression;
    }

    /**
     * this getIndexExpression() method gets the value for the index expression.
     * @return the expression for the index.
     */

    public Optional<Node> getIndexExpression() {
        return indexExpression;
    }

    /**
     * this setVariableName() sets the value for the name of the variable.
     * @param name the name of the variable.
     */
    public void setVariableName(String name) {
        variableName = name;
    }

    /**
     * this getVariableName() gets the value for the name of the variable.
     * @return the name of the variable.
     */

    public String getVariableName() {
        return variableName;
    }

    /**
     * this toString() method creates a string representation for the name of the variable and the expression for the index.
     * @return a string representation of the name of the variable and the expression for the index.
     */

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        for (int i = 0; i < getVariableName().charAt(i); i++) {
            toString.append("VariableName(").append(getVariableName().charAt(i)).append(")").append("+").append(" ");
        }
        while (getIndexExpression().isPresent()) {
            toString.append("IndexExpression(").append(getIndexExpression()).append(")").append("+").append(" ");
        }
        return toString.toString();
    }
}
