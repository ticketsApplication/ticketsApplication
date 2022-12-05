package org.stepProjectBooking.ticketsApplication.trips;

import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Booking implements Serializable {


    private int idBooking;

    private Purchaser purchaser;
    private Trip trip;
    private List<Passenger> passengerList;

    public Booking(int idBooking, Purchaser purchaser, Trip trip, List<Passenger> passengerList) {
        this.idBooking = idBooking;
        this.purchaser = purchaser;
        this.trip = trip;
        this.passengerList = passengerList;
    }

    public Booking(Purchaser purchaser, Trip trip, List<Passenger> passengerList) {

        this.idBooking = generateUniqueId();

        this.purchaser = purchaser;
        this.trip = trip;
        this.passengerList = passengerList;
    }


    public Booking() {
    }

    private int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }


    public int getIdBooking() {

        return idBooking;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public Trip getTrip() {
        return trip;
    }


    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "\nidBooking=" + idBooking +
                ", \npurchaser=" + purchaser +
                ", \ntrip=" + trip +
                ", \npassengerList=" + passengerList +
                '}';
    }
}