package ar.org.pachisoft.matelang.parser.rules;

import ar.org.pachisoft.matelang.parser.TokenList;
import ar.org.pachisoft.matelang.parser.expressions.Expr;
import ar.org.pachisoft.matelang.parser.expressions.UnaryExpression;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenericUnaryExpressionRule implements ParsingRule{
    private final ParsingRule nextRule;
    private final TokenType[] operators;

    @Override
    public Expr parse(TokenList tokenList) {
        if (tokenList.match(operators)) {
            Token operator = tokenList.peek();
            tokenList.advance();
            Expr right = parse(tokenList);
            return UnaryExpression.builder()
                    .operator(operator)
                    .right(right)
                    .build();
        }

        return nextRule.parse(tokenList);
    }
}
