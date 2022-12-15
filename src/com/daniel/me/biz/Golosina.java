/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.me.biz;

import com.daniel.utils.exceptions.TooHighNumberException;

/**
 *
 * @author enrique.nogal
 */
public class Golosina {

    private String nombre;
    private double precio;
    private int cantidad = 5;
    private String posicion;

    public Golosina(String nombre, double precio, String posicion) {
        this.nombre = nombre;
        this.precio = precio;
        this.posicion = posicion;
    }
    
    public Golosina(String nombre, double precio, String posicion, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.posicion = posicion;
        this.cantidad = cantidad;
    }

    public Golosina() {
    }

    @Override
    public String toString() {
        return "Golosina{" + "nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + ", posicion=" + posicion + '}';
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) throws TooHighNumberException {
        if (cantidad > 20) {
            throw new TooHighNumberException();
        } else {
            this.cantidad = cantidad;
        }
    }

}
