package com.github.tomokinakamaru.utility.antlr4;

import java.util.BitSet;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

final class ErrorListener implements ANTLRErrorListener {

  static final ErrorListener INSTANCE = new ErrorListener();

  @Override
  public void syntaxError(
      Recognizer<?, ?> recognizer,
      Object offendingSymbol,
      int line,
      int charPositionInLine,
      String msg,
      RecognitionException e) {
    throw new ParseError();
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
    throw new ParseError();
  }

  @Override
  public void reportAttemptingFullContext(
      Parser recognizer,
      DFA dfa,
      int startIndex,
      int stopIndex,
      BitSet conflictingAlts,
      ATNConfigSet configs) {
    throw new ParseError();
  }

  @Override
  public void reportContextSensitivity(
      Parser recognizer,
      DFA dfa,
      int startIndex,
      int stopIndex,
      int prediction,
      ATNConfigSet configs) {
    throw new ParseError();
  }
}
