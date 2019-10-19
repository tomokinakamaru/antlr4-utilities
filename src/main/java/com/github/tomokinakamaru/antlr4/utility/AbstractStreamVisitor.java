package com.github.tomokinakamaru.antlr4.utility;

import java.util.stream.Stream;

public abstract class AbstractStreamVisitor<T> extends AbstractVisitor<Stream<T>> {

  @Override
  public Stream<T> defaultResult() {
    return Stream.empty();
  }

  @Override
  public Stream<T> aggregateResult(Stream<T> aggregate, Stream<T> nextResult) {
    return Stream.concat(aggregate, nextResult);
  }
}
