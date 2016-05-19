/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.engine.impl.lucene;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sf.mmm.search.engine.api.SearchQueryBuilderFactory;
import net.sf.mmm.search.engine.impl.spring.SearchEngineSpringConfig;

/**
 * This is the test-case for {@link LuceneSearchQueryBuilderFactory} configured using spring.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SearchEngineSpringConfig.class })
public class LuceneSearchQueryBuilderFactorySpringTest extends LuceneSearchQueryBuilderFactoryTest {

  @Inject
  private SearchQueryBuilderFactory searchQueryBuilderFactory;

  @Override
  protected SearchQueryBuilderFactory getQueryBuilderFactory() {

    return this.searchQueryBuilderFactory;
  }

}
