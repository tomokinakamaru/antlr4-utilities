package com.github.tomokinakamaru.antlr4.utility;

import org.antlr.v4.runtime.ParserRuleContext;

public final class NodeFinder {

  private NodeFinder() {}

  public static <T extends ParserRuleContext> T findParent(Class<T> clazz, ParserRuleContext ctx) {
    ParserRuleContext c = ctx;
    while (true) {
      if (clazz.isInstance(c)) {
        return (T) c;
      }
      if (c.getParent() == null) {
        return null;
      }
      c = c.getParent();
    }
  }

  public static <T extends ParserRuleContext> T findChild(Class<T> clazz, ParserRuleContext ctx) {
    return ctx.getChild(clazz, 0);
  }
}
