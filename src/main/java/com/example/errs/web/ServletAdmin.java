package com.example.errs.web;

import com.example.errs.entities.Equipment;
import com.example.errs.entities.User;
import com.example.errs.helpers.EquipmentImpl;
import com.example.errs.helpers.UserImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletAdmin", value = "/ServletAdmin")
public class ServletAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        HttpSession session = request.getSession();

        try {
            switch (option) {
                case "equipments":
                    List<Equipment> equipmentList = new EquipmentImpl().findAll();
                    session.setAttribute("equipmentList", equipmentList);
                    request.getRequestDispatcher("pages/admin/admin_page.jsp").forward(request, response);
                    break;

                case "customers":
                    List<User> userList = new UserImpl().findAll();
                    userList.remove(0);
                    session.setAttribute("userList", userList);
                    request.getRequestDispatcher("pages/admin/customers.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
