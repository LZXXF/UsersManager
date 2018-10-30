package com.cl.service;

import com.cl.dao.SqlHelper;
import com.cl.domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    //get page counts
    public int getPageCount(int pageSize) {
        String sql = "select count(*) from users";
        ResultSet rs = SqlHelper.executeQuery(sql, null);
        int rowCount = 0;
        try {
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return (rowCount - 1) / pageSize + 1;

    }


    //get user objects for paging
    public List<User> getUserByPage(int pageNow, int pageSize) {
        String sql = "select * from (select t.*, rownum rn from (select * from users order by users.id_number)t where rownum <=" + pageNow * pageSize + ") where rn >" + pageSize * (pageNow - 1);
        List<User> list = new ArrayList<>();
        ResultSet rs = SqlHelper.executeQuery(sql, null);
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setGrade(rs.getInt(4));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return list;
    }

    //check if user is valid
    public boolean checkUser(User user) {
        boolean flag = false;

        try {

            String sql = "select * from users where id_number = ? and password = ?";
            String[] parameters = {user.getId() + "", user.getPassword()};
            ResultSet rs = SqlHelper.executeQuery(sql, parameters);
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper.getCt());
        }
        return flag;
    }

    //delete user
    public boolean deleteUser(String id) {
        boolean b = true;
        String sql = "delete from users where id_number = ?";
        String[] parameters = {id};
        try {
            SqlHelper.executeUpdate(sql, parameters);
        } catch (Exception e) {
            b = false;
        }
        return b;
    }
}
