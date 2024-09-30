/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subirtemperaturamediogrado;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author plcgc
 */
public class SubirTemperaturaMedioGrado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {

        float tempSeleccionada = comprobarTempCalderaActual();
        tempSeleccionada = tempSeleccionada + 0.5f;
        actualizarTempSeleccionada(tempSeleccionada);

    }//final main

    private static float comprobarTempCalderaActual() throws SQLException, IOException {

        Connection conexion = devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);

        resultado.next();//hay que hacerlo para acceder al primer resultado
        float temperaturaSeleccionada = resultado.getFloat("tempSeleccionada");

        cerrarConexion(conexion);
        return temperaturaSeleccionada;

    }//final metodo comprobarTempCalderaActual

    private static void actualizarTempSeleccionada(float tempSeleccionada1) throws SQLException, IOException {

        float superiorACero = 0;// condicion para que se cumpla siempre

        Connection conexion = devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set tempSeleccionada = '" + tempSeleccionada1 + "' where tempSeteo >='" + superiorACero + "'";
        int filasAfectadas = stmt.executeUpdate(consulta);

        cerrarConexion(conexion);

    }//final metodo actualizarTempSeleccionada
    
    public static Connection devolverConexionParaCrearBd() throws SQLException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");

        return conexion;

    }//final metodo devolverConexionParaCrearBd

    public static Connection devolverConexionParaCrearTablas() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/Temperaturas", "root", "");

        return conexion;
    }

    public static void cerrarConexion(Connection conexion) throws SQLException {
        conexion.close();
    }//final metodo cerrarConexion
    
}
