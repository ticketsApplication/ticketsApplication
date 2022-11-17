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
import java.util.stream.Collectors;

public class BookingService /*implements BookingDao*/ {

    private final CollectionBookingDao collectionBookingDao = new CollectionBookingDao();

    public List<TripBooking> tripBookingList() {
        return collectionBookingDao.tripBookingList();
    }


//    public List<Booking> getAllBookings() {
//        return new ArrayList<>(collectionBookingDao.getBookingList());
//    }
//
//    public Booking getBookingById(int id) {
//        List<Booking> bookingList = collectionBookingDao.getBookingList();
//        for (Booking booking : bookingList) {
//            if (booking.getIdBooking() == id) {
//                return booking;
//            }
//        }
//        System.out.println("id not found");
//        return null;
//    }
//    public List<Booking> getBookingByNameSurname(String name, String surname){
//        List<Booking> bookingList = collectionBookingDao.getBookingList();
//        List<Booking> list=new ArrayList<>();
//
//        for (Booking booking:bookingList){
//            if(booking.getPurchaser().getName().equals(name)&&
//            booking.getPurchaser().getSurname().equals(surname))
//                list.add(booking);
//            for (Passenger passenger: booking.getPassengerList()){
//                if(passenger.getName().equals(name)&&
//                passenger.getSurname().equals(surname))
//                    list.add(booking);
//            }
//        }
//        return list;
//    }

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

//    public void deleteBookingById(int id) {
//        List<Booking> bookingList = collectionBookingDao.getBookingList();
//        for (int i = 0; i < bookingList.size(); i++) {
//            if (bookingList.get(i).getIdBooking() == id) {
//                bookingList.remove(i);
//                collectionBookingDao.setBookingList(bookingList);
//                return;
//            }
//        }
//        System.out.println("id not found");
//    }

    public List<Trip> getAvailableTrips(Destinations destination, LocalDate data, int passengersNum) {
        List<TripBooking> currentDataTBList = new ArrayList<>();
        List<Trip> tripList = new ArrayList<>();
        List<Trip> availableTrips = new ArrayList<>(collectionBookingDao.getTripList()
                .stream()
                .filter(s -> s.getDestination().equals(destination)).toList());

        tripList.addAll(availableTrips);

        List<TripBooking> tripBookingList = collectionBookingDao.tripBookingList();
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

