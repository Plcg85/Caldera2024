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
 * @author Pedro Luis Esta clase devuelve un resulset de todas las ips
 * almacenadas en la base de datos
 */
public class DevuelveTodasIpAlmacenadas {

    public static ResultSet devolverTodasLasIpsAlmacenadas() throws SQLException, IOException {
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from ip";
        ResultSet resultadoBusqueda = stmt.executeQuery(consulta);
        
        return resultadoBusqueda;
    }//final metodo devolverTodasLasIpsAlmacenadas

}//
