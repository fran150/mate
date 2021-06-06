package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;

import java.util.Optional;

public class DecimalLiteralTokenizer implements Tokenizer {

    @Override
    public Optional<Token> parse(ParsingPointer pointer) {
        // Number start found, iterate until it ends or decimal starts
        if (isDigit(pointer.getCurrentChar())) {
            StringBuilder literal = new StringBuilder();
            int i = appendDigits(pointer, 0, literal);

            Optional<Character> current = pointer.peek(i);

            TokenType type;
            Object value;
            String text;

            if (current.isPresent() && current.get() == '.') {
                literal.append('.');
                i = appendDigits(pointer, ++i, literal);
                type = TokenType.DECIMAL;
                text = literal.toString();
                value = Double.parseDouble(text);
            } else {
                text = literal.toString();
                value = Integer.parseInt(text);
                type = TokenType.INTEGER;
            }

            Token token = Token.builder()
                    .lexeme(text)
                    .type(type)
                    .literal(value)
                    .pointer(pointer)
                    .build();

            pointer.advance(i - 1);

            return Optional.of(token);
        }

        return Optional.empty();
    }

    private int appendDigits(ParsingPointer pointer, int offset, StringBuilder literal) {
        Optional<Character> next = pointer.peek(offset);

        while (next.isPresent() && isDigit(next.get())) {
            literal.append(next.get());
            next = pointer.peek(++offset);
        }

        return offset;
    }

    private boolean isDigit(char current) {
        return current >= '0' && current <= '9';
    }
}
