/**
 * Name: Rusho Binnabi
 * Date: 8/31/2023
 * Assignment: Lexer 1
 * Class: ICSI 311 - Fall 2023
 */

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

class AwkTest {

    // this AwkTest class tests the main Awk class that has the lexer and parser.

    /**
     * this testNumber() method tests the lexer with numbers and prints out the tokens.
     */

    @Test
    void testNumber() {
        String input = "23";
        Lexer lexer = new Lexer(input);
        try {
            LinkedList<Token> tokens = lexer.lex();
            assertEquals(1, tokens.size());
            assertEquals(Token.TokenType.NUMBER, tokens.get(0).getTokenType());
            assertEquals("23", tokens.get(0).getTokenValue());
            for (int i = 0; i < tokens.toString().length(); i++) {
                System.out.println(tokens);
            }
        }
        catch (Exception e) {
            System.out.println("Error. Unrecognized character at line + " + lexer.getLineNumber() + " character " + lexer.getCharacterPosition());
        }
    }

    /**
     * this testString() method tests the lexer with words and prints out the tokens.
     */

    @Test
    void testString() {
        String input = "Rusho Binnabi";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(2, tokens.size());
        assertEquals(Token.TokenType.WORD, tokens.get(0).getTokenType());
        assertEquals("Rusho", tokens.get(0).getTokenValue());
        assertEquals(Token.TokenType.WORD, tokens.get(1).getTokenType());
        assertEquals("Binnabi", tokens.get(1).getTokenValue());
        for (int i = 0; i < tokens.toString().length(); i++) {
            System.out.println(tokens);
        }
    }

    /**
     * this testWordsAndNumbers() method tests the lexer with words and numbers and prints out the tokens.
     */

    @Test
    void testWordsAndNumbers() {
        String input = "Rusho 23";
        Lexer lexer = new Lexer(input);
        try {
            LinkedList<Token> tokens = lexer.lex();
            assertEquals(2, tokens.size());
            assertEquals(Token.TokenType.WORD, tokens.get(0).getTokenType());
            assertEquals("Rusho", tokens.get(0).getTokenValue());
            assertEquals(Token.TokenType.NUMBER, tokens.get(1).getTokenType());
            assertEquals("23", tokens.get(1).getTokenValue());
            for (int i = 0; i < tokens.toString().length(); i++) {
                System.out.println(tokens);
            }
        }
        catch (Exception e) {
            System.out.println("Error. Unrecognized character at line " + lexer.getLineNumber() + " and character " + lexer.getCharacterPosition());
        }
    }

    /**
     * this testSymbols() method tests the lexer with symbols and prints out the tokens.
     */

    @Test
    void testSymbols() {
        String input = "Rusho % Binnabi $ () ^ %";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(8, tokens.size());
        assertEquals(Token.TokenType.WORD, tokens.get(0).getTokenType());
        assertEquals("Rusho", tokens.get(0).getTokenValue());
        assertEquals(Token.TokenType.PERCENT, tokens.get(1).getTokenType());
        assertEquals("%", tokens.get(1).getTokenValue());
        assertEquals(Token.TokenType.WORD, tokens.get(2).getTokenType());
        assertEquals("Binnabi", tokens.get(2).getTokenValue());
        assertEquals(Token.TokenType.DOLLARSIGN, tokens.get(3).getTokenType());
        assertEquals("$", tokens.get(3).getTokenValue());
        assertEquals(Token.TokenType.LEFTPARENTHESIS, tokens.get(4).getTokenType());
        assertEquals("(", tokens.get(4).getTokenValue());
        assertEquals(Token.TokenType.RIGHTPARENTHESIS, tokens.get(5).getTokenType());
        assertEquals(")", tokens.get(5).getTokenValue());
        assertEquals(Token.TokenType.EXPONENT, tokens.get(6).getTokenType());
        assertEquals("^", tokens.get(6).getTokenValue());
        assertEquals(Token.TokenType.PERCENT, tokens.get(7).getTokenType());
        assertEquals("%", tokens.get(7).getTokenValue());
        for (int i = 0; i < tokens.toString().length(); i++) {
            System.out.println(tokens);
        }
    }

