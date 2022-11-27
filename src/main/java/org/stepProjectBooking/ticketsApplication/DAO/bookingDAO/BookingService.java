package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;
import org.stepProjectBooking.ticketsApplication.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class BookingService implements BookingDao {
    private final CollectionBookingDao collectionBookingDao = new CollectionBookingDao();

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

    public boolean deleteBookingById(int id) {
        List<TripBooking> tripBookingList = collectionBookingDao.getTripBookingList();
        for (TripBooking tripBooking : tripBookingList) {
            for (Booking booking : tripBooking.getBookingList()) {
                if (booking.getIdBooking() == id && tripBooking.getDate().isAfter(LocalDateTime.now())) {
                    tripBooking.getBookingList().remove(booking);
                    collectionBookingDao.setTripBookingList(tripBookingList);
                    return true;
                }
            }
        }
        return false;
    }
    public List<Booking> getBookingByUser(User user) {
        List<Booking> list = new ArrayList<>();
        List<TripBooking> tripBookingList = collectionBookingDao.getTripBookingList();

        for (TripBooking tripBooking : tripBookingList) {
            for (Booking booking : tripBooking.getBookingList()) {
                if (booking.getPurchaser().getName().equals(user.getName()) &&
                        booking.getPurchaser().getSurname().equals(user.getSurname())) {
                    list.add(booking);
                }
                for (Passenger passenger : booking.getPassengerList()) {
                    if (passenger.getName().equals(user.getName()) &&
                            passenger.getSurname().equals(user.getSurname()))
                        if (!list.contains(booking)) list.add(booking);
                }
            }
        }
        return list;
    }

    public TripBooking getTripBookingByTripIdData(String tripId, LocalDate date) {
        List<TripBooking> tripBookingList = collectionBookingDao.getTripBookingList();
        for (TripBooking tripBooking : tripBookingList) {
            if (tripBooking.getTrip().getTripId().equals(tripId) &&
                    tripBooking.getDate().getDayOfYear() == date.getDayOfYear() &&
                    tripBooking.getDate().isAfter(date.atStartOfDay())) {
                return tripBooking;
            }
        }
        return null;
    }

    public TripBooking getTripInfoById(String tripId) {
        Trip trip = collectionBookingDao.getTripById(tripId);
        LocalDate date = LocalDate.now();
        LocalDateTime f = trip.getTimeTrip().atDate(LocalDate.now());
        LocalDateTime n = LocalDateTime.now();
        if (f.isBefore(n)) {
            date = date.plusDays(1);
        }

        if (collectionBookingDao.getTripById(tripId) == null) return null;
        if (getTripBookingByTripIdData(tripId, date) == null) {
            return new TripBooking(collectionBookingDao.getTripById(tripId), LocalDateTime.now());
        }
        return getTripBookingByTripIdData(tripId, date);
    }

    public List<Trip> getAvailableTrips(Destinations destination, LocalDate data, int passengersNum) {
        if (data.isBefore(LocalDate.now())) return new ArrayList<>();
        List<TripBooking> currentDataTBList = new ArrayList<>();
        List<Trip> availableTrips = new ArrayList<>();

        List<TripBooking> tripBookingList = collectionBookingDao.getTripBookingList();
        for (TripBooking tripBooking : tripBookingList) {
            if (tripBooking.getDate().getDayOfYear() == data.getDayOfYear() &&
                    tripBooking.getTrip().getDestination().equals(destination)) {
                currentDataTBList.add(tripBooking);
            }
        }
        for (TripBooking tripBooking : currentDataTBList) {
            if (
                    tripBooking.getFreePlace() >= passengersNum)
                availableTrips.add(tripBooking.getTrip());
        }
        availableTrips = availableTrips.stream()
                .filter(s -> s.getCapacity() >= passengersNum).toList();

        return availableTrips;
    }

    public int createNewBooking(Purchaser purchaser, Trip
            trip, List<Passenger> passengerList, LocalDate
                                        date) {
        Booking booking = new Booking(purchaser, trip, passengerList);
        TripBooking tripBooking = getTripBookingByTripIdData(trip.getTripId(), date);

        if (tripBooking == null) {
            tripBooking = new TripBooking(date.atTime(trip.getTimeTrip()), trip, new ArrayList<>());
            tripBooking.getBookingList().add(booking);
            collectionBookingDao.addTripBooking(tripBooking);
            return booking.getIdBooking();
        }
        collectionBookingDao.removeTripBooking(tripBooking);
        tripBooking.getBookingList().add(booking);
        collectionBookingDao.addTripBooking(tripBooking);
        for(Passenger passenger:passengerList){
            passenger.setBookId(booking.getIdBooking());
            passenger.setTripId(booking.getTrip());
        }
        return booking.getIdBooking();
    }

    public void saveTripBookingList(List<TripBooking> tripBookingList){
        collectionBookingDao.setTripBookingListToFile(tripBookingList);
    }
    public List<TripBooking> getTripBookingList(){
        return collectionBookingDao.getTripBookingList();
    }
}