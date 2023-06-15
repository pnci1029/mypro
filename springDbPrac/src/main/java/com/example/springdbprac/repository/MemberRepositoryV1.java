package com.example.springdbprac.repository;

import com.example.springdbprac.connection.DBConnectionUtil;
import com.example.springdbprac.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;


/**
 * JDBC - DataSource 사용
 */

@Slf4j @RequiredArgsConstructor
public class MemberRepositoryV1 {

    private final DataSource dataSource;

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?,?)";

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, member.getMemberId());
            preparedStatement.setInt(1,member.getMoney());

            //위 쿼리가 db에 적용
            preparedStatement.executeUpdate();
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

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from Member where member_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException();
            }

        } catch (SQLException e) {
            log.error("error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, rs);
        }

    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "update Member set money = ?  where member_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, memberId);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            log.warn("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from Member where member_id = ? ";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    private void close(Connection con, Statement preparedStatement, ResultSet rs) {
        JdbcUtils.closeConnection(con);
        JdbcUtils.closeStatement(preparedStatement);
        JdbcUtils.closeResultSet(rs);
    }
    private  Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
