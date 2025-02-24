package ru.tbank.test239.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PaymentSearchResponse {
    private Integer id;
    private Double sum;
    private String date;
}
