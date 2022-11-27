package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripController;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionBookingDao {

    private static final String TRIP_BOOKING_LIST_FILE_NAME = "trip_book_list.dat";


    private List<TripBooking> tripBookingList = new ArrayList<>();

    public CollectionBookingDao() {
        List<TripBooking> baseTripBookingList = getTripBookingListFromFile();
        List<TripBooking> tripBookingList = new ArrayList<>();
        List<Trip> tripList = getTripList();
        LocalDate date = LocalDate.now();

        for (Trip trip : tripList) {
            if (trip.getTimeTrip().isBefore(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute()))) {
                date = date.plusDays(1);
            }
            tripBookingList.add(new TripBooking(trip,
                    LocalTime.of(trip.getTimeTrip().getHour(),
                            trip.getTimeTrip().getMinute()).atDate(date)));
            date = LocalDate.now();
        }
        for (TripBooking tripBooking : baseTripBookingList) {
            if (tripBooking.getBookingList().size() > 0 &&
                    tripBooking.getDate().isAfter(LocalDateTime.now())) {
                for (TripBooking newTripBooking : tripBookingList) {
                    if (newTripBooking.getTrip().equals(tripBooking.getTrip()) &&
                            newTripBooking.getDate().equals(tripBooking.getDate())) {
                        tripBookingList.remove(newTripBooking);
                        tripBookingList.add(tripBooking);
                        break;
                    }
                    if (newTripBooking.getTrip().equals(tripBooking.getTrip()) &&
                            newTripBooking.getDate().isAfter(tripBooking.getDate())) {
                        tripBookingList.add(tripBooking);
                        break;
                    }
                }
            }
        }
        this.tripBookingList.addAll(sortByDate(tripBookingList));
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    public void addTripBooking(TripBooking tripBooking) {
        this.tripBookingList.add(tripBooking);
    }

    public void removeTripBooking(TripBooking tripBooking) {
        this.tripBookingList.remove(tripBooking);
    }

    TripController tripController = new TripController();


    public List<TripBooking> sortByDate(List<TripBooking> tripBookingList) {
        return tripBookingList
                .stream()
                .sorted(Comparator.comparing(TripBooking::getDate))
                .collect(Collectors.toList());
    }

    public List<TripBooking> getTripBookingListFromFile() {
        List<TripBooking> tripBookingList = new ArrayList<>();
        boolean count = true;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRIP_BOOKING_LIST_FILE_NAME))) {
            while (count) {
                Object obj = null;
                try {
                    obj = ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (obj != null) tripBookingList.add((TripBooking) obj);
                else count = false;
            }

        } catch (EOFException e) {
            // End of stream
        } catch (IOException e) {
            System.out.println("");
        }
        return tripBookingList;
    }

    public void setTripBookingListToFile(List<TripBooking> tripBookingList) {
        List<TripBooking> tempTripBookingList = new ArrayList<>();
        List<TripBooking> baseTripBookingList = sortByDate(getTripBookingListFromFile());

        tripBookingList = sortByDate(tripBookingList);
        for (TripBooking baseTripBooking : baseTripBookingList) {
            if(baseTripBooking.getDate().isBefore(tripBookingList.get(0).getDate())){ //recording non-actual BookLists
                tempTripBookingList.add(baseTripBooking);
                break;
            }
        }
        tempTripBookingList.addAll(tripBookingList); //recording actual BookLists
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRIP_BOOKING_LIST_FILE_NAME))) {

            for (TripBooking tripBooking : tempTripBookingList) {
                oos.writeObject(tripBooking);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Trip> getTripList() {
        return tripController.downLoadAllTrips();
    }

    public Trip getTripById(String tripId) {
        List<Trip> tripList = getTripList();
        for (Trip trip : tripList) {
            if (trip.getTripId().equals(tripId)) {
                return trip;
            }
        }
        return null;
    }
}