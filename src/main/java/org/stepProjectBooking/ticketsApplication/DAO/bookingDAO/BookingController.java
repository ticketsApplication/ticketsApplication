package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.time.LocalDate;
import java.util.List;

import org.stepProjectBooking.ticketsApplication.user.User;

public class BookingController {
    public List<TripBooking> getTripBookingList() {
        return bookingService.getTripBookingList();
    }

    private final BookingService bookingService = new BookingService();

    public Booking getBookingById(int id) {
        return bookingService.getBookingById(id);
    }

    public boolean deleteBookingById(int id) {
        return bookingService.deleteBookingById(id);
    }

    public List<Booking> getBookingByNameSurname(User user) {
        return bookingService.getBookingByUser(user);
    }

    public TripBooking getTripInfoById(String tripId) {
        return bookingService.getTripInfoById(tripId);
    }

    public List<Trip> getAvailableTrips(Destinations destination, LocalDate data, int passengersNum) {
        return bookingService.getAvailableTrips(destination, data, passengersNum);
    }

    public int createNewBooking(Purchaser purchaser, Trip trip, List<Passenger> passengerList, LocalDate date) {
        return bookingService.createNewBooking(purchaser, trip, passengerList, date);
    }

    public void saveTripBookingList(List<TripBooking> tripBookingList) {
        bookingService.saveTripBookingList(tripBookingList);
    }
}