package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.ArrayList;

public interface TripDao {

    ArrayList<Trip> getTrips();

    void addTrip(Trip trip);
}
