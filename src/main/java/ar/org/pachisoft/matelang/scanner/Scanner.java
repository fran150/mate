package ar.org.pachisoft.matelang.scanner;

import ar.org.pachisoft.matelang.scanner.tokenizer.Tokenizer;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Scanner {
    private static final List<Tokenizer> tokenizers;

    static {
        tokenizers = TokenizersProvider.getTokenizers();
    }

    public void scanTokens(File file, String source, List<Token> tokens) {
        ParsingPointer pointer = ParsingPointer.builder()
                .file(file)
                .source(source)
                .build();

        while (!pointer.isAtEnd()) {
            Token token = getToken(pointer);

            // FIXME: Remove when the above function throws exception
            if (token != null && !token.getType().equals(TokenType.IGNORABLE)) {
                tokens.add(token);
            }

            pointer.advance();
        }
    }

    private Token getToken(ParsingPointer pointer) {
        for (Tokenizer tokenizer : tokenizers) {
            Optional<Token> token = tokenizer.parse(pointer);

            if (token.isPresent()) {
                return token.get();
            }
        }

        // FIXME: Should throw syntax error
        return null;
    }
}
