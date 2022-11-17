package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;
import org.stepProjectBooking.ticketsApplication.user.Passenger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService /*implements BookingDao*/ {

    private final CollectionBookingDao collectionBookingDao = new CollectionBookingDao();

    public List<TripBooking> tripBookingList() {
        return collectionBookingDao.getTripBookingList();
    }

   public Booking getBookingById(int id) {
    List<TripBooking> tripBookingList = collectionBookingDao.getTripBookingList();
        for (TripBooking tripBooking : tripBookingList) {
        for (Booking booking : tripBooking.getBookingList()) {
            if (booking.getIdBooking() == id) {
                return booking;
            }
        }
    }
        System.out.println("id not found");
        return null;
}
    public void deleteBookingById(int id) {
        List<TripBooking> tripBookingList = collectionBookingDao.getTripBookingList();
        for (TripBooking tripBooking : tripBookingList) {
            for (Booking booking : tripBooking.getBookingList()) {
                if (booking.getIdBooking() == id && tripBooking.getDate().isAfter(LocalDateTime.now())) {
                    tripBooking.getBookingList().remove(booking);
                    collectionBookingDao.setTripBookingList(tripBookingList);
                    return;
                }
            }
        }
        System.out.println("id not found");
    }

    public List<Booking> getBookingByNameSurname(String name, String surname) {

        List<Booking> list = new ArrayList<>();
        List<TripBooking> tripBookingList = collectionBookingDao.getTripBookingList();

        for (TripBooking tripBooking : tripBookingList) {
            for (Booking booking : tripBooking.getBookingList()) {
                if (booking.getPurchaser().getName().equals(name) &&
                        booking.getPurchaser().getSurname().equals(surname)){
                    list.add(booking);
                }
                for (Passenger passenger : booking.getPassengerList()) {
                    if (passenger.getName().equals(name) &&
                            passenger.getSurname().equals(surname))
                        if(!list.contains(booking))list.add(booking);
                }
            }
        }
        return list;
    }

//    public void saveBooking(Booking booking) {
//        List<Booking> bookingList = collectionBookingDao.getBookingList();
//        for (int i = 0; i < bookingList.size(); i++) {
//            if (bookingList.get(i).getIdBooking() == booking.getIdBooking()) {
//                bookingList.set(i, booking);
//                collectionBookingDao.setBookingList(bookingList);
//                return;
//            }
//        }
//        bookingList.add(booking);
//        collectionBookingDao.setBookingList(bookingList);

//    }

    public List<Trip> getAvailableTrips(Destinations destination, LocalDate data, int passengersNum) {
        List<TripBooking> currentDataTBList = new ArrayList<>();
        List<Trip> availableTrips = new ArrayList<>(collectionBookingDao.getTripList()
                .stream()
                .filter(s -> s.getDestination().equals(destination)).toList());

        List<Trip> tripList = new ArrayList<>(availableTrips);

        List<TripBooking> tripBookingList = collectionBookingDao.getTripBookingList();
        for (TripBooking tripBooking : tripBookingList) {
            if (tripBooking.getDate().getDayOfYear() == data.getDayOfYear()) {
                currentDataTBList.add(tripBooking);
            }
        }
        for (Trip trip : tripList) {
            for (TripBooking tripBooking : currentDataTBList) {
                if (tripBooking.getTrip().equals(trip) && (tripBooking.getFreePlace() < passengersNum)) {
                    availableTrips.remove(trip);
                }
            }
        }
        availableTrips = availableTrips.stream()
                .filter(s -> s.getCapacity() >= passengersNum).toList();

        return availableTrips;
    }
}