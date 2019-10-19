package com.github.tomokinakamaru.antlr4.utility;

public abstract class AbstractAnalyzer {

  private Context context;

  public abstract void analyze();

  public void initialize() {}

  public final <T extends AbstractAnalyzer> T run(T analyzer) {
    analyzer.analyze(context);
    return analyzer;
  }

  public final void dispatch(Context context) {
    this.context = context;
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
    dispatch(context);
    initialize();
    analyze();
  }
}
