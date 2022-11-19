package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

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
