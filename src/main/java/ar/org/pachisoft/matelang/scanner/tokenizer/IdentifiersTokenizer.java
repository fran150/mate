package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;
import ar.org.pachisoft.matelang.utils.ReservedWord;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Optional;

public class IdentifiersTokenizer implements Tokenizer {
    @Override
    public Optional<Token> parse(ParsingPointer pointer) {
        if (isValidStartChar(pointer.getCurrentChar())) {
            int i = 0;
            StringBuilder literal = new StringBuilder();
            literal.append(pointer.getCurrentChar());

            Optional<Character> next = pointer.peek(++i);

            while (next.isPresent() && isValidChar(next.get())) {
                literal.append(next.get());
                next = pointer.peek(++i);
            }

            String text = literal.toString();

            ReservedWord word = null;
            TokenType type;
            if (ReservedWord.isReservedWord(text)) {
                type = TokenType.RESERVED_WORD;
                word = ReservedWord.getReservedWordFor(text);
            } else {
                type = TokenType.IDENTIFIER;
            }

            Token token = Token.builder()
                    .lexeme(text)
                    .type(type)
                    .literal(StringEscapeUtils.unescapeJava(text))
                    .pointer(pointer)
                    .reservedWord(word)
                    .build();

            pointer.advance(i - 1);

            return Optional.of(token);
        }

        return Optional.empty();
    }

    private boolean isValidStartChar(char value) {
        return (value >= 'a' && value <= 'z') ||
                (value >= 'A' && value <= 'Z') ||
                (value == '_');
    }

    private boolean isValidChar(char value) {
        return isValidStartChar(value) || (value >= '0' && value <= '9');
    }
}
