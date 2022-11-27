package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;
import org.stepProjectBooking.ticketsApplication.user.User;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface BookingDao {

    Booking getBookingById(int id);

    boolean deleteBookingById(int id);

    List<Booking> getBookingByUser(User user);

    TripBooking getTripInfoById(String tripId);

    List<Trip> getAvailableTrips(Destinations destination, LocalDate data, int passengersNum);

    int createNewBooking(Purchaser purchaser, Trip trip, List<Passenger> passengerList, LocalDate date);

    void saveTripBookingList(List<TripBooking> tripBookingList) throws FileNotFoundException;

    List<TripBooking> getTripBookingList();
}