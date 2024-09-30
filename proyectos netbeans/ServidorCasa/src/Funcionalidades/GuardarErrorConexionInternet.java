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
 * esta clase guarda los errores de la conexion a internet cuando se producen. Solo cuando no hay internet
 */
public class GuardarErrorConexionInternet {
       
    public static void guardarErrorConexion() throws SQLException, IOException{
        
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String valorConexion = "No hay conexion a internet";
        String consulta = "INSERT INTO conexion_internet(estado_conexion) VALUES ('" + valorConexion + "');";
        int filasAfectadas = stmt.executeUpdate(consulta);
        DevolverConexion.cerrarConexion(conexion);
        
    }//final metodo guardarErrorConexion
    
}//final clase GuardarErrorConexionInternet
