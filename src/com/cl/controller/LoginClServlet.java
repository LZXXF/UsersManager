package com.cl.controller;

import com.cl.domain.User;
import com.cl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginClServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        UserService us = new UserService();
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setPassword(password);
        if (us.checkUser(user)) {
            request.getRequestDispatcher("/MainFrame").forward(request, response);
        } else {
            request.setAttribute("err", "user id or password is wrong!");
            request.getRequestDispatcher("/LoginServlet").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
