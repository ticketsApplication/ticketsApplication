package org.stepProjectBooking.ticketsApplication.trips;


import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;


public class TripBooking implements Serializable {


    private Trip trip;
    private List<Booking> bookingList;
    private int freePlace;
    private boolean isActive;

    private LocalDateTime date;

    public int getFreePlace() {
        // long passNum = bookingList.stream().map(s -> s.getPassengerList().size()).count();
        int passNum = 0;
        for (Booking booking : bookingList) {
            passNum = passNum + booking.getPassengerList().size();
        }
        int a = trip.getCapacity() - passNum;
        return a;
    }



    /**
     * Конструктор для базы данных
     */
    public TripBooking(LocalDateTime date, Trip trip, List<Booking> bookingList, boolean isActive) {
        this.date = date;
        this.trip = trip;
        this.bookingList = bookingList;
        this.freePlace = getFreePlace();
        this.isActive = isActive;
    }

    public TripBooking(Trip trip, /*int freePlace,*/ LocalDateTime date) {

        this.trip = trip;
        this.freePlace = trip.getCapacity();
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

//    public int getFreePlace() {
//        return getFreePlaces();
//    }

//    public void setFreePlace(int freePlace) {
//        this.freePlace = freePlace;
//    }

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
