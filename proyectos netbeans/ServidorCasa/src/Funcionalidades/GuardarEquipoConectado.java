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
public class GuardarEquipoConectado {

    public static void guardarEquipoConectado(String direccionIp, String direccionMac, String marca, String nombre) throws SQLException, IOException {
               
        String nombreLocal = nombre; //nombre local es la variable nombre de este metodo

        nombreLocal = comprobarSiEquipoConocido(direccionMac); //si el equipo es conocido se devuelve su nombre almacenado

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "INSERT INTO conexiones_router (direccion_ip, direccion_mac, marca, nombre) VALUES ('" + direccionIp + "','" + direccionMac + "','" + marca + "','" + nombreLocal + "');";
        int filasAfectadas = stmt.executeUpdate(consulta);
        DevolverConexion.cerrarConexion(conexion);

    }//final metodo guardar equipo conectado

    //Este metodo actualiza el nombre del equipo conectado al router por uno dado
    public static void modificarNombreEquipoConectado(String direccionMac, String nombreNuevo) throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE conexiones_router set nombre = '" + nombreNuevo + "' where direccion_mac ='" + direccionMac + "'";
        int filasAfectadas = stmt.executeUpdate(consulta);
        DevolverConexion.cerrarConexion(conexion);

    }//final modificar nombre equipo conectado

    //este metodo devuelve el nomre del equipo cuando esta en la base de datos equipos conocidos y se le pasa la direccion mac
    private static String comprobarSiEquipoConocido(String direccionMac) throws SQLException, IOException {

        String nombreConocido = "";
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from equipos_conocidos where direccion_mac = '" + direccionMac + "';";
        ResultSet resultado = stmt.executeQuery(consulta);

        if (resultado.next()) {
            nombreConocido = resultado.getString("nombre");//se obtiene el nombre
            DevolverConexion.cerrarConexion(conexion);
            return nombreConocido;
        } else {
            DevolverConexion.cerrarConexion(conexion);
            return "Desconocido";
        }
        
    }//final comprobarSiEquipoConocido
}//final clase GuardarEquipoConectado
