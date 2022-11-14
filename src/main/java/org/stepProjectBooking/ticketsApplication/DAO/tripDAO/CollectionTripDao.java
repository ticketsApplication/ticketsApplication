package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.ArrayList;
import java.util.List;

public class CollectionTripDao implements TripDao {
    private final List<Trip> trips;

    public CollectionTripDao(List<Trip> trip) {
        this.trips = new ArrayList<>();
    }

    @Override
    public ArrayList<Trip> getTrips() {
        return (ArrayList<Trip>) trips;
    }

    @Override
    public void addTrip(Trip trip) {
        trips.add(trip);
    }
}
