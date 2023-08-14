//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
/*
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberService {
    public MemberService() {
    }

    public void dbSelect() {
        String url = "jdbc:mariadb://localhost:3306/testdb3";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";
        String memberTypeValue = "email";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException var26) {
            throw new RuntimeException(var26);
        }

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql = " select member_type, user_id, password, name  from member  where member_type = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);
            rs = preparedStatement.executeQuery();

            while(rs.next()) {
                String memberType = rs.getString("member_type");
                String userId = rs.getString("user_id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                System.out.println(memberType + ", " + userId + ", " + password + ", " + name);
            }
        } catch (SQLException var29) {
            throw new RuntimeException(var29);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException var28) {
                throw new RuntimeException(var28);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException var25) {
                throw new RuntimeException(var25);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException var27) {
                throw new RuntimeException(var27);
            }

        }

    }

    public void dbInsert(Member member) {
        String url = "jdbc:mariadb://localhost:3306/testdb3";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException var28) {
            throw new RuntimeException(var28);
        }

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String memberTypeValue = member.getMemberType();
        String userIdValue = member.getUserId();
        String passwordValue = member.getPassword();
        String nameValue = member.getName();

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql = " insert into member (member_type, user_id, password, name) values (?, ?, ?, ?); ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);
            preparedStatement.setString(2, userIdValue);
            preparedStatement.setString(3, passwordValue);
            preparedStatement.setString(4, nameValue);
            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("성공");
            } else {
                System.out.println("성공");
            }
        } catch (SQLException var27) {
            throw new RuntimeException(var27);
        } finally {
            try {
                if (rs != null && !((ResultSet)rs).isClosed()) {
                    ((ResultSet)rs).close();
                }
            } catch (SQLException var30) {
                throw new RuntimeException(var30);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException var26) {
                throw new RuntimeException(var26);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException var29) {
                throw new RuntimeException(var29);
            }

        }

    }

    public void dbUpdate() {
        String url = "jdbc:mariadb://localhost:3306/testdb3";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException var26) {
            throw new RuntimeException(var26);
        }

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String memberTypeValue = "email";
        String userIdValue = "zerobase@naver.com";
        String passwordValue = "9999";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql = " update member  set  password = ?  where member_type = ? and user_id = ?; ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passwordValue);
            preparedStatement.setString(2, memberTypeValue);
            preparedStatement.setString(3, userIdValue);
            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("성공");
            } else {
                System.out.println("성공");
            }
        } catch (SQLException var25) {
            throw new RuntimeException(var25);
        } finally {
            try {
                if (rs != null && !((ResultSet)rs).isClosed()) {
                    ((ResultSet)rs).close();
                }
            } catch (SQLException var28) {
                throw new RuntimeException(var28);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException var24) {
                throw new RuntimeException(var24);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException var27) {
                throw new RuntimeException(var27);
            }

        }

    }

    public void dbDelete() {
        String url = "jdbc:mariadb://localhost:3306/testdb3";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException var25) {
            throw new RuntimeException(var25);
        }

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String memberTypeValue = "email";
        String userIdValue = "zerobase@naver.com";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql = " delete from member  where member_type = ? and user_id = ?; ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);
            preparedStatement.setString(2, userIdValue);
            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("성공");
            } else {
                System.out.println("성공");
            }
        } catch (SQLException var24) {
            throw new RuntimeException(var24);
        } finally {
            try {
                if (rs != null && !((ResultSet)rs).isClosed()) {
                    ((ResultSet)rs).close();
                }
            } catch (SQLException var27) {
                throw new RuntimeException(var27);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException var23) {
                throw new RuntimeException(var23);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException var26) {
                throw new RuntimeException(var26);
            }

        }

    }
}
*/