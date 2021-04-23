package ar.org.pachisoft.matelang.scanner;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {
    private final TokenType type;
    private final String lexeme;
    private final Object literal;
    private final String file;
    private final int column;
    private final int line;

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}