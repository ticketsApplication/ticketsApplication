package org.stepProjectBooking.ticketsApplication.trips;

import java.util.Objects;

public class Trip {
    private String tripId;
    private String timeTrip;
    private Departures departure;
    private Destinations destination;
    private int capacity;
    @Override
    public String toString() {
        return "Trip{" +
                "tripId='" + tripId + '\'' +
                ", timeTrip='" + timeTrip + '\'' +
                ", departure=" + departure +
                ", destination=" + destination +
                ", capacity=" + capacity +
                '}';
    }

    public String prettyFormat () {
        return String.format(" %5s  %s - %-10s  %s %s",  tripId, departure, destination, "\uD83D\uDEEB", timeTrip );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(tripId, trip.tripId) && Objects.equals(timeTrip, trip.timeTrip) && departure == trip.departure && destination == trip.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, timeTrip, departure, destination);
    }

    public Trip(String tripId, String timeTrip, Departures departure, Destinations destination, int capacity) {
        this.tripId = tripId;
        this.timeTrip = timeTrip;
        this.departure = departure;
        this.destination = destination;
        this.capacity = capacity;
    }

    public String getTimeTrip() {
        return timeTrip;
    }

    public void setTimeTrip(String timeTrip) {
        this.timeTrip = timeTrip;
    }

    public Departures getDeparture() {
        return departure;
    }

    public void setDeparture(Departures departure) {
        this.departure = departure;
    }

    public Destinations getDestination() {
        return destination;
    }

    public void setDestination(Destinations destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }


}
