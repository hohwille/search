/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.indexer.base.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import net.sf.mmm.search.base.config.AbstractSearchConfigurationBean;
import net.sf.mmm.search.indexer.api.config.SearchIndexerConfiguration;
import net.sf.mmm.search.indexer.api.config.SearchIndexerSource;
import net.sf.mmm.util.filter.base.FilterRuleChain;
import net.sf.mmm.util.io.api.EncodingUtil;
import net.sf.mmm.util.transformer.base.StringTransformerChain;

/**
 * This is the implementation of {@link SearchIndexerConfiguration} as
 * JAXB-ready Java-Bean.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@XmlRootElement(name = "search")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchIndexerConfigurationBean extends AbstractSearchConfigurationBean implements
    SearchIndexerConfiguration {

  /** @see #getSources() */
  @XmlElementWrapper(name = XML_ELEMENT_SOURCES)
  @XmlElement(name = XML_ELEMENT_SOURCE)
  private List<SearchIndexerSourceBean> sources;

  /** @see #getSourceMap() */
  private transient Map<String, SearchIndexerSourceBean> sourceMap;

  /** @see #getFilters() */
  @XmlElementWrapper(name = "filters")
  @XmlElement(name = "filter-chain")
  private List<FilterRuleChain<String>> filters;

  /** @see #getTransformers() */
  @XmlElementWrapper(name = "uri-rewriters")
  @XmlElement(name = "transformer-chain")
  private List<StringTransformerChain> transformers;

  /** @see #getNonUtfEncoding() */
  @XmlAttribute(name = "non-utf-encoding")
  private String nonUtfEncoding;

  /**
   * The constructor.
   */
  public SearchIndexerConfigurationBean() {

    super();
  }

  /**
   * @return the sources
   */
  public Collection<? extends SearchIndexerSource> getSources() {

    return this.sources;
  }

  /**
   * {@inheritDoc}
   */
  public SearchIndexerSourceBean getSource(String id) {

    return getSourceMap().get(id);
  }

  /**
   * @param sources is the sources to set
   */
  public void setSources(List<SearchIndexerSourceBean> sources) {

    this.sources = sources;
    this.sourceMap = null;
  }

  /**
   * @return the sourceMap
   */
  protected Map<String, SearchIndexerSourceBean> getSourceMap() {

    if (this.sourceMap == null) {
      Map<String, SearchIndexerSourceBean> map = new HashMap<String, SearchIndexerSourceBean>();
      for (SearchIndexerSourceBean source : this.sources) {
        map.put(source.getId(), source);
      }
      this.sourceMap = map;
    }
    return this.sourceMap;
  }

  /**
   * @return the transformers
   */
  public List<StringTransformerChain> getTransformers() {

    return this.transformers;
  }

  /**
   * @param transformers is the transformers to set
   */
  public void setTransformers(List<StringTransformerChain> transformers) {

    this.transformers = transformers;
  }

  /**
   * {@inheritDoc}
   */
  public String getNonUtfEncoding() {

    if (this.nonUtfEncoding == null) {
      return EncodingUtil.ENCODING_ISO_8859_15;
    }
    return this.nonUtfEncoding;
  }

  /**
   * @param nonUtfEncoding is the nonUtfEncoding to set
   */
  public void setNonUtfEncoding(String nonUtfEncoding) {

    this.nonUtfEncoding = nonUtfEncoding;
  }

  /**
   * This method gets the {@link List} of
   * {@link net.sf.mmm.util.filter.api.Filter}s that
   * {@link net.sf.mmm.util.filter.api.Filter#accept(Object) decide} which
   * resources should be indexed.
   * 
   * @see net.sf.mmm.search.indexer.api.config.SearchIndexerDataLocation#getFilter()
   * 
   * @return the {@link Collection} with the {@link FilterRuleChain}s.
   */
  public Collection<FilterRuleChain<String>> getFilters() {

    return this.filters;
  }

  /**
   * This method sets the {@link #getFilters() filters}.
   * 
   * @param filters is the list of {@link FilterRuleChain}.
   */
  public void setFilters(List<FilterRuleChain<String>> filters) {

    this.filters = filters;
  }

}
