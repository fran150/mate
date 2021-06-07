package ar.org.pachisoft.matelang.parser.expressions;

public interface Expr {
    <R> R accept(ExprVisitor<R> visitor);
}
