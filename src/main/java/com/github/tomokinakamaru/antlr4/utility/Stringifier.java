package com.github.tomokinakamaru.antlr4.utility;

import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public final class Stringifier {

  private Stringifier() {}

  public static String stringify(ParserRuleContext ctx) {
    return ctx.children.stream().map(Stringifier::stringify).collect(Collectors.joining(" "));
  }

  private static String stringify(ParseTree tree) {
    if (tree instanceof RuleNode) {
      return stringify((RuleNode) tree);
    }
    if (tree instanceof TerminalNode) {
      return stringify((TerminalNode) tree);
    }
    throw new RuntimeException();
  }

  private static String stringify(RuleNode node) {
    if (node.getChildCount() == 0) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    builder.append(stringify(node.getChild(0)));
    for (int i = 1; i < node.getChildCount(); i++) {
      builder.append(" ").append(stringify(node.getChild(i)));
    }
    return builder.toString();
  }

  private static String stringify(TerminalNode node) {
    return node.getText();
  }
}
