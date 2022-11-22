package org.stepProjectBooking.ticketsApplication.DAO.tripDAO;

import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripList;

import java.beans.*;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CollectionTripDao implements TripDao{
    private final List<Trip> tripList;
    private static final String TRIP_LIST_FILE_NAME="trip_list.xml";

    private final TripList tripListCollection = new TripList();
    public CollectionTripDao (){
        this.tripList = downLoadAllTrips().getTrips();
    }
    private TripList downLoadAllTrips () {

        XMLDecoder decoder=null;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(TRIP_LIST_FILE_NAME)));

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File dvd.xml not found");
        }

        TripList tripList = (TripList) decoder.readObject();

        return tripList;

    }

    @Override
    public List<Trip> getAllTrips() {
        return tripList;
    }
    //Method upload all trips wrapped into entity TripList that contains ArrayList of all trips

    @Override
    public void uploadAllTrips(List <Trip> tripList) {
        TripList tripListCollection = new TripList();
        tripListCollection.setTrips(tripList);
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

        xmlEncoder.writeObject(tripListCollection);
        xmlEncoder.close();
    }
}
