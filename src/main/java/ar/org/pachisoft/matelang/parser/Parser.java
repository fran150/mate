package ar.org.pachisoft.matelang.parser;

import ar.org.pachisoft.matelang.parser.expressions.BinaryExpression;
import ar.org.pachisoft.matelang.parser.expressions.Expr;
import ar.org.pachisoft.matelang.parser.expressions.GroupingExpression;
import ar.org.pachisoft.matelang.parser.expressions.LiteralExpression;
import ar.org.pachisoft.matelang.parser.expressions.UnaryExpression;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Parser {
    private TokenList tokenList;

    private Expr unary() {
    }

    private Token consume(TokenType type, String message) {
        if (check(type)) return advance();

        throw error(peek(), message);
    }
}
