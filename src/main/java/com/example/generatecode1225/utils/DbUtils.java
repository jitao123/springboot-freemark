package com.example.generatecode1225.utils;

import com.example.generatecode1225.model.Db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Objects;

/**
 * @description:
 * @author: AT
 * @Date: 2020/12/25 2:09 下午
 */
public class DbUtils {
    private static Connection connection;

    public static Connection getConnection(){
        return connection;
    }

    public static Connection initDb(Db db){

        if (Objects.isNull(connection)){
            try {
              Class.forName("com.mysql.cj.jdbc.Driver");
                 connection = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
