/**
 * Name: Rusho Binnabi
 * Date: 10/9/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */

import java.util.LinkedList;
import java.util.Optional;

public class FunctionCallNode extends StatementNode {

    // this FunctionCallNode class is used by the parser when parsing function calls with our without a list of parameters.

    private Node functionCallNode;
    private LinkedList<Node> parameters = new LinkedList<>();

    /**
     * this FunctionCallNode() initializes the objects and variables.
     * @param node the node which is the function being called.
     * @param parameters the list of parameters for the function.
     */
    public FunctionCallNode(Node node, LinkedList<Node> parameters) {
        functionCallNode = node;
        this.parameters = parameters;
    }

    /**
     * this setFunctionCallNode() method sets the node for parsing function calls when it's used by the parser.
     * @param node the name of the function.
     */

    public void setFunctionCallNode(Node node) {
        functionCallNode = node;
    }

    /**
     * this setParameters() method sets the linked list of type node of parameters when it's used by the parser.
     * @param nodes the list of parameters.
     */
    public void setParameters(LinkedList<Node> nodes) {
        parameters = nodes;
    }

    /**
     * this getFunctionCallNode() method gets the node for parsing function calls when it's used by the parser.
     * @return the name of the function.
     */
    public Node getFunctionCallNode() {
        return functionCallNode;
    }

    /**
     * this getParameters() method gets the linked list of type node of parameters when it's used by the parser.
     * @return the list of parameters.
     */
    public LinkedList<Node> getParameters() {
        return parameters;
    }

    /**
     * this toString() method creates a string representation of the values of the function calls and the list of parameters.
     * @return a string representation of the values of the function calls and the list of parameters.
     */

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Optional<Node> functionCalls = Optional.ofNullable(getFunctionCallNode());
        if (functionCalls.isPresent()) {
            Node node = functionCalls.get();
            output.append("Condition(").append(node).append(")").append("+");
        }
        Optional<LinkedList<Node>> params = Optional.ofNullable(getParameters());
        if (params.isPresent()) {
            LinkedList<Node> nodes = params.get();
            for (int i = 0; i < nodes.size(); i++) {
                output.append("Parameters(").append(nodes.get(i)).append(")").append("+");
            }
        }
        return output.toString();
    }
}
