package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.ArrayList;

public class TripService {

    private final TripDao tripDao;

    public TripService() {
        this.tripDao = new TripDao() {
            @Override
            public ArrayList<Trip> getTrips() {
                return tripDao.getTrips();
            }

            @Override
            public void addTrip(Trip trip) {

            }
        };

    }

    public ArrayList<Trip> getTrip() {
        return tripDao.getTrips();
    }

    public void addTrip(Trip trip) {
        tripDao.addTrip(trip);
    }
}
