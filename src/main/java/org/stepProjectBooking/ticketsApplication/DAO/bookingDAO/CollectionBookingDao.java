package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.DAO.DataBase;
import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Trip;

import java.util.List;

public class CollectionBookingDao {
    DataBase dataBase = new DataBase();

    public List<Booking> getBookingList() {
        return dataBase.getBookingList();
    }

    public void setBookingList(List<Booking> bookingList) {
        dataBase.setBookingList(bookingList);
    }

    public  List<Trip> getTripList(){
        return dataBase.getTripList();
    }
}
