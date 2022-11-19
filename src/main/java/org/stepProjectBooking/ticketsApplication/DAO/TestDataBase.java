package org.stepProjectBooking.ticketsApplication.DAO;

import org.stepProjectBooking.ticketsApplication.trips.*;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestDataBase {

    private List<TripBooking> createTripBooking() {
        List<TripBooking>tripBookingList = new ArrayList<>();

        Trip trip1 = createBookingList1().get(0).getTrip();
        Trip trip2 = createBookingList2().get(0).getTrip();
        Trip trip3 = createBookingList3().get(0).getTrip();

        List<Booking> bookingList1 = createBookingList1();
        List<Booking> bookingList2 = createBookingList2();
        List<Booking> bookingList3 = createBookingList3();

        TripBooking tripBooking1 = new TripBooking(
                LocalDateTime.of(2022,12,25,
                trip1.getTimeTrip().getHour(),trip1.getTimeTrip().getMinute()),
                trip1,bookingList1);
        TripBooking tripBooking2 = new TripBooking(
                LocalDateTime.of(2022,11,18,
                        trip2.getTimeTrip().getHour(),trip2.getTimeTrip().getMinute()),
                trip2,bookingList2);
        TripBooking tripBooking3 = new TripBooking(
                LocalDateTime.of(2022,11,18,
                        trip3.getTimeTrip().getHour(),trip3.getTimeTrip().getMinute()),
                trip3,bookingList3);

        tripBookingList.add(tripBooking1);
        tripBookingList.add(tripBooking2);
        tripBookingList.add(tripBooking3);

        return tripBookingList;
    }
    private List<TripBooking> tripBookingList = createTripBooking();

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    private List<Trip> tripList = createTripList();


    public List<Trip> getTripList() {
        return tripList;
    }


    public static List<Booking> createBookingList1() {

        List<Booking> bookingList = new ArrayList<>();

        Purchaser billSmith = new Purchaser("Bill", "Smith");
        Purchaser stanSecond = new Purchaser("Stan", "Second");
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50, 00), Departures.KYIV, Destinations.PARIS, 25);

        bookingList.add(new Booking(123456,billSmith, KP475, 124523, createPassengerList1()));
        bookingList.add(new Booking(123457,stanSecond, KP475, 124527, createPassengerList2()));
        bookingList.add(new Booking(132458,stanSecond, KP475, 124532, createPassengerList3()));
        return bookingList;
    }

    public static List<Booking> createBookingList2() {

        List<Booking> bookingList = new ArrayList<>();

        Purchaser billSmith = new Purchaser("Bill", "Smith");
        Purchaser stanSecond = new Purchaser("Stan", "Second");
        Trip KC579 = new Trip("KC579", LocalTime.of(21, 30, 00), Departures.KYIV, Destinations.COLOGNE, 10);

        bookingList.add(new Booking(123459,billSmith, KC579, 124557, createPassengerList1()));
        bookingList.add(new Booking(123460,stanSecond, KC579, 124589, createPassengerList2()));
        bookingList.add(new Booking(123461,stanSecond, KC579, 124593, createPassengerList3()));
        return bookingList;
    }

    public static List<Booking> createBookingList3() {

        List<Booking> bookingList = new ArrayList<>();

        Purchaser billSmith = new Purchaser("Bill", "Smith");
        Purchaser stanSecond = new Purchaser("Stan", "Second");
        Trip KP931 = new Trip("KP931", LocalTime.of(0, 40, 00), Departures.KYIV, Destinations.PARIS, 25);

        bookingList.add(new Booking(123462,billSmith, KP931, 124612, createPassengerList1()));
        bookingList.add(new Booking(123463,stanSecond, KP931, 124655, createPassengerList2()));
        bookingList.add(new Booking(123464,stanSecond, KP931, 124689, createPassengerList3()));
        return bookingList;
    }


    public static List<Trip> createTripList() {
        List<Trip> tripList = new ArrayList<>();
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50, 00), Departures.KYIV, Destinations.PARIS, 25);
        Trip KN045 = new Trip("KN045", LocalTime.of(4, 00, 00), Departures.KYIV, Destinations.NEW_YORK, 5);
        Trip KC021 = new Trip("KC021", LocalTime.of(10, 40, 00), Departures.KYIV, Destinations.PARIS, 35);
        Trip KC579 = new Trip("KC579", LocalTime.of(21, 30, 00), Departures.KYIV, Destinations.COLOGNE, 5);
        Trip KP931 = new Trip("KP931", LocalTime.of(0, 40, 00), Departures.KYIV, Destinations.PARIS, 25);
        Trip KA094 = new Trip("KA094", LocalTime.of(4, 55, 00), Departures.KYIV, Destinations.AMSTERDAM, 5);
        Trip KL699 = new Trip("KL699", LocalTime.of(21, 55, 00), Departures.KYIV, Destinations.LONDON, 5);
        Trip KF933 = new Trip("KF933", LocalTime.of(7, 55, 00), Departures.KYIV, Destinations.FRANKFURT, 5);
        Trip KW078 = new Trip("KW078", LocalTime.of(5, 20, 00), Departures.KYIV, Destinations.WASHINGTON, 15);
        Trip KW366 = new Trip("KW366", LocalTime.of(17, 20, 00), Departures.KYIV, Destinations.WASHINGTON, 22);
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

    public static List<Passenger> createPassengerList1() {
        List<Passenger> passengerList = new ArrayList<>();
        Trip KP475 = new Trip("KP475", LocalTime.of(11, 05, 00), Departures.KYIV, Destinations.PARIS, 10);
        passengerList.add(new Passenger("Bill", "Soyer", KP475, 124523));
        passengerList.add(new Passenger("Mike", "Murray", KP475, 124523));
        passengerList.add(new Passenger("Liza", "Trust", KP475, 124523));
        passengerList.add(new Passenger("Indy", "Chart", KP475, 124523));
        return passengerList;
    }

    public static List<Passenger> createPassengerList2() {
        List<Passenger> passengerList = new ArrayList<>();
        Trip KC579 = new Trip("KC579", LocalTime.of(21, 30, 00), Departures.KYIV, Destinations.COLOGNE, 10);
        passengerList.add(new Passenger("Tom", "Smith", KC579, 124527));
        passengerList.add(new Passenger("Nina", "Smith", KC579, 124527));
        passengerList.add(new Passenger("Liza", "Trust", KC579, 124527));
        passengerList.add(new Passenger("Stan", "Second", KC579, 124527));
        return passengerList;
    }

    public static List<Passenger> createPassengerList3() {
        List<Passenger> passengerList = new ArrayList<>();
        Trip KP931 = new Trip("KP931", LocalTime.of(0, 40, 00), Departures.KYIV, Destinations.PARIS, 6);
        passengerList.add(new Passenger("Tom", "Smith", KP931, 124532));
        passengerList.add(new Passenger("Nina", "Smith", KP931, 124532));
        passengerList.add(new Passenger("Liza", "Trust", KP931, 124532));
        passengerList.add(new Passenger("Indy", "Chart", KP931, 124532));
        passengerList.add(new Passenger("Andy", "Smith", KP931, 124532));

        return passengerList;
    }
}