    /**
     * this testRegularExpressionPattern() method tests the lexer with regular expression patterns and prints out the tokens.
     */

    @Test
    void testRegularExpressionPattern() {
        String input = "\n'Rusho'\n'Binnabi'\n";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(9, tokens.size());
        assertEquals(Token.TokenType.SEPARATOR, tokens.get(0).getTokenType());
        assertEquals("\n", tokens.get(0).getTokenValue());
        assertEquals(Token.TokenType.REGULAREXPRESSIONPATTERN, tokens.get(1).getTokenType());
        assertEquals("`", tokens.get(1).getTokenValue());
        assertEquals(Token.TokenType.WORD, tokens.get(2).getTokenType());
        assertEquals("Rusho", tokens.get(2).getTokenValue());
        assertEquals(Token.TokenType.REGULAREXPRESSIONPATTERN, tokens.get(3).getTokenType());
        assertEquals("`", tokens.get(3).getTokenValue());
        assertEquals(Token.TokenType.SEPARATOR, tokens.get(4).getTokenType());
        assertEquals("\n", tokens.get(4).getTokenValue());
        assertEquals(Token.TokenType.REGULAREXPRESSIONPATTERN, tokens.get(5).getTokenType());
        assertEquals("`", tokens.get(5).getTokenValue());
        assertEquals(Token.TokenType.WORD, tokens.get(6).getTokenType());
        assertEquals("Binnabi", tokens.get(6).getTokenValue());
        assertEquals(Token.TokenType.REGULAREXPRESSIONPATTERN, tokens.get(7).getTokenType());
        assertEquals("`", tokens.get(7).getTokenValue());
        assertEquals(Token.TokenType.SEPARATOR, tokens.get(8).getTokenType());
        assertEquals("\n", tokens.get(8).getTokenValue());
        for (int i = 0; i < tokens.toString().length(); i++) {
            System.out.println(tokens);
        }
    }

    /**
     * this testStringLiterals() method tests the lexer with string literals and prints out the tokens.
     */

    @Test
    void testStringLiterals() {
        String input = "\"Rusho Binnabi\"";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(4, tokens.size());
        assertEquals(Token.TokenType.STRINGLITERAL, tokens.get(0).getTokenType());
        assertEquals("\"", tokens.get(0).getTokenValue());
        assertEquals(Token.TokenType.WORD, tokens.get(1).getTokenType());
        assertEquals("Rusho", tokens.get(1).getTokenValue());
        assertEquals(Token.TokenType.WORD, tokens.get(2).getTokenType());
        assertEquals("Binnabi", tokens.get(2).getTokenValue());
        assertEquals(Token.TokenType.STRINGLITERAL, tokens.get(3).getTokenType());
        assertEquals("\"", tokens.get(3).getTokenValue());
        for (int i = 0; i < tokens.toString().length(); i++) {
            System.out.println(tokens);
        }
    }

    /**
     * this testAcceptSeparators() method tests the acceptSeparators() method from the Parser with separators.
     */

    @Test
    void testAcceptSeparators() {
        String input = "; Rusho Binnabi \n";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parse();
        assertEquals(parser.acceptSeparators(parser), ";");
        assertEquals(parser.acceptSeparators(parser), "R");
        assertEquals(parser.acceptSeparators(parser), "u");
        assertEquals(parser.acceptSeparators(parser), "s");
        assertEquals(parser.acceptSeparators(parser), "h");
        assertEquals(parser.acceptSeparators(parser), "o");
        assertEquals(parser.acceptSeparators(parser), "B");
        assertEquals(parser.acceptSeparators(parser), "i");
        assertEquals(parser.acceptSeparators(parser), "n");
        assertEquals(parser.acceptSeparators(parser), "n");
        assertEquals(parser.acceptSeparators(parser), "a");
        assertEquals(parser.acceptSeparators(parser), "b");
        assertEquals(parser.acceptSeparators(parser), "i");
        assertEquals(parser.acceptSeparators(parser), "\n");
    }

