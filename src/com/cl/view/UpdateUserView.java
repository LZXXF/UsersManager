package com.cl.view;

import com.cl.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateUserView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = utf-8");
        PrintWriter out = response.getWriter();

        //get user from controller
        User user = (User) request.getAttribute("user");

        out.println("<img src = 'imgs/1.jpg'/><hr/>");
        out.println("<form action = '/UserManager/UserClServlet?type=update' method = 'post'>");
        out.println("<table border = 1px bordercolor = green cellspacing = 0 width = 250px>");
        out.println("<tr><td>id</td><td><input type = 'text' readonly name = 'id' value ='" + user.getId()+"'></td></tr>");
        out.println("<tr><td>user name</td><td><input type = 'text' name = 'username' value ='" + user.getName()+"'></td></tr>");
        out.println("<tr><td>emaile</td><td><input type = 'text' name = 'email' value ='" + user.getEmail()+"'></td></tr>");
        out.println("<tr><td>grade</td><td><input type = 'text' name = 'grade' value ='" + user.getGrade()+"'></td></tr>");
        out.println("<tr><td>password</td><td><input type = 'text' name = 'password' value ='" + user.getPassword()+"'></td></tr>");
        out.println("<tr><td><input type = 'submit' value = 'submit'/></td><td><input type = 'reset' value = 'reset'/></td></tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("<hr/><img src = 'imgs/2.jpg'/>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
