package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;

import java.util.ArrayList;
import java.util.List;

public class CollectionBookingDao {

    private List<Booking> bookingList=new ArrayList<>(); //BookingDatabase

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
