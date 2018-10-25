package com.cl.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainFrame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = utf-8");
        PrintWriter out = response.getWriter();
        out.println("<img src = 'imgs/1.jpg'/> Welcome xx log in  <a href = '/UserManager/LoginServlet'>Back to Login Page </a><hr/>");

        out.println("<h3>Please choose page you want to go</h3><br/>");
        out.println("<a href = '/UserManager/ManageUsers'>manage users</a><br/>");
        out.println("<a href = ''>add users</a><br/>");
        out.println("<a href = ''>search users</a><br/>");
        out.println("<a href = ''>log out</a><br/>");
        out.println("<hr/>");
        out.println("<img src = 'imgs/2.jpg'/>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
