package arith;

import arith.analysis.Evaluator;
import arith.analysis.Parser;
import com.github.tomokinakamaru.antlr4.utility.AbstractAnalyzer;
import com.github.tomokinakamaru.antlr4.utility.AbstractCompiler;
import com.github.tomokinakamaru.antlr4.utility.Context;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;

public final class Arith extends AbstractCompiler {

  public Context run(String s) {
    Context context = new Context();
    context.set(CharStreams.fromString(s));
    compile(context);
    return context;
  }

  @Override
  protected List<AbstractAnalyzer> analyzers() {
    return Arrays.asList(new Parser(), new Evaluator());
  }
}
