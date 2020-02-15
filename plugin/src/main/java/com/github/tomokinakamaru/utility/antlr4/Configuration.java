package com.github.tomokinakamaru.utility.antlr4;

import groovy.lang.Closure;

public class Configuration {

  private Closure<?> filter;

  public Closure<?> getFilter() {
    return this.filter;
  }

  public void setFilter(Closure<?> filter) {
    this.filter = filter;
  }
}
