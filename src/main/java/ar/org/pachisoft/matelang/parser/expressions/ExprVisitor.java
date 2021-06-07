package ar.org.pachisoft.matelang.parser.expressions;

public interface ExprVisitor<R> {
    R visit(BinaryExpression binary);
    R visit(GroupingExpression grouping);
    R visit(LiteralExpression literal);
    R visit(UnaryExpression unary);
}
