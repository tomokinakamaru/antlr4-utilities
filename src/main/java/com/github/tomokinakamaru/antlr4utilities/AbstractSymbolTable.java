package com.github.tomokinakamaru.antlr4utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractSymbolTable<T extends AbstractSymbolTable<T, E>, E> {

  protected abstract T newSymbolTable();

  AbstractSymbolTable<T, E> parent = null;

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

  public final T newChildScope() {
    T table = newSymbolTable();
    table.parent = this;
    return table;
  }

  protected E resolveConflict(String key, E oldEntity, E newEntity) {
    throw new RuntimeException();
  }

  protected E defaultEntity(String key) {
    throw new RuntimeException();
  }
}
