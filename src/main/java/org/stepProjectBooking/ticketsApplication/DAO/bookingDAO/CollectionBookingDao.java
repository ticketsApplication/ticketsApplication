package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.DAO.DataBase;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;

import java.util.List;

public class CollectionBookingDao {
    DataBase dataBase = new DataBase();
    public List<TripBooking> getTripBookingList(){
        return dataBase.getTripBookingList();
    }
    public void setTripBookingList(List<TripBooking> tripBookingList){
        dataBase.setTripBookingList(tripBookingList);
    }

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
}
