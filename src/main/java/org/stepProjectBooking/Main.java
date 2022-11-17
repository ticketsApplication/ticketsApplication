package org.stepProjectBooking;

import org.stepProjectBooking.ticketsApplication.DAO.bookingDAO.BookingService;
import org.stepProjectBooking.ticketsApplication.trips.*;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        TripCreator tripCreator = new TripCreator();
        Trip trip;

        for (int i = 0; i < 10; i++) {
            trip =tripCreator.createTrip();
            System.out.println(trip.prettyFormat());
        }

        BookingService bookingService = new BookingService();
        System.out.println(bookingService.getAvailableTrips(Destinations.PARIS,2));
        System.out.println(bookingService.getBookingByNameSurName("Bill","Smith"));
    }
}