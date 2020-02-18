package com.github.tomokinakamaru.utility.antlr4;

import java.util.function.Function;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;

final class Parse<P extends Parser> implements Function<String, ParseResult> {

  private final AbstractParserTest<P> test;

  private final Function<P, ? extends ParserRuleContext> getContext;

  Parse(AbstractParserTest<P> test, Function<P, ? extends ParserRuleContext> getContext) {
    this.test = test;
    this.getContext = getContext;
  }

  @Override
  public ParseResult apply(String text) {
    CharStream charStream = CharStreams.fromString(text);
    Lexer lexer = test.newLexer().apply(charStream);
    lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
    lexer.addErrorListener(ErrorListener.INSTANCE);

    P parser = test.newParser().apply(new CommonTokenStream(lexer));
    parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
    parser.addErrorListener(ErrorListener.INSTANCE);

    try {
      return new ParseResult(text, parser, getContext.apply(parser));
    } catch (ParseError e) {
      return new ParseResult(text, parser);
    }
  }
}
