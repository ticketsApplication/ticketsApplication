package org.stepProjectBooking;

import org.stepProjectBooking.ticketsApplication.trips.Departures;

public class Main {
    public static void main(String[] args) {

        TripCreator tripCreator = new TripCreator();
        Trip trip;

        for (int i = 0; i < 10; i++) {
            trip =tripCreator.createTrip();
            System.out.println(trip.prettyFormat());
        }

    }
}