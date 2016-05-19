/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.impl.lucene;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import net.sf.mmm.search.api.config.SearchIndexConfiguration;
import net.sf.mmm.util.component.base.AbstractComponent;
import net.sf.mmm.util.file.api.FileUtil;
import net.sf.mmm.util.file.base.FileUtilImpl;
import net.sf.mmm.util.io.api.IoMode;
import net.sf.mmm.util.io.api.RuntimeIoException;
import net.sf.mmm.util.lang.api.SystemInformation;
import net.sf.mmm.util.lang.api.SystemUtil;
import net.sf.mmm.util.lang.base.SystemUtilImpl;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

/**
 * This is the implementation of the {@link LuceneDirectoryBuilder} interface.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@Singleton
@Named
public class LuceneDirectoryBuilderImpl extends AbstractComponent implements LuceneDirectoryBuilder {

  /** The suffix for the lucene directory. */
  private static final String PATH_SUFFIX_LUCENE = "/lucene";

  /** @see #setFileUtil(FileUtil) */
  private FileUtil fileUtil;

  /** @see #setSystemUtil(SystemUtil) */
  private SystemUtil systemUtil;

  /**
   * The constructor.
   */
  public LuceneDirectoryBuilderImpl() {

    super();
  }

  /**
   * @param fileUtil is the fileUtil to set
   */
  @Inject
  public void setFileUtil(FileUtil fileUtil) {

    getInitializationState().requireNotInitilized();
    this.fileUtil = fileUtil;
  }

  /**
   * @param systemUtil is the systemUtil to set
   */
  @Inject
  public void setSystemUtil(SystemUtil systemUtil) {

    getInitializationState().requireNotInitilized();
    this.systemUtil = systemUtil;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doInitialize() {

    super.doInitialize();
    if (this.fileUtil == null) {
      this.fileUtil = FileUtilImpl.getInstance();
    }
    if (this.systemUtil == null) {
      this.systemUtil = SystemUtilImpl.getInstance();
    }
  }

  /**
   * {@inheritDoc}
   */
  public Directory createDirectory(SearchIndexConfiguration configuration) {

    return createDirectory(configuration.getLocation() + PATH_SUFFIX_LUCENE);
  }

  /**
   * {@inheritDoc}
   */
  public Directory createDirectory(String directory) {

    String location = this.fileUtil.normalizePath(directory);
    if (location.toLowerCase(Locale.US).startsWith("file://")) {
      // "file://".length() ==
      location = location.substring(7);
    }
    File path = new File(location);
    boolean windows = SystemInformation.SYSTEM_TYPE_WINDOWS.equals(this.systemUtil
        .getSystemInformation().getSystemType());
    try {
      if (windows) {
        return new SimpleFSDirectory(path);
      } else {
        return new NIOFSDirectory(path);
      }
    } catch (IOException e) {
      throw new RuntimeIoException(e, IoMode.READ);
    }
  }
}
