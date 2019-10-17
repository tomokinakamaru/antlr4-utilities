package com.github.tomokinakamaru.antlr4utilities;

import java.util.BitSet;
import java.util.function.Function;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public abstract class AbstractParser<P extends Parser> extends AbstractAnalyzer
    implements ANTLRErrorListener {

  protected abstract Function<CharStream, ? extends Lexer> newLexer();

  protected abstract Function<TokenStream, P> newParser();

  protected abstract Function<P, ? extends ParserRuleContext> getRootContext();

  @Override
  public void syntaxError(
      Recognizer<?, ?> recognizer,
      Object offendingSymbol,
      int line,
      int charPositionInLine,
      String msg,
      RecognitionException e) {
    throw new RuntimeException();
  }

  @Override
  public void reportAmbiguity(
      Parser recognizer,
      DFA dfa,
      int startIndex,
      int stopIndex,
      boolean exact,
      BitSet ambigAlts,
      ATNConfigSet configs) {
    throw new RuntimeException();
  }

  @Override
  public void reportAttemptingFullContext(
      Parser recognizer,
      DFA dfa,
      int startIndex,
      int stopIndex,
      BitSet conflictingAlts,
      ATNConfigSet configs) {
    throw new RuntimeException();
  }

  @Override
  public void reportContextSensitivity(
      Parser recognizer,
      DFA dfa,
      int startIndex,
      int stopIndex,
      int prediction,
      ATNConfigSet configs) {
    throw new RuntimeException();
  }

  @Override
  public final void analyze() {
    Lexer lexer = newLexer().apply(get(CharStream.class));
    lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
    lexer.addErrorListener(this);

    P parser = newParser().apply(new CommonTokenStream(lexer));
    parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
    parser.addErrorListener(this);

    set(getRootContext().apply(parser));
  }
}
