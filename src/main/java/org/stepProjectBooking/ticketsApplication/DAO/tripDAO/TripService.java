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

public class TripService implements TripDao {

    private final CollectionTripDao collectionTripDao = new CollectionTripDao();

    private static final String TRIP_LIST_FILE_NAME="trip_list.xml";


    @Override
    public List <Trip>  getAllTrips() {
        List<Trip> list = new ArrayList<>();

        return collectionTripDao.getTripList();
    }

    @Override
    public List<String> displayAllTrips() {
        List<String> list = new ArrayList<>();
        list.add("information about all flight from Kyiv in the next 24 hours: \n");
        collectionTripDao.getTripList()
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
        collectionTripDao.getTripList()
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
        collectionTripDao.getTripList()
                .stream()
                .filter(x -> x.getDestination().equals(destinations)
//                        && x.getDate.equals(date)           // По условию нужно сортировать по дате
                        && x.getCapacity() == (numPassenger) )
                .map(x -> tripsByDestinationDateNumPassenger.add("\n" + x))
                .toList();
        System.out.println(tripsByDestinationDateNumPassenger);
        return tripsByDestinationDateNumPassenger;
    }

    /*Method upload to the trip_list.xml all the current trips */

    public void uploadTripList(TripList tripList) throws FileNotFoundException, IOException {

        XMLEncoder xmlEncoder = null;

        try{
            xmlEncoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(TRIP_LIST_FILE_NAME)));
            xmlEncoder.setPersistenceDelegate(LocalTime.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object obj, Encoder encdr) {
                            LocalTime localTime = (LocalTime) obj;
                            return new Expression(localTime,
                                    LocalTime.class,
                                    "of",
                                    new Object[]{localTime.getHour(), localTime.getMinute(), localTime.getSecond()});
                        }
                    });

        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File animalPack.xml");
        } catch (IOException e) {
            e.getMessage();
        }

        xmlEncoder.writeObject(tripList);
        xmlEncoder.close();
    }

    public TripList downLoadAllTrips () {

        XMLDecoder decoder=null;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(TRIP_LIST_FILE_NAME)));

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File dvd.xml not found");
        }

        TripList tripList = (TripList) decoder.readObject();

        return tripList;

    }

    }





