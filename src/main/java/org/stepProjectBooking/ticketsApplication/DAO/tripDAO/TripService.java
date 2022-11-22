package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripList;

import java.beans.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TripService {

    private final CollectionTripDao collectionTripDao = new CollectionTripDao();

    private static final String TRIP_LIST_FILE_NAME="trip_list.xml";

    public List <Trip> downLoadAllTrips() {
        return collectionTripDao.getAllTrips();
    }

    public void uploadAllTrips(List<Trip> trips) {
        collectionTripDao.uploadAllTrips(trips);
    }
}





