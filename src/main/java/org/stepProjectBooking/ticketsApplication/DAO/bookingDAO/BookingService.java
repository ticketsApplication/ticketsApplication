package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.user.Passenger;

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
        for (Booking booking : bookingList) {
            if (booking.getIdBooking() == id) {
                return booking;
            }
        }
        System.out.println("id not found");
        return null;
    }
    public List<Booking> getBookingByNameSurname(String name, String surname){
        List<Booking> bookingList = collectionBookingDao.getBookingList();
        List<Booking> list=new ArrayList<>();

        for (Booking booking:bookingList){
            if(booking.getPurchaser().getName().equals(name)&&
            booking.getPurchaser().getSurname().equals(surname))
                list.add(booking);
            for (Passenger passenger: booking.getPassengerList()){
                if(passenger.getName().equals(name)&&
                passenger.getSurname().equals(surname))
                    list.add(booking);
            }
        }
        return list;
    }

    public void saveBooking(Booking booking) {
        List<Booking> bookingList = collectionBookingDao.getBookingList();
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getIdBooking() == booking.getIdBooking()) {
                bookingList.set(i, booking);
                collectionBookingDao.setBookingList(bookingList);
                return;
            }
        }
        bookingList.add(booking);
        collectionBookingDao.setBookingList(bookingList);
    }

    public void deleteBookingById(UUID id) {
        List<Booking> bookingList = collectionBookingDao.getBookingList();
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getIdBooking() == id) {
                bookingList.remove(i);
                collectionBookingDao.setBookingList(bookingList);
                return;
            }
        }
        System.out.println("id not found");
    }

    public List<Trip> getAvailableTrips(Destinations destination, /*LocalDateTime date,*/ int passengersNum) {
        List<Trip> list;
        List<Trip> availableTrips = new ArrayList<>();
        List<Trip> tripList = collectionBookingDao.getTripList();
        List<Booking> bookingList = collectionBookingDao.getBookingList();

        list = tripList.stream().filter(s -> (s.getDestination().equals(destination))).toList();


        for (Trip trip : list) {
            int bookedPlaces = 0;
            for (Booking booking : bookingList) {
                if (booking.getTrip().equals(trip))
                    bookedPlaces = bookedPlaces + booking.getPassengerList().size();
            }
            if (trip.getCapacity() - bookedPlaces >= passengersNum)
                availableTrips.add(trip);
        }

        return availableTrips;
    }
}

