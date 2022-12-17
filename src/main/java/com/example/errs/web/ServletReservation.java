package com.example.errs.web;

import com.example.errs.dao.EquipmentDAO;
import com.example.errs.dao.ReservationDAO;
import com.example.errs.entities.Equipment;
import com.example.errs.entities.Reservation;
import com.example.errs.entities.User;
import com.example.errs.helpers.EquipmentImpl;
import com.example.errs.helpers.ReservationImpl;
import com.example.errs.utils.MySQLConn;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletReservation", value = "/ServletReservation")
public class ServletReservation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "display":
                displayReservations(request, response);
                break;

            case "edit":
                goToEditItemRented(request, response);
                break;

            case "delete":
                deleteItemRented(request, response);
                break;

            case "main":
                request.getRequestDispatcher("pages/users/main_page.jsp").forward(request, response);
                break;
        }
    }

    private void goToEditItemRented(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Connection conn = null;
        try {
            conn = MySQLConn.getConnection();
            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            EquipmentDAO equipmentDAO = new EquipmentImpl(conn);
            Reservation item = new ReservationImpl().findOne(new Reservation(id));
            Equipment e = equipmentDAO.findOne(new Equipment(item.getIdEquipment()));
            e.setInStock(e.getInStock() + item.getQuantity());
            equipmentDAO.update(e);

            session.setAttribute("item", item);
            session.setAttribute("equipment", e);
            request.getRequestDispatcher("pages/users/edit.jsp").forward(request, response);
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItemRented(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Connection connection = null;

        try {
            connection = MySQLConn.getConnection();
            if (connection.getAutoCommit())
                connection.setAutoCommit(false);
            ReservationDAO reservationDAO = new ReservationImpl(connection);
            EquipmentDAO equipmentDAO = new EquipmentImpl(connection);
            Reservation res = reservationDAO.findOne(new Reservation(id));
            reservationDAO.delete(res);
            Equipment item = equipmentDAO.findOne(new Equipment(res.getIdEquipment()));
            item.setInStock(item.getInStock() + res.getQuantity());
            item.setAvailability(true);
            prepareDao(session, user, connection, reservationDAO, equipmentDAO, item);
            request.getRequestDispatcher("pages/users/reservation.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    private void prepareDao(HttpSession session, User user, Connection connection, ReservationDAO reservationDAO, EquipmentDAO equipmentDAO, Equipment item) throws SQLException {
        equipmentDAO.update(item);
        connection.commit();

        List<Reservation> items = reservationDAO.findAll(user);
        float total = 0;
        for (Reservation r : items) {
            total += r.getTotalPrice();
        }
        session.setAttribute("items", items);
        session.setAttribute("total", total);
        List<Equipment> equipmentList = equipmentDAO.findAll();
        session.setAttribute("equipmentList", equipmentList);
    }

    private void displayReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try {
            List<Reservation> items = new ReservationImpl().findAll(user);
            float total = 0;
            for (Reservation res : items) {
                total += res.getTotalPrice();
            }
            session.setAttribute("items", items);
            session.setAttribute("total", total);
            request.getRequestDispatcher("pages/users/reservation.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "reserve":
                makeReservation(request, response);
                break;

            case "edit":
                editItemRented(request, response);
                break;
        }
    }

    private void editItemRented(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date fDate = Date.valueOf(request.getParameter("fromDate"));
        Date tDate = Date.valueOf(request.getParameter("toDate"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Reservation item = (Reservation) session.getAttribute("item");
        Connection conn = null;

        try {
            conn = MySQLConn.getConnection();
            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            ReservationDAO reservationDAO = new ReservationImpl(conn);
            EquipmentDAO equipmentDAO = new EquipmentImpl(conn);
            item.setQuantity(quantity);
            item.setFromDate(fDate);
            item.setToDate(tDate);
            reservationDAO.modify(item);
            Equipment e = equipmentDAO.findOne(new Equipment(item.getIdEquipment()));
            e.setInStock(e.getInStock() - quantity);
            if (e.getInStock() <= 0) {
                e.setInStock(0);
                e.setAvailability(false);
            }
            prepareDao(session, user, conn, reservationDAO, equipmentDAO, e);
            response.sendRedirect("pages/users/reservation.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    private void makeReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date fDate = Date.valueOf(request.getParameter("fromDate"));
        Date tDate = Date.valueOf(request.getParameter("toDate"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Equipment item = (Equipment) session.getAttribute("item");
        Connection conn = null;
        String error_reserve = "";

        try {
            conn = MySQLConn.getConnection();
            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            ReservationDAO reservationDAO = new ReservationImpl(conn);
            EquipmentDAO equipmentDAO = new EquipmentImpl(conn);
            if (!item.isAvailability()) {
                error_reserve = "This item is not available at this moment";
                session.setAttribute("error_reserve", error_reserve);
                response.sendRedirect("pages/users/add.jsp");
            } else {
                session.setAttribute("error_reserve", error_reserve);
                Reservation reservation = new Reservation(user.getIdUser(), item.getIdEquipment(), quantity, fDate, tDate);
                reservationDAO.process(reservation);
                item.setInStock(item.getInStock() - quantity);
                if (item.getInStock() <= 0) {
                    item.setAvailability(false);
                    item.setInStock(0);
                }
                prepareDao(session, user, conn, reservationDAO, equipmentDAO, item);
                request.getRequestDispatcher("pages/users/main_page.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }
}
