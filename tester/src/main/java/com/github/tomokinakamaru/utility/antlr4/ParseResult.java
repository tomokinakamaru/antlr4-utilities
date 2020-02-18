package com.github.tomokinakamaru.utility.antlr4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

final class ParseResult {

  private final String text;

  private final Parser parser;

  private final ParserRuleContext context;

  ParseResult(String text, Parser parser, ParserRuleContext context) {
    this.text = text;
    this.parser = parser;
    this.context = context;
  }

  ParseResult(String text, Parser parser) {
    this(text, parser, null);
  }

  void assertSuccess() {
    if (!hasParsedAllChars()) {
      throw new AssertionError("Failed to parse text: " + text);
    }
  }

  void assertFailure() {
    if (hasParsedAllChars()) {
      throw new AssertionError("Successfully parsed text: " + text);
    }
  }

  void assertTreeEquals(String tree) {
    String t = context.toStringTree(parser);
    if (!t.equals(tree)) {
      throw new AssertionError("Unexpected tree: " + t);
    }
  }

  private boolean hasParsedAllChars() {
    if (context == null) {
      return false;
    }
    CharStream stream = context.start.getInputStream();
    int start = context.start.getStartIndex();
    int stop = context.stop.getStopIndex();
    return stream.getText(new Interval(start, stop)).length() == text.length();
  }
}
