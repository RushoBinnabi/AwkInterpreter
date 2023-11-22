/**
 * Name: Rusho Binnabi
 * Date: 9/15/2023
 * Assignment: Parser 1
 * Class: ICSI 311 - Fall 2023
 */

import java.util.LinkedList;
import java.util.Optional;

public class TokenHandler {

    // this TokenHandler class manages the token "stream" in the same way that the String Handler
    // managed the stream of characters for the Lexer.
    private final LinkedList<Token> tokens;

    /**
     * this TokenHandler() method initializes the linked list of tokens.
     * @param l the argument is the linked list of tokens that the Lexer lexed.
     */

    public TokenHandler(LinkedList<Token> l) {
        tokens = new LinkedList<>(l);
    }

    /**
     * this peek() method looks ahead by i tokens and returns the token if the end of the list of tokens hasn't been reached yet.
     * @param i the amount of tokens to look ahead by.
     * @return the token.
     */

    Optional<Token> peek(int i) {
        Optional<Token> token = Optional.empty();
        if (tokens.getLast() != null) {
            token = Optional.ofNullable(tokens.get(i));
        }
        return token;
    }


    /**
     * this getTokens() method gets the token value for the linked list.
     * @param i the token to be returned.
     * @return a token at position i.
     */

    public Token getTokens(int i) {
        return tokens.get(i);
    }

    /**
     * this moreTokens() checks to see if the list of tokens is empty or not.
     * @return true if the token list is empty, or false otherwise.
     */

    public boolean moreTokens() {
        return tokens.isEmpty();
    }

    /**
     * this matchAndRemove() method looks at the head of the list. If the type of token of the head is the same as the argument,
     * it then removes that token from the list of tokens and returns it. In all other cases, it returns Optional.Empty().
     * @param t the token to be matched and possibly removed.
     * @return an empty optional if the token type of the head is not the same as the parameter. It also returns the token it removed
     * if the type of token of the head is the same as the argument.
     */

    Optional<Token> matchAndRemove(Token.TokenType t) {
        if (tokens.isEmpty())
            return Optional.empty();
        else if (tokens.peek().getTokenType() == t) {
            return Optional.ofNullable(tokens.remove());
        }
        else {
            return Optional.empty();
        }
    }
}
