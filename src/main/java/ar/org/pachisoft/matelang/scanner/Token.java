package ar.org.pachisoft.matelang.scanner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.File;

@Value
public class Token {
    TokenType type;
    String lexeme;
    Object literal;

    File file;
    int column;
    int line;

    @Builder
    public Token(ParsingPointer pointer, TokenType type, String lexeme, Object literal) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;

        this.file = pointer.getFile();
        this.line = pointer.getLine();
        this.column = pointer.getColumn();
    }

    public String toString() {
        return type + " " + lexeme + " " + literal + "\n";
    }
}