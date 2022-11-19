package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.List;

public interface TripDao {

    /**
     * Возвращает информацию про все рейсы из Киева в ближайшие 24 часа
     */
    public  <Trip> List<Trip> getAllTrips();

    /**
     * Выводит на экран информацию про все рейсы из Киева в ближайшие 24 часа
     */
    List<String> displayAllTrips();

    /**
     * Возвращает информацию о рейсе
     */
    List<String> getTripsById(String id);

    /**
     * Возвращает информацию о рейсах по запросу: место назначения,
     * дата, количество человек (сколько необходимо купить билетов)
     */
    List<String> getTripsByDestinationNumPassenger(Destinations destinations, int numPassenger);

//    void saveAllTrips (List <Trip> tripList);

}
