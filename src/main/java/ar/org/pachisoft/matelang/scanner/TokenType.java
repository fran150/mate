package ar.org.pachisoft.matelang.scanner;

public enum TokenType {
    IGNORABLE,

    TRUE, FALSE,

    // Single-character tokens.
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    LEFT_BRACKET, RIGHT_BRACKET,
    COMMA, DOT,

    // One or two character tokens.
    MINUS, PLUS, SLASH, STAR,
    MINUS_EQUAL, PLUS_EQUAL, SLASH_EQUAL, STAR_EQUAL,
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Comments
    INLINE_COMMENT, MULTILINE_COMMENT,

    // Literals.
    IDENTIFIER, STRING, INTEGER, DECIMAL,

    // Keywords.
    RESERVED_WORD,

    EOF
}
