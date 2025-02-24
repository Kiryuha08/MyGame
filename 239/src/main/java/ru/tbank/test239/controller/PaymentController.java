package ru.tbank.test239.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tbank.test239.controller.request.PaymentSearchRequest;
import ru.tbank.test239.controller.response.PaymentSearchResponse;
import ru.tbank.test239.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  // localhost:8080/api/payment/sum/{id}
  @GetMapping("/sum/{id}")
  public String getPaymentSum(@PathVariable Integer id) {
    if (id < 0) {
      return "Некорректный идентификатор платежа. Id должно быть не меньше 0";
    }
    if (paymentService.containsPayment(id)) {
      return paymentService.getSum(id).toString();
    }
    return "Платёж не найден";
  }

  @GetMapping("/sum")
  public PaymentSearchResponse getPaymentSum(@RequestBody PaymentSearchRequest request) {
    return new PaymentSearchResponse(
        request.getId(),
        request.getSum(),
        request.getDate()
    );
  }

}
