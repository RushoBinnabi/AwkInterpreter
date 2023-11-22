/**
 * Name: Rusho Binnabi
 * Date: 10/13/2023
 * Assignment: Interpreter 1
 * Class: ICSI 311 - Fall 2023
 */

import java.util.HashMap;

public class InterpreterArrayDataType extends InterpreterDataType {

    // this InterpreterArrayDataType class will be the other variable storage class as the interpreter runs.

    private HashMap<String, InterpreterDataType> dataType;

    /**
     * this InterpreterArrayDataType() constructor initializes the hashmap.
     */

    public InterpreterArrayDataType() {
        dataType = new HashMap<>();
    }

    /**
     * this getDataType() method gets the hashmap.
     * @return the hashmap.
     */

    public HashMap<String, InterpreterDataType> getDataType() {
        return dataType;
    }

    /**
     * this setDataType() method adds to the hashmap.
     * @param string the value of the data type.
     * @param idt the InterpreterDataType object.
     */

    public void setDataType(String string, InterpreterDataType idt) {
        dataType.put(string, idt);
    }

    /**
     * this toString() method creates a string representation of the hashmap.
     * @return a string representation of the hashmap.
     */

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getDataType().size(); i++) {
            output.append("InterpreterArrayDataType(").append(getDataType().toString()).append(")").append("+");
        }
        return output.toString();
    }
}
