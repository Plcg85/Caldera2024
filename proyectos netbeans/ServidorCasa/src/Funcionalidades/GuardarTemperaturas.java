/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;
import BasesDeDatos.DevolverConexion;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author Pedro Luis
 * aqui se guardan las temperaturas del salon y de la cpu en la misma tabla
 */
public class GuardarTemperaturas {
    
    public static void guardarTemperaturas(float temperaturaCasa, float humedadSalon,float temperaturaCpu) throws SQLException, IOException{
        
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "INSERT INTO temperaturas(temperatura_salon,humedad_salon,temperatura_cpu) VALUES ('" + temperaturaCasa + "','" + humedadSalon + "','" + temperaturaCpu + "' );";
        int filasAfectadas = stmt.executeUpdate(consulta);
        DevolverConexion.cerrarConexion(conexion);
        
    }//final guardarTemperaturaCasa
    
}//final clase GuardarTEmperaturaCAsa
