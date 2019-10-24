package arith.abst;

import com.github.tomokinakamaru.utility.antlr4.AbstractVisitor;

public abstract class Visitor<T> extends AbstractVisitor<T> implements DefaultVisitor<T> {}