    /**
     *  this testParseFunction() method tests the parseFunction() method from the Parser with functions.
     */

    @Test
    void testParseFunction() {
        ProgramNode program = new ProgramNode();
        String input = "function testFunction(int i) {" + "int j = i;" + "return j;";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parse();
        assertEquals(parser.parseFunction(program), "function");
        assertEquals(parser.parseFunction(program), " ");
        assertEquals(parser.parseFunction(program), "testFunction");
        assertEquals(parser.parseFunction(program), "(");
        assertEquals(parser.parseFunction(program), "int");
        assertEquals(parser.parseFunction(program), " ");
        assertEquals(parser.parseFunction(program), "i");
        assertEquals(parser.parseFunction(program), ")");
        assertEquals(parser.parseFunction(program), " ");
        assertEquals(parser.parseFunction(program), "{");
        assertEquals(parser.parseFunction(program), "int");
        assertEquals(parser.parseFunction(program), " ");
        assertEquals(parser.parseFunction(program), "j");
        assertEquals(parser.parseFunction(program), " ");
        assertEquals(parser.parseFunction(program), "i");
        assertEquals(parser.parseFunction(program), ";");
        assertEquals(parser.parseFunction(program), "return");
        assertEquals(parser.parseFunction(program), "j");
        assertEquals(parser.parseFunction(program), ";");
    }

    /**
     * this testParseAction() method tests the parseAction() method from the Parser with actions.
     */

