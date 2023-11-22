/**
 * Name: Rusho Binnabi
 * Date: 9/2/2023
 * Assignment: Lexer 1
 * Class: ICSI 311 - Fall 2023
 */

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Awk {

    // this Awk class will be the "main" class that will take a command line parameter of a filename.
    // It then checks the parameter. It calls the readAllBytes() on the filename. Then it passes
    // the result to the lexer and prints out the tokens.

    // it also parses and interprets the data from the lexer.


    /**
     * the main() method runs the code for the Lexer, the Parser, and the Interpreter.
     * @param args the argument is the code being run.
     */

    public static void main(String[] args) {
        Lexer lexer = null;
        Parser parser = null;
        var awkFileName = args[0];
        var textFileName = args[1];
        try {
            Path filePath = Paths.get(awkFileName);
            String fileContents = new String(Files.readAllBytes(filePath));
            lexer = new Lexer(fileContents);
            LinkedList<Token> tokens = lexer.lex();
            parser = new Parser(tokens);
            new Interpreter(parser.parse(), textFileName);
        }
        catch (Exception e) {
            if (lexer != null)
                System.out.println("Error. Something went wrong at line " + lexer.getLineNumber() + " and character " + lexer.getCharacterPosition() + " in the lexer.");
            System.out.println("Error. Something went wrong at " + parser + " in the parser.");
        }
    }

}
