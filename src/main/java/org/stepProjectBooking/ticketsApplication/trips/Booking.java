package org.stepProjectBooking.ticketsApplication.trips;

import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.util.List;
import java.util.UUID;

public class Booking {

    private UUID idBooking;
    private Purchaser purchaser;
    private Trip trip;
    private int reserveNum;
    private List<Passenger> passengerList;

    public Booking(Purchaser purchaser, Trip trip, int reserveNum, List<Passenger> passengerList) {
        this.idBooking = UUID.randomUUID();
        this.purchaser = purchaser;
        this.trip = trip;
        this.reserveNum = reserveNum;
        this.passengerList = passengerList;
    }

    public void setReserveNum(int reserveNum) {
        this.reserveNum = reserveNum;
    }

    public UUID getIdBooking() {
        return idBooking;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public Trip getTrip() {
        return trip;
    }

    public int getReserveNum() {
        return reserveNum;
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
                ", \nreserveNum=" + reserveNum +
                ", \npassengerList=" + passengerList +
                '}';
    }
}
