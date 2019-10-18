package com.github.tomokinakamaru.antlr4.utility;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractSymbolTable<T extends AbstractSymbolTable<T, E>, E> {

  protected abstract Supplier<T> newSymbolTable();

  T parent = null;

  private final Map<String, E> map = new LinkedHashMap<>();

  public final boolean has(String key) {
    if (map.containsKey(key)) {
      return true;
    }
    if (parent == null) {
      return false;
    }
    return parent.has(key);
  }

  public final E get(String key) {
    if (map.containsKey(key)) {
      return map.get(key);
    }
    if (parent == null) {
      return defaultEntity(key);
    }
    return parent.get(key);
  }

  public final void set(String key, E entity) {
    if (map.containsKey(key)) {
      map.put(key, resolveConflict(key, map.get(key), entity));
    } else {
      map.put(key, entity);
    }
  }

  @SuppressWarnings("unchecked")
  public final T createChildScope() {
    T table = newSymbolTable().get();
    table.parent = (T) this;
    return table;
  }

  public final T getParent() {
    return parent;
  }

  protected E resolveConflict(String key, E oldEntity, E newEntity) {
    throw new RuntimeException();
  }

  protected E defaultEntity(String key) {
    throw new RuntimeException();
  }
}
