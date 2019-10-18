package arith.analysis;

import arith.antlr.ArithLexer;
import arith.antlr.ArithParser;
import com.github.tomokinakamaru.antlr4.utility.AbstractParser;
import java.util.function.Function;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;

public final class Parser extends AbstractParser<ArithParser> {

  @Override
  protected Function<CharStream, ? extends Lexer> newLexer() {
    return ArithLexer::new;
  }

  @Override
  protected Function<TokenStream, ArithParser> newParser() {
    return ArithParser::new;
  }

  @Override
  protected Function<ArithParser, ? extends ParserRuleContext> getRootContext() {
    return ArithParser::prog;
  }

  @Override
  public void syntaxError(Recognizer r, Object o, int l, int c, String m, RecognitionException e) {
    throw new RuntimeException(String.format("%s (L%dC%d)", m, l, c));
  }
}
