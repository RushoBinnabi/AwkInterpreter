/**
 * Name: Rusho Binnabi
 * Date: 9/29/2023
 * Assignment: Parser 2
 * Class: ICSI 311 - Fall 2023
 */

import java.util.Optional;

public class OperationNode extends Node {

    // this OperationNode class is a node type that has two other nodes and an operation type.
    // it tries to work with math operations and enforce order of operations.

    private Node leftNode;
    private Optional<Node> rightNode;

    /**
     * this Operations enum has the enum values for the types of math operations,
     * keywords, etc., that the operation node will have and use.
     */
    public enum Operations {
        EQ, NE, LT, LE, GT, GE, AND, OR, NOT, MATCH, NOTMATCH, DOLLAR,
        PREINC, POSTINC, PREDEC, POSTDEC, UNARYPOS, UNARYNEG, IN, EXPONENT,
        ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO, CONCATENATION, OPENARRAY, CLOSEARRAY }

    private Operations operation;
    private String operationValue;

    /**
     * this setOperationValue() method sets the value for the operation.
     * @param value the value for the operation.
     */
    public void setOperationValue(String value) {
        operationValue = value;
    }

    /**
     * this getOperationValue() method gets the value of the operation.
     * @return the value of the operation.
     */
    public String getOperationValue() {
        return operationValue;
    }

    /**
     * this setOperation() method sets the operation type for the type of operation that will be done.
     * @param operation the type of operation that will be done.
     */

    public void setOperation(OperationNode.Operations operation) {
        this.operation = operation;
    }

    /**
     * this getOperation() gets the operation type for the type of operation that was done.
     * @return the type of operation that was done.
     */

    public Operations getOperation() {
        return operation;
    }

    /**
     * this setLeftNode() method sets the left node.
     * @param left the left node.
     */

    public void setLeftNode(Node left) {
        leftNode = left;
    }

    /**
     * this getLeftNode() method gets the left node.
     * @return the left node.
     */

    public Node getLeftNode() {
        return leftNode;
    }

    /**
     * this setRightNode() method sets the right node.
     * @param right the right node.
     */

    public void setRightNode(Optional<Node> right) {
        rightNode = right;
    }

    /**
     * this Optional getRightNode() method gets the right node.
     * @return the right node.
     */

    public Optional<Node> getRightNode() {
        return rightNode;
    }

    /**
     * this OperationNode() constructor initializes the objects and variables.
     * @param leftNode the left node.
     * @param operation the type of operation.
     * @param rightNode the right node.
     */

    public OperationNode(Node leftNode, OperationNode.Operations operation, Optional<Node> rightNode) {
        this.leftNode = leftNode;
        this.operation = operation;
        this.rightNode = rightNode;
    }

    /**
     * this toString() method creates a string representation of the operation node.
     * @return a string representation of the operation node.
     */

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        for (int i = 0; i < getOperation().toString().charAt(i); i++) {
            if (getOperation() == OperationNode.Operations.EQ) {
                toString.append(Operations.EQ.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.NE) {
                toString.append(OperationNode.Operations.NE.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.LT) {
                toString.append(OperationNode.Operations.LT.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.LE) {
                toString.append(OperationNode.Operations.LE.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.GT) {
                toString.append(OperationNode.Operations.GT.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.GE) {
                toString.append(OperationNode.Operations.GE.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.AND) {
                toString.append(OperationNode.Operations.AND.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.OR) {
                toString.append(OperationNode.Operations.OR.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.NOT) {
                toString.append(OperationNode.Operations.NOT.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.MATCH) {
                toString.append(OperationNode.Operations.MATCH.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.NOTMATCH) {
                toString.append(OperationNode.Operations.NOTMATCH.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.DOLLAR) {
                toString.append(OperationNode.Operations.DOLLAR.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.PREINC) {
                toString.append(OperationNode.Operations.PREINC.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.POSTINC) {
                toString.append(OperationNode.Operations.POSTINC.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.PREDEC) {
                toString.append(OperationNode.Operations.PREDEC.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.POSTDEC) {
                toString.append(OperationNode.Operations.POSTDEC.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.UNARYPOS) {
                toString.append(OperationNode.Operations.UNARYPOS.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.UNARYNEG) {
                toString.append(OperationNode.Operations.UNARYNEG.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.IN) {
                toString.append(OperationNode.Operations.IN.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.EXPONENT) {
                toString.append(OperationNode.Operations.EXPONENT.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.ADD) {
                toString.append(OperationNode.Operations.ADD.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.SUBTRACT) {
                toString.append(OperationNode.Operations.SUBTRACT.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.MULTIPLY) {
                toString.append(OperationNode.Operations.MULTIPLY.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.DIVIDE) {
                toString.append(OperationNode.Operations.DIVIDE.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.MODULO) {
                toString.append(OperationNode.Operations.MODULO.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.CONCATENATION) {
                toString.append(OperationNode.Operations.CONCATENATION.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.OPENARRAY) {
                toString.append(OperationNode.Operations.OPENARRAY.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
            else if (getOperation() == OperationNode.Operations.CLOSEARRAY) {
                toString.append(OperationNode.Operations.CLOSEARRAY.name()).append("(").append(getOperationValue()).append(")").append("+");
            }
        }
        return toString.toString();
    }
}