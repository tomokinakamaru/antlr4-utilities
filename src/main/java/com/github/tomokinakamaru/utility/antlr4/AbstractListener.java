package com.github.tomokinakamaru.utility.antlr4;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

public abstract class AbstractListener extends AbstractAnalyzer implements ParseTreeListener {

  protected abstract ParserRuleContext getContext();

  @Override
  public final void analyze() {
    ParseTreeWalker.DEFAULT.walk(this, getContext());
  }

  @Override
  public void visitTerminal(TerminalNode node) {}

  @Override
  public void visitErrorNode(ErrorNode node) {}

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {}

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {}
}
