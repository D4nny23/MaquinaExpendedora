/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.me.DAO;

import com.daniel.me.biz.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author enrique
 */
public class VentaDAOImpl implements VentaDAO, AutoCloseable {

    private String URL;
    Connection conexion = null;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
        }
    }

    public VentaDAOImpl() throws Exception {
        this.URL = "jdbc:sqlite:./golosinas.db";
        this.conexion = DriverManager.getConnection(URL);
    }

    @Override
    public boolean insertVenta(Venta v) throws Exception {
        String sql = "INSERT INTO VENTAS (FECHA,VENTAS,INGRESOS) VALUES (DATETIME('now'),?,?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql);) {
            sentencia.setInt(1, v.getVentas());
            sentencia.setDouble(2, v.getIngresos());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

    @Override
    public void close() throws Exception {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

}
