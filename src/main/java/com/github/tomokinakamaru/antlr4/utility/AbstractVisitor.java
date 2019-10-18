package com.github.tomokinakamaru.antlr4.utility;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public abstract class AbstractVisitor<T> extends AbstractAnalyzer implements ParseTreeVisitor<T> {

  protected abstract Class<? extends ParserRuleContext> getContextClass();

  @Override
  public final void analyze() {
    set(visit(get(getContextClass())));
  }

  @Override
  public T visit(ParseTree tree) {
    return tree.accept(this);
  }

  @Override
  public T visitChildren(RuleNode node) {
    T result = defaultResult();
    int n = node.getChildCount();
    for (int i = 0; i < n; i++) {
      if (!shouldVisitNextChild(node, result)) {
        break;
      }

      ParseTree c = node.getChild(i);
      T childResult = c.accept(this);
      result = aggregateResult(result, childResult);
    }

    return result;
  }

  @Override
  public T visitTerminal(TerminalNode node) {
    return defaultResult();
  }

  @Override
  public T visitErrorNode(ErrorNode node) {
    return defaultResult();
  }

  protected T defaultResult() {
    return null;
  }

  protected T aggregateResult(T aggregate, T nextResult) {
    return nextResult;
  }

  protected boolean shouldVisitNextChild(RuleNode node, T currentResult) {
    return true;
  }
}
