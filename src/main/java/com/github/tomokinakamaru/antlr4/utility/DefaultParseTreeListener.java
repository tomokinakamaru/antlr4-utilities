package com.github.tomokinakamaru.antlr4.utility;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

public interface DefaultParseTreeListener extends ParseTreeListener {

  default void visitTerminal(TerminalNode node) {}

  default void visitErrorNode(ErrorNode node) {}

  default void enterEveryRule(ParserRuleContext ctx) {}

  default void exitEveryRule(ParserRuleContext ctx) {}
}
