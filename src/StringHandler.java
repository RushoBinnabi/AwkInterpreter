/**
 * Name: Rusho Binnabi
 * Date: 8/31/2023
 * Assignment: Lexer 1
 * Class: ICSI 311 - Fall 2023
 */

public class StringHandler {

    // This StringHandler class will try to manage the stream of letters that come through, which will allow the lexer to look or "peek" ahead in the stream,
    // move the finger (get a character) and check to see if it is at the end of the document.

    private String document;
    private int index; // the "finger."

    /**
     * this StringHandler() constructor will initialize the variables.
     * @param document the argument is the name of the document being read by the StringHandler.
     */

    public StringHandler(String document) {
        this.document = document;
        index = 0;
    }

    /**
     * this setDocument() modifier method sets the name of the document.
     * @param document the argument is the name of the document.
     */

    public void setDocument(String document) {
        this.document = document;
    }

    /**
     * this getDocument() accessor method gets the document.
     * @return the argument is the document being returned.
     */

    public String getDocument() {
        return document;
    }

    /**
     * this peek() method looks ahead by the number of characters in the document provided by the argument,
     * but it doesn't move the index.
     * @param i the argument is the number of characters.
     * @return looks ahead in the document by the amount of characters specified by i.
     */

    public char peek(int i) {
        return document.charAt(i);
    }

    /**
     * this peekString() method returns a string of the number of characters from the document using the argument.
     * @param i the argument is the numbers of characters to return as a string.
     * @return a string of the number of characters using i.
     */

    public String peekString(int i) {
        return String.valueOf(document.charAt(i));
    }

    /**
     * this getChar() method gets the next character and moves the index.
     * @return the next character.
     */

    public char getChar() {
        index++;
        return document.charAt(index);
    }

    /**
     * this swallow() method moves the index ahead by the number of positions using i.
     * @param i the argument is the number of positions to move the index by.
     */

    public void swallow(int i) {
        index += i;
    }

    /**
     * this isDone() method checks to see if the end of the document has been reached.
     * @return true if it is at the end of the document, false otherwise.
     */

    public boolean isDone() {
        return document.endsWith(" ");
    }

    /**
     * this remainder() method turns the rest of the document as a string.
     * @return the document as a string.
     */

    public String remainder() {
        return document;
    }
}
