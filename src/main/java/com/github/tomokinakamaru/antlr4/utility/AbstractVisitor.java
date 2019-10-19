package com.github.tomokinakamaru.antlr4.utility;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractVisitor<T> extends AbstractAnalyzer
    implements DefaultParseTreeVisitor<T> {

  protected abstract ParserRuleContext getContext();

  protected void analyze(T result) {}

  @Override
  public final void analyze() {
    analyze(visit(getContext()));
  }
}
