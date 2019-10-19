import arith.Arith;
import com.github.tomokinakamaru.antlr4.utility.Context;
import org.junit.jupiter.api.Test;

final class TestArith {

  @Test
  void test1() {
    assert test("1 + 1;") == 2;
  }

  @Test
  void test2() {
    assert test("x = 1; x + 1;") == 2;
  }

  @Test
  void test3() {
    assert test("x = 2; y = 3; 1 + x * y;") == 7;
  }

  @Test
  void test4() {
    assert test("x = { y = 1; y + 1; } * 2; x * 3;") == 12;
  }

  private int test(String s) {
    Context context = new Arith().run(s);
    Integer result = context.get(Integer.class);
    if (result == null) {
      throw new RuntimeException();
    }
    return result;
  }
}
