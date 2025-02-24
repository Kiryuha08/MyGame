package ru.tbank.test239.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tbank.test239.service.DateService;

@RestController
@RequestMapping("/api/date")
public class DateController {

  @Autowired
  public DateService dateService;

  // localhost:8080/api/date/yesterday-date

  @GetMapping("/yesterday-date")
  public String getYesterdayDate() {
    return dateService.getYesterdayDate();
  }

}
