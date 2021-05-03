package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;

import java.util.Optional;

public class MultilineCommentTokenizer implements Tokenizer {
    @Override
    public Optional<Token> parse(ParsingPointer pointer) {
        if (pointer.getCurrentChar() == '/') {
            Optional<Character> next = pointer.peek();
            if (next.isPresent() && next.get() == '*') {
                return Optional.of(buildToken(pointer));
            }
        }

        return Optional.empty();
    }
    private Token buildToken(ParsingPointer pointer) {
        StringBuilder lexeme = new StringBuilder();

        int i = 2;
        Optional<Character> optionalValue = pointer.peek(i);

        while (optionalValue.isPresent()) {
            char value = optionalValue.get();

            if (value == '*') {
                Optional<Character> next = pointer.peek(i + 1);
                if (next.isPresent() && next.get() == '/') {
                    i++;
                    break;
                }
            }

            lexeme.append(value);
            optionalValue = pointer.peek(++i);
        }

        Token token = Token.builder()
                .type(TokenType.MULTILINE_COMMENT)
                .lexeme(lexeme.toString())
                .pointer(pointer)
                .build();

        pointer.advance(i);

        return token;
    }
}
