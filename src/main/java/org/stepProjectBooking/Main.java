package org.stepProjectBooking;

import org.stepProjectBooking.ticketsApplication.DAO.bookingDAO.BookingService;
import org.stepProjectBooking.ticketsApplication.trips.*;

public class Main {
    public static void main(String[] args) {

        TripCreator tripCreator = new TripCreator();
        Trip trip;

        for (int i = 0; i < 10; i++) {
            trip =tripCreator.createTrip();
            System.out.println(trip.prettyFormat());
        }

        BookingService bookingService = new BookingService();
        System.out.println("--------------getAvailableTrips");
        System.out.println(bookingService.getAvailableTrips(Destinations.PARIS,1));
        System.out.println(bookingService.getAvailableTrips(Destinations.WASHINGTON,5));
        System.out.println("--------------getBookingByNameSurname");
        System.out.println(bookingService.getBookingByNameSurname("Bill","Smith"));
    }
}