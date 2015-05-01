/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.content.parser.impl.poi;

import java.io.InputStream;

import net.sf.mmm.content.parser.api.ContentParserOptions;
import net.sf.mmm.content.parser.base.AbstractContentParser;
import net.sf.mmm.util.context.api.MutableGenericContext;

import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * This is the abstract base implementation of the
 * {@link net.sf.mmm.content.parser.api.ContentParser} interface for parsing
 * binary Microsoft office documents using apache POI.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public abstract class AbstractContentParserPoi extends AbstractContentParser {

  /** name of the entry for a word document in the POI filesystem */
  public static final String POIFS_WORD_DOC = "WordDocument";

  /** name of the entry for a powerpoint document in the POI filesystem */
  public static final String POIFS_POWERPOINT_DOC = "PowerPoint Document";

  /** name of the entry for a excel document in the POI filesystem */
  public static final String POIFS_EXCEL_DOC = "Workbook";

  /**
   * The constructor.
   */
  public AbstractContentParserPoi() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void parse(InputStream inputStream, long filesize, ContentParserOptions options,
      MutableGenericContext context) throws Exception {

    POIFSFileSystem poiFs = new POIFSFileSystem(inputStream);
    SummaryInformation summaryInfo = (SummaryInformation) PropertySetFactory.create(poiFs
        .createDocumentInputStream(SummaryInformation.DEFAULT_STREAM_NAME));
    String title = summaryInfo.getTitle();
    if (title != null) {
      context.setVariable(VARIABLE_NAME_TITLE, title);
    }
    String author = summaryInfo.getAuthor();
    if (author != null) {
      context.setVariable(VARIABLE_NAME_CREATOR, author);
    }
    String keywords = summaryInfo.getKeywords();
    if (keywords != null) {
      context.setVariable(VARIABLE_NAME_KEYWORDS, keywords);
    }
    context.setVariable(VARIABLE_NAME_TEXT, extractText(poiFs, filesize, options));
  }

  /**
   * This method extracts the text from the office document given by
   * <code>poiFs</code>.
   * 
   * @param poiFs is the POI filesystem of the office document.
   * @param filesize is the size (content-length) of the content to parse in
   *        bytes or <code>0</code> if NOT available (unknown). If available,
   *        the parser may use this value for optimized allocations.
   * @param options are the {@link ContentParserOptions}.
   * @return the plain text extracted from the content.
   * @throws Exception if something goes wrong.
   */
  protected abstract String extractText(POIFSFileSystem poiFs, long filesize,
      ContentParserOptions options) throws Exception;

}
