package arith.abst;

import arith.antlr.ArithParser;
import arith.antlr.ArithVisitor;
import com.github.tomokinakamaru.antlr4utilities.AbstractVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class Visitor<T> extends AbstractVisitor<T> implements ArithVisitor<T> {

  @Override
  protected Class<? extends ParserRuleContext> getContextClass() {
    return ArithParser.ProgContext.class;
  }

  @Override
  public T visitProg(ArithParser.ProgContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public T visitStmt(ArithParser.StmtContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public T visitAStmt(ArithParser.AStmtContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public T visitCStmt(ArithParser.CStmtContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public T visitExpr(ArithParser.ExprContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public T visitTerm(ArithParser.TermContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public T visitFact(ArithParser.FactContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public T visitElem(ArithParser.ElemContext ctx) {
    return visitChildren(ctx);
  }
}
