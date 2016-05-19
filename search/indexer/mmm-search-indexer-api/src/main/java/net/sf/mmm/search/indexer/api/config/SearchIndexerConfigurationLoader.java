/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.indexer.api.config;

import net.sf.mmm.search.api.config.SearchConfigurationLoader;
import net.sf.mmm.util.component.api.ComponentSpecification;

/**
 * This is the {@link SearchConfigurationLoader} for the {@link SearchIndexerConfiguration}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@ComponentSpecification
public interface SearchIndexerConfigurationLoader extends SearchConfigurationLoader {

  /** The {@link net.sf.mmm.util.component.api.Cdi#CDI_NAME CDI name}. */
  String CDI_NAME = "net.sf.mmm.search.indexer.api.config.SearchIndexerConfigurationLoader";

  /**
   * {@inheritDoc}
   */
  @Override
  SearchIndexerConfigurationHolder loadConfiguration();

  /**
   * {@inheritDoc}
   */
  @Override
  SearchIndexerConfigurationHolder loadConfiguration(String locationUrl);

  /**
   * This method validates the given <code>configuration</code> according to logical constraints.
   * 
   * @param configuration is the {@link SearchIndexerConfiguration} to validate.
   * @throws RuntimeException if the configuration is invalid.
   */
  void validateConfiguration(SearchIndexerConfiguration configuration) throws RuntimeException;

}
