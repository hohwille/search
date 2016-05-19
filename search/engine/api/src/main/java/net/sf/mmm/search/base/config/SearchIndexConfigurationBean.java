/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.base.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import net.sf.mmm.search.api.config.SearchIndexConfiguration;

/**
 * This is implementation of {@link SearchIndexConfiguration} as JAXB-ready Java-Bean.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchIndexConfigurationBean implements SearchIndexConfiguration {

  /** @see #getLocation() */
  @XmlAttribute(name = "location")
  private String location;

  /**
   * The constructor.
   */
  public SearchIndexConfigurationBean() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLocation() {

    return this.location;
  }

  /**
   * @param location is the location to set
   */
  public void setLocation(String location) {

    this.location = location;
  }

}
