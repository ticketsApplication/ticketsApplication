package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService implements BookingDao {

    private final CollectionBookingDao collectionBookingDao = new CollectionBookingDao();

    public List<Booking> getAllBookings() {
        return new ArrayList<>(collectionBookingDao.getBookingList());
    }

    public Booking getBookingById(UUID id) {
        List<Booking> bookingList = collectionBookingDao.getBookingList();
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getIdBooking() == id) {  //когда будет bookingId
                return bookingList.get(i);
            }
        }
        System.out.println("id not found");
        return null;
    }

    public void saveBooking(Booking booking) {
        List<Booking> bookingList = collectionBookingDao.getBookingList();
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getIdBooking() == booking.getIdBooking()) {  //когда будет bookingId
                bookingList.set(i, booking);
                return;
            }
        }
        bookingList.add(booking);
    }

    public void deleteBookingById(UUID id) {
        List<Booking> bookingList = collectionBookingDao.getBookingList();
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getIdBooking() == id) {  //когда будет bookingId
                bookingList.remove(i);
                collectionBookingDao.setBookingList(bookingList);
                return;
            }
        }
        System.out.println("id not found");
    }

    public Booking createBooking()
}
