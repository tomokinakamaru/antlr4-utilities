import arith.Arith;
import org.junit.jupiter.api.Test;

final class TestArith {

  @Test
  void test1() {
    assert new Arith().run("1 + 1;") == 2;
  }

  @Test
  void test2() {
    assert new Arith().run("x = 1; x + 1;") == 2;
  }

  @Test
  void test3() {
    assert new Arith().run("x = 2; y = 3; 1 + x * y;") == 7;
  }
}
