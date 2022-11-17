package org.stepProjectBooking.ticketsApplication.DAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Departures;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private List<Booking> bookingList = createBookingList();
    private List<Trip> tripList = createTripList();

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    public static List<Booking> createBookingList(){

        List<Booking> bookingList = new ArrayList<>();

        Purchaser billSmith = new Purchaser("Bill", "Smith");
        Purchaser stanSecond = new Purchaser("Stan", "Second");
        Trip KP475 = new Trip("KP475","10:05", Departures.KYIV, Destinations.PARIS,10);
        Trip KC579 = new Trip("KC579","21:30",Departures.KYIV, Destinations.COLOGNE,10);
        Trip KP931 = new Trip("KP931","00:40",Departures.KYIV, Destinations.PARIS,6);

        bookingList.add( new Booking(billSmith, KP475, 124523,createPassengerList1()));
        bookingList.add( new Booking(stanSecond, KC579, 124527,createPassengerList2()));
        bookingList.add( new Booking(stanSecond, KP931, 124532,createPassengerList3()));
        return bookingList;
    }

    public static List<Trip> createTripList(){
        List<Trip> tripList = new ArrayList<>();
        Trip KP475 = new Trip("KP475","10:05",Departures.KYIV, Destinations.PARIS,10);
        Trip KN045 = new Trip("KN045","04:00",Departures.KYIV, Destinations.NEW_YORK,5);
        Trip KC021 = new Trip("KC021","10:40",Departures.KYIV, Destinations.CHICAGO,5);
        Trip KC579 = new Trip("KC579","21:30",Departures.KYIV, Destinations.COLOGNE,5);
        Trip KP931 = new Trip("KP931","00:40",Departures.KYIV, Destinations.PARIS,6);
        Trip KA094 = new Trip("KA094","04:55",Departures.KYIV, Destinations.AMSTERDAM,5);
        Trip KL699 = new Trip("KL699","21:55",Departures.KYIV, Destinations.LONDON,5);
        Trip KF933 = new Trip("KF933","07:55",Departures.KYIV, Destinations.FRANKFURT,5);
        Trip KW078 = new Trip("KW078","05:20",Departures.KYIV, Destinations.WASHINGTON,5);
        Trip KW366 = new Trip("KW366","17:20",Departures.KYIV, Destinations.WASHINGTON,5);
        tripList.add(KP475);
        tripList.add(KN045);
        tripList.add(KC021);
        tripList.add(KC579);
        tripList.add(KP931);
        tripList.add(KA094);
        tripList.add(KL699);
        tripList.add(KF933);
        tripList.add(KW078);
        tripList.add(KW366);
        return tripList;
    }

    public static List<Passenger> createPassengerList1(){
        List<Passenger> passengerList = new ArrayList<>();
        Trip KP475 = new Trip("KP475","10:05", Departures.KYIV, Destinations.PARIS,10);
        passengerList.add( new Passenger("Bill", "Soyer",KP475,124523));
        passengerList.add( new Passenger("Mike", "Murray",KP475,124523));
        passengerList.add( new Passenger("Liza", "Trust",KP475,124523));
        passengerList.add( new Passenger("Indy", "Chart",KP475,124523));
        return passengerList;
    }
    public static List<Passenger> createPassengerList2(){
        List<Passenger> passengerList = new ArrayList<>();
        Trip KC579 = new Trip("KC579","21:30",Departures.KYIV, Destinations.COLOGNE,10);
        passengerList.add( new Passenger("Tom", "Smith",KC579,124527));
        passengerList.add( new Passenger("Nina", "Smith",KC579,124527));
        passengerList.add( new Passenger("Liza", "Trust",KC579,124527));
        passengerList.add( new Passenger("Indy", "Chart",KC579,124527));
        return passengerList;
    }
    public static List<Passenger> createPassengerList3(){
        List<Passenger> passengerList = new ArrayList<>();
        Trip KP931 = new Trip("KP931","00:40",Departures.KYIV, Destinations.PARIS,6);
        passengerList.add( new Passenger("Tom", "Smith",KP931,124532));
        passengerList.add( new Passenger("Nina", "Smith",KP931,124532));
        passengerList.add( new Passenger("Liza", "Trust",KP931,124532));
        passengerList.add( new Passenger("Indy", "Chart",KP931,124532));
        passengerList.add( new Passenger("Andy", "Smith",KP931,124532));

        return passengerList;
    }
}
