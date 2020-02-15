package com.github.tomokinakamaru.utility.antlr4;

import java.util.List;

public abstract class AbstractCompiler {

  protected abstract List<AbstractAnalyzer> getAnalyzers();

  public final void compile(Context context) {
    for (AbstractAnalyzer analyzer : getAnalyzers()) {
      analyzer.analyze(context);
    }
  }
}
