/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BasesDeDatos;

import java.sql.*;

/**
 * Esta clase borra toda la base de datos, en principio es solo para ir
 * programando
 *
 * @author Pedro Luis
 */
public class BorrarBaseDeDatos {

    public static void borrarBaseDeDatos() throws SQLException {

        try {
            Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
            Statement stmt = conexion.createStatement();
            String consulta = "drop database casa;";
            int numeroFilasAfectadas = stmt.executeUpdate(consulta);
            DevolverConexion.cerrarConexion(conexion);
        } catch (Exception e) {
            System.out.println("Error al borrar la base de datos porque no existe");
        }

    }//final metodo borrar Base De Datos

}//final clase
