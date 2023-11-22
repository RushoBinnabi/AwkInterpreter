/**
 * Name: Rusho Binnabi
 * Date: 10/24/2023
 * Assignment: Interpreter 1
 * Class: ICSI 311 - Fall 2023
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Function;

public class BuiltInFunctionDefinitionNode extends FunctionDefinitionNode {

    // this BuiltInFunctionDefinitionNode class holds the functionality for the built-in functions using a lambda expression.
    private boolean isVariadic; // used to indicate if a function is variadic or not.

    /**
     * this setIsVariadic() method sets the value for the boolean value.
     * @param variadic the boolean value being set.
     */
    public void setIsVariadic(boolean variadic) {
        isVariadic = variadic;
    }

    /**
     * this getIsVariadic() method gets the value of the boolean.
     * @return the value of the boolean.
     */
    public boolean getIsVariadic() {
        return isVariadic;
    }

    /**
     * this BuiltInFunctionDefinitionNode() constructor initializes the objects and variables.
     * @param name the name of the function.
     * @param parameterCollection the collection of parameters.
     * @param statementList the list of statements.
     */

    public BuiltInFunctionDefinitionNode(String name, Collection<Node> parameterCollection, LinkedList<StatementNode> statementList) {
        super(name, parameterCollection, statementList);
    }

    /**
     * this Execute lambda function will be used whenever there is a BuiltInFunctionDefinitionNode
     * and there is work to be done within the BuiltInFunctionDefinitionNode.
     */
    public Function<HashMap<String,InterpreterDataType>,String> Execute;
}
