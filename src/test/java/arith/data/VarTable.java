package arith.data;

import com.github.tomokinakamaru.utility.antlr4.AbstractSymbolTable;
import java.util.function.Supplier;

public final class VarTable extends AbstractSymbolTable<VarTable, Integer> {

  @Override
  protected Supplier<VarTable> newSymbolTable() {
    return VarTable::new;
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
