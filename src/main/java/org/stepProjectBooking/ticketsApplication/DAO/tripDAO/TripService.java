package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Destinations;

import java.util.ArrayList;
import java.util.List;

public class TripService implements TripDao {

    private final CollectionTripDao collectionTripDao = new CollectionTripDao();


    @Override
    public <Trip> List getAllTrips() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < collectionTripDao.getCollectionTripDao().size(); i++) {
            list.add(String.valueOf(collectionTripDao.getCollectionTripDao().get(i)));
        }
        return list;
    }

    @Override
    public List<String> displayAllTrips() {
        List<String> list = new ArrayList<>();
        list.add("information about all flight from Kyiv in the next 24 hours: \n");
        collectionTripDao.getCollectionTripDao()
                .stream()
                .map(x -> list.add("\n" + x))
                .toList();
        System.out.println(list);
        return list;
    }

    @Override
    public List<String> getTripsById(String id) {
        System.out.println("Flight information: ");
        List<String> tripsById = new ArrayList<>();
        collectionTripDao.getCollectionTripDao()
                .stream()
                .filter(x -> x.getTripId().equals(id))
                .map(x -> tripsById.add("\n" + x))
                .toList();
        System.out.println(tripsById);
        return tripsById;
    }


    public List<String> getTripsByDestinationNumPassenger(Destinations destinations, int numPassenger) {
        System.out.println("Flight information: ");
        List<String> tripsByDestinationDateNumPassenger = new ArrayList<>();
        collectionTripDao.getCollectionTripDao()
                .stream()
                .filter(x -> x.getDestination().equals(destinations)
//                        && x.getDate.equals(date)           // По условию нужно сортировать по дате
                        && x.getCapacity() == (numPassenger) )
                .map(x -> tripsByDestinationDateNumPassenger.add("\n" + x))
                .toList();
        System.out.println(tripsByDestinationDateNumPassenger);
        return tripsByDestinationDateNumPassenger;
    }
}

