package com.example.errs.dao;

import com.example.errs.entities.Reservation;
import com.example.errs.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO {
    // Find all reservations
    public List<Reservation> findAll() throws SQLException;
    // Find all reservations made by a user
    public List<Reservation> findAll(User user) throws SQLException;
    // Find a reservation by given ID
    public Reservation findOne(Reservation reservation) throws SQLException;
    // Process a reservation
    public void process(Reservation reservation) throws SQLException;
    // Modify a reservation
    public void modify(Reservation reservation) throws SQLException;
    // Delete a reservation
    public void delete(Reservation reservation) throws SQLException;
}
