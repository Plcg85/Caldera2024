/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BasesDeDatos;

import java.io.IOException;
import java.sql.*;

/**
 * crea toda la basew de datos y su estructura
 *
 * @author Pedro Luis
 */
public class CrearEstructuraBaseDeDatos {

    public static void crearNuevaBd() throws SQLException, IOException {
        
        Connection conexion = DevolverConexion.devolverConexionParaCrearBd();
        Statement stmt = conexion.createStatement();
        
        String consulta = "create database casa;";
        int numeroFilasAfectadas = stmt.executeUpdate(consulta);
        DevolverConexion.cerrarConexion(conexion);
        
        //ahora se crean las tablas observar que la conexion es diferente
        Connection conexion2 = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt2 = conexion2.createStatement();
       
        //tabla de direccion ip o log
        consulta = "CREATE TABLE ip (direccion_ip varchar(16),created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla de direccion errores
        consulta = "CREATE TABLE errores (ubicacion_error varchar(50), tipo_error varchar(50),descripcion_error varchar(255),created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla de conexion a internet
        consulta = "CREATE TABLE conexion_internet (estado_conexion varchar (30),created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla conexiones_router
        consulta = "CREATE TABLE conexiones_router (direccion_ip varchar(30), direccion_mac varchar(17),marca varchar(40),nombre varchar(40),created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla equipos_conocidos
        consulta = "CREATE TABLE equipos_conocidos (direccion_mac varchar(17),nombre varchar(40),created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla temperaturas
        consulta = "CREATE TABLE temperaturas (temperatura_salon FLOAT, humedad_salon FLOAT ,temperatura_cpu FLOAT,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //se cierra la conexion al final
        DevolverConexion.cerrarConexion(conexion2);
        
    }//final procedimiento crearNuevaBd
}//final clase CrearEstructuraBaseDeDatos
