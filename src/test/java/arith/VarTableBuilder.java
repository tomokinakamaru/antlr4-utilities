package arith;

import arith.abst.Listener;
import arith.antlr.ArithParser;

final class VarTableBuilder extends Listener {

  @Override
  public void enterProg(ArithParser.ProgContext ctx) {
    set(new VarTable());
  }

  @Override
  public void enterVar(ArithParser.VarContext ctx) {
    get(VarTable.class).set(ctx.NAME().getText(), Integer.valueOf(ctx.NUM().getText()));
  }
}
