package org.stepProjectBooking;

import org.stepProjectBooking.ticketsApplication.ConsoleMenuUser;
import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripController;
import org.stepProjectBooking.ticketsApplication.trips.Departures;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripCreator;
import org.stepProjectBooking.ticketsApplication.trips.TripList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args)  {

        List<Trip> trips=  new ArrayList<>();
        TripList tripList = new TripList();

        TripCreator tripCreator = new TripCreator();
        Trip trip;

//        for (int i = 0; i < 30; i++) {
//            trip = tripCreator.createTrip();
//            trips.add(trip);
//            System.out.println(trip.prettyFormat());
//        }

        tripList.setTrips(trips);

        System.out.println("________________");


        ConsoleMenuUser consoleMenuUser = new ConsoleMenuUser();

        consoleMenuUser.run();

        TripController tripController = new TripController();

        try {
            tripController.uploadTripList(tripList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        tripList = tripController.downLoadAllTrips();
//
//        trips = tripList.getTrips();
//
//        trips.stream().forEach(trip1 -> System.out.println(trip1.prettyFormat()));


    }
}