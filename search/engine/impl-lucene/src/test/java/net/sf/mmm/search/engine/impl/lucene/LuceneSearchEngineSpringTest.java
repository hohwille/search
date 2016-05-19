/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.engine.impl.lucene;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sf.mmm.search.engine.api.SearchEngineBuilder;
import net.sf.mmm.search.engine.api.config.SearchEngineConfigurationLoader;
import net.sf.mmm.search.engine.impl.spring.SearchEngineSpringConfig;

/**
 * This is the test-case for {@link LuceneSearchEngine} configured using spring.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SearchEngineSpringConfig.class })
public class LuceneSearchEngineSpringTest extends LuceneSearchEngineTest {

  @Inject
  private SearchEngineBuilder searchEngineBuilder;

  @Inject
  private SearchEngineConfigurationLoader searchEngineConfigurationLoader;

  @Override
  protected SearchEngineBuilder getSearchEngineBuilder() {

    return this.searchEngineBuilder;
  }

  @Override
  protected SearchEngineConfigurationLoader getSearchEngineConfigurationLoader() {

    return this.searchEngineConfigurationLoader;
  }

}
