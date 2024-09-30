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
public class GuardarErrores {

    public static void guardarErrores(String ubicacionError, String tipoError, String descripcionError) throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "INSERT INTO errores (ubicacion_error, tipo_error, descripcion_error) VALUES ('" + ubicacionError + "','" + tipoError + "','" + descripcionError + "');";
        int filasAfectadas = stmt.executeUpdate(consulta);
        DevolverConexion.cerrarConexion(conexion);
        
    }//final metodo Guardar Errores
}//final clase GuardarErrores
