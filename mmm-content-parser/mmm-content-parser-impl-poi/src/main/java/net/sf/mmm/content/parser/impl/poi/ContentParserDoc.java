/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.content.parser.impl.poi;

import javax.inject.Named;
import javax.inject.Singleton;

import net.sf.mmm.content.parser.api.ContentParserOptions;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * This is the implementation of the
 * {@link net.sf.mmm.content.parser.api.ContentParser} interface for binary
 * MS-Word documents.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@Singleton
@Named
public class ContentParserDoc extends AbstractContentParserPoi {

  /** The mimetype. */
  public static final String KEY_MIMETYPE = "application/msword";

  /** The default extension. */
  public static final String KEY_EXTENSION = "doc";

  /**
   * The constructor.
   */
  public ContentParserDoc() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  public String getExtension() {

    return KEY_EXTENSION;
  }

  /**
   * {@inheritDoc}
   */
  public String getMimetype() {

    return KEY_MIMETYPE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getAlternativeKeyArray() {

    return new String[] { "dot" };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String extractText(POIFSFileSystem poiFs, long filesize, ContentParserOptions options)
      throws Exception {

    // DocumentEntry documentEntry = (DocumentEntry)
    // poiFs.getRoot().getEntry(POIFS_WORD_DOC);
    // DocumentInputStream documentInputStream =
    // poiFs.createDocumentInputStream(POIFS_ENTRY);

    WordExtractor extractor = new WordExtractor(poiFs);
    return extractor.getText();
  }

}
