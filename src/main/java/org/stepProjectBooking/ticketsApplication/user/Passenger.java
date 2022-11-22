package org.stepProjectBooking.ticketsApplication.user;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

public class Passenger extends User {

    private Trip tripId;
    int bookId;

    public Passenger(String name, String surname) {
        super(name, surname);
    }

    public Passenger(String name, String surname, Trip tripId, int bookId) {
        super(name, surname);
        this.tripId = tripId;
        this.bookId = bookId;
    }

    public Passenger() {

    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                "tripId='" + tripId + '\'' +
                ", bookId=" + bookId +
                '}'+"\n";
    }
}
