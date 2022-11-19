package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.ArrayList;
import java.util.List;

public class CollectionTripDao {
    private List<Trip> tripList = new ArrayList<>(); //database

    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }
}
