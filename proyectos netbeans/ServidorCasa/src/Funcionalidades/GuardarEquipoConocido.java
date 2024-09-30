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
 */


public class GuardarEquipoConocido {

    //este metodo es llamado cuando se pulsa con el raton en la tabla de equipos conocidos y se guarda un nombre nuevo
    //viene de la clase VentanaEquiposConectados, evento tablaEquiposConectadosMoussepressed
    public static void guardarEquipoConocido(String direccionIp, String direccionMac, String marca, String nombreNuevo) throws SQLException, IOException{
        
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "INSERT INTO equipos_conocidos (direccion_mac,nombre) VALUES ('" + direccionMac + "','" + nombreNuevo + "');";
        int filasModificadas = stmt.executeUpdate(consulta);
        DevolverConexion.cerrarConexion(conexion);
        
    }//final metodo guardarEquiposConocidos
    
}//final clase GuardarEquiposConocidos
