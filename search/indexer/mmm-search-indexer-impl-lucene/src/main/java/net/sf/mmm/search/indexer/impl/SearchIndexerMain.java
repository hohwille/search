/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.indexer.impl;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.mmm.search.NlsBundleSearchRoot;
import net.sf.mmm.search.indexer.base.AbstractSearchIndexerMain;
import net.sf.mmm.search.indexer.impl.spring.SearchIndexerSpringConfig;
import net.sf.mmm.util.cli.api.CliOption;
import net.sf.mmm.util.component.api.IocContainer;
import net.sf.mmm.util.component.impl.SpringContainer;
import net.sf.mmm.util.reflect.api.TypeNotFoundException;

/**
 * This is the main program that triggers the {@link net.sf.mmm.search.indexer.api.ConfiguredSearchIndexer
 * search-indexer} according to a given {@link net.sf.mmm.search.indexer.api.config.SearchIndexerConfiguration
 * configuration}. It uses springframework as {@link IocContainer}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class SearchIndexerMain extends AbstractSearchIndexerMain {

  /** The optional context-path for the spring-context. */
  @CliOption(name = "--spring-xml", required = false, operand = "FILE", usage = NlsBundleSearchRoot.MSG_MAIN_OPTION_USAGE_SPRING_XML)
  private String springConfig;

  /** The optional context-path for the spring-context. */
  @CliOption(name = "--spring-class", required = false, operand = "CLASSES", usage = NlsBundleSearchRoot.MSG_MAIN_OPTION_USAGE_SPRING_CLASSES)
  private String[] springClasses;

  /** The {@link IocContainer}. */
  private IocContainer container;

  /**
   * The constructor.
   */
  public SearchIndexerMain() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IocContainer getIocContainer() {

    if (this.container == null) {
      getLogger().info("Starting spring context...");
      ConfigurableApplicationContext springContext;
      if (this.springConfig != null) {
        springContext = new ClassPathXmlApplicationContext(this.springConfig);
      } else {
        @SuppressWarnings("resource")
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        Class<?>[] configClasses;
        if ((this.springClasses == null) || (this.springClasses.length == 0)) {
          configClasses = new Class<?>[] { SearchIndexerSpringConfig.class };
        } else {
          configClasses = new Class<?>[this.springClasses.length];
          for (int i = 0; i < this.springClasses.length; i++) {
            try {
              configClasses[i++] = Class.forName(this.springClasses[i]);
            } catch (ClassNotFoundException e) {
              throw new TypeNotFoundException(e, this.springClasses[i]);
            }
          }
        }
        context.register(configClasses);
        context.refresh();
        springContext = context;
      }
      this.container = new SpringContainer(springContext);
      getLogger().info("Spring context started...");
    }
    return this.container;
  }

  /**
   * This is the main-method of this program.
   *
   * @param args are the commandline-arguments.
   */
  public static void main(String[] args) {

    new SearchIndexerMain().runAndExit(args);
  }

}
