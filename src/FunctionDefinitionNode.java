/**
 * Name: Rusho Binnabi
 * Date: 9/18/2023
 * Assignment: Parser 1
 * Class: ICSI 311 - Fall 2023
 */

import java.util.Collection;
import java.util.LinkedList;

public class FunctionDefinitionNode extends StatementNode {

    // this FunctionDefinitionNode class holds the function definition name, the collection of names for the parameters,
    // as well as a Linked List of statement nodes.
    private String functionName;

    private final Collection<Node> parameterName;
    private final LinkedList<StatementNode> statementNode;

    /**
     * this FunctionDefinitionNode() constructor initializes the objects and variables.
     * @param name the function name.
     * @param parameterCollection the collection of parameters.
     * @param statementList the linked list of statements.
     */
    public FunctionDefinitionNode(String name, Collection<Node> parameterCollection, LinkedList<StatementNode> statementList) {
        functionName = name;
        parameterName = new LinkedList<>(parameterCollection);
        statementNode = new LinkedList<>(statementList);
    }

    /**
     * this setParameterName() sets the parameter name of the FunctionDefinitionNode of the tree.
     * @param n the FunctionDefinitionNode node to be added to the collection.
     */

    public void setParameterName(Node n) {
        parameterName.add(n);
    }

    /**
     * this getParameterName() gets the parameter name of the FunctionDefinitionNode of the tree.
     * @return a parameter name of the FunctionDefinitionNode.
     */

    public Node getParameterName() {
        return parameterName.iterator().next();
    }

    /**
     * this setStatementNode() method sets the statement of the StatementNode of the tree.
     * @param statement the StatementNode node to be added to the linked list.
     */

    public void setStatementNode(StatementNode statement) {
        statementNode.add(statement);
    }

    /**
     * this getStatementNode() method gets the statement node of the StatementNode of the tree.
     * @param i the StatementNode that will be returned from the linked list.
     * @return a StatementNode.
     */

    public StatementNode getStatementNode(int i) {
        return statementNode.get(i);
    }

    /**
     * this getFunctionName() method gets the function name.
     * @return the function name.
     */

    public String getFunctionName() {
        return functionName;
    }

    /**
     * this setFunctionName() method sets the function name.
     * @param s the name of the function.
     */

    public void setFunctionName(String s) {
        functionName = s;
    }

    /**
     * this toString() method creates a string representation of FunctionDefinitionNode and StatementNode nodes.
     * @return a string representation of FunctionDefinitionNode and StatementNode nodes.
     */

    public String toString() {
        StringBuilder toString = new StringBuilder();
        for (int i = 0; i < parameterName.size(); i++) {
            toString.append("FunctionName(").append(getFunctionName()).append(")").append("+").append("ParameterNode(").append(parameterName.iterator().next()).append(")").append("+");
        }
        for (int i = 0; i < statementNode.size(); i++) {
            toString.append("StatementNode(").append(getFunctionName()).append(")").append("+").append("StatementNode(").append(statementNode.iterator().next()).append(")").append("+");
        }
        return toString.toString();
    }
}
