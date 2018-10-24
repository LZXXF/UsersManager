package com.cl.controller;

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
        PrintWriter out = response.getWriter();
        out.println(id + " " + password);
        //authenticate user in database
        Connection ct = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //1. add driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //2. get connection
            ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "cl", "cl");
            //3. create PreparedStatement
            ps = ct.prepareStatement("select * from users where id_number = ? and password = ?");
            // assign value for ?
            ps.setObject(1, id);
            ps.setObject(2, password);
            //4. excute query
            rs = ps.executeQuery();
            //5. check valid user
            if (rs.next()) {
                request.getRequestDispatcher("/MainFrame").forward(request, response);
            } else {
                request.setAttribute("err", "user id or password is wrong!");
                request.getRequestDispatcher("/LoginServlet").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            rs = null;

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ps = null;

            if (ct != null) {
                try {
                    ct.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ct = null;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
