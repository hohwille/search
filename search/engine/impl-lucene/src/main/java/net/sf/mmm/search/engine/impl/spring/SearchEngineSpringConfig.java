/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.engine.impl.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.sf.mmm.search.engine.api.config.SearchEngineConfigurationLoader;
import net.sf.mmm.search.engine.base.config.SearchEngineConfigurationLoaderImpl;

/**
 * This is the Spring {@link Configuration} for {@link net.sf.mmm.search.engine}.
 *
 * @author hohwille
 * @since 1.1.0
 */
@Configuration
@Import(SearchSpringConfig.class)
@SuppressWarnings("javadoc")
public class SearchEngineSpringConfig {

  @Bean
  public SearchEngineConfigurationLoader searchEngineConfigurationLoader() {

    return new SearchEngineConfigurationLoaderImpl();
  }

}
