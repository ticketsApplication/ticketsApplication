package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.List;

public interface TripDao {

    /**
     * Возвращает информацию про все рейсы из Киева в ближайшие 24 часа
     */
    public List<Trip> getAllTrips();

    public void saveAllTrips (List<Trip> tripList);


    public void uploadAllTripsFromExternalSource(List <Trip> tripList);




}
