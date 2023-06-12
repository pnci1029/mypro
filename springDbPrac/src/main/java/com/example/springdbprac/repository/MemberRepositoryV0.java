package com.example.springdbprac.repository;

import com.example.springdbprac.connection.ConnectionConst;
import com.example.springdbprac.connection.DBConnectionUtil;
import com.example.springdbprac.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;


/**
 * JDBC - DriverManager 사용
 */

@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?,?)";

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, member.getMemberId());
            preparedStatement.setInt(1,member.getMoney());
            return member;
        } catch (SQLException e) {
            log.error("db error");
            throw e;
        } finally {
//            preparedStatement.close();
//            con.close();
            /**
             * 주석친 부분을 메소드 뺀 이유는 preparedStatement 예외가 생기면 커넥션이 닫히지 않음.
             * 둘 중 하나가 예외가 발생하더라도 둘 다 close 처리를 하기위함
             */
            close(con, preparedStatement, null);
        }
    }

    private void close(Connection con, Statement preparedStatement, ResultSet rs) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("error", e);
            }

        }
    }
    // 커넥션을 꺼내는 메소드
    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
