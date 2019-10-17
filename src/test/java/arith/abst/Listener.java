package arith.abst;

import arith.antlr.ArithListener;
import arith.antlr.ArithParser;
import com.github.tomokinakamaru.antlr4utilities.AbstractListener;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Listener extends AbstractListener implements ArithListener {

  @Override
  protected Class<? extends ParserRuleContext> getContextClass() {
    return ArithParser.ProgContext.class;
  }

  @Override
  public void enterProg(ArithParser.ProgContext ctx) {}

  @Override
  public void exitProg(ArithParser.ProgContext ctx) {}

  @Override
  public void enterVar(ArithParser.VarContext ctx) {}

  @Override
  public void exitVar(ArithParser.VarContext ctx) {}

  @Override
  public void enterEval(ArithParser.EvalContext ctx) {}

  @Override
  public void exitEval(ArithParser.EvalContext ctx) {}

  @Override
  public void enterExpr(ArithParser.ExprContext ctx) {}

  @Override
  public void exitExpr(ArithParser.ExprContext ctx) {}

  @Override
  public void enterTerm(ArithParser.TermContext ctx) {}

  @Override
  public void exitTerm(ArithParser.TermContext ctx) {}

  @Override
  public void enterFact(ArithParser.FactContext ctx) {}

  @Override
  public void exitFact(ArithParser.FactContext ctx) {}

  @Override
  public void enterElem(ArithParser.ElemContext ctx) {}

  @Override
  public void exitElem(ArithParser.ElemContext ctx) {}
}
