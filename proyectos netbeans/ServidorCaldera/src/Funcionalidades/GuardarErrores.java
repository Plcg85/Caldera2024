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
 */
public class GuardarErrores {

    public static void guardarErrores(String ubicacionError, String tipoError, String descripcionError) throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        
        try {

            Statement stmt = conexion.createStatement();
            String consulta = "INSERT INTO errores (ubicacion_error, tipo_error, descripcion_error) VALUES ('" + ubicacionError + "','" + tipoError + "','" + descripcionError + "');";
            int filasAfectadas = stmt.executeUpdate(consulta);

        } catch (Exception e) {
            
            System.out.println("Error al guardar en la tabla errores" + e.toString());
            
        } finally {
             DevolverConexion.cerrarConexion(conexion);
        }

       

    }//final metodo guardarErrores

}//final clase GuardarErrores
