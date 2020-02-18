package com.github.tomokinakamaru.utility.antlr4;

public abstract class AbstractAnalyzer {

  private Context context;

  protected abstract void analyze();

  protected void beforeAnalyze() {}

  protected void afterAnalyze() {}

  public final <T extends AbstractAnalyzer> T run(T analyzer) {
    analyzer.analyze(context);
    return analyzer;
  }

  protected final <T> boolean has(Class<T> clazz) {
    return context.has(clazz);
  }

  protected final <T> T get(Class<T> clazz) {
    return context.get(clazz);
  }

  protected final <T> T set(T object) {
    return context.set(object);
  }

  protected final <T> void remove(Class<T> clazz) {
    context.remove(clazz);
  }

  final void analyze(Context context) {
    this.context = context;
    beforeAnalyze();
    analyze();
    afterAnalyze();
  }
}
