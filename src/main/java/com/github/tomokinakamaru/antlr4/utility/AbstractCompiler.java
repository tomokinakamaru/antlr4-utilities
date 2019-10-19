package com.github.tomokinakamaru.antlr4.utility;

import java.util.List;

public abstract class AbstractCompiler {

  protected abstract List<AbstractAnalyzer> analyzers();

  public final void compile(Context context) {
    for (AbstractAnalyzer analyzer : analyzers()) {
      analyzer.dispatch(context);
      analyzer.initialize();
      analyzer.analyze();
    }
  }
}
