package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;


import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripList;

import java.io.IOException;
import java.util.List;

public class TripController {

    private final TripService tripService;

    public TripController() {
        this.tripService = new TripService();
    }

    public List <Trip> downLoadAllTrips () {
        return tripService.downLoadAllTrips();
    }

    public void uploadAllTrips (List <Trip> trips) {

        tripService.uploadAllTrips(trips);

    }





}
