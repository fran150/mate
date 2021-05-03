package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class SingleCharTokenizer implements Tokenizer {
    private final char expected;
    private final TokenType type;

    @Override
    public Optional<Token> parse(ParsingPointer pointer) {
        char currentChar = pointer.getCurrentChar();

        if (currentChar == expected) {
            return Optional.of(Token.builder()
                    .type(type)
                    .lexeme(Character.toString(currentChar))
                    .pointer(pointer)
                    .build());
        } else {
            return Optional.empty();
        }
    }
}
