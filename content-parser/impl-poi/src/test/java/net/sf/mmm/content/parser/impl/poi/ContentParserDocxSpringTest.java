/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.content.parser.impl.poi;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sf.mmm.content.parser.api.ContentParser;
import net.sf.mmm.content.parser.impl.spring.ContentParserSpringConfig;

/**
 * This is the test-case for {@link ContentParserDocx} configured using spring.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContentParserSpringConfig.class })
public class ContentParserDocxSpringTest extends ContentParserDocxTest {

  @Inject
  private ContentParserDocx contentParser;

  @Override
  protected ContentParser getContentParser() {

    return this.contentParser;
  }

}
