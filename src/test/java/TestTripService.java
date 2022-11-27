import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.CollectionTripDao;
import org.stepProjectBooking.ticketsApplication.trips.Departures;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.testng.Assert;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class TestTripService {

    CollectionTripDao collectionTripDao = new CollectionTripDao();

    @Test
    void getAllTripsTest() {
        List<Trip> tripList = new ArrayList<>();
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50), Departures.KYIV, Destinations.PARIS, 25);
        Trip KP485 = new Trip("KP485", LocalTime.of(19, 50), Departures.KYIV, Destinations.LONDON, 25);
        tripList.add(KP475);
        tripList.add(KP485);
        collectionTripDao.saveAllTrips(tripList);
        Assertions.assertEquals(collectionTripDao.getAllTrips(), tripList);
    }

    @Test
    public void getAllTrips_NO_NULL() {
        List<Trip> tripList = new ArrayList<>();
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50), Departures.KYIV, Destinations.PARIS, 25);
        Trip KP485 = new Trip("KP485", LocalTime.of(19, 50), Departures.KYIV, Destinations.LONDON, 25);
        tripList.add(KP475);
        tripList.add(KP485);
        collectionTripDao.saveAllTrips(tripList);
        List<Trip> expected = collectionTripDao.getAllTrips();
        Assert.assertNotNull(expected);
    }

    @Test
    void saveAllTripsTest() {
        List<Trip> tripList = new ArrayList<>();
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50), Departures.KYIV, Destinations.PARIS, 25);
        Trip KP485 = new Trip("KP485", LocalTime.of(19, 50), Departures.KYIV, Destinations.LONDON, 25);
        tripList.add(KP475);
        tripList.add(KP485);

        Assertions.assertEquals(collectionTripDao.saveAllTrips(tripList), tripList);
    }

    @Test
    public void saveAllTrips_NO_NULL() {
        List<Trip> tripList = new ArrayList<>();
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50), Departures.KYIV, Destinations.PARIS, 25);
        Trip KP485 = new Trip("KP485", LocalTime.of(19, 50), Departures.KYIV, Destinations.LONDON, 25);
        tripList.add(KP475);
        tripList.add(KP485);
        List<Trip> expected = collectionTripDao.saveAllTrips(tripList);
        Assert.assertNotNull(expected);
    }
}
