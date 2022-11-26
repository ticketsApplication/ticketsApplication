package org.stepProjectBooking.ticketsApplication.user;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

public class Passenger extends User {

    private Trip tripId;
    int bookId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Trip getTripId() {
        return tripId;
    }

    public void setTripId(Trip tripId) {
        this.tripId = tripId;
    }

    public Passenger(String name, String surname) {
        super(name, surname);
    }

    public Passenger(String name, String surname, Trip tripId, int bookId) {
        super(name, surname);
        this.tripId = tripId;
        this.bookId = bookId;
    }

    public Passenger() {
        super();
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", tripId='" + tripId.getTripId() + '\'' +
                ", bookId=" + bookId +
                '}'+"\n";
    }
}
