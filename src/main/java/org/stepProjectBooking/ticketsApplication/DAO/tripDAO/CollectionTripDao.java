package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.ArrayList;
import java.util.List;

public class CollectionTripDao {
    private List<Trip> tripList = new ArrayList<>(); //database

    public List<Trip> getCollectionTripDao() {
        return tripList;
    }

    public void setCollectionTripDao(List<Trip> tripList) {
        this.tripList = tripList;
    }

}
