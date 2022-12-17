package com.example.errs.helpers;

import com.example.errs.dao.UserDAO;
import com.example.errs.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.errs.utils.MySQLConn.close;
import static com.example.errs.utils.MySQLConn.getConnection;

public class UserImpl implements UserDAO {
    private Connection connection;
    // SQL Queries variables
    private static final String SELECT = "SELECT * FROM user";
    private static final String INSERT = "INSERT INTO user(full_name, email, phone_number, username, password) " +
            "VALUES(?, ?, ?, ?, ?)";

    // Constructors
    public UserImpl() {
    }

    public UserImpl(Connection connection) {
        this.connection = connection;
    }

    public Connection createConnection(Connection connection) {
        try {
            return connection != null ? connection : getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(SELECT);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_user");
                String name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                String username = rs.getString("username");
                String password = rs.getString("password");

                User user = new User(id, name, email, phone, username, password);
                users.add(user);
            }
        } finally {
            assert rs != null;
            close(rs);
            close(pstm);
            if (this.connection == null) {
                close(conn);
            }
        }

        return users;
    }

    @Override
    public User findOne(User user) throws SQLException {
        List<User> users = findAll();

        for (User u : users) {
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
                return u;
        }
        return null;
    }

    @Override
    public void create(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, user.getFullName());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPhoneNumber());
            pstm.setString(4, user.getUsername());
            pstm.setString(5, user.getPassword());

            pstm.executeUpdate();

        } finally {
            assert pstm != null;
            close(pstm);
            if (this.connection == null) {
                close(conn);
            }
        }
    }
}
