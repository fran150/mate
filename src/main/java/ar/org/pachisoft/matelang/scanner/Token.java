package ar.org.pachisoft.matelang.scanner;

import ar.org.pachisoft.matelang.utils.ReservedWord;
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

    ReservedWord reservedWord;

    @Builder
    public Token(ParsingPointer pointer, TokenType type, String lexeme, Object literal,
                 ReservedWord reservedWord) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;

        this.file = pointer.getFile();
        this.line = pointer.getLine();
        this.column = pointer.getColumn();

        this.reservedWord = reservedWord;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal + "\n";
    }
}