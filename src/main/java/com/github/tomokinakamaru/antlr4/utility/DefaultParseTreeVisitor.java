package com.github.tomokinakamaru.antlr4.utility;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public interface DefaultParseTreeVisitor<T> extends ParseTreeVisitor<T> {

  default T visit(ParseTree tree) {
    return tree.accept(this);
  }

  default T visitChildren(RuleNode node) {
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

  default T visitTerminal(TerminalNode node) {
    return defaultResult();
  }

  default T visitErrorNode(ErrorNode node) {
    return defaultResult();
  }

  default T defaultResult() {
    return null;
  }

  default T aggregateResult(T aggregate, T nextResult) {
    return nextResult;
  }

  default boolean shouldVisitNextChild(RuleNode node, T currentResult) {
    return true;
  }
}
