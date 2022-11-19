package org.stepProjectBooking;

import org.stepProjectBooking.ticketsApplication.DAO.bookingDAO.BookingController;
import org.stepProjectBooking.ticketsApplication.trips.*;
import org.stepProjectBooking.ticketsApplication.user.Passenger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        TripCreator tripCreator = new TripCreator();
//        Trip trip;
//
//        for (int i = 0; i < 10; i++) {
//            trip =tripCreator.createTrip();
//            System.out.println(trip.prettyFormat());
//        }
       BookingController bookingController = new BookingController();
//        System.out.println();
//
//
//        System.out.println("--------------getAvailableTrips");
//        System.out.println(bookingController.getAvailableTrips(Destinations.PARIS,
//                LocalDate.of(2022,11,18),8));
//        System.out.println(bookingController.getAvailableTrips(Destinations.WASHINGTON,
//                LocalDate.of(2022,11,18),15));
//
//        System.out.println("--------------getBookingById");
//        System.out.println(bookingController.getBookingById(123456));
//        //bookingController.deleteBookingById(123456);
//        System.out.println();
//        System.out.println("--------------getBookingByNameSurname");
//        System.out.println(bookingController.getBookingByNameSurname("Bill","Smith"));
//
//        System.out.println();
//        System.out.println();

//        System.out.println(bookingController.getBookingByNameSurname("Stan","Second"));
//
//        var a =bookingController.getTripInfoById("1");
//
//
//        System.out.println(a);


//        System.out.println(bookingController.getTripInfoById("KW078").getFreePlace());
//
//        Purchaser billSmith = new Purchaser("Bill", "Smith");
//
//        Trip KW078 = new Trip("KW078", LocalTime.of(5, 20), Departures.KYIV, Destinations.WASHINGTON, 15);
//        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50), Departures.KYIV, Destinations.PARIS, 25);
//
//        System.out.println("Номер резерва "+bookingController.createNewBooking(billSmith,KW078,createPassengerList1(),
//                LocalDate.of(2022,11,19)));
//
//        System.out.println(bookingController.getTripInfoById("KW078").getFreePlace());
//        System.out.println("Номер резерва "+bookingController.createNewBooking(billSmith,KW078,createPassengerList1(),
//                LocalDate.of(2022,11,19)));
//
//        var aa = bookingController.getTripInfoById("KW078");
//        System.out.println(aa);
//
//        var a=bookingController.getAvailableTrips(Destinations.WASHINGTON,
//                LocalDate.of(2022,11,19),7);
//        System.out.println(a);

        bookingController.getBookingById(123456);
        bookingController.deleteBookingById(123456);

    }


    public static List<Passenger> createPassengerList1() {
        List<Passenger> passengerList = new ArrayList<>();
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50), Departures.KYIV, Destinations.PARIS, 20);
        passengerList.add(new Passenger("Bill", "Soyer", KP475, 124523));
        passengerList.add(new Passenger("Mike", "Murray", KP475, 124523));
        passengerList.add(new Passenger("Liza", "Trust", KP475, 124523));
        passengerList.add(new Passenger("Indy", "Chart", KP475, 124523));
        return passengerList;
    }
}