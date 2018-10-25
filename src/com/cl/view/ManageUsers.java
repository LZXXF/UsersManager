package com.cl.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ManageUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = urf-8");
        PrintWriter out = response.getWriter();
        out.println("<img src = 'imgs/1.jpg'/> Welcome xx log in  <a href = '/UserManager/MainFrame'>Back to Main Page</a>   <a href = '/UserManager/LoginServlet'>Log out</a><hr/>");
        out.println("<h1>manage users</h1>");
        Connection ct = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "cl", "cl");
            ps = ct.prepareStatement("select * from users");
            rs = ps.executeQuery();
            out.println("<table border = 1px bordercolor = green cellspacing = 0 width = 500px>");
            out.println("<tr><th>id</th><th>user name</th><th>email</th><th>grade</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt(1) +
                        "</td><td>" + rs.getString(2) +
                        "</td><td>" + rs.getString(3) +
                        "</td><td>" + rs.getInt(4) +
                        "</td></tr>");
            }
            out.println("</table>");
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
        out.println("<hr/><img src = 'imgs/2.jpg'/>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
