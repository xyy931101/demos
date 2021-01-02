package com.examplexyy.demo.mybatis;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/25 0:09
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class JDBCDriverDemo {

    public static void main(String[] args) throws SQLException {


        DriverManager.getConnection("jdbc:postgresql://8.129.216.97:5432/base");
    }
}
