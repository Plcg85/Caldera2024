/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BasesDeDatos;

import Funcionalidades.CrearArchivoIniciacion;
import Funcionalidades.DevuelveDatosDeIniciacion;
import java.io.IOException;
import java.sql.*;

/**
 * Esta clase devuelve un objeto de tipo conexion a la base de datos
 *
 * @author Pedro Luis
 */
public class DevolverConexion {

    //este metodo devuelve la conexion pero directamente a localhost para que se cree la bd casa
    public static Connection devolverConexionParaCrearBd() throws SQLException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
        //String usuario = "root";
        //String password = "";
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

        //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/casa", "root", "");
        //String usuario = "root";
        //String password = "";
        String usuario = DevuelveDatosDeIniciacion.devuelveDatos(0);
        String password = DevuelveDatosDeIniciacion.devuelveDatos(1);
        
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/casa", usuario, password);

        return conexion;
    }

    public static void cerrarConexion(Connection conexion) throws SQLException {
        conexion.close();
    }//final metodo cerrarConexion

}//final clase DevolverConexion
