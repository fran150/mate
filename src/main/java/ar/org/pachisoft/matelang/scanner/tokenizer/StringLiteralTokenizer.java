package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Optional;

public class StringLiteralTokenizer implements Tokenizer {

    @Override
    public Optional<Token> parse(ParsingPointer pointer) {
        if (pointer.getCurrentChar() == '"') {
            int i = 0;
            Optional<Character> next = pointer.peek(++i);

            StringBuilder literal = new StringBuilder();
            while (next.isPresent() && next.get() != '"') {
                literal.append(next.get());
                next = pointer.peek(++i);
            }

            String text = literal.toString();
            Token token = Token.builder()
                    .lexeme(text)
                    .type(TokenType.STRING)
                    .literal(StringEscapeUtils.unescapeJava(text))
                    .pointer(pointer)
                    .build();

            pointer.advance(i);

            return Optional.of(token);
        }

        return Optional.empty();
    }
}
