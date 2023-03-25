package com.driver.services;

import com.driver.Model.Payment;

public interface PaymentService {
    Payment pay(Integer reservationId, int amountSent, String mode) throws Exception;
}
