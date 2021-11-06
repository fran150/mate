package ar.org.pachisoft.matelang.parser;

import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;

import java.util.ArrayList;
import java.util.List;

public class TokenList {
    private List<Token> tokens;
    private int current;

    private TokenList(List<Token> tokens) {
        this.tokens = new ArrayList<>(tokens);
        current = 0;
    }

    public boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                return true;
            }
        }

        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().getType() == type;
    }

    public Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    public boolean isAtEnd() {
        return current == tokens.size();
    }

    public Token peek() {
        return tokens.get(current);
    }

    public Token previous() {
        return tokens.get(current - 1);
    }
}
