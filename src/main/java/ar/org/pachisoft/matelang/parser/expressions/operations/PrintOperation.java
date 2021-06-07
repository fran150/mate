package ar.org.pachisoft.matelang.parser.expressions.operations;

import ar.org.pachisoft.matelang.parser.expressions.BinaryExpression;
import ar.org.pachisoft.matelang.parser.expressions.Expr;
import ar.org.pachisoft.matelang.parser.expressions.ExprVisitor;
import ar.org.pachisoft.matelang.parser.expressions.GroupingExpression;
import ar.org.pachisoft.matelang.parser.expressions.LiteralExpression;
import ar.org.pachisoft.matelang.parser.expressions.UnaryExpression;

public class PrintOperation implements ExprVisitor<String> {
    @Override
    public String visit(BinaryExpression binary) {
        return parenthesize(binary.getOperator().getLexeme(),
                binary.getLeft(), binary.getRight());
    }

    @Override
    public String visit(GroupingExpression grouping) {
        return parenthesize("group", grouping.getExpression());
    }

    @Override
    public String visit(LiteralExpression literal) {
        if (literal.getValue() == null) return "null";
        return literal.getValue().toString();
    }

    @Override
    public String visit(UnaryExpression unary) {
        return parenthesize(unary.getOperator().getLexeme(), unary.getRight());
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
}