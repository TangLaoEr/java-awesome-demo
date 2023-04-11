package cn.tanglaoer.demo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/11
 */
public class HikariCPTest {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:33061/demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");


        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = ds.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from role");
            while (rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
