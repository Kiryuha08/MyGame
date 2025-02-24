package ru.tbank.test239.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentSearchRequest {

  private Integer id;
  private Double sum;
  private String date;

}
