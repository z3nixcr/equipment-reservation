package com.example.errs.dao;

import com.example.errs.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    // List all users
    public List<User> findAll() throws SQLException;
    // Find a user
    public User findOne(User user) throws SQLException;
    // Create new user
    public void create(User user) throws SQLException;
}
