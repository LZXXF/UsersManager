package com.cl.view;

import com.cl.dao.SqlHelper;
import com.cl.domain.User;
import com.cl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

public class ManageUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = utf-8");
        PrintWriter out = response.getWriter();
        out.println("<img src = 'imgs/1.jpg'/> Welcome xx log in  <a href = '/UserManager/MainFrame'>Back to Main Page</a>   <a href = '/UserManager/LoginServlet'>Log out</a><hr/>");
        out.println("<h1>manage users</h1>");

        out.println("<script type = 'text/javascript' language = 'javascript'>");
        out.println(" function confirmOper() {return window.confirm('Are you sure to delete'); }");
        out.println("</script>");


        //define variables for splitting pages
        int pageNow = 1; //current page
        String pageNowString = request.getParameter("pageNow");
        if (pageNowString != null) {
            pageNow = Integer.parseInt(pageNowString);
        }
        int pageSize = 3; //number of records for each page
        int pageCount = 0;

        try {
            UserService userService = new UserService();
            pageCount = userService.getPageCount(pageSize);
            List<User> list = userService.getUserByPage(pageNow, pageSize);
            out.println("<table border = 1px bordercolor = green cellspacing = 0 width = 500px>");
            out.println("<tr><th>id</th><th>user name</th><th>email</th><th>grade</th><th>delete users</th><th>modify users</th></tr>");
            for (User user : list) {
                out.println("<tr><td>" + user.getId() +
                        "</td><td>" + user.getName() +
                        "</td><td>" + user.getEmail() +
                        "</td><td>" + user.getGrade() +
                        "</td><td><a onclick = 'return confirmOper();' href = '/UserManager/DeleteClServlet?id=" + user.getId() + "'>delete</a></td><td><a href = '#'>modify</a></td></tr>");
            }
            out.println("</table>");
            if (pageNow != 1) {
                out.println("<a href = '/UserManager/ManageUsers?pageNow=" + (pageNow - 1) + "'>previous</a>");
            }
            for (int i = 1; i <= pageCount; i++) {
                out.println("<a href = '/UserManager/ManageUsers?pageNow=" + i + "'><" + i + "></a>");
            }
            if (pageNow != pageCount) {
                out.println("<a href = '/UserManager/ManageUsers?pageNow=" + (pageNow + 1) + "'>next</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("<hr/><img src = 'imgs/2.jpg'/>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
