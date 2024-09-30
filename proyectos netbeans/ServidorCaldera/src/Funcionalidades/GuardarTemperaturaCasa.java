/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import BasesDeDatos.DevolverConexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author plcgc
 * ensta clase guarda la temperatura de casa que se recibe por socket del servidor del salon
 */
public class GuardarTemperaturaCasa {
    
    public static void guardarTemperaturaCasa(float temperaturaCasa) throws SQLException, IOException{
    
        float temperatura = temperaturaCasa;
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "INSERT INTO tempcasa (temp) VALUES ('" + temperatura+ "');";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);
        
    }//final metodo guardarTemperaturaCasa
    
}//final GuardarTemperaturaCasa
