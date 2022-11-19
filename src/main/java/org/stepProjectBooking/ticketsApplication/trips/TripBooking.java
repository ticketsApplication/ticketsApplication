package org.stepProjectBooking.ticketsApplication.trips;


import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TripBooking implements Serializable {


    private Trip trip;
    private List<Booking> bookingList;
    private int freePlace;
    private boolean isActive;

    private LocalDateTime date;

    public TripBooking(Trip trip, int freePlace, LocalDateTime date) {

        this.trip = trip;
        this.freePlace = freePlace;
        this.date = date; // should be defined by other method or before creation
        this.bookingList = new ArrayList<>();
        this.isActive = true;
    }



    public List<Booking> getBookingList() {
        return bookingList;
    }
    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public int getFreePlace() {
        return freePlace;
    }

    public void setFreePlace(int freePlace) {
        this.freePlace = freePlace;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {

        this.date = date;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}
