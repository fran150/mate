package ar.org.pachisoft.matelang.scanner.tokenizer;

import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.scanner.Token;

import java.util.Optional;

public interface Tokenizer {
    Optional<Token> parse(ParsingPointer pointer);
}
