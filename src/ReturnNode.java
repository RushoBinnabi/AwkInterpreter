/**
 * Name: Rusho Binnabi
 * Date: 10/9/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */

public class ReturnNode extends StatementNode {

    // this ReturnNode class has the return statement type for parsing return statements by the parser.

    private Node returnParseOperation;

    /**
     * this ReturnNode() constructor initializes the objects and variables.
     * @param node the return statement type.
     */

    public ReturnNode(Node node) {
        returnParseOperation = node;
    }

    /**
     * this setReturnParseOperation() method sets the return statement to return the result of the parseOperation() method.
     * @param node the return statement to return the result of the parseOperation() method.
     */
    public void setReturnParseOperation(Node node) {
        returnParseOperation = node;
    }

    /**
     * this getReturnParseOperation() gets the return statement that returns the result of the parseOperation() method.
     * @return the result of the parseOperation() method.
     */
    public Node getReturnParseOperation() {
        return returnParseOperation;
    }

    /**
     * this toString() method creates a string representation of the values of the return statement types when it's used by the parser.
     * @return a string representation of the values of the return statement types when it's used by the parser.
     */

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getReturnParseOperation().toString().charAt(i); i++) {
            output.append("ReturnNode(").append(getReturnParseOperation()).append(")").append("+");
        }
        return output.toString();
    }
}
