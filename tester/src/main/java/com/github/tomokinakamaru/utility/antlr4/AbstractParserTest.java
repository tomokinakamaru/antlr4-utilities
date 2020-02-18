package com.github.tomokinakamaru.utility.antlr4;

import java.util.function.Function;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;

public abstract class AbstractParserTest<P extends Parser> {

  protected abstract Function<CharStream, ? extends Lexer> newLexer();

  protected abstract Function<TokenStream, P> newParser();

  protected final Tester<P> assertThat(Function<P, ? extends ParserRuleContext> getContext) {
    Parse<P> parse = new Parse<>(this, getContext);
    return new Tester<>(parse);
  }
}
