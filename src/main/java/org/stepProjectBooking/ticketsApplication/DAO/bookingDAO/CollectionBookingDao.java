package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.DAO.TestDataBase;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;

import java.util.List;

public class CollectionBookingDao {
    TestDataBase dataBase = new TestDataBase();
    public List<TripBooking> getTripBookingList(){
        return dataBase.getTripBookingList();
    }
    public void setTripBookingList(List<TripBooking> tripBookingList){
        dataBase.setTripBookingList(tripBookingList);
    }
//public class CollectionBookingDao{

//    public List<Booking> getBookingList() {
//        return dataBase.getBookingList();
//    }

//
//    public void setBookingList(List<Booking> bookingList) {
//        dataBase.setBookingList(bookingList);
//    }

    public  List<Trip> getTripList(){
        return dataBase.getTripList();
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
