/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.engine.impl.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.sf.mmm.search.base.SearchDependencies;
import net.sf.mmm.search.base.SearchDependenciesImpl;
import net.sf.mmm.search.engine.api.SearchEngineBuilder;
import net.sf.mmm.search.engine.api.SearchQueryBuilderFactory;
import net.sf.mmm.search.engine.api.config.SearchEngineConfigurationLoader;
import net.sf.mmm.search.engine.base.config.SearchEngineConfigurationLoaderImpl;
import net.sf.mmm.search.engine.impl.lucene.HighlightFormatter;
import net.sf.mmm.search.engine.impl.lucene.LuceneFieldManagerFactory;
import net.sf.mmm.search.engine.impl.lucene.LuceneFieldManagerFactoryImpl;
import net.sf.mmm.search.engine.impl.lucene.LuceneSearchEngineBuilder;
import net.sf.mmm.search.engine.impl.lucene.LuceneSearchQueryBuilderFactory;
import net.sf.mmm.search.impl.lucene.LuceneAnalyzer;
import net.sf.mmm.search.impl.lucene.LuceneAnalyzerImpl;
import net.sf.mmm.search.impl.lucene.LuceneDirectoryBuilder;
import net.sf.mmm.search.impl.lucene.LuceneDirectoryBuilderImpl;
import net.sf.mmm.search.impl.lucene.LuceneVersion;
import net.sf.mmm.search.impl.lucene.LuceneVersionImpl;
import net.sf.mmm.util.component.impl.spring.UtilComponentSpringConfig;
import net.sf.mmm.util.exception.impl.spring.UtilExceptionSpringConfig;
import net.sf.mmm.util.resource.impl.spring.UtilResourceSpringConfig;
import net.sf.mmm.util.xml.impl.spring.UtilXmlSpringConfig;

/**
 * This is the Spring {@link Configuration} for {@link net.sf.mmm.util.io}.
 *
 * @author hohwille
 * @since 1.1.0
 */
@Configuration
@Import({ UtilExceptionSpringConfig.class, UtilResourceSpringConfig.class, UtilXmlSpringConfig.class,
    UtilComponentSpringConfig.class })
@SuppressWarnings("javadoc")
public class SearchEngineSpringConfig {

  @Bean
  public SearchEngineBuilder contentParserService() {

    return new LuceneSearchEngineBuilder();
  }

  @Bean
  public LuceneFieldManagerFactory luceneFieldManagerFactory() {

    return new LuceneFieldManagerFactoryImpl();
  }

  @Bean
  public HighlightFormatter highlightFormatter() {

    return new HighlightFormatter();
  }

  @Bean
  public SearchEngineConfigurationLoader searchEngineConfigurationLoader() {

    return new SearchEngineConfigurationLoaderImpl();
  }

  @Bean
  public SearchQueryBuilderFactory searchQueryBuilderFactory() {

    return new LuceneSearchQueryBuilderFactory();
  }

  @Bean
  public LuceneAnalyzer luceneAnalyzer() {

    return new LuceneAnalyzerImpl();
  }

  @Bean
  public LuceneVersion luceneVersion() {

    return new LuceneVersionImpl();
  }

  @Bean
  public SearchDependencies searchDependencies() {

    return new SearchDependenciesImpl();
  }

  @Bean
  public LuceneDirectoryBuilder luceneDirectoryBuilder() {

    return new LuceneDirectoryBuilderImpl();
  }

}
