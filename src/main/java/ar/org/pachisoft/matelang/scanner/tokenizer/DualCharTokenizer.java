package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DualCharTokenizer implements Tokenizer {
    private final char first;
    private final char second;

    private final TokenType singleCharTokenType;
    private final TokenType doubleCharTokenType;

    @Override
    public Optional<Token> parse(ParsingPointer pointer) {
        if (pointer.getCurrentChar() == first) {
            Optional<Character> nextChar = pointer.peek();

            if (nextChar.isPresent() && nextChar.get().equals(second)) {
                pointer.advance();

                return Optional.of(Token.builder()
                        .type(doubleCharTokenType)
                        .lexeme(String.valueOf(first) + second)
                        .pointer(pointer)
                        .build());
            } else {
                return Optional.of(Token.builder()
                        .type(singleCharTokenType)
                        .lexeme(Character.toString(first))
                        .pointer(pointer)
                        .build());
            }
        }

        return Optional.empty();
    }
}
