package ar.org.pachisoft.matelang.scanner;

import ar.org.pachisoft.matelang.utils.ReservedWord;
import lombok.Builder;
import lombok.Value;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Value
public class Token {
    TokenType type;
    String lexeme;
    Object literal;

    File file;
    int column;
    int line;

    int tokenPosition;

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
        this.tokenPosition = 0;

        this.reservedWord = reservedWord;
    }

    public ParsingPointer getPointer() throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(file);
        String source = new String(bytes, Charset.defaultCharset());

        ParsingPointer pointer = ParsingPointer.builder()
                .file(file)
                .source(source)
                .build();

        positionPointer(pointer);

        return pointer;
    }

    private void positionPointer(ParsingPointer pointer) {
        while (pointer.getLine() < line && pointer.getColumn() < column) {
            pointer.advance();
        }
    }

    public String toString() {
        return type + " " + lexeme + " " + literal + "\n";
    }
}