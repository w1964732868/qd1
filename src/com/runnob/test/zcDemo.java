package com.runnob.test;


import java.sql.*;

public class zcDemo {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";   //mysql8
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   //mysql5.7

    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/TEST?useSSL=false&serverTimezone=UTC";  //RUNOOB数据库名 //mysql8
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";  //RUNOOB数据库名  //mysql5.7

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "wuhuijie";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");  //mysql8
//          Class.forName("com.mysql.jdbc.Driver");  //mysql5.7

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;

            sql = "select * from users";

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs.next());

            // 展开结果集数据库
            while (rs.next()) {

                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");


                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("用户: " + username);
                System.out.print("密码" + password);
                System.out.print("\n");
//                // 通过字段检索
//                String password = rs.getString("password");
//                String username = rs.getString("username");
//
//                // 输出数据
//                System.out.print( password);
//                System.out.print( username);
//                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}