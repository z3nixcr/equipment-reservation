package com.example.errs.web;

import com.example.errs.entities.Equipment;
import com.example.errs.helpers.EquipmentImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletEquipment", value = "/ServletEquipment")
public class ServletEquipment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");


        System.out.println();

        switch (action) {
            case "add":
                toAddPage(request, response);
                break;
        }
    }

    private void toAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("itemId"));
        HttpSession session = request.getSession();

        try {
            Equipment item = new EquipmentImpl().findOne(new Equipment(id));
            session.setAttribute("item", item);
            request.getRequestDispatcher("pages/users/add.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
