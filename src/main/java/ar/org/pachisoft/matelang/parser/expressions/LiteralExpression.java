package ar.org.pachisoft.matelang.parser.expressions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LiteralExpression implements Expr {
    private final Object value;

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
