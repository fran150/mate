package ar.org.pachisoft.matelang.parser.expressions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupingExpression implements Expr {
    private final Expr expression;

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
