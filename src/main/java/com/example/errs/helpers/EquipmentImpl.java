package com.example.errs.helpers;

import com.example.errs.dao.EquipmentDAO;
import com.example.errs.entities.Equipment;
import com.example.errs.utils.MySQLConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.errs.utils.MySQLConn.close;
import static com.example.errs.utils.MySQLConn.getConnection;

public class EquipmentImpl implements EquipmentDAO {
    private Connection connection;
    private static final String SELECT = "SELECT * FROM equipment";
    private static final String UPDATE = "UPDATE equipment SET stock = ?, availability = ? WHERE id_equipment = ?";

    // Constructors
    public EquipmentImpl() {
    }

    public EquipmentImpl(Connection connection) {
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
    public List<Equipment> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Equipment> equipmentList = new ArrayList<>();

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(SELECT);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_equipment");
                String name = rs.getString("name");
                int stock = rs.getInt("stock");
                float price = rs.getFloat("price");
                boolean availability = rs.getBoolean("availability");

                Equipment equipment = new Equipment(id, name, stock, price, availability);
                equipmentList.add(equipment);
            }

        } finally {
            assert rs != null;
            close(rs);
            close(pstm);
            if (this.connection == null) {
                close(conn);
            }
        }
        return equipmentList;
    }

    @Override
    public Equipment findOne(Equipment equipment) throws SQLException {
        List<Equipment> equipmentList = findAll();

        for (Equipment e : equipmentList) {
            if (e.getIdEquipment() == equipment.getIdEquipment())
                return e;
        }
        return null;
    }

    @Override
    public void update(Equipment equipment) throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = createConnection(connection);
            pstm = conn.prepareStatement(UPDATE);
            pstm.setInt(1, equipment.getInStock());
            pstm.setBoolean(2, equipment.isAvailability());
            pstm.setInt(3, equipment.getIdEquipment());

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
