package org.stepProjectBooking.ticketsApplication.trips;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;


public class TripBooking implements Serializable {


    private Trip trip;
    private List<Booking> bookingList;

    private LocalDateTime date;

    public TripBooking() {
    }

    public int getFreePlace() {
        int passNum = 0;
        for (Booking booking : bookingList) {
            passNum = passNum + booking.getPassengerList().size();
        }
        return trip.getCapacity() - passNum;
    }

    public TripBooking(LocalDateTime date, Trip trip, List<Booking> bookingList) {
        this.date = date;
        this.trip = trip;
        this.bookingList = bookingList;
    }

    public TripBooking(Trip trip, LocalDateTime date) {
        this.trip = trip;
        this.date = date; // should be defined by other method or before creation
        this.bookingList = new ArrayList<>();
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "TripBooking{" +
                "trip=" + trip +
                ", bookingList=" + bookingList +
                ", freePlace=" + getFreePlace() +
                ", date=" + date +
                '}';
    }
}
