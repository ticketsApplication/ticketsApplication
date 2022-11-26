package org.stepProjectBooking.ticketsApplication.trips;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TripList implements Serializable {
    private List <Trip> trips = new ArrayList();

    public TripList () {}

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }


    @Override
    public String toString() {
        return "TripList{" +
                "trips=" + trips +
                '}';
    }
}
