package com.examplexyy.demo.mybatis;

import lombok.Data;

import java.sql.*;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/21 23:59
 * .............................................
 * 佛祖保佑             永无BUG
 */
@Data
public class MyDataSourcePool {
    private static Integer MAX_SIZE = 10;
    private static Integer CORE_SIZE = 3;
    private static LinkedList<Connection> pool = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    static {
        for (int i = 0; i < CORE_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xyy", "root", "root");
                pool.add(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        MyDataSourcePool pool = new MyDataSourcePool();
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from file_info");
        while (resultSet.next()){
            System.out.println(resultSet.getString("name") + "  " + resultSet.getLong("length")
                    + "  " + resultSet.getString("path"));
        }
        pool.releaseConnection(connection);
        System.out.println(MyDataSourcePool.pool.size());
    }

    private Connection getConnection() {
        Connection conn = null;
        lock.lock();
        try {
            while (pool.size() < 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!pool.isEmpty()) {
                conn = pool.removeFirst();
            }
            return conn;
        } finally {
            lock.unlock();
        }

    }

    public void releaseConnection(Connection conn) {
        if (conn != null) {
            lock.lock();
            try {
                // 释放连接过程就是把连接放回连接池过程
                pool.addLast(conn);
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
