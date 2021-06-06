package ar.org.pachisoft.matelang.scanner;

import ar.org.pachisoft.matelang.error.ErrorHandler;
import ar.org.pachisoft.matelang.scanner.tokenizer.Tokenizer;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class Scanner {
    private static final List<Tokenizer> tokenizers;
    private final ErrorHandler errorHandler;

    static {
        tokenizers = TokenizersProvider.getTokenizers();
    }

    public void scanTokens(File file, String source, List<Token> tokens) {
        ParsingPointer pointer = ParsingPointer.builder()
                .file(file)
                .source(source)
                .build();

        boolean unknownToken = false;
        while (!pointer.isAtEnd()) {
            Token token = getToken(pointer);

            if (token != null) {
                if (!token.getType().equals(TokenType.IGNORABLE)) {
                    tokens.add(token);
                }

                unknownToken = false;
            } else {
                if (!unknownToken) {
                    errorHandler.compilerError(pointer, "Syntax Error.");
                }

                unknownToken = true;
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

        return null;
    }
}
