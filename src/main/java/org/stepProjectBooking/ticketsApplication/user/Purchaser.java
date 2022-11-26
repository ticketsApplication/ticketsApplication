package org.stepProjectBooking.ticketsApplication.user;

import org.stepProjectBooking.ticketsApplication.trips.Booking;

import java.util.ArrayList;

public class Purchaser extends User{

    private int userId;
    private String email;
    private ArrayList<Booking> bookings;

    public Purchaser(String name, String surname) {
        super(name, surname);
    }

    public Purchaser(String name, String surname, int userId, String email, ArrayList<Booking> bookings) {
        super(name, surname);
        this.userId = userId;
        this.email = email;
        this.bookings = bookings;
    }

    public Purchaser() {
        super();
    }

    @Override
    public String toString() {
        return "Purchaser{" +
                "userId=" + userId +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", email='" + email + '\'' +
                ", bookings=" + bookings +
                '}';
    }

}
