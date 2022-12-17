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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "reserve":
                makeReservation(request, response);
                break;
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
                conn.commit();
                item.setInStock(item.getInStock() - quantity);
                if (item.getInStock() <= 0) {
                    item.setAvailability(false);
                    item.setInStock(0);
                }

                List<Equipment> equipmentList = equipmentDAO.findAll();
                session.setAttribute("equipmentList", equipmentList);
                request.getRequestDispatcher("pages/users/main_page.jsp");
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
