import com.cl.dao.SqlHelper;
import oracle.jdbc.OracleTypes;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TestSqlHelper {

    //Test single update/delete/insert function SqlHelper.executeUpdate()

    @Ignore
    public void testExecuteUpdate() {
        String sql = "insert into users values(?, ?, ?, ?, ?)";
        String[] parameters = {"10", "aaa10", "aaa10@sohu.com", "1", "123"};
        SqlHelper.executeUpdate(sql, parameters);
    }

    //Test multiple update/delete/insert function SqlHelper.executeUpdate2()

    @Ignore
    public void testExecuteUpdate2() {
        String sql1 = "insert into users values(?, ?, ?, ?, ?)";
        String sql2 = "insert into users values(?, ?, ?, ?, ?)";
        String[] sqls = {sql1, sql2};
        String[] sql1Parameters = {"11", "aaa11", "aaa11@sohu.com", "1", "123"};
        String[] sql2Parameters = {"12", "aaa12", "aaa12@sohu.com", "1", "123"};
        String[][] parameters = {sql1Parameters, sql2Parameters};
        SqlHelper.excuteUpdate2(sqls, parameters);
    }

    //Test select sql function SqlHelper.exexuteQuery
    @Ignore
    public void testExecuteQuery() {
        String sql = "select * from users";
        try {
            ResultSet rs = SqlHelper.executeQuery(sql, null);
            while (rs.next()) {
                System.out.println(rs.getInt("id_number") + rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper.getCt());
        }
    }

    //Test call procedures without return type
    @Ignore
    public void testCallPro1() {
        String sql = "{call pro1(?, ?, ?, ?, ?)}";
        String[] parameters = {"13", "aaa13", "aaa13@sohu.com", "1", "123"};
        SqlHelper.callPro1(sql, parameters);
    }

    //Test call procedures with return type
    @Test
    public void testCallPro2() {
        ResultSet rs = null;
        try {
            String sql = "{call pro2(?, ?)}";
            String[] inParameters = {"1"};
            Integer[] outParameters = {oracle.jdbc.OracleTypes.CURSOR};
            CallableStatement cs = SqlHelper.callPro2(sql, inParameters, outParameters);
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getCs(), SqlHelper.getCt());
        }
    }
}
