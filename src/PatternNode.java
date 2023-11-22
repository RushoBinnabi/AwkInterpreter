/**
 * Name: Rusho Binnabi
 * Date: 9/29/2023
 * Assignment: Parser 2
 * Class: ICSI 311 - Fall 2023
 */
public class PatternNode extends Node {

    // this PatternNode class is an ast node that holds the value of a pattern.
    private String patternNode;

    /**
     * this PatternNode() constructor initializes the objects and variables.
     * @param pattern the value of the pattern node.
     */
    public PatternNode(String pattern) {
        patternNode = pattern;
    }

    /**
     * this setPatternNode() method sets the value for the pattern.
     * @param pattern the value of the pattern.
     */
    public void setPatternNode(String pattern) {
        patternNode = pattern;
    }

    /**
     * this getPatternNode() method gets the value for the pattern.
     * @return the value of the pattern.
     */
    public String getPatternNode() {
        return patternNode;
    }

    /**
     * this toString() method creates a string representation of the value of a pattern node.
     * @return a string representation of the value of the pattern node.
     */
    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        for (int i = 0; i < getPatternNode().charAt(i); i++) {
            toString.append("PatternNode(").append(getPatternNode().charAt(i)).append(")").append("+");
        }
        return toString.toString();
    }
}
