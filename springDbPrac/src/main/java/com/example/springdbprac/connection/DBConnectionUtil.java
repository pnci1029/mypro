package com.example.springdbprac.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.springdbprac.connection.ConnectionConst.PASSWORD;

@Slf4j
public class DBConnectionUtil {

    /**
     * 해당 connection은 jdbc가 제공하는 표준 인터페이스
     * DB 연결을 위해 JDBC에서 제공하는 DriverManager를 통해
     * 라이브러리에 있는 DB드라이버를 찾아서 커넥션 제공
     */
    //
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.NAME, PASSWORD);
            log.info("get Connection = {}, class = {}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
