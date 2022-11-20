package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;


import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.time.LocalDate;
import java.util.List;

import org.stepProjectBooking.ticketsApplication.trips.TripBookingList;

import java.io.IOException;

public class BookingController {


    BookingService bookingService = new BookingService();
    public TripBookingList downLoadAllTrips () {
        return  bookingService.downLoadAllTrips();
    }

    public void uploadTripBookingList(TripBookingList tripBookingList) throws IOException {

        bookingService.uploadTripBookingList(tripBookingList);
    }

}
