package arith.analysis;

import arith.abst.Visitor;
import arith.antlr.ArithParser;
import arith.data.VarTable;
import org.antlr.v4.runtime.ParserRuleContext;

public final class Evaluator extends Visitor<Integer> {

  private VarTable varTable;

  @Override
  protected ParserRuleContext getContext() {
    return get(ArithParser.ProgContext.class);
  }

  @Override
  protected void analyze(Integer result) {
    set(result);
  }

  @Override
  public Integer visitProg(ArithParser.ProgContext ctx) {
    varTable = set(new VarTable());

    Integer result = null;
    for (ArithParser.StmtContext c : ctx.stmts().stmt()) {
      result = visit(c);
    }

    return result;
  }

  @Override
  public Integer visitAStmt(ArithParser.AStmtContext ctx) {
    String lhs = ctx.NAME().getText();
    Integer rhs = visit(ctx.expr());
    varTable.set(lhs, rhs);
    return rhs;
  }

  @Override
  public Integer visitCStmt(ArithParser.CStmtContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public Integer visitExpr(ArithParser.ExprContext ctx) {
    if (ctx.ADD() != null) {
      return visit(ctx.term()) + visit(ctx.expr());
    }
    if (ctx.SUB() != null) {
      return visit(ctx.term()) - visit(ctx.expr());
    }
    return visit(ctx.term());
  }

  @Override
  public Integer visitTerm(ArithParser.TermContext ctx) {
    if (ctx.MUL() != null) {
      return visit(ctx.fact()) * visit(ctx.term());
    }
    if (ctx.DIV() != null) {
      return visit(ctx.fact()) / visit(ctx.term());
    }
    return visit(ctx.fact());
  }

  @Override
  public Integer visitFact(ArithParser.FactContext ctx) {
    if (ctx.SUB() == null) {
      return visit(ctx.elem());
    }
    return -visit(ctx.elem());
  }

  @Override
  public Integer visitElem(ArithParser.ElemContext ctx) {
    if (ctx.NAME() != null) {
      return varTable.get(ctx.NAME().getText());
    }
    if (ctx.NUM() != null) {
      return Integer.valueOf(ctx.NUM().getText());
    }
    if (ctx.expr() != null) {
      return visit(ctx.expr());
    }

    varTable = varTable.createChildScope();
    Integer result = visit(ctx.stmts());
    varTable = varTable.getParent();
    return result;
  }
}
