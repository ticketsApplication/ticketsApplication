package org.stepProjectBooking.ticketsApplication.user;

public class Passenger extends User {

    private String tripId;
    int bookId;

    public Passenger(String name, String surname) {
        super(name, surname);
    }

    public Passenger(String name, String surname, String tripId, int bookId) {
        super(name, surname);
        this.tripId = tripId;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                "tripId='" + tripId + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}
