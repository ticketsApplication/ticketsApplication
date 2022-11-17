package org.stepProjectBooking;

import org.stepProjectBooking.ticketsApplication.DAO.bookingDAO.BookingService;
import org.stepProjectBooking.ticketsApplication.trips.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        TripCreator tripCreator = new TripCreator();
        Trip trip;

        for (int i = 0; i < 10; i++) {
            trip =tripCreator.createTrip();
            System.out.println(trip.prettyFormat());
        }
        BookingService bookingService = new BookingService();
        System.out.println();


        System.out.println("--------------getAvailableTrips");
        System.out.println(bookingService.getAvailableTrips(Destinations.PARIS,
                LocalDate.of(2022,11,18),7));
        System.out.println(bookingService.getAvailableTrips(Destinations.WASHINGTON,
                LocalDate.of(2022,11,18),15));
        System.out.println("--------------getBookingByNameSurname");
//        System.out.println(bookingService.getBookingByNameSurname("Bill","Smith"));
    }
}