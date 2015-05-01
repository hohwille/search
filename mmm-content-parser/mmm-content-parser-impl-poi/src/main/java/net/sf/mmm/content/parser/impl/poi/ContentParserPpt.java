/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.content.parser.impl.poi;

import java.io.UnsupportedEncodingException;

import javax.inject.Named;
import javax.inject.Singleton;

import net.sf.mmm.content.parser.api.ContentParserOptions;

import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.LittleEndian;

/**
 * This is the implementation of the
 * {@link net.sf.mmm.content.parser.api.ContentParser} interface for binary
 * MS-Powerpoint documents.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@Singleton
@Named
public class ContentParserPpt extends AbstractContentParserPoi {

  /** The mimetype. */
  public static final String KEY_MIMETYPE = "application/vnd.ms-powerpoint";

  /** The default extension. */
  public static final String KEY_EXTENSION = "ppt";

  /** type of document */
  protected static final int PPT_TYPE_DOCUMENT = 1000;

  /** type of document atom - ignore */
  protected static final int PPT_TYPE_DOCUMENT_ATOM = 1001;

  /** type of slide */
  protected static final int PPT_TYPE_SLIDE = 1006;

  /** type of slide atom */
  protected static final int PPT_TYPE_SLIDE_ATOM = 1007;

  /** type of notes */
  protected static final int PPT_TYPE_NOTES = 1008;

  /** type of notes atom */
  protected static final int PPT_TYPE_NOTES_ATOM = 1009;

  /** type of environment - ignore */
  protected static final int PPT_TYPE_ENVIRONMENT = 1010;

  /** type of slide persist atom - ignore */
  protected static final int PPT_TYPE_SLIDE_PERSIST_ATOM = 1011;

  /** type of main master - ignore */
  protected static final int PPT_TYPE_MAIN_MASTER = 1016;

  /** type of extended object list - ignore */
  protected static final int PPT_TYPE_EXTENDED_OBJECT_LIST = 1033;

  /** type of drawing group - ignore */
  protected static final int PPT_TYPE_DRAWING_GROUP = 1035;

  /** type of drawing - ignore */
  protected static final int PPT_TYPE_DRAWING = 1036;

  /** type of list - ignore */
  protected static final int PPT_TYPE_LIST = 2000;

  /** type of text header atom */
  protected static final int PPT_TYPE_TEXT_HEADER_ATOM = 3999;

  /** type of text chars atom */
  protected static final int PPT_TYPE_TEXT_CHARS_ATOM = 4000;

  /** type of style text property atom */
  protected static final int PPT_TYPE_STYLE_TEXT_PROPERTY_ATOM = 4001;

  /** type of text bytes atom */
  protected static final int PPT_TYPE_TEXT_BYTES_ATOM = 4008;

  /** type of spec info atom - ignore */
  protected static final int PPT_TYPE_SPEC_INFO_ATOM = 4010;

  /** type of character stream */
  protected static final int PPT_TYPE_CHAR_STRING = 4026;

  /** type of header/footer */
  protected static final int PPT_TYPE_HEADER_FOOTER = 4057;

  /** type of header/footer atom */
  protected static final int PPT_TYPE_HEADER_FOOTER_ATOM = 4058;

  /** type of interactive info */
  protected static final int PPT_TYPE_TX_INTERACTIVE_INFO_ATOM = 4063;

  /** type of slide-list */
  protected static final int PPT_TYPE_SLIDE_LIST_WITH_TEXT = 4080;

  /** type of interactive info */
  protected static final int PPT_TYPE_INTERACTIVE_INFO = 4082;

  /** type of escher dg container */
  protected static final int PPT_TYPE_ESCHER_DG_CONTAINER = 61442;

  /** type of escher spgr container */
  protected static final int PPT_TYPE_ESCHER_SPGR_CONTAINER = 61443;

  /** type of escher sp container */
  protected static final int PPT_TYPE_ESCHER_SP_CONTAINER = 61444;

  /** type of escher dg */
  protected static final int PPT_TYPE_ESCHER_DG = 61448;

  /** type of escher sp */
  protected static final int PPT_TYPE_ESCHER_SP = 61450;

  /** type of escher opt */
  protected static final int PPT_TYPE_ESCHER_OPT = 61451;

  /** type of escher textbox */
  protected static final int PPT_TYPE_ESCHER_TEXTBOX = 61453;

  /** first record item: info (little endian short) */
  protected static final int PPT_RECORD_INFO_OFFSET = 0;

  /** second record item: type (little endian short) */
  protected static final int PPT_RECORD_TYPE_OFFSET = 2;

  /** third record item: size (little endian int) */
  protected static final int PPT_RECORD_SIZE_OFFSET = 4;

  /** third record item: size (little endian int) */
  protected static final int PPT_RECORD_LENGTH = 8;

  /** encoding name */
  protected static final String ENCODING_UTF16LE = "UTF-16LE";

  /**
   * The constructor.
   * 
   */
  public ContentParserPpt() {

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

    return new String[] { "pot" };
  }

  /**
   * 
   * @param buffer
   * @param offset
   * @param length
   * @param textBuffer
   * @throws UnsupportedEncodingException
   */
  private void extractRecursive(byte[] buffer, int offset, int length, StringBuffer textBuffer)
      throws UnsupportedEncodingException {

    int offsetLength = offset + length - 8;
    if (offsetLength > buffer.length - 8) {
      /*
       * System.out.println("Illegal array index: offset=" + offset + ",
       * length=" + length + ", bufferSize=" + buffer.length);
       */
      offsetLength = buffer.length - 8;
    }
    int index = offset;
    while (index < offsetLength) {
      // int info = LittleEndian.getUShort(buffer, index +
      // PPT_RECORD_INFO_OFFSET);
      int type = LittleEndian.getUShort(buffer, index + PPT_RECORD_TYPE_OFFSET);
      long longSize = LittleEndian.getUInt(buffer, index + PPT_RECORD_SIZE_OFFSET);
      // System.out.println("Index is: " + index);
      // System.out.println("record info: " + info);
      // System.out.println("record type: " + type);
      // System.out.println("record size: " + longSize);
      int size = (int) longSize;
      if (size < 0) {
        // System.out.println("size truncated: " + longSize + "");
        return;
      }
      index += PPT_RECORD_LENGTH;
      if (type == PPT_TYPE_SLIDE_LIST_WITH_TEXT) {
        extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_HEADER_FOOTER) {
        extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_DOCUMENT) {
        extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_SLIDE) {
        // extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_MAIN_MASTER) {
        // extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_CHAR_STRING) {
        String text = new String(buffer, index, size, ENCODING_UTF16LE);
        textBuffer.append(text);
        textBuffer.append('\n');
      } else if (type == PPT_TYPE_TEXT_BYTES_ATOM) {
        String text = new String(buffer, index, size);
        textBuffer.append(text);
        textBuffer.append('\n');
      } else if (type == PPT_TYPE_TEXT_CHARS_ATOM) {
        String text = new String(buffer, index, size, ENCODING_UTF16LE);
        textBuffer.append(text);
        textBuffer.append('\n');
      } else if (type == PPT_TYPE_NOTES) {
        // --> PPT_TYPE_DRAWING
        extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_DRAWING) {
        // --> PPT_TYPE_ESCHER_DG_CONTAINER
        extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_ESCHER_DG_CONTAINER) {
        // --> PPT_TYPE_ESCHER_SPGR_CONTAINER
        extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_ESCHER_SPGR_CONTAINER) {
        // --> PPT_TYPE_ESCHER_SP_CONTAINER
        extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_ESCHER_SP_CONTAINER) {
        // --> PPT_TYPE_ESCHER_TEXTBOX
        extractRecursive(buffer, index, size, textBuffer);
      } else if (type == PPT_TYPE_ESCHER_TEXTBOX) {
        // --> PPT_TYPE_TEXT_BYTES_ATOM
        extractRecursive(buffer, index, size, textBuffer);
        /*
         * } else if (type == PPT_TYPE_DOCUMENT_ATOM) { // ignore } else if
         * (type == PPT_TYPE_SLIDE_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_NOTES_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_ENVIRONMENT) { // ignore } else if (type ==
         * PPT_TYPE_SLIDE_PERSIST_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_EXTENDED_OBJECT_LIST) { // ignore } else if (type ==
         * PPT_TYPE_DRAWING_GROUP) { // ignore } else if (type ==
         * PPT_TYPE_ESCHER_DG) { // ignore } else if (type ==
         * PPT_TYPE_INTERACTIVE_INFO) { // ignore } else if (type ==
         * PPT_TYPE_HEADER_FOOTER_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_SPEC_INFO_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_STYLE_TEXT_PROPERTY_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_SLIDE_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_SLIDE_PERSIST_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_SLIDE_PERSIST_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_TEXT_HEADER_ATOM) { // ignore } else if (type ==
         * PPT_TYPE_TX_INTERACTIVE_INFO_ATOM) { // ignore
         */
      }
      index += size;
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String extractText(POIFSFileSystem poiFs, long filesize, ContentParserOptions options)
      throws Exception {

    // PowerPointExtractor pptExtractor = new PowerPointExtractor(poiFs);
    // return pptExtractor.getText();

    DocumentInputStream docStream = poiFs.createDocumentInputStream(POIFS_POWERPOINT_DOC);

    int length = docStream.available();
    int maximumBufferSize = options.getMaximumBufferSize();
    if (maximumBufferSize < length) {
      length = maximumBufferSize;
    }
    int capacity = length / 10;
    StringBuffer textBuffer = new StringBuffer(capacity);
    byte[] buffer = new byte[length];
    docStream.read(buffer);
    docStream.close();
    extractRecursive(buffer, 0, length, textBuffer);
    return textBuffer.toString();
  }

}
