/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.me.DAO;

import com.daniel.me.biz.Venta;

/**
 *
 * @author enrique
 */
public interface VentaDAO {
    public boolean insertVenta(Venta v) throws Exception;
    public void close() throws Exception;
}
