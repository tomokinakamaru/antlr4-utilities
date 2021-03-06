package com.github.tomokinakamaru.utility.antlr4;

import static java.lang.String.format;

import groovy.lang.Closure;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.antlr.AntlrTask;

final class Configurator {

  private final Project project;

  private final AntlrTask task;

  private final String sourceSet;

  private static final String PKG = "com.github.tomokinakamaru.utility.antlr4";

  Configurator(Project project, AntlrTask task, String sourceSet) {
    this.project = project;
    this.task = task;
    this.sourceSet = sourceSet;
  }

  void configure() {
    for (Path file : getGrammarFiles()) {
      String name = file.getFileName().toString().split("\\.")[0];
      task.setProperty("arguments", getArguments(file));
      task.doLast(getFilterAction());
      task.doLast(getGenerateListenerAction(name));
      task.doLast(getGenerateVisitorAction(name));
      task.doLast(getGenerateStreamVisitorAction(name));
    }
  }

  private Action<Task> getGenerateListenerAction(String name) {
    return task -> {
      Path src = findOutputFile(name + "BaseListener.java");
      Path dst = src.getParent().resolve("Listener.java");
      Function<String, String> function = createGenerateListenerFilter(name);
      copy(src, dst, function);
    };
  }

  private static Function<String, String> createGenerateListenerFilter(String name) {
    String s = format("class %sBaseListener", name);
    String t = format("abstract class Listener extends %s.AbstractListener", PKG);
    return content -> content.replace(s, t);
  }

  private Action<Task> getGenerateVisitorAction(String name) {
    return task -> {
      Path src = findOutputFile(name + "BaseVisitor.java");
      Path dst = src.getParent().resolve("Visitor.java");
      Function<String, String> function = createGenerateVisitorFilter(name);
      copy(src, dst, function);
    };
  }

  private static Function<String, String> createGenerateVisitorFilter(String name) {
    String s = format("class %sBaseVisitor<T> extends AbstractParseTreeVisitor<T>", name);
    String t = format("abstract class Visitor<T> extends %s.AbstractVisitor<T>", PKG);
    return content -> content.replace(s, t);
  }

  private Action<Task> getGenerateStreamVisitorAction(String name) {
    return task -> {
      Path src = findOutputFile(name + "BaseVisitor.java");
      Path dst = src.getParent().resolve("StreamVisitor.java");
      Function<String, String> function = createGenerateStreamVisitorFilter(name);
      copy(src, dst, function);
    };
  }

  private static Function<String, String> createGenerateStreamVisitorFilter(String name) {
    String s1 = format("class %sBaseVisitor<T> extends AbstractParseTreeVisitor<T>", name);
    String t1 = format("abstract class StreamVisitor<T> extends %s.AbstractStreamVisitor<T>", PKG);
    String s2 = format("implements %sVisitor<T>", name);
    String t2 = format("implements %sVisitor<java.util.stream.Stream<T>>", name);
    String s3 = "public T ";
    String t3 = "public java.util.stream.Stream<T> ";
    return content -> content.replace(s1, t1).replace(s2, t2).replaceAll(s3, t3);
  }

  private void copy(Path src, Path dst, Function<String, String> function) {
    try {
      String content = new String(Files.readAllBytes(src));
      Files.write(dst, function.apply(content).getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Path findOutputFile(String suffix) {
    try {
      return Files.walk(task.getOutputDirectory().toPath())
          .filter(p -> p.toString().endsWith(suffix))
          .findAny()
          .orElse(null);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Action<Task> getFilterAction() {
    return t -> {
      Closure<?> filter = getConfiguration().filter;
      if (filter == null) {
        return;
      }

      String outDir = task.getOutputDirectory().toString();
      String tmpOutDir = outDir + ".tmp";

      project.copy(
          copySpec -> {
            copySpec.from(outDir);
            copySpec.into(tmpOutDir);
            copySpec.filter(filter);
          });

      project.copy(
          copySpec -> {
            copySpec.from(tmpOutDir);
            copySpec.into(outDir);
          });

      project.delete(deleteSpec -> deleteSpec.delete(tmpOutDir));
    };
  }

  private Configuration getConfiguration() {
    return project.getExtensions().getByType(Configuration.class);
  }

  private List<String> getArguments(Path file) {
    List<String> args = new ArrayList<>();
    args.add("-visitor");
    args.add("-listener");

    String pkg = getPackage(file);
    if (!pkg.isEmpty()) {
      args.add("-package");
      args.add(pkg);
    }

    return args;
  }

  private String getPackage(Path file) {
    Path pkgDir = getSourceSet().relativize(file.getParent());
    return pkgDir.toString().replace(File.separator, ".");
  }

  private Path getSourceSet() {
    return project.getProjectDir().toPath().resolve("src").resolve(sourceSet).resolve("antlr");
  }

  private Set<Path> getGrammarFiles() {
    return task.getSource().getFiles().stream().map(File::toPath).collect(Collectors.toSet());
  }
}
