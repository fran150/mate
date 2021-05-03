package ar.org.pachisoft.matelang.scanner;

public enum TokenType {
    IGNORABLE,

    // Single-character tokens.
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
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
    IDENTIFIER, STRING, NUMBER,

    // Keywords.
    AND, CLASS, ELSE, FALSE, FUN, FOR, IF, NIL, OR,
    PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE,

    EOF
}
