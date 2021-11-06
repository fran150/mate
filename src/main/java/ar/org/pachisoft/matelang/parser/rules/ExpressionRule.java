package ar.org.pachisoft.matelang.parser.rules;

import ar.org.pachisoft.matelang.parser.TokenList;
import ar.org.pachisoft.matelang.parser.expressions.Expr;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExpressionRule implements ParsingRule {
    private final GenericBinaryExpressionRule equalityRule;

    @Override
    public Expr parse(TokenList tokenList) {
        return equalityRule.parse(tokenList);
    }
}