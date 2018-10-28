package com.cl.view;

import com.cl.dao.SqlHelper;

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
        response.setContentType("text/html; charset = utf-8");
        PrintWriter out = response.getWriter();
        out.println("<img src = 'imgs/1.jpg'/> Welcome xx log in  <a href = '/UserManager/MainFrame'>Back to Main Page</a>   <a href = '/UserManager/LoginServlet'>Log out</a><hr/>");
        out.println("<h1>manage users</h1>");
        Connection ct = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        //define variables for splitting pages
        int pageNow = 1; //current page
        String pageNowString = request.getParameter("pageNow");
        if (pageNowString != null) {
            pageNow = Integer.parseInt(pageNowString);
        }
        int pageSize = 3; //number of records for each page
        int pageCount = 1; //total number of pages
        int rowCount = 1; //total number of records fetching from database

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "cl", "cl");
            ps = ct.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
            pageCount = (rowCount - 1) / pageSize + 1;
            String sql = "select * from (select t.*, rownum rn from (select * from users order by users.id_number)t where rownum <=" + pageNow * pageSize + ") where rn >" + pageSize * (pageNow - 1);
            System.out.println(sql);
            ps = ct.prepareStatement(sql);
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
        } finally {
            SqlHelper.close(rs, ps, ct);
        }
        out.println("<hr/><img src = 'imgs/2.jpg'/>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
