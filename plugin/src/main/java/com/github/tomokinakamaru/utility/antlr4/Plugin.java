package com.github.tomokinakamaru.utility.antlr4;

import com.github.tomokinakamaru.manifestattributes.ManifestAttributes;
import javax.inject.Inject;
import org.gradle.api.Project;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.plugins.antlr.AntlrPlugin;
import org.gradle.api.plugins.antlr.AntlrTask;
import org.gradle.api.tasks.TaskCollection;

public final class Plugin extends AntlrPlugin {

  private static final ManifestAttributes attributes = new ManifestAttributes(Plugin.class);

  private Project project;

  @Inject
  public Plugin(ObjectFactory objectFactory) {
    super(objectFactory);
  }

  @Override
  public void apply(Project project) {
    super.apply(project);
    this.project = project;
    this.project.getExtensions().add("antlr", new Configuration());
    configureDependency();
    configureTasks();
  }

  private void configureDependency() {
    String dependency = "org.antlr:antlr4:" + attributes.get("Antlr-Version");
    project.getDependencies().add("antlr", dependency);
  }

  private void configureTasks() {
    TaskCollection<AntlrTask> tasks = project.getTasks().withType(AntlrTask.class);
    new Configurator(project, tasks.getByName("generateGrammarSource"), "main").configure();
    new Configurator(project, tasks.getByName("generateTestGrammarSource"), "test").configure();
  }
}
