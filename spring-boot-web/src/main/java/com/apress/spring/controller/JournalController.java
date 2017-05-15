package com.apress.spring.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.apress.spring.domain.JournalEntry;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JournalController {
  private static List<JournalEntry> entries = new ArrayList<>();

  static {
    try {
      entries.add(new JournalEntry("Get to know spring boot", "Today I will learn spring boot", "01/01/2016"));
      entries.add(new JournalEntry("Simple spring boot project", "I will do my first spring boot project",
          "01/02/2016"));
      entries.add(new JournalEntry("Spring boot reading", "Read more about spring boot", "02/01/2016"));
      entries.add(new JournalEntry("Spring boot in the cloud", "Spring boot using cloud foundry", "03/01/2016"));
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/journal/all")
  public List<JournalEntry> getAll() {
    return entries;
  }

  @RequestMapping("/journal/findBy/title/{title}")
  public List<JournalEntry> findByTitleContains(@PathVariable String title) {
    return entries.stream().filter(entry -> entry.getTitle().toLowerCase().contains(title.toLowerCase()))
        .collect(Collectors.toList());
  }

  @RequestMapping(
    value="/journal",
    method=RequestMethod.POST
  )
  public JournalEntry add(@RequestBody JournalEntry entry) {
    entries.add(entry);
    return entry;
  }
}