package ar.org.pachisoft.matelang.parser.rules;

import ar.org.pachisoft.matelang.parser.TokenList;
import ar.org.pachisoft.matelang.parser.expressions.BinaryExpression;
import ar.org.pachisoft.matelang.parser.expressions.Expr;
import ar.org.pachisoft.matelang.scanner.Token;
import ar.org.pachisoft.matelang.scanner.TokenType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenericBinaryExpressionRule implements ParsingRule {
    private final ParsingRule nextRule;
    private final TokenType[] operators;

    @Override
    public Expr parse(TokenList tokenList) {
        Expr expr = nextRule.parse(tokenList);

        while (tokenList.match(operators)) {
            Token operator = tokenList.peek();
            tokenList.advance();
            Expr right = nextRule.parse(tokenList);
            expr = BinaryExpression.builder()
                    .left(expr)
                    .operator(operator)
                    .right(right)
                    .build();
        }

        return expr;
    }
}