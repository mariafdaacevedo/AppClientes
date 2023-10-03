/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclientes.dao;

import com.mycompany.appclientes.models.Client;
import com.mysql.jdbc.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Fernanda
 */
public class ClientDao {
    
    /** Data base connection in phpmyadmin
     * @return 
    */
    
    public Connection dataBaseConnection(){
        
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
          
        return connection;
    }
    
    /**
     * Allows to add a new client 
     * @param client 
     */
    
    public void insertClient(Client client){

        try {
           Connection connection = dataBaseConnection();
           String sql = "INSERT INTO `clients` (`id`, `name`, `last_name`, `phone_number`, `email`) VALUES (NULL, '" 
                   + client.getName()+ "', '" 
                   + client.getLastName() + "', '" 
                   + client.getTelephone() + "', '" 
                   + client.getEmail()+ "');";
           Statement statement = connection.createStatement();
           statement.execute(sql);
           
        } catch (Exception ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * List with the clients 
     * @return 
     */
    
    public List<Client> getAllClients(){
        
        
        List<Client> clients = new ArrayList();
        
        try {
           Connection connection = dataBaseConnection();
           String sql = "SELECT * FROM `clients`";
 
           Statement statement = connection.createStatement();
           ResultSet queryResult= statement.executeQuery(sql);
           
           while(queryResult.next()){
               Client client = new Client();
               client.setId(queryResult.getString("id"));
               client.setName(queryResult.getString("name"));
               client.setLastName(queryResult.getString("last_name"));
               client.setTelephone(queryResult.getString("phone_number"));
               client.setEmail(queryResult.getString("email")); 
               clients.add(client);
           }
           
           
        } catch (Exception ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clients;
    }
    
    /**
     * Delete client selected
     * @param id 
     */
    
    public void deleteClient(String id){
        
        try {
           Connection connection = dataBaseConnection();
           
           String sql = "DELETE FROM clients WHERE `clients`.`id` = " + id;
 
           Statement statement = connection.createStatement();
           statement.execute(sql);
 
        } catch (Exception ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void updateClient(Client client){

        try {
           Connection connection = dataBaseConnection();
           String sql = "UPDATE `clients` SET `name` = '" + client.getName()
                   + "', `last_name` = '"+ client.getLastName()
                   +"', `phone_number` = '" + client.getTelephone()
                   +"', `email` = '"+ client.getEmail()
                   +"' WHERE `clients`.`id` = "+ client.getId()+";";
           
           Statement statement = connection.createStatement();
           statement.execute(sql);
           
        } catch (Exception ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SaveClient(Client client) {
        if(StringUtils.isEmptyOrWhitespaceOnly(client.getId())){
            insertClient(client);
        }else{
            updateClient(client);
        }
    }
    
    
}
