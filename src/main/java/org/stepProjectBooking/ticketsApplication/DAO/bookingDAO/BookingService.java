package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService /*implements BookingDao*/ {

    private final CollectionBookingDao collectionBookingDao = new CollectionBookingDao();

    private List<TripBooking> getTripBookingList() {
        return collectionBookingDao.getTripBookingList();
    }

    public Booking getBookingById(int id) {
        List<TripBooking> tripBookingList = getTripBookingList();
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
        List<TripBooking> tripBookingList = getTripBookingList();
        for (TripBooking tripBooking : tripBookingList) {
            for (Booking booking : tripBooking.getBookingList()) {
                if (booking.getIdBooking() == id && tripBooking.getDate().isAfter(LocalDateTime.now())) {
                    tripBooking.getBookingList().remove(booking);
                    collectionBookingDao.setTripBookingList(tripBookingList);
                    return true;
                }
            }
        }
        System.out.println("id not found");
        return false;
    }

    public List<Booking> getBookingByNameSurname(String name, String surname) {

        List<Booking> list = new ArrayList<>();
        List<TripBooking> tripBookingList = getTripBookingList();

        for (TripBooking tripBooking : tripBookingList) {
            for (Booking booking : tripBooking.getBookingList()) {
                if (booking.getPurchaser().getName().equals(name) &&
                        booking.getPurchaser().getSurname().equals(surname)) {
                    list.add(booking);
                }
                for (Passenger passenger : booking.getPassengerList()) {
                    if (passenger.getName().equals(name) &&
                            passenger.getSurname().equals(surname))
                        if (!list.contains(booking)) list.add(booking);
                }
            }
        }
        return list;
    }

    public TripBooking getTripBookingByTripIdData(String tripId, LocalDate date) {
        List<TripBooking> tripBookingList = getTripBookingList();
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

        if (collectionBookingDao.getTripById(tripId) == null) return null;
        if (getTripBookingByTripIdData(tripId, LocalDate.now()) == null) {
            return new TripBooking(collectionBookingDao.getTripById(tripId), LocalDateTime.now());
        }
        return getTripBookingByTripIdData(tripId, LocalDate.now());
    }

    public List<Trip> getAvailableTrips(Destinations destination, LocalDate data, int passengersNum) {
        if (data.isBefore(LocalDate.now())) return new ArrayList<>();
        List<TripBooking> currentDataTBList = new ArrayList<>();
        List<Trip> availableTrips = new ArrayList<>(collectionBookingDao.getTripList()
                .stream()
                .filter(s -> s.getDestination().equals(destination)).toList());

        List<Trip> tripList = new ArrayList<>(availableTrips);

        List<TripBooking> tripBookingList = getTripBookingList();
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

    public int createNewBooking(Purchaser purchaser, Trip trip, List<Passenger> passengerList, LocalDate date) {
        Booking booking = new Booking(purchaser, trip, 0, passengerList);

        if (trip.getTimeTrip().isBefore(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute()))) {
            date = date.plusDays(1);
        }

        TripBooking tripBooking = getTripBookingByTripIdData(trip.getTripId(), date);

        if (tripBooking == null) {
            tripBooking = new TripBooking(date.atTime(trip.getTimeTrip()), trip, new ArrayList<>());
            tripBooking.getBookingList().add(booking);
            getTripBookingList().add(tripBooking);

            return booking.getIdBooking();
        }
        getTripBookingList().remove(tripBooking);
        tripBooking.getBookingList().add(booking);
        getTripBookingList().add(tripBooking);
        return booking.getIdBooking();
    }

    public void saveTripBookingList(List<TripBooking> tripBookingList) {
        collectionBookingDao.setTripBookingList(tripBookingList);
    }
}