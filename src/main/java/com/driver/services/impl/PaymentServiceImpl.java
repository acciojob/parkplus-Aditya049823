package com.driver.services.impl;

import com.driver.Model.Payment;
import com.driver.Model.Reservation;
import com.driver.Model.Spot;
import com.driver.model.PaymentMode;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Reservation reservation = reservationRepository2.findById(reservationId).get();
        Spot spot = reservation.getSpot();
        int amt = spot.getPricePerHour();
        int totalAmt = amt*reservation.getNumberOfHours();

        if(mode.equalsIgnoreCase("cash")||mode.equalsIgnoreCase("card")||mode.equalsIgnoreCase("upi")){

            if(amountSent != totalAmt)
                throw new Exception("Insufficient Amount");

            Payment payment = new Payment();
            if(mode.equalsIgnoreCase("cash"))
                payment.setPaymentMode(PaymentMode.CASH);
            else if (mode.equalsIgnoreCase("card"))
                payment.setPaymentMode(PaymentMode.CARD);
            else payment.setPaymentMode(PaymentMode.UPI);

            payment.setPaymentCompleted(true);
            payment.setReservation(reservation);

            reservation.setPayment(payment);
            //   spot.setOccupied(true);

            reservationRepository2.save(reservation);
            return payment;
        }
        else
            throw new Exception("Payment mode not detected");
    }
}
