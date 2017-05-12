package com.apress.spring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity @NoArgsConstructor @Getter @Setter @ToString
public class Journal {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String title;
  private Date created;
  private String summary;

  @Transient
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

  public Journal(String title, String summary, String date)
      throws ParseException {
    this.title = title;
    this.summary = summary;
    this.created = format.parse(date);
  }

  public String getCreatedAsShort() {
    return format.format(created);
  }
}