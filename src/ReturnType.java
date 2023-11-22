/**
 * Name: Rusho Binnabi
 * Date: 10/31/2023
 * Assignment: Interpreter 3
 * Class: ICSI 311 - Fall 2023
 */
public class ReturnType {

    // this ReturnType class deals with the result of executing an instruction.

    private String returnValue;

    /**
     * this Returns enum has the return types that will be used to determine what the result of an execution of an instruction was.
     */

    public enum Returns {
        NORMAL, BREAK, CONTINUE, RETURN
    }

    private Returns returns;

    /**
     * this ReturnType() constructor initializes the objects and variables.
     * @param type the return type enum.
     */
    public ReturnType(ReturnType.Returns type) {
        returns = type;
    }

    /**
     * this ReturnType() constructor initializes the objects and variables.
     * @param type the return type enum.
     * @param returnValue the return value.
     */

    public ReturnType(ReturnType.Returns type, String returnValue) {
        returns = type;
        this.returnValue = returnValue;
    }

    /**
     * this getReturnValue() method gets the return value.
     * @return the return value.
     */

    public String getReturnValue() {
        return returnValue;
    }

    /**
     * this setReturnValue() method sets the return value.
     * @param returnValue the return value.
     */

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    /**
     * this getReturns() method gets the return type enums.
     * @return the return type enums
     */

    public Returns getReturns() {
        return returns;
    }

    /**
     * this setReturns() method sets the return type enums.
     * @param returns the return type enums.
     */

    public void setReturns(Returns returns) {
        this.returns = returns;
    }

    /**
     * this toString() method creates a string representation of the return type enums.
     * @return a string representation of the return type enums.
     */

    public String toString() {
        StringBuilder toString = new StringBuilder();
        while (getReturns() != null) {
            if (getReturns() == ReturnType.Returns.NORMAL) {
                toString.append("Return Type(").append(getReturns().name()).append(")").append(" ");
            }
            else if (getReturns() == ReturnType.Returns.BREAK) {
                toString.append("Return Type(").append(getReturns().name()).append(")").append(" ");
            }
            else if (getReturns() == ReturnType.Returns.CONTINUE) {
                toString.append("Return Type(").append(getReturns().name()).append(")").append(" ");
            }
            else if (getReturns() == ReturnType.Returns.RETURN) {
                toString.append("Return Type(").append(getReturns().name()).append(")").append(" ");
            }
        }
        while (!getReturnValue().isEmpty()) {
            toString.append("Return Value(").append(getReturnValue()).append(")").append(" ");
        }
        return toString.toString();
    }

}