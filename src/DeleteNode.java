/**
 * Name: Rusho Binnabi
 * Date: 10/9/2023
 * Assignment: Parser 4
 * Class: ICSI 311 - Fall 2023
 */
public class DeleteNode extends StatementNode {

    // this DeleteNode class has the delete statement type for parsing delete statements by the parser.

    private String arrayReferenceName;

    /**
     * this DeleteNode() constructor initializes the objects and variables.
     * @param deleteArray the name of the array, which would delete the whole array, or an array reference
     * with a comma separated list of indices to be deleted.
     */

    public DeleteNode(String deleteArray) {
        arrayReferenceName = deleteArray;
    }

    /**
     * this setArrayReferenceName() method sets the name of the array or an array reference.
     * @param delete the array using its name or a reference of the array.
     */

    public void setArrayReferenceName(String delete) {
        arrayReferenceName = delete;
    }

    /**
     * this getArrayReferenceName() gets the name of the array or an array reference.
     * @return the array using its name or a reference of the array.
     */
    public String getArrayReferenceName() {
        return arrayReferenceName;
    }

    /**
     * this toString() method creates a string representation of the value of the name of the array or the reference of the array.
     * @return a string representation of the values of the name of the array or the reference of the array.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getArrayReferenceName().charAt(i); i++) {
            output.append("DeleteArray(").append(getArrayReferenceName()).append(")").append("+");
        }
        return output.toString();
    }
}
