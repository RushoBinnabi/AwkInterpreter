/**
 * Name: Rusho Binnabi
 * Date: 8/31/2023
 * Assignment: Lexer 1
 * Class: ICSI 311 - Fall 2023
 */
public class Token {

    // this Token class will be used by the lexer to create and manage tokens to hold the type and a value of the token.


    /**
     * this TokenType enum has the several types of tokens that will be created and managed by the lexer.
     */

    public enum TokenType { WORD, NUMBER, SEPARATOR, // SEPARATOR indicates a new line in the document.
                            WHILE, IF, DO, FOR, BREAK,
                            CONTINUE, ELSE, RETURN, BEGIN,
                            END, PRINT, PRINTF, NEXT,
                            IN, DELETE, GETLINE, EXIT,
                            NEXTFILE, FUNCTION, STRINGLITERAL, REGULAREXPRESSIONPATTERN,
                            GREATERTHANOREQUALTO, DOUBLEPLUS, DOUBLEMINUS, LESSTHANOREQUALTO,
                            DOUBLEEQUALS, NOTEQUALTO, EXPONENTEQUALS, PERCENTEQUALS,
                            ASTERISKEQUALS, SLASHEQUALS, PLUSEQUALS, MINUSEQUALS,
                            DOESNOTMATCH, CONDITIONALAND, APPEND, CONDITIONALOR,
                            LEFTCURLYBRACE, RIGHTCURLYBRACE, LEFTSQUAREBRACKET, RIGHTSQUAREBRACKET,
                            LEFTPARENTHESIS, RIGHTPARENTHESIS, DOLLARSIGN, TILDE,
                            EQUALS, LESSTHAN, GREATERTHAN, NOT,
                            PLUS, EXPONENT, HYPHEN, QUESTIONMARK,
                            COLON, ASTERISK, SLASH, PERCENT,
                            SEMICOLON, NEWLINE, OR, COMMA, MINUS, TIMES, DIVIDE,
                            INCREMENT, DECREMENT, CONCATENATION, MATCH, ARRAY, MULTIDIMENSIONARRAY,
                            CONDITIONALEXPRESSION, CONSEQUENT, ALTERNATE, ASSIGNMENT, EXPONENTIATIONASSIGNMENT,
                            MODULUSASSIGNMENT, MULTIPLICATIONASSIGNMENT, DIVISIONASSIGNMENT, ADDITIONASSIGNMENT,
                            SUBTRACTIONASSIGNMENT, MULTIPLYEQUALS, DIVIDEEQUALS, FOREACH }

    private TokenType tokenType;
    private String tokenValue;
    private int lineNumber;
    private char tokenCharacterStartPosition;

    /**
     * this first Token() constructor will initialize the variables for TokenType, line number and the starting position of the character of the token.
     * @param token the argument is the type of token.
     * @param lineNumber the argument is the line number that the StringHandler is on when reading the document.
     * @param tokenCharacterStartPosition the argument is the starting position of the character of the token.
     */

    public Token(TokenType token, int lineNumber, char tokenCharacterStartPosition) {
        this.lineNumber = lineNumber;
        this.tokenCharacterStartPosition = tokenCharacterStartPosition;
        this.tokenType = token;
    }

    /**
     * this second Token() initializes the value of the token.
     * @param tokenValue the argument is the value of the token.
     */

