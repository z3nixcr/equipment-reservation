package com.example.errs.helpers;

import com.example.errs.dao.ReservationDAO;
import com.example.errs.dao.UserDAO;
import com.example.errs.entities.Reservation;
import com.example.errs.entities.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

import static com.example.errs.utils.MySQLConn.close;
import static com.example.errs.utils.MySQLConn.getConnection;

public class ReservationImpl implements ReservationDAO {
    private Connection connection;
    // SQL Queries variables
    private static final String SELECT1 = "SELECT * FROM reservation r " +
            "JOIN user u ON r.id_user = u.id_user " +
            "JOIN equipment e ON r.id_equipment = e.id_equipment";
    private static final String SELECT2 = "SELECT * FROM reservation r " +
            "JOIN user u ON r.id_user = u.id_user " +
            "JOIN equipment e ON r.id_equipment = e.id_equipment " +
            "WHERE r.id_user = ?";
    private static final String INSERT = "INSERT INTO reservation(id_user, id_equipment, quantity, from_date, to_date) " +
            " VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE reservation SET from_date = ?, " +
            "to_date = ? WHERE id_reservation = ?";
    private static final String DELETE = "DELETE FROM reservation WHERE id_reservation = ?";


    // Constructors
    public ReservationImpl() {
    }

    public ReservationImpl(Connection connection) {
        this.connection = connection;
    }

    public Connection createConnection(Connection connection) {
        try {
            return connection != null ? connection : getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reservation> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Reservation> reservations = new ArrayList<>();

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(SELECT1);
            rs = pstm.executeQuery();

            getReservations(rs, reservations);

        } finally {
            assert rs != null;
            close(rs);
            close(pstm);
            if (this.connection == null) {
                close(conn);
            }
        }
        return reservations;
    }

    @Override
    public List<Reservation> findAll(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Reservation> reservations = new ArrayList<>();

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(SELECT2);
            pstm.setInt(1, user.getIdUser());
            rs = pstm.executeQuery();

            getReservations(rs, reservations);

        } finally {
            assert rs != null;
            close(rs);
            close(pstm);
            if (this.connection == null) {
                close(conn);
            }
        }
        return reservations;
    }

    public void getReservations(ResultSet rs, List<Reservation> reservations) throws SQLException {
        while (rs.next()) {
            int idRes = rs.getInt("id_reservation");
            int idUser = rs.getInt("id_user");
            int idEquip = rs.getInt("id_equipment");
            int quantity = rs.getInt("quantity");
            String equipment = rs.getString("name");
            String uName = rs.getString("full_name");
            String email = rs.getString("email");
            String phone = rs.getString("phone_number");
            float price = rs.getFloat("price");
            Date fDate = rs.getDate("from_date");
            Date tDate = rs.getDate("to_date");
            LocalDate date1 = fDate.toLocalDate();
            LocalDate date2 = tDate.toLocalDate();
            float total = ChronoUnit.DAYS.between(date1, date2) * price * quantity;

            Reservation reservation = new Reservation(idRes, idUser, idEquip, equipment,
                    uName, email, phone, total, fDate, tDate);
            reservation.setQuantity(quantity);
            reservation.setPrice(price);

            reservations.add(reservation);
        }
    }

    @Override
    public Reservation findOne(Reservation reservation) throws SQLException {
        List<Reservation> reservations = findAll();

        for (Reservation res : reservations) {
            if (res.getIdReservation() == reservation.getIdReservation())
                return res;
        }
        return null;
    }

    @Override
    public void process(Reservation reservation) throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(INSERT);
            pstm.setInt(1, reservation.getIdUser());
            pstm.setInt(2, reservation.getIdEquipment());
            pstm.setInt(3, reservation.getQuantity());
            pstm.setDate(4, reservation.getFromDate());
            pstm.setDate(5, reservation.getToDate());

            pstm.executeUpdate();
        } finally {
            assert pstm != null;
            close(pstm);
            if (this.connection == null) {
                close(conn);
            }
        }
    }

    @Override
    public void modify(Reservation reservation) throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(UPDATE);
            pstm.setDate(1, reservation.getFromDate());
            pstm.setDate(2, reservation.getToDate());

            pstm.executeUpdate();
        } finally {
            assert pstm != null;
            close(pstm);
            if (this.connection == null) {
                close(conn);
            }
        }
    }

    @Override
    public void delete(Reservation reservation) throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, reservation.getIdReservation());

            pstm.executeUpdate();
        } finally {
            assert pstm != null;
            close(pstm);
            if (this.connection == null) {
                close(conn);
            }
        }
    }
}
