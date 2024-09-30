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
public class GuardarIpExterna {

    public static void guardarIp(String ip) throws SQLException, IOException {

        //Antes de guardar la ip en la base de datos hay que comprobar que no exista ya
        if (ComprobarSiExisteLaIp.comprobarSiExisteLaIp(ip) == true) {//si ya existe la ip en la base de datos no se guarda
            //System.out.println("La direccion ip ya esta almacenada");
        } else {
            Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
            Statement stmt = conexion.createStatement();
            String consulta = "INSERT INTO ip (direccion_ip) VALUES ('" + ip + "');";
            int filasAfectadas = stmt.executeUpdate(consulta);
            DevolverConexion.cerrarConexion(conexion);
        }

    }//final metodo guardarIpExterna
}//final clase
