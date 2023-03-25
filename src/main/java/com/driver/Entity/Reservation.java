package com.driver.Entity;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numberOfHours;

    public Reservation(Integer timeInHours) {
    }

    public Reservation(int id, int numberOfHours,Spot spot,Payment payment,User user) {
        this.id = id;
        this.numberOfHours = numberOfHours;
        this.spot=spot;
        this.payment=payment;
        this.user=user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    Spot spot;

    @OneToOne
    Payment payment;

    @ManyToOne
    User user;
}
