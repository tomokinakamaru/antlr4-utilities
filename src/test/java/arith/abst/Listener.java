package arith.abst;

import arith.antlr.ArithListener;
import arith.antlr.ArithParser;
import com.github.tomokinakamaru.antlr4utilities.AbstractListener;
import org.antlr.v4.runtime.ParserRuleContext;

public class Listener extends AbstractListener implements ArithListener {

  @Override
  protected Class<? extends ParserRuleContext> getContextClass() {
    return ArithParser.ProgContext.class;
  }

  @Override
  public void enterProg(ArithParser.ProgContext ctx) {}

  @Override
  public void exitProg(ArithParser.ProgContext ctx) {}

  @Override
  public void enterStmt(ArithParser.StmtContext ctx) {}

  @Override
  public void exitStmt(ArithParser.StmtContext ctx) {}

  @Override
  public void enterAStmt(ArithParser.AStmtContext ctx) {}

  @Override
  public void exitAStmt(ArithParser.AStmtContext ctx) {}

  @Override
  public void enterCStmt(ArithParser.CStmtContext ctx) {}

  @Override
  public void exitCStmt(ArithParser.CStmtContext ctx) {}

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