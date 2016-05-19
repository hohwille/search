/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.content.parser.impl;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sf.mmm.content.parser.api.ContentParserService;
import net.sf.mmm.content.parser.impl.spring.ContentParserSpringConfig;

/**
 * This is the test-case for {@link ContentParserService} configured using spring.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContentParserSpringConfig.class })
public class ContentParserServiceSpringTest extends ContentParserServiceImplTest {

  @Inject
  private ContentParserService contentParserService;

  @Override
  protected ContentParserService getContentParserService() {

    return this.contentParserService;
  }

}
