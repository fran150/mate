package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;

import java.util.Optional;

public class InlineCommentTokenizer implements Tokenizer {
    @Override
    public Optional<Token> parse(ParsingPointer pointer) {
        if (pointer.getCurrentChar() == '/') {
            Optional<Character> next = pointer.peek();
            if (next.isPresent() && next.get() == '/') {
                return Optional.of(buildToken(pointer));
            }
        }

        return Optional.empty();
    }

    private Token buildToken(ParsingPointer pointer) {
        StringBuilder lexeme = new StringBuilder();

        int i = 2;
        Optional<Character> value = pointer.peek(i);

        while (value.isPresent() && value.get() != '\n') {
            lexeme.append(value.get());
            value = pointer.peek(i++);
        }

        Token token = Token.builder()
                .type(TokenType.INLINE_COMMENT)
                .lexeme(lexeme.toString())
                .pointer(pointer)
                .build();

        pointer.advance(i);

        return token;
    }
}
