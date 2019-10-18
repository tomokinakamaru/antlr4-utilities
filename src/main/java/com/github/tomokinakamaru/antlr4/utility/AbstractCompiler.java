package com.github.tomokinakamaru.antlr4.utility;

import java.util.List;

public abstract class AbstractCompiler {

  protected abstract List<AbstractAnalyzer> analyses();

  public final void compile(Context context) {
    for (AbstractAnalyzer analyzer : analyses()) {
      analyzer.dispatch(context);
      analyzer.init();
      analyzer.analyze();
    }
  }
}
