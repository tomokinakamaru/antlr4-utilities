import arith.Arith;
import org.junit.jupiter.api.Test;

final class TestArith {

  @Test
  void test1() {
    assert new Arith().run("eval 1 + 1;") == 2;
  }

  @Test
  void test2() {
    assert new Arith().run("var x = 1; eval x + 1;") == 2;
  }

  @Test
  void test3() {
    assert new Arith().run("var x = 2; var y = 3; eval 1 + x * y;") == 7;
  }
}
