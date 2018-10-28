package com.cl.service;

import com.cl.dao.SqlHelper;
import com.cl.domain.User;
import java.sql.*;

public class UserService {
    Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    //check if user is valid
    public boolean checkUser(User user) {
        boolean flag = false;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "cl", "cl");
            ps = ct.prepareStatement("select * from users where id_number = ? and password = ?");
            ps.setObject(1, user.getId());
            ps.setObject(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, ps, ct);
        }
        return flag;
    }
}
