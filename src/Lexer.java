/**
 * Name: Rusho Binnabi
 * Date: 8/31/2023
 * Assignment: Lexer 1
 * Class: ICSI 311 - Fall 2023
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Lexer {

    // the Lexer class will break the data from the StringHandler into a linked list of tokens.

    private StringHandler stringHandler;
    private int lineNumber;
    private int characterPosition;
    private String tokenValue;
    private Token.TokenType token;
    private int processWordMethodState = 1;
    private int processNumberMethodState = 1;
    private int handleStringLiteralState = 1;
    private int handleRegularExpressionPatternState = 1;
    private int processSymbolMethodState = 1;
    private String oneString;
    private String twoStrings1;
    private String twoStrings2;
    private boolean matchFound1;
    private boolean matchFound2;
    private boolean matchFound3;
    private boolean matchFound4;
    private boolean matchFound5;
    private boolean matchFound6;
    private char c;
    Pattern string1 = Pattern.compile("[a-z]|[A-Z]|[0-9]|_", Pattern.CASE_INSENSITIVE);
    Matcher match1 = string1.matcher("[a-z]|[A-Z]|[0-9]|_");
    HashMap<String, Token.TokenType> keywords = new HashMap<>();
    HashMap<String, Token.TokenType> twoCharacterSymbols = new HashMap<>();
    HashMap<String, Token.TokenType> oneCharacterSymbols = new HashMap<>();

    Pattern string2 = Pattern.compile("[0-9]|.", Pattern.CASE_INSENSITIVE);
    Matcher match2 = string2.matcher("[0-9]|.");
    Pattern string3 = Pattern.compile("[a-z]|[A-Z]|[0-9]|\"");
    Matcher match3 = string3.matcher("[a-z]|[A-Z]|[0-9]|\"");
    Pattern string4 = Pattern.compile("^$");
    Matcher match4 = string4.matcher("^$");
    Pattern string5 = Pattern.compile("[a-z]|[A-Z]|[0-9]|\"`");
    Matcher match5 = string5.matcher("[a-z]|[A-Z]|[0-9]|\"`");
    Pattern string6 = Pattern.compile("^$");
    Matcher match6 = string6.matcher("^$");
    final LinkedList<Token.TokenType> tokens = new LinkedList<>();

    /**
     * this Lexer() constructor initializes the StringHandler and the variables.
     * @param string the argument is the name of the document for the StringHandler.
     */

    public Lexer(String string) {
        setStringHandler(string);
        lineNumber = 0;
        characterPosition = 0;
        activateKeywordsHashMap();
        activateTwoCharacterSymbolsHashMap();
        activateOneCharacterSymbolsHashMap();
    }

    /**
     * this setStringHandler() method sets the string handler.
     * @param string the string that be used for the string handler.
     */

    public void setStringHandler(String string){
        stringHandler = new StringHandler(string);
    }

    /**
     * this getStringHandler() method gets the string handler.
     * @return the string handler.
     */

    public StringHandler getStringHandler() {
        return stringHandler;
    }

    /**
     * this setOneString() method sets the string that will be used to look up the value of the symbol from the one-character hashmap.
     *
     * @param s the string that will be used to look up the value of the symbol from the one-character hashmap.
     */

    public void setOneString(String s) {
        oneString = s;
    }

    /**
     * this getOneString() method gets the string that was used to look up the value of the symbol from the one-character hashmap.
     *
     * @return the string that was used to look up the value of the symbol from the one-character hashmap.
     */

    public String getOneString() {
        return oneString;
    }

    /**
     * this setTwoStrings1() method sets the 1st string that will be used to look up one of the values of the symbol from the two-character hashmap.
     *
     * @param s the string that will be used to look up the value of the symbol from the two-character hashmap.
     */

    public void setTwoStrings1(String s) {
        twoStrings1 = s;
    }

    /**
     * this getTwoStrings1() method gets the 1st string that was used to look up one of the values of the symbol from the two-character hashmap.
     *
     * @return the 1st string that was used to look up the value of the symbol from the two-character hashmap.
     */

    public String getTwoStrings1() {
        return twoStrings1;
    }

    /**
     * this setTwoStrings2() method sets the 2nd string that will be used to look up the other value of the symbol from the two-character hashmap.
     *
     * @param s the string that will be used to look up the value of the other symbol from the two-character hashmap.
     */

    public void setTwoStrings2(String s) {
        twoStrings2 = s;
    }

    /**
     * this getTwoStrings2() method gets the 2nd string that was used to look up the other value of the symbol from the two-character hashmap.
     *
     * @return the string that was used to look up the value of the other symbol from the two-character hashmap.
     */

    public String getTwoStrings2() {
        return twoStrings2;
    }

    /**
     * this setProcessSymbolMethodState() method sets the value of the state machine for the processSymbol() method.
     *
     * @param i the value of the state machine.
     */

    public void setProcessSymbolMethodState(int i) {
        processSymbolMethodState = i;
    }

    /**
     * this getProcessSymbolMethodState() method gets the value of the state machine for the processSymbol() method.
     *
     * @return the value of the state machine.
     */

    public int getProcessSymbolMethodState() {
        return processSymbolMethodState;
    }

    /**
     * this setHandleRegularExpressionPatternState() method sets the value of the state machine for the handleRegularExpressionPattern() method.
     *
     * @param i the value of the state machine.
     */

    public void setHandleRegularExpressionPatternState(int i) {
        handleRegularExpressionPatternState = i;
    }

    /**
     * this getHandleRegularExpressionPatternState() method gets the value of the state machine for the handleRegularExpressionPattern() method.
     *
     * @return the value of the state machine.
     */

    public int getHandleRegularExpressionPatternState() {
        return handleRegularExpressionPatternState;
    }

    /**
     * this setMatchFound5() method sets the boolean value for the literal backtick regular expressions.
     *
     * @param state true if there is a match for the pattern, false otherwise.
     */

    public void setMatchFound5(boolean state) {
        matchFound5 = state;
    }

    /**
     * this getMatchFound5() gets the boolean value for the backtick literal regular expressions.
     *
     * @return true if there was a match for the pattern, false otherwise.
     */

    public boolean getMatchFound5() {
        return matchFound5;
    }

    /**
     * this setMatchFound6() method sets the boolean value for the empty literal regular expressions.
     *
     * @param state true if there is a match for the pattern, false otherwise.
     */

    public void setMatchFound6(boolean state) {
        matchFound6 = state;
    }

    /**
     * this getMatchFound6() method gets the boolean value for the empty literal regular expressions.
     *
     * @return true if there was a match for the pattern, false otherwise.
     */

    public boolean getMatchFound6() {
        return matchFound6;
    }

    /**
     * this setMatchFound4() method sets the boolean value for the empty string literal.
     *
     * @param state true if there is a match for the pattern, false otherwise.
     */
    public void setMatchFound4(boolean state) {
        matchFound4 = state;
    }

    /**
     * this getMatchFound4() method gets the boolean value for the empty string literal.
     *
     * @return true if there was a match for the pattern, false otherwise.
     */

    public boolean getMatchFound4() {
        return matchFound4;
    }

    /**
     * this setMatchFound3() sets the boolean value for the string literal.
     *
     * @param state true if there was a match for the pattern, false otherwise.
     */

    public void setMatchFound3(boolean state) {
        matchFound3 = state;
    }

    /**
     * this getMatchFound3() gets the boolean value for the string literal.
     *
     * @return true if there was a match for the pattern, false otherwise.
     */

    public boolean getMatchFound3() {
        return matchFound3;
    }

    /**
     * this setHandleStringLiteralState() method sets the value of the state machine for the handleStringLiteral() method.
     *
     * @param i the value of the state machine.
     */

    public void setHandleStringLiteralState(int i) {
        handleStringLiteralState = i;
    }

    /**
     * this getHandleStringLiteralState() method gets the value of the state machine for the handleStringLiteral() method.
     *
     * @return the value of the state machine.
     */

    public int getHandleStringLiteralState() {
        return handleStringLiteralState;
    }

    /**
     * this setCharacter() sets the character value that will be used to create the appropriate tokens.
     *
     * @param character the character value that will be used to create the appropriate tokens.
     */
    public void setCharacter(char character) {
        c = character;
    }

    /**
     * this getCharacter() method gets the character value that will be used to create the appropriate tokens.
     *
     * @return the character value that will be used to create the appropriate tokens.
     */

    public char getCharacter() {
        return c;
    }

    /**
     * this activateOneCharacterSymbolsHashMap() method initializes the hashmap for the one-character symbols.
     */

    public void activateOneCharacterSymbolsHashMap() {
        oneCharacterSymbols.put("{", Token.TokenType.LEFTCURLYBRACE);
        oneCharacterSymbols.put("}", Token.TokenType.RIGHTCURLYBRACE);
        oneCharacterSymbols.put("[", Token.TokenType.LEFTSQUAREBRACKET);
        oneCharacterSymbols.put("]", Token.TokenType.RIGHTSQUAREBRACKET);
        oneCharacterSymbols.put("(", Token.TokenType.LEFTPARENTHESIS);
        oneCharacterSymbols.put(")", Token.TokenType.RIGHTPARENTHESIS);
        oneCharacterSymbols.put("$", Token.TokenType.DOLLARSIGN);
        oneCharacterSymbols.put("~", Token.TokenType.TILDE);
        oneCharacterSymbols.put("=", Token.TokenType.EQUALS);
        oneCharacterSymbols.put("<", Token.TokenType.LESSTHAN);
        oneCharacterSymbols.put(">", Token.TokenType.GREATERTHAN);
        oneCharacterSymbols.put("!", Token.TokenType.NOT);
        oneCharacterSymbols.put("+", Token.TokenType.PLUS);
        oneCharacterSymbols.put("^", Token.TokenType.EXPONENT);
        oneCharacterSymbols.put("-", Token.TokenType.HYPHEN);
        oneCharacterSymbols.put("?", Token.TokenType.QUESTIONMARK);
        oneCharacterSymbols.put(":", Token.TokenType.COLON);
        oneCharacterSymbols.put("*", Token.TokenType.ASTERISK);
        oneCharacterSymbols.put("/", Token.TokenType.SLASH);
        oneCharacterSymbols.put("%", Token.TokenType.PERCENT);
        oneCharacterSymbols.put(";", Token.TokenType.SEMICOLON);
        oneCharacterSymbols.put("\n", Token.TokenType.NEWLINE);
        oneCharacterSymbols.put("|", Token.TokenType.OR);
        oneCharacterSymbols.put(",", Token.TokenType.COMMA);
    }

    /**
     * this activateTwoCharacterSymbolsHashMap() method initializes the hashmap for the two-characters symbols.
     */
    public void activateTwoCharacterSymbolsHashMap() {
        twoCharacterSymbols.put(">=", Token.TokenType.GREATERTHANOREQUALTO);
        twoCharacterSymbols.put("++", Token.TokenType.DOUBLEPLUS);
        twoCharacterSymbols.put("--", Token.TokenType.DOUBLEMINUS);
        twoCharacterSymbols.put("<=", Token.TokenType.LESSTHANOREQUALTO);
        twoCharacterSymbols.put("==", Token.TokenType.DOUBLEEQUALS);
        twoCharacterSymbols.put("!=", Token.TokenType.NOTEQUALTO);
        twoCharacterSymbols.put("^=", Token.TokenType.EXPONENTEQUALS);
        twoCharacterSymbols.put("%=", Token.TokenType.PERCENTEQUALS);
        twoCharacterSymbols.put("*=", Token.TokenType.ASTERISKEQUALS);
        twoCharacterSymbols.put("/=", Token.TokenType.SLASHEQUALS);
        twoCharacterSymbols.put("+=", Token.TokenType.PLUSEQUALS);
        twoCharacterSymbols.put("-=", Token.TokenType.MINUSEQUALS);
        twoCharacterSymbols.put("!~", Token.TokenType.DOESNOTMATCH);
        twoCharacterSymbols.put("&&", Token.TokenType.CONDITIONALAND);
        twoCharacterSymbols.put(">>", Token.TokenType.APPEND);
        twoCharacterSymbols.put("||", Token.TokenType.CONDITIONALOR);
    }

    /**
     * this activateKeywordsHashMap() method initializes the hashmap for the keywords.
     */
    public void activateKeywordsHashMap() {
        keywords.put("while", Token.TokenType.WHILE);
        keywords.put("if", Token.TokenType.IF);
        keywords.put("do", Token.TokenType.DO);
        keywords.put("for", Token.TokenType.FOR);
        keywords.put("break", Token.TokenType.BREAK);
        keywords.put("continue", Token.TokenType.CONTINUE);
        keywords.put("else", Token.TokenType.ELSE);
        keywords.put("return", Token.TokenType.RETURN);
        keywords.put("BEGIN", Token.TokenType.BEGIN);
        keywords.put("END", Token.TokenType.END);
        keywords.put("print", Token.TokenType.PRINT);
        keywords.put("printf", Token.TokenType.PRINTF);
        keywords.put("next", Token.TokenType.NEXT);
        keywords.put("in", Token.TokenType.IN);
        keywords.put("delete", Token.TokenType.DELETE);
        keywords.put("getline", Token.TokenType.GETLINE);
        keywords.put("exit", Token.TokenType.EXIT);
        keywords.put("nextfile", Token.TokenType.NEXTFILE);
        keywords.put("function", Token.TokenType.FUNCTION);
    }

    /**
     * this setMatchFound1() method sets and is used to determine if a character consists of letters, digits and underscores.
     *
     * @param state sets the argument to true if the character is a letter, digit or underscore, false otherwise.
     */

    public void setMatchFound1(boolean state) {
        matchFound1 = state;
    }

    /**
     * this getMatchFound1() method gets and is used to determine if a character consists of letters, digits and underscores.
     *
     * @return true if the character is a letter, digit or underscore, false otherwise.
     */

    public boolean getMatchFound1() {
        return matchFound1;
    }

    /**
     * this setMatchFound2() method sets and is used to determine if a character consists of a number between 0 and 9 along with one ".".
     *
     * @param state sets the argument to true if the character consists of a number between 0 and 9 along with one ".", false otherwise.
     */

    public void setMatchFound2(boolean state) {
        matchFound2 = state;
    }

    /**
     * this getMatchFound2() method gets and is used to determine if a character consists of a number between 0 and 9 along with one ".".
     *
     * @return true if the character consists of a number between 0 and 9 along with one ".", false otherwise.
     */

    public boolean getMatchFound2() {
        return matchFound2;
    }

    /**
     * this getLineNumber() gets the line number from the StringHandler.
     *
     * @return the line number.
     */

    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * this setLineNumber() sets the line number from the StringHandler.
     *
     * @param i sets the line number.
     */

    public void setLineNumber(int i) {
        lineNumber = i;
    }

    /**
     * this getCharacterPosition() gets the character position (index) of the line from the StringHandler.
     *
     * @return the character position of the line.
     */

    public int getCharacterPosition() {
        return characterPosition;
    }

    /**
     * this setCharacterPosition() sets the character position (index) of the line from the StringHandler.
     *
     * @param i sets the character position of the line.
     */

    public void setCharacterPosition(int i) {
        characterPosition += i;
    }

    /**
     * this getTokenValue() gets the value of the type of token.
     *
     * @return the value of the type of token.
     */

    public String getTokenValue() {
        return tokenValue;
    }

    /**
     * this setTokenValue() method sets the value of the type of token.
     *
     * @param s sets the value of the type of token.
     */

    public void setTokenValue(String s) {
        tokenValue += s;
    }

    /**
     * this getToken() method gets the type of token.
     *
     * @return the type of token.
     */

    public Token.TokenType getToken() {
        return token;
    }

    /**
     * this setToken() method sets the type of token.
     *
     * @param t sets the type of token.
     */

    public void setToken(Token.TokenType t) {
        token = t;
    }

    /**
     * this getProcessWordMethodState() gets the state value of the processWord() method state, so it can be used to
     * check what kind of state the processWord() method is in.
     *
     * @return the state of the processWord() method.
     */

    public int getProcessWordMethodState() {
        return processWordMethodState;
    }

    /**
     * this setProcessWordMethodState() sets the state value of the processWord() method state, so it can be used to
     * change what kind of state the processWord() method is in to do different things.
     *
     * @param i sets the state of the processWord() method.
     */

    public void setProcessWordMethodState(int i) {
        processWordMethodState = i;
    }

    /**
     * this getProcessNumberMethodState() gets the state value of the processNumber() method state, so it can be used to
     * check what kind of state the processNumber() method is in.
     *
     * @return the state of the processNumber() method.
     */

    public int getProcessNumberMethodState() {
        return processNumberMethodState;
    }

    /**
     * this setProcessNumberMethodState() sets the state value of the processNumber() method state, so it can be used to
     * change what kind of state the processNumber() method is in to do different things.
     *
     * @param i sets the state of the processNumber() method.
     */

    public void setProcessNumberMethodState(int i) {
        processNumberMethodState = i;
    }

    /**
     * this processWord() method makes a string out of letters, digits, and underscores.
     * It makes a WORD token type when it comes across a character within the HashMap that is not one of those, and
     * it sets the value of the token type to the string that it created.
     *
     * @return a token.
     */

    public Token.TokenType processWord() {
        setMatchFound1(match1.find());
        while (getProcessWordMethodState() == 1 || getProcessWordMethodState() == 2) {
            if (getProcessWordMethodState() == 1) {
                while (getStringHandler().isDone() == FALSE && getMatchFound1() == FALSE) {
                    while (Character.isLetter(getStringHandler().peek(getCharacterPosition()))) {
                        tokens.add(new Token(Token.TokenType.WORD.name()).getTokenType());
                        setProcessWordMethodState(2);
                        setCharacterPosition(1);
                    }
                }
                setMatchFound1(TRUE);
            } else if (getProcessWordMethodState() == 2) {
                while (getMatchFound1() == TRUE && getStringHandler().isDone() == FALSE) {
                    while (!Character.isLetter(getStringHandler().peek(getCharacterPosition()))) {
                        setCharacter(getStringHandler().getChar());
                        setTokenValue(String.valueOf(getCharacter()));
                        if (keywords.containsKey(getTokenValue())) {
                            tokens.add(new Token(Token.TokenType.WORD.name()).getTokenType());
                        }
                        setProcessWordMethodState(1);
                        setCharacterPosition(1);
                    }
                }
                setMatchFound1(FALSE);
            }
        }
        return getToken();
    }

    /**
     * this processSymbol() method processes symbols by using the peekString() method from the StringHandler class
     * to get two characters from the two-characters hashmap and create appropriate tokens.
     * Or if it can't, then it uses the peekString() method from the StringHandler class to get one character from
     * the one-character hashmap and create appropriate tokens.
     *
     * @return a token.
     */

    public Token.TokenType processSymbol() {
        while (getProcessSymbolMethodState() == 1 || getProcessSymbolMethodState() == 2) { // the states for the processSymbol() method's state machine say 1 and 2,
            // but they are states 9 and 10.
            if (getProcessSymbolMethodState() == 1) {
                setTwoStrings1(getStringHandler().peekString(getCharacterPosition()));
                setTwoStrings2(getStringHandler().peekString(getCharacterPosition()));
                if (twoCharacterSymbols.containsKey(getTwoStrings1()) && twoCharacterSymbols.containsKey(getTwoStrings2())) {
                    setCharacter(getStringHandler().getChar());
                    setTokenValue(String.valueOf(getCharacter()));
                    tokens.add(new Token(Token.TokenType.valueOf(getTwoStrings1()).name()).getTokenType());
                    tokens.add(new Token(Token.TokenType.valueOf(getTwoStrings2()).name()).getTokenType());
                    setProcessSymbolMethodState(2);
                    setCharacterPosition(1);
                } else {
                    setCharacterPosition(1);
                    return null;
                }
            } else if (getProcessSymbolMethodState() == 2) {
                setOneString(getStringHandler().peekString(getCharacterPosition()));
                if (oneCharacterSymbols.containsKey(getOneString())) {
                    setCharacter(getStringHandler().getChar());
                    setTokenValue(String.valueOf(getCharacter()));
                    tokens.add(new Token(Token.TokenType.valueOf(getOneString()).name()).getTokenType());
                    setProcessSymbolMethodState(1);
                    setCharacterPosition(1);
                } else {
                    setCharacterPosition(1);
                    return null;
                }
            }
        }
        return getToken();
    }

    /**
     * this handleStringLiteral() method handles string literals where it reads up to the matching “ and creates appropriate tokens.
     * It also deals with empty string literals and escaped ".
     *
     * @return a token.
     */

    public Token.TokenType handleStringLiteral() {
        setMatchFound3(match3.find());
        setMatchFound4(match4.find());
        while (getHandleStringLiteralState() == 1 || getHandleStringLiteralState() == 2) { // the states for the handleStringLiteral() method's state machine say 1 and 2,
            // but they are states 5 and 6.
            if (getHandleStringLiteralState() == 1) {
                while ((getStringHandler().isDone() == FALSE && getMatchFound3() == FALSE) && (getStringHandler().isDone() == FALSE && getMatchFound4() == FALSE)) {
                    while (Character.isLetter(getStringHandler().peek(getCharacterPosition()))) {
                        tokens.add(new Token(Token.TokenType.STRINGLITERAL.name()).getTokenType());
                        setHandleStringLiteralState(2);
                        setCharacterPosition(1);
                    }
                }
                setMatchFound3(TRUE);
                setMatchFound4(TRUE);
            } else if (getHandleStringLiteralState() == 2) {
                while ((getMatchFound4() == TRUE && getStringHandler().isDone() == FALSE) && (getMatchFound3() == TRUE && getStringHandler().isDone() == FALSE)) {
                    while (!Character.isLetter(getStringHandler().peek(getCharacterPosition()))) {
                        setCharacter(getStringHandler().getChar());
                        setTokenValue(String.valueOf(getCharacter()));
                        tokens.add(new Token(Token.TokenType.STRINGLITERAL.name()).getTokenType());
                        setHandleStringLiteralState(1);
                        setCharacterPosition(1);
                    }
                }
                setMatchFound3(FALSE);
                setMatchFound4(FALSE);
            }
        }
        return getToken();
    }

    /**
     * this handleRegularExpressionPattern() method handles regular expressions where it reads up to the matching ' and creates appropriate tokens.
     * It also deals with empty backticks and escaped '.
     *
     * @return a token.
     */

    public Token.TokenType handleRegularExpressionPattern() {
        setMatchFound5(match5.find());
        setMatchFound6(match6.find());
        while (getHandleRegularExpressionPatternState() == 1 || getHandleRegularExpressionPatternState() == 2) { // the states for the handleRegularExpressionPattern() method's state machine say 1 and 2,
            // but they are states 7 and 8.
            if (getHandleRegularExpressionPatternState() == 1) {
                while ((getStringHandler().isDone() == FALSE && getMatchFound5() == FALSE) && (getStringHandler().isDone() == FALSE && getMatchFound6() == FALSE)) {
                    while (Character.isLetter(getStringHandler().peek(getCharacterPosition()))) {
                        setCharacter(getStringHandler().getChar());
                        setTokenValue(String.valueOf(getCharacter()));
                        tokens.add(new Token(Token.TokenType.REGULAREXPRESSIONPATTERN.name()).getTokenType());
                        setHandleRegularExpressionPatternState(2);
                        setCharacterPosition(1);
                    }
                }
                setMatchFound5(TRUE);
                setMatchFound6(TRUE);
            } else if (getHandleRegularExpressionPatternState() == 2) {
                while ((getStringHandler().isDone() == FALSE && getMatchFound5() == TRUE) && (getStringHandler().isDone() == FALSE && getMatchFound6() == TRUE)) {
                    while (!Character.isLetter(getStringHandler().peek(getCharacterPosition()))) {
                        setCharacter(getStringHandler().getChar());
                        setTokenValue(String.valueOf(getCharacter()));
                        tokens.add(new Token(Token.TokenType.REGULAREXPRESSIONPATTERN.name()).getTokenType());
                        setHandleRegularExpressionPatternState(1);
                        setCharacterPosition(1);
                    }
                }
                setMatchFound5(FALSE);
                setMatchFound6(FALSE);
            }
        }
        return getToken();
    }

    /**
     * this processNumber() method makes a string out of numbers from 0 to 9 along with a ".".
     * It makes a NUMBER token type when it comes across a character that is not one of those, and
     * it sets the value of the token type to the string that it created.
     * If it comes across more than one period ".", then it throws an exception.
     *
     * @return a token.
     * @throws Exception throws an exception if there are two periods instead of one.
     */

    public Token.TokenType processNumber() throws Exception { // the states for the processNumber() method say 1 and 2, but they are
        // really states 3 and 4 from the state machine diagram from the assignment document.
        setMatchFound2(match2.find());
        while (getProcessNumberMethodState() == 1 || getProcessNumberMethodState() == 2) {
            if (getProcessNumberMethodState() == 1) {
                while (getStringHandler().isDone() == FALSE && getMatchFound2() == FALSE) {
                    while (Character.isLetter(getStringHandler().peek(getCharacterPosition()))) {
                        tokens.add(new Token(Token.TokenType.NUMBER.name()).getTokenType());
                        setCharacter(getStringHandler().getChar());
                        if (getCharacter() == '.') {
                            setTokenValue(String.valueOf(getCharacter()));
                            if (getCharacter() + 1 == '.') {
                                throw new Exception();
                            }
                        }
                        setProcessNumberMethodState(2);
                        setCharacterPosition(1);
                    }
                }
                setMatchFound2(TRUE);
            } else if (getProcessNumberMethodState() == 2) {
                while (getMatchFound2() == TRUE && getStringHandler().isDone() == FALSE) {
                    while (!Character.isLetter(getStringHandler().peek(getCharacterPosition()))) {
                        tokens.add(new Token(Token.TokenType.NUMBER.name()).getTokenType());
                        setCharacter(getStringHandler().getChar());
                        setTokenValue(String.valueOf(getCharacter()));
                        setProcessNumberMethodState(1);
                        setCharacterPosition(1);
                    }
                }
                setMatchFound2(FALSE);
            }
        }
        return getToken();
    }

    /**
     * this lex() method breaks the data from the StringHandler into a linked list of tokens.
     * While there is still data from the StringHandler, it takes a look, or "peeks", at the next character
     * to see what to do with it.
     *
     * @return a linked list of tokens.
     */

    public LinkedList<Token> lex() {
        try {
            while (getStringHandler().isDone() == FALSE) {
                if (getStringHandler().getChar() == ' ' || getStringHandler().getChar() == '\t') {
                    setCharacterPosition(1);
                }
                else if (getStringHandler().getChar() == '\n' || getStringHandler().getChar() == ';') {
                    setToken(Token.TokenType.SEPARATOR);
                    tokens.add(new Token(tokenValue).getTokenType());
                    characterPosition = 0;
                    lineNumber++;
                }
                else if (getStringHandler().getChar() == '\r') {
                    setCharacterPosition(1);
                }
                else if (Character.isLetter(getStringHandler().getChar())) {
                    tokens.add(processWord());
                }
                else if (Character.isDigit(getStringHandler().getChar())) {
                    tokens.add(processNumber());
                }
                else if (getStringHandler().getChar() == '#') {
                    while (getStringHandler().getChar() == '#' && getStringHandler().getChar() != '\n') {
                        setCharacterPosition(1);
                        setLineNumber(1);
                    }
                }
                else if (getStringHandler().getChar() == '"') {
                    while (String.valueOf(getStringHandler().getChar()).isEmpty() || getStringHandler().getChar() == '\"') {
                        tokens.add(handleStringLiteral());
                    }
                }
                else if (getStringHandler().getChar() == '`') {
                    while (String.valueOf(getStringHandler().getChar()).isEmpty() || getStringHandler().getChar() == '`') {
                        tokens.add(handleRegularExpressionPattern());
                    }
                }
                else if (!Character.isDigit(getStringHandler().getChar()) || !Character.isLetter(getStringHandler().getChar())) {
                    tokens.add(processSymbol());
                }
            }
        } catch (Exception e) {
            System.out.println("Error. Unrecognized Character.");
        }
        return null;
    }
}