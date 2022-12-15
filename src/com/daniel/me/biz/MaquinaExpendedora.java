/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.me.biz;

import com.daniel.utils.exceptions.TooHighNumberException;
import com.daniel.utils.exceptions.EmptyPositionException;
import com.daniel.utils.exceptions.IncorrectPasswordException;
import com.daniel.utils.exceptions.IncorrectPositionException;
import com.daniel.me.DAO.VentaDAOImpl;
import com.daniel.me.DAO.GolosinaDAOImpl;
import com.daniel.metodos.Metodos;
import com.daniel.utils.exceptions.IncorrectAmountException;
import com.daniel.utils.exceptions.InsufficientMoney;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author enrique.nogal
 */
public class MaquinaExpendedora {

    private double ingresos = 0;
    private int ventas = 0;
    private final String CLAVE = "dev";
    private String fileName;
    private double monedero;

    
    public MaquinaExpendedora() throws Exception {
        this.fileName = "./golosinas.db";
    }

    public Golosina rellenarGolosina(String posicion, int c, String clave) throws Exception, IncorrectPasswordException, IncorrectPositionException, TooHighNumberException, SQLException, IncorrectAmountException {
        Golosina conseguido = null;
        if (clave.equals(CLAVE)) {
            if (c > 0) {
                try (GolosinaDAOImpl gDatos = new GolosinaDAOImpl(fileName);) {
                    conseguido = gDatos.getGolosinaById(posicion);
                    if (conseguido != null) {
                        int cantidadActual = conseguido.getCantidad();
                        conseguido.setCantidad(cantidadActual + c);
                        gDatos.updateGolosina(conseguido);
                    } else {
                        throw new IncorrectPositionException();
                    }
                } catch (Exception e) {
                    throw e;
                }
            } else {
                throw new IncorrectAmountException();
            }
        } else {
            throw new IncorrectPasswordException();
        }
        return conseguido;
    }

    public Golosina pedirGolosina(String posicion) throws Exception, IncorrectPositionException, EmptyPositionException, TooHighNumberException, SQLException, InsufficientMoney {
        Golosina conseguido = null;
        Metodos me=new Metodos();
        try (GolosinaDAOImpl gDatos = new GolosinaDAOImpl(fileName);) {
            conseguido = gDatos.getGolosinaById(posicion);
            if (conseguido != null) {
                if (this.monedero >= me.redondeoDosNumeros(conseguido.getPrecio())) {
                    if (conseguido.getCantidad() > 0) {
                        int cantidadActual = conseguido.getCantidad();
                        conseguido.setCantidad(cantidadActual - 1);
                        this.ventas++;
                        this.ingresos += conseguido.getPrecio();
                        gDatos.updateGolosina(conseguido);
                    } else {
                        throw new EmptyPositionException();
                    }
                } else {
                    throw new InsufficientMoney();
                }
            } else {
                throw new IncorrectPositionException();
            }
        } catch (Exception e) {
            throw e;
        }
        return conseguido;
    }

    public ArrayList<Golosina> devuelveGolosina() throws Exception, SQLException {
        ArrayList<Golosina> gs;
        try (GolosinaDAOImpl gDatos = new GolosinaDAOImpl(fileName);) {
            gs = new ArrayList(gDatos.getGolosinas());
        } catch (Exception e) {
            throw e;
        }
        return gs;
    }

    public void guardarDatos() throws Exception {
        Metodos me = new Metodos();
        Venta v = new Venta(this.ventas, me.redondeoDosNumeros(this.ingresos));
        try (VentaDAOImpl vDatos = new VentaDAOImpl();) {
            vDatos.insertVenta(v);
        } catch (SQLException e) {
            throw e;
        }
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public double getMonedero() {
        return monedero;
    }

    public void setMonedero(double monedero) {
        this.monedero = monedero;
    }

}
