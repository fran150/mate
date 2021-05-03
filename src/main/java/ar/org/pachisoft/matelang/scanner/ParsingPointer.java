package ar.org.pachisoft.matelang.scanner;

import lombok.Builder;
import lombok.Getter;

import java.io.File;
import java.util.Optional;

public class ParsingPointer {
    @Getter
    private final File file;
    private final StringBuilder source;

    private int current;

    @Getter
    private int line;
    @Getter
    private int column;

    private int lineStart;

    @Builder
    private ParsingPointer(File file, String source) {
        this.file = file;
        this.source = new StringBuilder(source);

        this.current = 0;
        this.line = 0;
        this.column = 0;
        this.lineStart = 0;
    }

    public boolean advance() {
        if (isAtEnd()) {
            return false;
        } else {
            char value = getCurrentChar();
            if (value == '\n') {
                line++;
                column = 0;
                lineStart = current + 1;
            } else {
                column++;
            }

            current++;

            return true;
        }
    }

    public boolean advance(int count) {
        if ((current + count) >= source.length()) {
            return false;
        }

        for (int i = 0; i < count; i++) {
            advance();
        }

        return true;
    }

    public boolean isAtEnd() {
        return current > source.length() - 1;
    }

    public String getPreContext(int lines) {
        StringBuilder context = new StringBuilder();
        int remainingLines = lines;

        for (int i = lineStart - 1; i >= 0 && remainingLines > 0; i--) {
            char character = source.charAt(i);

            if (character == '\n') {
                remainingLines--;
            }

            context.insert(0, character);
        }

        return context.toString();
    }

    public String getPostContext(int lines) {
        StringBuilder context = new StringBuilder();
        int remainingLines = lines;

        for (int i = getCurrentLineEnd() + 1; i < source.length() && remainingLines > 0; i++) {
            char character = source.charAt(i);

            if (character == '\n') {
                remainingLines--;
            }

            context.append(character);
        }

        return context.toString();
    }

    private int getCurrentLineEnd() {
        int lineEnd = source.indexOf("\n", lineStart);

        if (lineEnd >= 0) {
            return lineEnd;
        } else {
            return source.length() - 1;
        }
    }

    public String getCurrentLine() {
        return source.substring(lineStart, getCurrentLineEnd());
    }

    public char getCurrentChar() {
        return source.charAt(current);
    }

    public Optional<Character> peek() {
        return peek(1);
    }

    public Optional<Character> peek(int offset) {
        int offsetPosition = current + offset;

        if (offsetPosition >= 0 && offsetPosition < source.length()) {
            return Optional.of(source.charAt(current + offset));
        } else {
            return Optional.empty();
        }
    }

    public ParsingPointer clone() {
        ParsingPointer clone = new ParsingPointer(file, source.toString());

        clone.current = this.current;
        clone.line = this.line;
        clone.column = this.column;
        clone.lineStart = this.lineStart;

        return clone;
    }
}
