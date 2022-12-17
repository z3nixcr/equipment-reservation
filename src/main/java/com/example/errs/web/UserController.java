package com.example.errs.web;

import com.example.errs.dao.EquipmentDAO;
import com.example.errs.dao.UserDAO;
import com.example.errs.entities.Equipment;
import com.example.errs.entities.User;
import com.example.errs.helpers.EquipmentImpl;
import com.example.errs.helpers.UserImpl;
import com.example.errs.utils.MySQLConn;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserController", value = "/UserController")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "toLogin":

            case "logout":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "toRegister":
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                validateLogin(request, response);
                break;

            case "register":
                processRegister(request, response);
                break;
        }
    }

    private void processRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Connection connection = null;
        String error = "";

        try {
            connection = MySQLConn.getConnection();
            if (connection.getAutoCommit())
                connection.setAutoCommit(false);

            UserDAO userDAO = new UserImpl(connection);
            User user = userDAO.findOne(new User(email, password));

            if (user == null) {
                user = new User(fullName, email, phone, username, password);
                if (email.equals("admin@mail.com") || username.equals("admin")) {
                    error = "* Warning: this email or username is reserved for admin";
                    session.setAttribute("register_error", error);
                    response.sendRedirect("register.jsp");
                } else {
                    userDAO.create(user);
                    connection.commit();
                    session.setAttribute("register_error", error);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                error = "* Warning: user already registered, please login or register another account";
                session.setAttribute("register_error", error);
                response.sendRedirect("register.jsp");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserImpl();
        EquipmentDAO equipmentDAO = new EquipmentImpl();
        String error = "";

        try {
            User user = userDAO.findOne(new User(email, password));
            if (user == null) {
                error = "* Warning: email or password incorrect";
                session.setAttribute("login_error", error);
                response.sendRedirect("index.jsp");
            } else {
                List<Equipment> equipmentList = equipmentDAO.findAll();
                session.setAttribute("equipmentList", equipmentList);
                session.setAttribute("login_error", error);
                session.setAttribute("user", user);
                if (user.getUsername().equals("admin")) {
                    request.getRequestDispatcher("pages/admin/admin_page.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("pages/users/main_page.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
