/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.engine.impl.lucene;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;

import net.sf.mmm.search.engine.api.ComplexSearchQuery;
import net.sf.mmm.search.engine.api.SearchQuery;

/**
 * This is the implementation of the
 * {@link net.sf.mmm.search.engine.api.SearchQuery} interface using lucene as
 * underlying search-engine.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class LuceneComplexSearchQuery extends AbstractLuceneSearchQuery implements
    ComplexSearchQuery {

  /** the actual lucene query */
  private BooleanQuery query;

  /** @see #getSubQueryCount() */
  private int size;

  /**
   * The constructor.
   */
  public LuceneComplexSearchQuery() {

    super();
    this.query = new BooleanQuery();
    this.size = 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BooleanQuery getLuceneQuery() {

    return this.query;
  }

  /**
   * This method adds the given <code>subQuery</code> using the given
   * <code>occur</code>.
   * 
   * @param occur is the occur to use.
   * @param subQuery is the sub-query to add.
   */
  private void addQuery(BooleanClause.Occur occur, SearchQuery subQuery) {

    if (subQuery != LuceneSearchQueryBuilder.NULL_QUERY) {
      AbstractLuceneSearchQuery luceneQuery = (AbstractLuceneSearchQuery) subQuery;
      this.query.add(luceneQuery.getLuceneQuery(), occur);
      this.size++;
    }
  }

  /**
   * {@inheritDoc}
   */
  public void addExcludingQuery(SearchQuery subQuery) {

    addQuery(BooleanClause.Occur.MUST_NOT, subQuery);
  }

  /**
   * {@inheritDoc}
   */
  public void addOptionalQuery(SearchQuery subQuery) {

    addQuery(BooleanClause.Occur.SHOULD, subQuery);
  }

  /**
   * {@inheritDoc}
   */
  public void addRequiredQuery(SearchQuery subQuery) {

    addQuery(BooleanClause.Occur.MUST, subQuery);
  }

  /**
   * {@inheritDoc}
   */
  public int getSubQueryCount() {

    return this.size;
  }

}
