package com.examplexyy.demo.mybatis;

import java.io.PrintWriter;
import java.sql.*;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/25 0:09
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class JDBCDriverDemo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /**
         * 这里利用java的SPI机制,会自动的装载mySql的驱动类
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xyy", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from file_info");
        while (resultSet.next()){
            System.out.println(resultSet.getString("name") + "  " + resultSet.getLong("length")
                    + "  " + resultSet.getString("path"));
        }
        statement.close();
        connection.close();
    }
}
