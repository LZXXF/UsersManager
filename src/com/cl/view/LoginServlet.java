package com.cl.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>user login</h1>");
        //action: /web application/Servlet
        out.println("<form action = '/UserManager/LoginClServlet' method = 'post'>");
        out.println("user id: <input type = 'text' name = 'id'/><br/>");
        out.println("password: <input type = 'password' name = 'password'/><br/>");
        out.println("<input type = 'submit' value = 'submit'/><br/>");
        out.println("</form>");
        String errInfo = (String) request.getAttribute("err");
        if (errInfo != null) {
            out.println(request.getAttribute("err").toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
