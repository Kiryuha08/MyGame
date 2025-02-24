package ru.tbank.test239.repository;

import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {

  private final Map<Integer, Double> payment = Map.of(
      1, 12.0,
      2, 239.50,
      0, 1000000.0
  );

  public Double getSum(int id) {
    return payment.get(id);
  }

  public boolean containsPayment(int id) {
    return payment.containsKey(id);
  }
}
