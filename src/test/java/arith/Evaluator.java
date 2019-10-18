package arith;

import arith.abst.Visitor;
import arith.antlr.ArithParser;
import org.antlr.v4.runtime.ParserRuleContext;

final class Evaluator extends Visitor<Integer> {

  @Override
  protected Class<? extends ParserRuleContext> getContextClass() {
    return ArithParser.ProgContext.class;
  }

  @Override
  public Integer visitProg(ArithParser.ProgContext ctx) {
    set(new VarTable());

    Integer result = null;
    for (ArithParser.StmtContext c : ctx.stmt()) {
      result = visit(c);
    }
    return result;
  }

  @Override
  public Integer visitAStmt(ArithParser.AStmtContext ctx) {
    get(VarTable.class).set(ctx.NAME().getText(), visit(ctx.expr()));
    return null;
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
      return get(VarTable.class).get(ctx.NAME().getText());
    }
    if (ctx.NUM() != null) {
      return Integer.valueOf(ctx.NUM().getText());
    }
    return visit(ctx.expr());
  }
}
