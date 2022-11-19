package org.stepProjectBooking.ticketsApplication.trips;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TripBookingList implements Serializable {

    List<TripBooking> tripBookingList = new ArrayList<>();

    public TripBookingList () {}

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }
}
