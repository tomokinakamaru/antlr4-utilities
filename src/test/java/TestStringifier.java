import arith.Arith;
import arith.antlr.ArithParser;
import com.github.tomokinakamaru.utility.antlr4.Stringifier;
import org.junit.jupiter.api.Test;

final class TestStringifier {

  @Test
  void test1() {
    assert test("3  + 1    + 2   ;").equals("3 + 1 + 2 ; <EOF>");
  }

  @Test
  void test2() {
    assert test("x   = 1; x +   1   ;").equals("x = 1 ; x + 1 ; <EOF>");
  }

  private String test(String s) {
    ArithParser.ProgContext c = new Arith().run(s).get(ArithParser.ProgContext.class);
    return Stringifier.stringify(c);
  }
}
