package com.github.tomokinakamaru.utility.antlr4;

import org.antlr.v4.runtime.Parser;

public final class Tester<P extends Parser> {

  private final Parse<P> parse;

  Tester(Parse<P> parse) {
    this.parse = parse;
  }

  public Tester<P> parses(String text) {
    ParseResult result = parse.apply(text);
    result.assertSuccess();
    return this;
  }

  public Tester<P> parses(String text, String tree) {
    ParseResult result = parse.apply(text);
    result.assertSuccess();
    result.assertTreeEquals(tree);
    return this;
  }

  public Tester<P> failsToParse(String text) {
    ParseResult result = parse.apply(text);
    result.assertFailure();
    return this;
  }
}