    @Test
    void testParseAction() throws Exception {
        String input = "BEGIN {" + "(a=5) {" + "{" + "END }\n";
        ProgramNode program = new ProgramNode();
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parse();
        try {
            assertEquals(parser.parseAction(program), "BEGIN");
            assertEquals(parser.parseAction(program), " ");
            assertEquals(parser.parseAction(program), "{");
            assertEquals(parser.parseAction(program), "(");
            assertEquals(parser.parseAction(program), "a");
            assertEquals(parser.parseAction(program), "=");
            assertEquals(parser.parseAction(program), "5");
            assertEquals(parser.parseAction(program), ")");
            assertEquals(parser.parseAction(program), " ");
            assertEquals(parser.parseAction(program), "{");
            assertEquals(parser.parseAction(program), "{");
            assertEquals(parser.parseAction(program), "END");
            assertEquals(parser.parseAction(program), "}");
            assertEquals(parser.parseAction(program), "\n");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParseOperation() tests the parseOperation() method from the Parser with math operations.
     */

    @Test
    void testParseOperation() {
        String input = "++a"; // the 1st expression from the list of sample expressions in the assignment document for Parser 2 gets tested.
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "a");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "++$b"; // the 2nd expression from the list of sample expressions in the assignment document for Parser 2 gets tested.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "$");
            assertEquals(parser.parseOperation(), "b");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "(++d)"; // the 3rd expression from the list of sample expressions in the assignment document for Parser 2 gets tested.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "(");
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "d");
            assertEquals(parser.parseOperation(), ")");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "-5"; // the 4th expression from the list of sample expressions in the assignment document for Parser 2 gets tested.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "-");
            assertEquals(parser.parseOperation(), "5");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "'[abc]'"; // the 5th expression from the list of sample expressions in the assignment document for Parser 2 gets tested.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "'");
            assertEquals(parser.parseOperation(), "[");
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), "b");
            assertEquals(parser.parseOperation(), "c");
            assertEquals(parser.parseOperation(), "]");
            assertEquals(parser.parseOperation(), "'");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "e[++b]"; // the 6th expression from the list of sample expressions in the assignment document for Parser 2 gets tested.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "e");
            assertEquals(parser.parseOperation(), "[");
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "b");
            assertEquals(parser.parseOperation(), "]");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "$7"; // the 7th expression from the list of sample expressions in the assignment document for Parser 2 gets tested.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "$");
            assertEquals(parser.parseOperation(), "7");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParseOperationsAssignmentNodes() tests the parseOperation() method
     * from the Parser with full expressions for Assignment Nodes.
     */

    @Test
    void testParseOperationsAssignmentNodes() {
        String input = "a+=5";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "=");
            assertEquals(parser.parseOperation(), "5");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParseOperationsExponents() method tests the parseOperation() method
     * from the Parser with full expressions for exponents.
     */

    @Test
    void testParseOperationsExponents() {
        String input = "2^(3^4)"; // right associativity.
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "2");
            assertEquals(parser.parseOperation(), "^");
            assertEquals(parser.parseOperation(), "(");
            assertEquals(parser.parseOperation(), "3");
            assertEquals(parser.parseOperation(), "^");
            assertEquals(parser.parseOperation(), "4");
            assertEquals(parser.parseOperation(), ")");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "(2^3)^4"; // left associativity.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "(");
            assertEquals(parser.parseOperation(), "2");
            assertEquals(parser.parseOperation(), "^");
            assertEquals(parser.parseOperation(), "3");
            assertEquals(parser.parseOperation(), ")");
            assertEquals(parser.parseOperation(), "^");
            assertEquals(parser.parseOperation(), "4");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParsePostIncrementDecrement() method tests the parseOperation() method
     * from the Parser with full expressions with increments and decrements.
     */

    @Test
    void testParsePostIncrementDecrement() {
        String input = "a++"; // increment.
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), "+");
            assertEquals(parser.parseOperation(), "+");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "a--"; // decrement.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), "-");
            assertEquals(parser.parseOperation(), "-");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParseOperationsStringConcatenation() method tests the parseOperation() method
     * from the Parser with full expressions for string concatenation.
     */

    @Test
    void testParseOperationsStringConcatenation() {
        String input = "string string";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "s");
            assertEquals(parser.parseOperation(), "t");
            assertEquals(parser.parseOperation(), "r");
            assertEquals(parser.parseOperation(), "i");
            assertEquals(parser.parseOperation(), "n");
            assertEquals(parser.parseOperation(), "g");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "s");
            assertEquals(parser.parseOperation(), "t");
            assertEquals(parser.parseOperation(), "r");
            assertEquals(parser.parseOperation(), "i");
            assertEquals(parser.parseOperation(), "n");
            assertEquals(parser.parseOperation(), "g");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParseOperationMatch() method tests the parseOperation() method
     * from the Parser with full expressions for match and non-match.
     */

    @Test
    void testParseOperationMatch() {
        String input = "a ~ b"; // match.
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "~");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "b");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
        input = "a !~ b"; // non-match.
        lexer = new Lexer(input);
        tokens = lexer.lex();
        parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "!");
            assertEquals(parser.parseOperation(), "~");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "b");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParseOperationArray() method tests the parseOperation() method
     * from the Parser with full expressions for arrays.
     */

    @Test
    void testParseOperationArray() {
        String input = "a in array";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "in");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "array");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParseOperationLogicalAND() method tests the parseOperation() method
     * from the Parser with full expressions for logical ANDs.
     */

    @Test
    void testParseOperationLogicalAND() {
        String input = "a && b";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "&&");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "b");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParseOperationLogicalOR() method tests the parseOperation() method
     * from the Parser with full expressions for logical ORs.
     */

    @Test
    void testParseOperationLogicalOR() {
        String input = "a || b";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parseOperation();
        try {
            assertEquals(parser.parseOperation(), "a");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "||");
            assertEquals(parser.parseOperation(), " ");
            assertEquals(parser.parseOperation(), "b");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParser() method tests the parse() method from the Parser to try and parse full AWK programs.
     */

    @Test
    void testParser() {
        String input = """
                BEGIN {FS=","; line1=0; line2=0; sum=0}

                    {line1+=$0}
                    {line2+=$0}
                    {sum+=$0}
                  \s
                   END {print() "Line 1: "line1" \\nLine 2: "line2" \\nGrand Total:", sum}""";

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parse();
        try {
            assertEquals(parser.parse(), "BEGIN");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "{FS=\",\";");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "line1=0;");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "line2=0;");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "sum=0}\n");
            assertEquals(parser.parse(), "\n");
            assertEquals(parser.parse(), "{line1+=$0}\n");
            assertEquals(parser.parse(), "{line2+=$0}\n");
            assertEquals(parser.parse(), "\n");
            assertEquals(parser.parse(), "END");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "{print()");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "\"Line 1:");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "\\nLine 2:");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "\\nGrand Total:\",");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "sum}");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testParserChanges() method tests the parse() method from the Parser
     * to try and parse full AWK programs with the changes made to the Parser.
     */

    @Test
    void testParserChanges() {
        String input = """
                BEGIN {FS=","; line1=0; line2=0; sum=0}

                    {line1+=$0}
                    {line2+=$0}
                    {sum+=$0}
                  \s
                   END {print "Line 1: "line1" \\nLine 2: "line2" \\nGrand Total:", sum}""";

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        parser.parse();
        try {
            assertEquals(parser.parse(), "BEGIN");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "{FS=\",\";");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "line1=0;");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "line2=0;");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "sum=0}\n");
            assertEquals(parser.parse(), "\n");
            assertEquals(parser.parse(), "{line1+=$0}\n");
            assertEquals(parser.parse(), "{line2+=$0}\n");
            assertEquals(parser.parse(), "\n");
            assertEquals(parser.parse(), "END");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "{print");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "\"Line 1:");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "\\nLine 2:");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "\\nGrand Total:\",");
            assertEquals(parser.parse(), " ");
            assertEquals(parser.parse(), "sum}");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error parsing this program.");
        }
    }

    /**
     * this testLineManager() method tests the line manager from the interpreter.
     */

    @Test
    void testLineManager() {
        String input = """
                Line 1,
                Sample test.
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.LineManager, "Line");
            assertEquals(interpreter.LineManager," ");
            assertEquals(interpreter.LineManager, "1");
            assertEquals(interpreter.LineManager, ",");
            assertEquals(interpreter.LineManager, "Sample");
            assertEquals(interpreter.LineManager, " ");
            assertEquals(interpreter.LineManager, "test");
            assertEquals(interpreter.LineManager, ".");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testPrint() tests the print built-in function from the interpreter.
     */

    @Test
    void testPrint() {
        String input = """
                print(test)
                print(number-integer)
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.print(), "print");
            assertEquals(interpreter.print(), "(");
            assertEquals(interpreter.print(), "test");
            assertEquals(interpreter.print(), ")");
            assertEquals(interpreter.print(), "print");
            assertEquals(interpreter.print(), "(");
            assertEquals(interpreter.print(), "number");
            assertEquals(interpreter.print(), "-");
            assertEquals(interpreter.print(), "integer");
            assertEquals(interpreter.print(), ")");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testPrintF() method tests the printf built-in function from the interpreter.
     */

    @Test
    void testPrintF() {
        String input = """
                printf(%s, string)
                printf(%d, integer)
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.printf(), "printf");
            assertEquals(interpreter.printf(), "(");
            assertEquals(interpreter.printf(), "%s");
            assertEquals(interpreter.printf(), ",");
            assertEquals(interpreter.printf(), " ");
            assertEquals(interpreter.printf(), "string");
            assertEquals(interpreter.printf(), ")");
            assertEquals(interpreter.printf(), "printf");
            assertEquals(interpreter.printf(), "(");
            assertEquals(interpreter.printf(), "%d");
            assertEquals(interpreter.printf(), ",");
            assertEquals(interpreter.printf(), " ");
            assertEquals(interpreter.printf(), "integer");
            assertEquals(interpreter.printf(), ")");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testGetLine() method tests the getline built-in function from the interpreter.
     */

    @Test
    void testGetLine() {
        String input = """
                Sample Text 1
                Sample Text 2
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.getLine(), "Sample");
            assertEquals(interpreter.getLine(), " ");
            assertEquals(interpreter.getLine(), "Text");
            assertEquals(interpreter.getLine(), " ");
            assertEquals(interpreter.getLine(), "1");
            assertEquals(interpreter.getLine(), "Sample");
            assertEquals(interpreter.getLine(), " ");
            assertEquals(interpreter.getLine(), "Text");
            assertEquals(interpreter.getLine(), " ");
            assertEquals(interpreter.getLine(), "2");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testNext() method tests the next built-in function from the interpreter.
     */

    @Test
    void testNext() {
        String input = """
                Sample Text
                next
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.next(), "Sample");
            assertEquals(interpreter.next(), " ");
            assertEquals(interpreter.next(), "Test");
            assertEquals(interpreter.next(), "next");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testGSub() method tests the gsub built-in function from the interpreter.
     */

    @Test
    void testGSub() {
        String input = """
                ICSI311
                ICSI333
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals("`311`", "The Greatest Course EVAR");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testIndex() method tests the index built-in function from the interpreter.
     */

    @Test
    void testIndex() {
        String input = """
                /Britain/, United Kingdom
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.index(), "/");
            assertEquals(interpreter.index(), "Britain");
            assertEquals(interpreter.index(), "/");
            assertEquals(interpreter.index(), ",");
            assertEquals(interpreter.index(), " ");
            assertEquals(interpreter.index(), "United");
            assertEquals(interpreter.index(), " ");
            assertEquals(interpreter.index(), "Kingdom");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testLength() method tests the length built-in function from the interpreter.
     */

    @Test
    void testLength() {
        String input = """
                1: Rusho ICSI311
                2: Bin ICSI210
                3: Nabi ICSI300Z
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.length(), "1:");
            assertEquals(interpreter.length(), " ");
            assertEquals(interpreter.length(), "Rusho");
            assertEquals(interpreter.length(), " ");
            assertEquals(interpreter.length(), "ICSI311");
            assertEquals(interpreter.length(), "2:");
            assertEquals(interpreter.length(), " ");
            assertEquals(interpreter.length(), "Bin");
            assertEquals(interpreter.length(), " ");
            assertEquals(interpreter.length(), "ICSI210");
            assertEquals(interpreter.length(), "3:");
            assertEquals(interpreter.length(), " ");
            assertEquals(interpreter.length(), "Nabi");
            assertEquals(interpreter.length(), " ");
            assertEquals(interpreter.length(), "ICSI300Z");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testMatch() method tests the match built-in function from the interpreter.
     */

    @Test
    void testMatch() {
        String input = """
                xxx
                yyy
                zzz
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.match(), "/xxx/");
            assertEquals(interpreter.match(), "/yyy/");
            assertEquals(interpreter.match(), "/zzz/");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testSplit() method tests the split built-in function from the interpreter.
     */

    @Test
    void testSplit() {
        String input = """
                cul-de-sac
                """;

        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.split(), "cul-de-sac");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testGetIDT() method tests the getIDT() method from the interpreter.
     */

    @Test
    void testGetIDT() {
        Lexer lexer = new Lexer("");
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        var node = new OperationNode(new VariableReferenceNode("a", null), OperationNode.Operations.ADD,
                Optional.of(new OperationNode(new ConstantNode(2), OperationNode.Operations.ADD, Optional.of(new ConstantNode(2)))));
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.getIDT(node, null), "");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error interpreting this program.");
        }
    }

    /**
     * this testConditionalBlock() method tests conditional blocks from the interpreter.
     */

    @Test
    void testConditionalBlocks() {
        Lexer lexer = new Lexer("rushoSampleProgramAWKNumberOfWords.txt");
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        // this node is saying i is initialized to 0, and it has a condition block testing to see if i is less than or equal to 2.
        var node = new OperationNode(new VariableReferenceNode("i", Optional.of(new ConstantNode(0))), OperationNode.Operations.LE,
                Optional.of(new OperationNode(new VariableReferenceNode("i", Optional.empty()), OperationNode.Operations.LE,
                        Optional.of(new ConstantNode(2)))));
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.interpretBlock(node), node);
            assertEquals(interpreter.interpretProgram(), node);
        }
        catch (Exception e) {
            System.out.println("Error. There was an error running this program.");
        }
    }

    /**
     * this testUserCreatedFunctionsWithCalls() method tests user created functions with calls from the interpreter.
     */

    @Test
    void testUserCreatedFunctionsWithCalls() {
        Lexer lexer = new Lexer("rushoUserCreatedFunctionWithCalls.txt");
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        var node = new FunctionCallNode(new VariableReferenceNode("testFunction", null), null);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.runFunctionCall(node, null), "");
            assertEquals(interpreter.interpretProgram(), node);
        }
        catch (Exception e) {
            System.out.println("Error. There was an error running this program.");
        }
    }

    /**
     * this testInputProcessing() method tests input processing from the interpreter.
     */

    @Test
    void testInputProcessing() throws Exception {
        Lexer lexer = new Lexer("rushoInputProcessing.txt");
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        var node = new BlockNode(parser.parseBlock().getStatements(), null);
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.interpretBlock(node), "");
            assertEquals(interpreter.interpretProgram(), "");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error running this program.");
        }
    }

    /**
     * this testMathAndLogic() method tests math and logic from the interpreter.
     */

    @Test
    void testMathAndLogic() {
        Lexer lexer = new Lexer("rushoMathAndLogic.txt");
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        // this node1 is saying that a variable called a is initialized to a value of 6.
        var node1 = new OperationNode(new VariableReferenceNode("a", Optional.of(new ConstantNode(6))),
                OperationNode.Operations.EQ, Optional.of(new OperationNode(new VariableReferenceNode("a",
                Optional.of(new ConstantNode(6))), OperationNode.Operations.EQ, Optional.of(new ConstantNode(6)))));
        // this node1 is saying that a variable called b is initialized to a value of 9.
        var node2 = new OperationNode(new VariableReferenceNode("b", Optional.of(new ConstantNode(9))),
                OperationNode.Operations.EQ, Optional.of(new OperationNode(new VariableReferenceNode("b",
                Optional.of(new ConstantNode(9))), OperationNode.Operations.EQ, Optional.of(new ConstantNode(9)))));
        // this node3 is saying that a variable called c has the added values of a + b (node1 and node2).
        var node3 = new OperationNode(new VariableReferenceNode("c", Optional.of(node1)), OperationNode.Operations.ADD,
                Optional.of(new VariableReferenceNode("c", Optional.of(node2))));
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.operationNode(node1, null), "");
            assertEquals(interpreter.operationNode(node2, null), "");
            assertEquals(interpreter.operationNode(node3, null), "");
            assertEquals(interpreter.interpretProgram(), "");
        }
        catch (Exception e) {
            System.out.println("Error. There was an error running this program.");
        }
    }

    /**
     * this testLoops() method tests loops from the interpreter.
     */

    @Test
    void testLoops() {
        Lexer lexer = new Lexer("rushoSampleProgramAWKNumberOfWords.txt");
        LinkedList<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        var loopNode = new OperationNode(new VariableReferenceNode("i", Optional.of(new ConstantNode(0))), null,
                Optional.of(new OperationNode(new VariableReferenceNode("i", Optional.empty()), OperationNode.Operations.LE,
                        Optional.of(new ConstantNode(2)))));
        Interpreter interpreter = new Interpreter(parser.parse(), "");
        try {
            assertEquals(interpreter.interpretBlock(loopNode++), loopNode);
            assertEquals(interpreter.interpretProgram(), loopNode);
        }
        catch (Exception e) {
            System.out.println("Error. There was an error running this program.");
        }
    }
}