package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;


import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.ArrayList;

public class TripController {

    private final TripService tripService;

    public TripController() {
        this.tripService = new TripService();
    }

    public ArrayList<Trip> getTrips() {
        return tripService.getTrip();
    }

    public void addTrip(Trip trip) {
        tripService.addTrip(trip);
    }
}
