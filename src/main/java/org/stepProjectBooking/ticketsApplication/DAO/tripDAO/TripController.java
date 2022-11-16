package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;


import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.List;

public class TripController {

    private final TripService tripService;

    public TripController() {
        this.tripService = new TripService();
    }

    public List<Trip> getAllFamilies() {
        return tripService.getAllTrips();
    }

    public List<String> displayAllTrips() {
        return tripService.displayAllTrips();
    }

    public List<String> getTripsById(String id) {
        return tripService.getTripsById(id);
    }

    public List<String> getTripsByDestinationNumPassenger(Destinations destinations, long date, int numPassenger) {
        return tripService.getTripsByDestinationNumPassenger(destinations, numPassenger);
    }

}
