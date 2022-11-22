package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripController;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionBookingDao {

    private List<TripBooking> tripBookingList = new ArrayList<>();

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    public CollectionBookingDao(){
        this.tripBookingList = getTripBookingListFromFile();
    }

    public void addTripBooking(TripBooking tripBooking){
        this.tripBookingList.add(tripBooking);
    }
    public void removeTripBooking(TripBooking tripBooking){
        this.tripBookingList.remove(tripBooking);
    }



    TripController tripController = new TripController();

    private static final String TRIP_BOOKING_LIST_FILE_NAME = "trip_book_list.xml";
    public List<TripBooking> getTripBookingListFromFile(){
        List<TripBooking> tripBookingList = new ArrayList<>();
        boolean count = true;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRIP_BOOKING_LIST_FILE_NAME))) {
            while (count) {
                Object obj = null;
                try {
                    obj = ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (obj != null) tripBookingList.add((TripBooking) obj);
                else count = false;
            }

        } catch (EOFException e) {
            // End of stream
        } catch (IOException e) {
            System.out.println("");
        }
        return tripBookingList;
        //return dataBase.getTripBookingList();
    }

    public void setTripBookingListToFile(List<TripBooking> tripBookingList){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRIP_BOOKING_LIST_FILE_NAME))) {

            for (TripBooking tripBooking :tripBookingList) {
                oos.writeObject(tripBooking);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public  List<Trip> getTripList(){
        return tripController.getTripList().getTrips();

    }
    public Trip getTripById(String tripId){
        List <Trip> tripList = getTripList();
        for(Trip trip:tripList){
            if(trip.getTripId().equals(tripId)){
                return trip;
            }
        }
        return null;
    }
}
