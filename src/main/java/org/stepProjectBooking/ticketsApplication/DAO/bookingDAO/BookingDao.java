package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;

import java.util.List;

public interface BookingDao {

    /**Возвращает соллекцию со всеми бронями */
    List<Booking> getAllBookings();

    /**Возвращает бронь по id*/
    Booking getBookingById(int id);

    /**Сохраняет бронь*/
    void saveBooking (Booking booking);

    /**Удаляет бронь по id*/
    void deleteBookingById(int id);








}
