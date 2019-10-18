package arith;

import arith.analysis.Evaluator;
import arith.analysis.Parser;
import com.github.tomokinakamaru.antlr4utilities.AbstractAnalyzer;
import com.github.tomokinakamaru.antlr4utilities.AbstractCompiler;
import com.github.tomokinakamaru.antlr4utilities.Context;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;

public final class Arith extends AbstractCompiler {

  public int run(String s) {
    Context context = new Context();
    context.set(CharStreams.fromString(s));
    compile(context);
    Integer result = context.get(Integer.class);
    return result == null ? Integer.MIN_VALUE : result;
  }

  @Override
  protected List<AbstractAnalyzer> analyses() {
    return Arrays.asList(new Parser(), new Evaluator());
  }
}
