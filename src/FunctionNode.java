/**
 * Name: Rusho Binnabi
 * Date: 9/15/2023
 * Assignment: Parser 1
 * Class: ICSI 311 - Fall 2023
 */

import java.util.LinkedList;

public class FunctionNode extends Node {

    // this FunctionNode class contains a function name, a linked list of parameters and a linked list of statement nodes.
    private String functionName;
    private LinkedList<Node> parameters = new LinkedList<>();
    private LinkedList<StatementNode> statements = new LinkedList<>();

    /**
     * this setParameters() method sets the parameter of the FunctionNode of the tree.
     * @param parameter the FunctionNode node to be added to the linked list.
     */
    public void setParameters(Node parameter) {
        parameters.add(parameter);
    }

    /**
     * this getParameters() method gets the parameter of the FunctionNode of the tree.
     * @return a function node of the FunctionNode.
     */
    public LinkedList<Node> getParameters() {
        return parameters;
    }

    /**
     * this setStatements() method sets the statements of the StatementNode of the tree.
     * @param statement the StatementNode node to be added to the linked list.
     */
    public void setStatements(StatementNode statement) {
        statements.add(statement);
    }

    /**
     * this getStatementNode() gets the statement node of the StatementNode of the tree.
     * @return a statement node of the StatementNode.
     */
    public LinkedList<StatementNode> getStatementNode() {
        return statements;
    }

    /**
     * this FunctionNode() constructor initializes all the objects and variables.
     * @param name the name of the function.
     * @param parameter the linked list of parameters.
     * @param statement the linked list of statements
     */

    public FunctionNode(String name, LinkedList<Node> parameter, LinkedList<StatementNode> statement) {
        functionName = name;
        parameters = parameter;
        statements = statement;
    }

    /**
     * this getFunctionName() method gets the function name.
     * @return the function name.
     */

    public String getFunctionName() {
        return functionName;
    }

    /**
     * this setFunctionName() sets the function name.
     * @param s the name of the function.
     */
    public void setFunctionName(String s) {
        functionName = s;
    }

    /**
     * this toString() method creates a string representation of FunctionNode and StatementNode nodes.
     * @return a string representation of FunctionNode and StatementNode nodes.
     */

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Node parameter : getParameters()) {
            output.append("FunctionNode( ").append(parameter).append(")").append("+");
        }
        for (StatementNode statement : getStatementNode()) {
            output.append("StatementNode( ").append(statement).append(")").append("+");
        }
        return output.toString();
    }
}