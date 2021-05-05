package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Builder
public class NonDecimalLiteralTokenizer implements Tokenizer {
    private final Map<Character, Integer> symbolValues;
    private final int numericBase;
    private final String expectedStart;

    @Override
    public Optional<Token> parse(ParsingPointer pointer) {
        if (checkFor(pointer, expectedStart)) {
            int i = 1;
            Optional<Character> current = pointer.peek(++i);

            StringBuilder literal = new StringBuilder();

            while (current.isPresent() && validChar(current.get())) {
                literal.append(current.get());
                current = pointer.peek(++i);
            }

            Token token = Token.builder()
                    .lexeme(literal.toString())
                    .type(TokenType.INTEGER)
                    .literal(getValue(literal))
                    .pointer(pointer)
                    .build();

            pointer.advance(i);

            return Optional.of(token);
        }

        return Optional.empty();
    }

    private boolean validChar(char value) {
        return symbolValues.containsKey(value);
    }

    private long getValue(StringBuilder literal) {
        long value = 0;

        for (int i = 0; i < literal.length(); i++) {
            int index = literal.length() - (i + 1);
            value += symbolValues.get(literal.charAt(index)) * Math.pow(numericBase, i);
        }

        return value;
    }

    private boolean checkFor(ParsingPointer pointer, String expected) {
        if (pointer.getCurrentChar() != expected.charAt(0)) {
            return false;
        }

        for (int i = 1; i < expected.length(); i++) {
            Optional<Character> current = pointer.peek(i);
            if (current.isEmpty() || !current.get().equals(expected.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
