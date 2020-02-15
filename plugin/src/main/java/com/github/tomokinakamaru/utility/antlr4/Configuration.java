package com.github.tomokinakamaru.utility.antlr4;

import groovy.lang.Closure;

public class Configuration {

  Closure<?> filter;

  public void filter(Closure<?> filter) {
    this.filter = filter;
  }
}
