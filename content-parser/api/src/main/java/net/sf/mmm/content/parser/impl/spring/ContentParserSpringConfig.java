/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.content.parser.impl.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.sf.mmm.content.parser.api.ContentParserService;
import net.sf.mmm.content.parser.impl.ContentParserServiceImpl;
import net.sf.mmm.util.context.impl.spring.UtilContextSpringConfig;

/**
 * This is the Spring {@link Configuration} for {@link net.sf.mmm.util.io}.
 *
 * @author hohwille
 * @since 7.1.0
 */
@Configuration
@ComponentScan(basePackageClasses = ContentParserServiceImpl.class)
@Import(UtilContextSpringConfig.class)
@SuppressWarnings("javadoc")
public class ContentParserSpringConfig {

  @Bean
  public ContentParserService contentParserService() {

    return new ContentParserServiceImpl();
  }

}
