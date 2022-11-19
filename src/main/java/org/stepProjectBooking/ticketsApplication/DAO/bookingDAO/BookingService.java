package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.TripBookingList;
import org.stepProjectBooking.ticketsApplication.trips.TripList;

import java.beans.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService  {

    private final CollectionBookingDao collectionBookingDao = new CollectionBookingDao();

    private static final String TRIP_BOOKING_LIST_FILE_NAME="trip_book_list.xml";
    public List<Booking> getAllBookings() {
        return new ArrayList<>(collectionBookingDao.getBookingList());
    }

    public Booking getBookingById(int id) {
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

    public void deleteBookingById(int id) {
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

    public void uploadTripBookingList(TripBookingList tripBookingList) throws FileNotFoundException, IOException {

        XMLEncoder xmlEncoder = null;

        try{
            xmlEncoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(TRIP_BOOKING_LIST_FILE_NAME)));
            xmlEncoder.setPersistenceDelegate(LocalDateTime.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object obj, Encoder encdr) {
                            LocalDateTime localDateTime = (LocalDateTime) obj;
                            return new Expression(localDateTime,
                                    LocalDateTime.class,
                                    "of",
                                    new Object[]{localDateTime.getYear(),localDateTime.getMonth(),localDateTime.getDayOfMonth(),
                                            localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond()});
                        }
                    });

        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File animalPack.xml");
        } catch (IOException e) {
            e.getMessage();
        }

        xmlEncoder.writeObject(tripBookingList);
        xmlEncoder.close();
    }

    public TripBookingList downLoadAllTrips () {

        XMLDecoder decoder=null;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(TRIP_BOOKING_LIST_FILE_NAME)));

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File dvd.xml not found");
        }

        return (TripBookingList) decoder.readObject();

    }


}

