package org.stepProjectBooking.ticketsApplication.trips;

public enum Destinations {

    PARIS (1),
    LONDON (2),
    BRUSSELS (3),
    CHICAGO (4),
    BERLIN (5),
    TOKYO (6),
    AMSTERDAM (7),
    WARSAW (8),
    BUCHAREST (9),
    PRAGUE (10),
    BRATISLAVA (11),
    COLOGNE (12),
    FRANKFURT (13),
    WASHINGTON (14),
    NEW_YORK  (15),
    LISBON(16);



    int nameIndex = 0;

    Destinations(int index) {
        this.nameIndex = index;
    }

    public static Destinations getName(int nameIndex) {
        return Destinations.values()[nameIndex];
    }

}
