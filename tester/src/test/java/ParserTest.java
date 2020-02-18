import com.github.tomokinakamaru.utility.antlr4.AbstractParserTest;
import java.util.function.Function;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.Test;

public class ParserTest extends AbstractParserTest<MathParser> {

  @Test
  void test1() {
    assertThat(MathParser::prog)
        .parses("0")
        .parses("1", "(prog (expr 1) <EOF>)")
        .parses("10", "(prog (expr 10) <EOF>)");
  }

  @Test
  void test2() {
    assertThat(MathParser::prog).failsToParse("+ 1").failsToParse("- 1");
  }

  @Test
  void test3() {
    assertThat(MathParser::expr).parses("0", "(expr 0)");
  }

  @Override
  protected Function<CharStream, ? extends Lexer> newLexer() {
    return MathLexer::new;
  }

  @Override
  protected Function<TokenStream, MathParser> newParser() {
    return MathParser::new;
  }
}
