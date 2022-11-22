package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripList;

import java.util.List;

public interface TripDao {

    /**
     * Возвращает информацию про все рейсы из Киева в ближайшие 24 часа
     */
    public List<Trip> getAllTrips();


    public void uploadAllTrips (List <Trip> tripList);




}
