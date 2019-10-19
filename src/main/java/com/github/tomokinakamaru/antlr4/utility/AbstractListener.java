package com.github.tomokinakamaru.antlr4.utility;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public abstract class AbstractListener extends AbstractAnalyzer
    implements DefaultParseTreeListener {

  protected abstract ParserRuleContext getContext();

  @Override
  public final void analyze() {
    ParseTreeWalker.DEFAULT.walk(this, getContext());
  }
}
