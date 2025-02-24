package ru.tbank.test239.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class DateService {

  public String getYesterdayDate() {
    return LocalDateTime.now().minusDays(1).getDayOfMonth() + " "
        + LocalDateTime.now().minusDays(1).getMonth();
  }

}
