package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.List;

public class TripService {

    private final CollectionTripDao collectionTripDao = new CollectionTripDao();

    public List <Trip> downLoadAllTrips() {return collectionTripDao.getAllTrips();
    }

    public void uploadAllTrips(List<Trip> trips) {
        collectionTripDao.saveAllTrips(trips);
    }

}





