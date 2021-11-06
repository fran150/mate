package ar.org.pachisoft.matelang.parser.rules;

import ar.org.pachisoft.matelang.parser.TokenList;
import ar.org.pachisoft.matelang.parser.expressions.Expr;

public interface ParsingRule {
    Expr parse(TokenList tokenList);
}
