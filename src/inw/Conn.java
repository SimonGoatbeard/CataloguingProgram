/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Lukasz Szymkowski "Simon Goatbeard"
 */
public class Conn {
    public Connection connectMe(){
        try {
            String host = "XXX";
            String username = "XXX";
            String password = "XXX";

            Connection con = DriverManager.getConnection(host, username, password);
            
            return con;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
    
    public Statement connectStatement(Connection con){
        try {
            Statement stmt = con.createStatement();
            return stmt;
        }
        catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
}
