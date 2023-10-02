/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclientes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Fernanda
 */
public class ClientDao {
    
    /** Conexión a la base de datos
    *
    * @author Maria Fernanda
    */
    
    public void dataBaseConnection(){
        
        String dataBase = "app_clients";
        String user = "root";
        String password = "";
        String host = "localhost";
        String port = "3306";
        String driver = "com.mysql.jdbc.Driver";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + dataBase+ "?useSSL=false";
        
        Connection connection = null;
        
        try {
           Class.forName(driver);
           connection = DriverManager.getConnection(urlConnection, user, password);
        } catch (Exception ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
