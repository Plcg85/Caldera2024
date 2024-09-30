/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BasesDeDatos;

import Funcionalidades.DevuelveDatosDeIniciacion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pedro Luis
 */
public class DevolverConexion {

    public static Connection devolverConexionParaCrearBd() throws SQLException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String usuario = DevuelveDatosDeIniciacion.devuelveDatos(0);
        String password = DevuelveDatosDeIniciacion.devuelveDatos(1);

        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/", usuario, password);

        return conexion;

    }//final metodo devolverConexionParaCrearBd

    public static Connection devolverConexionParaCrearTablas() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        String usuario = DevuelveDatosDeIniciacion.devuelveDatos(0);
        String password = DevuelveDatosDeIniciacion.devuelveDatos(1);

        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/Temperaturas", usuario, password);

        return conexion;
    }

    public static void cerrarConexion(Connection conexion) throws SQLException {
        conexion.close();
    }//final metodo cerrarConexion

}//final clase DevolverConexion