    public Token(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    /**
     * this getToken() accessor method gets the type of token.
     * @return the type of token.
     */

    public TokenType getTokenType() {
        return tokenType;
    }

    /**
     * this setToken() method sets the type for the token.
     * @param tokenType the argument is the type of token.
     */

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * this getTokenValue() gets the value of the token.
     * @return the token value.
     */

    public String getTokenValue() {
        return tokenValue;
    }

    /**
     * this setTokenValue() method sets the value of the token.
     * @param tokenValue the argument is the value of the token.
     */

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    /**
     * this getLineNumber() method gets the line number.
     * @return the number of the line.
     */

    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * this setLineNumber() method sets the line number.
     * @param lineNumber the number of the line.
     */

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * this getTokenCharacterStartPosition() gets the starting position of the character of the token.
     * @return the starting position of the character of the token.
     */

    public char getTokenCharacterStartPosition() {
        return tokenCharacterStartPosition;
    }

    /**
     * this setTokenCharacterStartPosition sets the starting position of the character of the token.
     * @param tokenCharacterStartPosition the starting positing of the character of the token.
     */

    public void setTokenCharacterStartPosition(char tokenCharacterStartPosition) {
        this.tokenCharacterStartPosition = tokenCharacterStartPosition;
    }

    /**
     * this toString() method outputs the token type and the value of the token in parentheses if it was set.
     * @return the token type and the value of the token in parentheses if it was set.
     */

    public String toString() {
        StringBuilder output = new StringBuilder();
        while (getTokenType() !=  null) {
            if (getTokenType() == Token.TokenType.SEPARATOR) {
                output.append(TokenType.SEPARATOR.name());
            }
            else if (getTokenType() == Token.TokenType.NUMBER) {
                output.append(TokenType.NUMBER.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.WORD) {
                output.append(TokenType.WORD.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            // start of keywords.
            else if (getTokenType() == Token.TokenType.WHILE) {
                output.append(TokenType.WHILE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.IF) {
                output.append(TokenType.IF.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DO) {
                output.append(TokenType.DO.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.FOR) {
                output.append(TokenType.FOR.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.BREAK) {
                output.append(TokenType.BREAK.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.CONTINUE) {
                output.append(TokenType.CONTINUE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.ELSE) {
                output.append(TokenType.ELSE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.RETURN) {
                output.append(TokenType.RETURN.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.BEGIN) {
                output.append(TokenType.BEGIN.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.END) {
                output.append(TokenType.END.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.PRINT) {
                output.append(TokenType.PRINT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.PRINTF) {
                output.append(TokenType.PRINTF.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.NEXT) {
                output.append(TokenType.NEXT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.IN) {
                output.append(TokenType.IN.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DELETE) {
                output.append(TokenType.DELETE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.GETLINE) {
                output.append(TokenType.GETLINE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.EXIT) {
                output.append(TokenType.EXIT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.NEXTFILE) {
                output.append(TokenType.NEXTFILE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.FUNCTION) {
                output.append(TokenType.FUNCTION.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            // end of keywords.
            // start of string literals and regular expressions.
            else if (getTokenType() == Token.TokenType.STRINGLITERAL) {
                output.append(TokenType.STRINGLITERAL.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.REGULAREXPRESSIONPATTERN) {
                output.append(TokenType.REGULAREXPRESSIONPATTERN.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            // end of string literals and regular expressions.
            // start of two-character symbols.
            else if (getTokenType() == Token.TokenType.GREATERTHANOREQUALTO) {
                output.append(TokenType.GREATERTHANOREQUALTO.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DOUBLEPLUS) {
                output.append(TokenType.DOUBLEPLUS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DOUBLEMINUS) {
                output.append(TokenType.DOUBLEMINUS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.LESSTHANOREQUALTO) {
                output.append(TokenType.LESSTHANOREQUALTO.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DOUBLEEQUALS) {
                output.append(TokenType.DOUBLEEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.NOTEQUALTO) {
                output.append(TokenType.NOTEQUALTO.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.EXPONENTEQUALS) {
                output.append(TokenType.EXPONENTEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.PERCENTEQUALS) {
                output.append(TokenType.PERCENTEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.ASTERISKEQUALS) {
                output.append(TokenType.ASTERISKEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.SLASHEQUALS) {
                output.append(TokenType.SLASHEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.PLUSEQUALS) {
                output.append(TokenType.PLUSEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.MINUSEQUALS) {
                output.append(TokenType.MINUSEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DOESNOTMATCH) {
                output.append(TokenType.DOESNOTMATCH.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.CONDITIONALAND) {
                output.append(TokenType.CONDITIONALAND.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.APPEND) {
                output.append(TokenType.APPEND.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.CONDITIONALOR) {
                output.append(TokenType.CONDITIONALOR.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            // end of two-character symbols.
            // start of one-character symbols.
            else if (getTokenType() == Token.TokenType.LEFTCURLYBRACE) {
                output.append(TokenType.LEFTCURLYBRACE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.RIGHTCURLYBRACE) {
                output.append(TokenType.RIGHTCURLYBRACE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.LEFTSQUAREBRACKET) {
                output.append(TokenType.LEFTSQUAREBRACKET.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.RIGHTSQUAREBRACKET) {
                output.append(TokenType.RIGHTSQUAREBRACKET.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.LEFTPARENTHESIS) {
                output.append(TokenType.LEFTPARENTHESIS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.RIGHTPARENTHESIS) {
                output.append(TokenType.RIGHTPARENTHESIS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DOLLARSIGN) {
                output.append(TokenType.DOLLARSIGN.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.TILDE) {
                output.append(TokenType.TILDE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.EQUALS) {
                output.append(TokenType.EQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.LESSTHAN) {
                output.append(TokenType.LESSTHAN.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.GREATERTHAN) {
                output.append(TokenType.GREATERTHAN.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.NOT) {
                output.append(TokenType.NOT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.PLUS) {
                output.append(TokenType.PLUS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.EXPONENT) {
                output.append(TokenType.EXPONENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.HYPHEN) {
                output.append(TokenType.HYPHEN.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.QUESTIONMARK) {
                output.append(TokenType.QUESTIONMARK.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.COLON) {
                output.append(TokenType.COLON.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.ASTERISK) {
                output.append(TokenType.ASTERISK.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.SLASH) {
                output.append(TokenType.SLASH.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.PERCENT) {
                output.append(TokenType.PERCENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.SEMICOLON) {
                output.append(TokenType.SEMICOLON.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.NEWLINE) {
                output.append(TokenType.NEWLINE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.OR) {
                output.append(TokenType.OR.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.COMMA) {
                output.append(TokenType.COMMA.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            // end of one-character symbols.
            else if (getTokenType() == Token.TokenType.MINUS) {
                output.append(TokenType.MINUS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.TIMES) {
                output.append(TokenType.TIMES.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DIVIDE) {
                output.append(TokenType.DIVIDE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.INCREMENT) {
                output.append(TokenType.INCREMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DECREMENT) {
                output.append(TokenType.DECREMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.CONCATENATION) {
                output.append(TokenType.CONCATENATION.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.MATCH) {
                output.append(TokenType.MATCH.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.ARRAY) {
                output.append(TokenType.ARRAY.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.MULTIDIMENSIONARRAY) {
                output.append(TokenType.MULTIDIMENSIONARRAY.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.CONDITIONALEXPRESSION) {
                output.append(TokenType.CONDITIONALEXPRESSION.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.CONSEQUENT) {
                output.append(TokenType.CONSEQUENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.ALTERNATE) {
                output.append(TokenType.ALTERNATE.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.ASSIGNMENT) {
                output.append(TokenType.ASSIGNMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.EXPONENTIATIONASSIGNMENT) {
                output.append(TokenType.EXPONENTIATIONASSIGNMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.MODULUSASSIGNMENT) {
                output.append(TokenType.MODULUSASSIGNMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DIVISIONASSIGNMENT) {
                output.append(TokenType.DIVISIONASSIGNMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.MULTIPLICATIONASSIGNMENT) {
                output.append(TokenType.MULTIPLICATIONASSIGNMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.ADDITIONASSIGNMENT) {
                output.append(TokenType.ADDITIONASSIGNMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.SUBTRACTIONASSIGNMENT) {
                output.append(TokenType.SUBTRACTIONASSIGNMENT.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.MULTIPLYEQUALS) {
                output.append(TokenType.MULTIPLYEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.DIVIDEEQUALS) {
                output.append(TokenType.DIVIDEEQUALS.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
            else if (getTokenType() == Token.TokenType.FOREACH) {
                output.append(TokenType.FOREACH.name()).append("(").append(getTokenValue()).append(")").append(" ");
            }
        }
        return output.toString();
    }

}