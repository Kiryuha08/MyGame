package ru.tbank.test239.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tbank.test239.repository.PaymentRepository;

@Service
public class PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;

  public Double getSum(int id) {
    return paymentRepository.getSum(id);
  }

  public boolean containsPayment(int id) {
    return paymentRepository.containsPayment(id);
  }

}
