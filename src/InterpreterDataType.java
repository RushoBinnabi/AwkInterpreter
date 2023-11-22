/**
 * Name: Rusho Binnabi
 * Date: 10/13/2023
 * Assignment: Interpreter 1
 * Class: ICSI 311 - Fall 2023
 */

public class InterpreterDataType extends Node {

    // this InterpreterDataType class will be one of the variable storage classes as the interpreter runs.

    private String value;

    /**
     * this getValue() method gets the value of the variable.
     * @return the value of the variable.
     */

    public String getValue() {
        return value;
    }

    /**
     * this setValue() method sets the value of the variable.
     * @param value the value of the variable.
     */

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * empty InterpreterDataType() constructor initializes the class without an initial value supplied.
     */
    public InterpreterDataType() {

    }

    /**
     * this InterpreterDataType() constructor initializes the class with an initial value supplied.
     * @param initialValue the value of the variable.
     */

    public InterpreterDataType(String initialValue) {
        value = initialValue;
    }

    /**
     * this toString() method creates a string representation of the value of the variable storage class.
     * @return a string representation of the value of the variable storage class.
     */

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getValue().length(); i++) {
            output.append("InterpreterDataType(").append(getValue()).append(")").append("+");
        }
        return output.toString();
    }
}
