package arith.abst;

import arith.antlr.ArithParser;
import arith.antlr.ArithVisitor;
import com.github.tomokinakamaru.antlr4.utility.AbstractVisitor;

public abstract class Visitor<T> extends AbstractVisitor<T> implements ArithVisitor<T> {

  @Override
  public T visitProg(ArithParser.ProgContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public T visitStmts(ArithParser.StmtsContext ctx) {
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
