/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daniel.metodos;

/**
 *
 * @author dev
 */
public class Metodos {
    public double redondeoDosNumeros(double numero){
        double decimal=0;
        decimal=(double)Math.round(numero * 100d) / 100d;
        return decimal;
    }
    
}
