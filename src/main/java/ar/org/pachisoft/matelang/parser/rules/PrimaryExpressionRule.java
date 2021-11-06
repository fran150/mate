package ar.org.pachisoft.matelang.parser.rules;

import ar.org.pachisoft.matelang.error.ErrorHandler;
import ar.org.pachisoft.matelang.exceptions.ParseException;
import ar.org.pachisoft.matelang.parser.TokenList;
import ar.org.pachisoft.matelang.parser.expressions.Expr;
import ar.org.pachisoft.matelang.parser.expressions.GroupingExpression;
import ar.org.pachisoft.matelang.parser.expressions.LiteralExpression;
import ar.org.pachisoft.matelang.scanner.TokenType;
import ar.org.pachisoft.matelang.utils.ReservedWord;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrimaryExpressionRule implements ParsingRule {
    private final ParsingRule generalRule;
    private final ErrorHandler errorHandler;

    @Override
    public Expr parse(TokenList tokenList) {
        if (tokenList.match(TokenType.RESERVED_WORD)) {
            ReservedWord reservedWord = tokenList.peek().getReservedWord();

            if (reservedWord.equals(ReservedWord.TRUE)) {
                return new LiteralExpression(true);
            }

            if (reservedWord.equals(ReservedWord.FALSE)) {
                return new LiteralExpression(false);
            }
        }

        if (tokenList.match(TokenType.DECIMAL, TokenType.STRING)) {
            return new LiteralExpression(tokenList.peek().getLiteral());
        }

        if (tokenList.match(TokenType.LEFT_PAREN)) {
            tokenList.advance();
            Expr expr = generalRule.parse(tokenList);

            if (tokenList.match(TokenType.RIGHT_PAREN)) {
                tokenList.advance();
            } else {
                String message = "Expect ')' after expression.";
                errorHandler.parserError(tokenList.peek(), message);
                throw new ParseException(message);
            }

            return new GroupingExpression(expr);
        }

        String message = "Expected expression";
        errorHandler.parserError(tokenList.peek(), message);
        throw new ParseException(message);
    }
}
