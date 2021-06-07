package ar.org.pachisoft.matelang.parser.expressions;

import ar.org.pachisoft.matelang.scanner.Token;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnaryExpression implements Expr {
    private final Token operator;
    private final Expr right;

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
