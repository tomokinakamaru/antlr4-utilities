package arith;

import com.github.tomokinakamaru.antlr4utilities.AbstractSymbolTable;

final class VarTable extends AbstractSymbolTable<VarTable, Integer> {

  @Override
  protected VarTable newSymbolTable() {
    return new VarTable();
  }

  @Override
  protected Integer resolveConflict(String key, Integer oldEntity, Integer newEntity) {
    throw new RuntimeException("Duplicate variable: " + key);
  }

  @Override
  protected Integer defaultEntity(String key) {
    throw new RuntimeException("Undefined variable: " + key);
  }
}
