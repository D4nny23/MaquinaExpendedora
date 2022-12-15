/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.me.DAO;

import com.daniel.me.biz.Golosina;
import java.util.List;

/**
 *
 * @author enrique
 */
public interface GolosinaDAO {   
    public List<Golosina> getGolosinas() throws Exception;
    public Golosina getGolosinaById(String posicion) throws Exception;
    public boolean updateGolosina(Golosina g) throws Exception;
    public void close() throws Exception;  
}
