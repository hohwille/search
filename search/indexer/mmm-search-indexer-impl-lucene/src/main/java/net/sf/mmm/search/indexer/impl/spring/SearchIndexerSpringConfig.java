/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.indexer.impl.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.sf.mmm.content.parser.impl.spring.ContentParserSpringConfig;
import net.sf.mmm.search.engine.api.SearchEngineBuilder;
import net.sf.mmm.search.engine.api.config.SearchEngineConfigurationLoader;
import net.sf.mmm.search.engine.base.config.SearchEngineConfigurationLoaderImpl;
import net.sf.mmm.search.engine.impl.lucene.LuceneSearchEngineBuilder;
import net.sf.mmm.search.engine.impl.spring.SearchSpringConfig;
import net.sf.mmm.search.indexer.api.ConfiguredSearchIndexer;
import net.sf.mmm.search.indexer.api.ResourceSearchIndexer;
import net.sf.mmm.search.indexer.api.SearchIndexerBuilder;
import net.sf.mmm.search.indexer.api.config.SearchIndexerConfigurationLoader;
import net.sf.mmm.search.indexer.api.state.SearchIndexerStateLoader;
import net.sf.mmm.search.indexer.api.strategy.SearchIndexerUpdateStrategy;
import net.sf.mmm.search.indexer.api.strategy.SearchIndexerUpdateStrategyManager;
import net.sf.mmm.search.indexer.base.IndexerDependencies;
import net.sf.mmm.search.indexer.base.IndexerDependenciesImpl;
import net.sf.mmm.search.indexer.base.ResourceSearchIndexerImpl;
import net.sf.mmm.search.indexer.base.config.SearchIndexerConfigurationLoaderImpl;
import net.sf.mmm.search.indexer.base.state.SearchIndexerStateLoaderImpl;
import net.sf.mmm.search.indexer.base.strategy.SearchIndexerUpdateStrategyManagerImpl;
import net.sf.mmm.search.indexer.impl.ConfiguredSearchIndexerImpl;
import net.sf.mmm.search.indexer.impl.lucene.LuceneSearchIndexerBuilder;
import net.sf.mmm.search.indexer.impl.strategy.SearchIndexerUpdateStrategyLastModified;
import net.sf.mmm.search.indexer.impl.strategy.SearchIndexerUpdateStrategyNone;
import net.sf.mmm.search.indexer.impl.strategy.SearchIndexerUpdateStrategyVcs;
import net.sf.mmm.util.cli.impl.spring.UtilCliSpringConfig;

/**
 * This is the Spring {@link Configuration} for {@link net.sf.mmm.search.indexer}.
 *
 * @author hohwille
 * @since 1.1.0
 */
@Configuration
@Import({ SearchSpringConfig.class, ContentParserSpringConfig.class, UtilCliSpringConfig.class })
@SuppressWarnings("javadoc")
public class SearchIndexerSpringConfig {

  @Bean
  public SearchIndexerBuilder searchIndexerBuilder() {

    return new LuceneSearchIndexerBuilder();
  }

  @Bean
  public SearchIndexerUpdateStrategy searchIndexerUpdateStrategyVcs() {

    return new SearchIndexerUpdateStrategyVcs();
  }

  @Bean
  public SearchIndexerUpdateStrategy searchIndexerUpdateStrategyLastModified() {

    return new SearchIndexerUpdateStrategyLastModified();
  }

  @Bean
  public SearchIndexerUpdateStrategy searchIndexerUpdateStrategyNone() {

    return new SearchIndexerUpdateStrategyNone();
  }

  @Bean
  public IndexerDependencies indexerDependencies() {

    return new IndexerDependenciesImpl();
  }

  @Bean
  public ResourceSearchIndexer resourceSearchIndexer() {

    return new ResourceSearchIndexerImpl();
  }

  @Bean
  public SearchIndexerUpdateStrategyManager searchIndexerUpdateStrategyManager() {

    return new SearchIndexerUpdateStrategyManagerImpl();
  }

  @Bean
  public SearchEngineConfigurationLoader searchEngineConfigurationLoader() {

    return new SearchEngineConfigurationLoaderImpl();
  }

  @Bean
  public SearchEngineBuilder searchEngineBuilder() {

    return new LuceneSearchEngineBuilder();
  }

  @Bean
  public ConfiguredSearchIndexer configuredSearchIndexer() {

    return new ConfiguredSearchIndexerImpl();
  }

  @Bean
  public SearchIndexerConfigurationLoader searchIndexerConfigurationLoader() {

    return new SearchIndexerConfigurationLoaderImpl();
  }

  @Bean
  public SearchIndexerStateLoader searchIndexerStateLoader() {

    return new SearchIndexerStateLoaderImpl();
  }

}
