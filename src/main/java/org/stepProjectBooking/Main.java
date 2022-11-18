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
                LocalDate.of(2022,11,18),8));
        System.out.println(bookingService.getAvailableTrips(Destinations.WASHINGTON,
                LocalDate.of(2022,11,18),15));

        System.out.println("--------------getBookingById");
        System.out.println(bookingService.getBookingById(123456));
        //bookingService.deleteBookingById(123456);
        System.out.println();
        System.out.println("--------------getBookingByNameSurname");
        System.out.println(bookingService.getBookingByNameSurname("Bill","Smith"));

        System.out.println();
        System.out.println();

        System.out.println(bookingService.getBookingByNameSurname("Stan","Second"));

        var a =bookingService.getTripInfoById("1");


        System.out.println(a);
    }
}