/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.modelo;

import gestor.hotel.app.controlador.MySQLController;

/**
 *
 * @author user
 */
public interface Insertable {
    public void insert(MySQLController controller);
    public void setPropertyMap();
}
