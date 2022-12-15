/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.me.biz;

import java.time.LocalDate;

/**
 *
 * @author enrique
 */
public class Venta {
    private LocalDate fecha;
    private int ventas;
    private double ingresos;

    public Venta(LocalDate fecha, int ventas, double ingresos) {
        this.fecha = fecha;
        this.ventas = ventas;
        this.ingresos = ingresos;
    }

    public Venta(int ventas, double ingresos) {
        this.ventas = ventas;
        this.ingresos = ingresos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }
}
