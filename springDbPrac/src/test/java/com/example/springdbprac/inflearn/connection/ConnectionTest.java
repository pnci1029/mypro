package com.example.springdbprac.inflearn.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.springdbprac.inflearn.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ConnectionTest {

    // 커넥션을 맺는 테스트
    @Test
    void driverManagerTest() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, NAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, NAME, PASSWORD);

        assertThat(con1.getClass()).isEqualTo(con2.getClass());
    }

    @Test
    void dataSourceDriverTest() {

    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        // 히카리 커넥션 풀 사용
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(NAME);
        dataSource.setUsername(PASSWORD);

        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyHikariPool");

        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("{}",con1);
        log.info("{}",con2);
        Thread.sleep(1000);

    }
}
