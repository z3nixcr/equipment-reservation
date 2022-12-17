package com.example.errs.dao;

import com.example.errs.entities.Equipment;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentDAO {
    // List all equipments
    public List<Equipment> findAll() throws SQLException;
    // Find one equipment
    public Equipment findOne(Equipment equipment) throws SQLException;
    // Update equipment stock or set availability true or false
    public void update(Equipment equipment) throws SQLException;
}
