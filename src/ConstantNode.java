/**
 * Name: Rusho Binnabi
 * Date: 9/29/2023
 * Assignment: Parser 2
 * Class: ICSI 311 - Fall 2023
 */
public class ConstantNode extends Node {

    // this ConstantNode class is an ast node that holds the value of a constant.
    private int constantNode;

    /**
     * this setConstantNode() method sets the value for the constant node.
     * @param i the value of the constant node.
     */
    public void setConstantNode(int i) {
        constantNode = i;
    }

    /**
     * this getConstantNode() method gets the value for the constant node.
     * @return the value of the constant node.
     */
    public int getConstantNode() {
        return constantNode;
    }

    /**
     * this ConstantNode() constructor initializes the objects and variables.
     * @param constantNode the value of the constant node.
     */
    public ConstantNode(int constantNode) {
        this.constantNode = constantNode;
    }

    /**
     * this toString() method creates a string representation of the value of a constant node.
     * @return a string representation of the value of the constant node.
     */

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        for (int i = 0; i < getConstantNode(); i++) {
            toString.append("ConstantNode(").append(getConstantNode()).append(")").append("+");
        }
        return toString.toString();
    }
}
