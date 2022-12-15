/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.me.DAO;

import com.daniel.me.biz.Golosina;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enrique
 */
public class GolosinaDAOImpl implements GolosinaDAO, AutoCloseable {

    private String URL;
    Connection conexion = null;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
        }
    }

    public GolosinaDAOImpl(String fileName) throws Exception {
        this.URL = "jdbc:sqlite:" + fileName;
        this.conexion = DriverManager.getConnection(URL);
    }

    public GolosinaDAOImpl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Golosina> getGolosinas() throws Exception {
        Golosina g = null;
        ArrayList<Golosina> gs = new ArrayList<Golosina>();
        String sql = "SELECT * FROM GOLOSINAS";
        try (PreparedStatement sentencia = this.conexion.prepareStatement(sql);
                ResultSet resul = sentencia.executeQuery();) {
            // Ejecutamos la consulta
            while (resul.next()) {
                g = new Golosina(resul.getString("NOMBRE"), resul.getFloat("PRECIO"), resul.getString("POSICION"), resul.getInt("CANTIDAD"));
                gs.add(g);
            }
        } catch (Exception e) {
            throw e;
        }
        return gs;
    }

    @Override
    public Golosina getGolosinaById(String posicion) throws Exception {
        Golosina g = null;
        String sql = "SELECT * FROM GOLOSINAS WHERE POSICION=?";
        ResultSet resul = null;
        try (PreparedStatement sentencia = this.conexion.prepareStatement(sql);) {
            sentencia.setString(1, posicion);
            // Ejecutamos la consulta
            resul = sentencia.executeQuery();
            while (resul.next()) {
                g = new Golosina(resul.getString("NOMBRE"), resul.getFloat("PRECIO"), resul.getString("POSICION"), resul.getInt("CANTIDAD"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (resul != null) {
                resul.close();
            }
        }
        return g;
    }

    @Override
    public boolean updateGolosina(Golosina g) throws Exception {
        int n = 0;
        String sql = "UPDATE GOLOSINAS SET CANTIDAD = ? WHERE POSICION = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql);) {
            sentencia.setInt(1, g.getCantidad());
            sentencia.setString(2, g.getPosicion());
            n = sentencia.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        if (n > 0) {
            return true;
        } else {
            return false;
        }
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
