package com.github.tomokinakamaru.antlr4utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Context {

  private final Map<Class<?>, Object> map = new LinkedHashMap<>();

  @SuppressWarnings("unchecked")
  public <T> T get(Class<T> clazz) {
    if (map.containsKey(clazz)) {
      return (T) map.get(clazz);
    }
    for (Class<?> key : map.keySet()) {
      if (clazz.isAssignableFrom(key)) {
        return (T) map.get(key);
      }
    }
    return null;
  }

  public void set(Object object) {
    map.put(object.getClass(), object);
  }

  public <T> void remove(Class<T> clazz) {
    map.remove(clazz);
    for (Class<?> key : map.keySet()) {
      if (clazz.isAssignableFrom(key)) {
        map.remove(key);
      }
    }
  }
}