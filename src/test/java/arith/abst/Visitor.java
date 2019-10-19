package arith.abst;

import com.github.tomokinakamaru.antlr4.utility.AbstractVisitor;

public abstract class Visitor<T> extends AbstractVisitor<T> implements DefaultVisitor<T> {}
