package arith.abst;

import arith.antlr.ArithParser;
import arith.antlr.ArithVisitor;

public interface DefaultVisitor<T> extends ArithVisitor<T> {

  default T visitProg(ArithParser.ProgContext ctx) {
    return visitChildren(ctx);
  }

  default T visitStmts(ArithParser.StmtsContext ctx) {
    return visitChildren(ctx);
  }

  default T visitStmt(ArithParser.StmtContext ctx) {
    return visitChildren(ctx);
  }

  default T visitAStmt(ArithParser.AStmtContext ctx) {
    return visitChildren(ctx);
  }

  default T visitCStmt(ArithParser.CStmtContext ctx) {
    return visitChildren(ctx);
  }

  default T visitExpr(ArithParser.ExprContext ctx) {
    return visitChildren(ctx);
  }

  default T visitTerm(ArithParser.TermContext ctx) {
    return visitChildren(ctx);
  }

  default T visitFact(ArithParser.FactContext ctx) {
    return visitChildren(ctx);
  }

  default T visitElem(ArithParser.ElemContext ctx) {
    return visitChildren(ctx);
  }
}
