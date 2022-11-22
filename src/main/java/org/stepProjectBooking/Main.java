package org.stepProjectBooking;

import org.stepProjectBooking.ticketsApplication.ConsoleMenuUser;
import org.stepProjectBooking.ticketsApplication.DAO.bookingDAO.BookingController;
import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripController;
import org.stepProjectBooking.ticketsApplication.trips.*;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args)  {

        BookingController bookingController = new BookingController();
        System.out.println("________________");
        System.out.println(bookingController.getBookingById(123457));
        System.out.println("________________");
        System.out.println(bookingController.getAvailableTrips(Destinations.WASHINGTON,
                LocalDate.of(2022,12,25),5));
        System.out.println("________________");
        System.out.println(bookingController.getTripInfoById("ADM404"));
        System.out.println("________________");
        System.out.println(bookingController.getAvailableTrips(Destinations.PARIS,
                LocalDate.of(2022, 12, 25), 10));





        System.out.println("________________");


        ConsoleMenuUser consoleMenuUser = new ConsoleMenuUser();

        consoleMenuUser.run();

        TripController tripController = new TripController();



    }
}