package ar.org.pachisoft.matelang.scanner;

import ar.org.pachisoft.matelang.scanner.tokenizer.DualCharTokenizer;
import ar.org.pachisoft.matelang.scanner.tokenizer.InlineCommentTokenizer;
import ar.org.pachisoft.matelang.scanner.tokenizer.MultilineCommentTokenizer;
import ar.org.pachisoft.matelang.scanner.tokenizer.SingleCharTokenizer;
import ar.org.pachisoft.matelang.scanner.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenizersProvider {
    public static List<Tokenizer> getTokenizers() {
        List<Tokenizer> tokenizers = new ArrayList<>();

        // Ignorable tokens
        tokenizers.add(new SingleCharTokenizer(' ', TokenType.IGNORABLE));
        tokenizers.add(new SingleCharTokenizer('\t', TokenType.IGNORABLE));
        tokenizers.add(new SingleCharTokenizer('\r', TokenType.IGNORABLE));
        tokenizers.add(new SingleCharTokenizer('\n', TokenType.IGNORABLE));

        // Single Character tokens
        tokenizers.add(new SingleCharTokenizer('(', TokenType.LEFT_PAREN));
        tokenizers.add(new SingleCharTokenizer(')', TokenType.RIGHT_PAREN));
        tokenizers.add(new SingleCharTokenizer('{', TokenType.LEFT_BRACE));
        tokenizers.add(new SingleCharTokenizer('}', TokenType.RIGHT_BRACE));
        tokenizers.add(new SingleCharTokenizer(',', TokenType.COMMA));
        tokenizers.add(new SingleCharTokenizer('.', TokenType.DOT));

        // Single or dual character tokens
        tokenizers.add(new DualCharTokenizer('+', '=', TokenType.PLUS, TokenType.PLUS_EQUAL));
        tokenizers.add(new DualCharTokenizer('-', '=', TokenType.MINUS, TokenType.MINUS_EQUAL));
        tokenizers.add(new DualCharTokenizer('*', '=', TokenType.STAR, TokenType.STAR_EQUAL));

        tokenizers.add(new DualCharTokenizer('!', '=', TokenType.BANG, TokenType.BANG_EQUAL));
        tokenizers.add(new DualCharTokenizer('=', '=', TokenType.EQUAL, TokenType.EQUAL_EQUAL));
        tokenizers.add(new DualCharTokenizer('>', '=', TokenType.GREATER, TokenType.GREATER_EQUAL));
        tokenizers.add(new DualCharTokenizer('<', '=', TokenType.LESS, TokenType.LESS_EQUAL));

        tokenizers.add(new InlineCommentTokenizer());
        tokenizers.add(new MultilineCommentTokenizer());

        // Order matters here, we need first to discard that the / was not a comment
        tokenizers.add(new DualCharTokenizer('/', '=', TokenType.SLASH, TokenType.SLASH_EQUAL));

        return tokenizers;
    }
}
