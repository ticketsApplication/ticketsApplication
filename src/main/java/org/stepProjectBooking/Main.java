package org.stepProjectBooking;

import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripController;
import org.stepProjectBooking.ticketsApplication.trips.Departures;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripCreator;
import org.stepProjectBooking.ticketsApplication.user.User;

public class Main {
    public static void main(String[] args) {

        TripCreator tripCreator = new TripCreator();
        Trip trip;

        for (int i = 0; i < 10; i++) {
            trip =tripCreator.createTrip();
            System.out.println(trip.prettyFormat());
        }

        TripController tripController = new TripController();



    }
}