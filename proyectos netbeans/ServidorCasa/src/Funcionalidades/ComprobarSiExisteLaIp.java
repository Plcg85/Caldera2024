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
 * esta clase comprueba si la ip que se le pasa ya esta almacenada en la base de datos
 */
public class ComprobarSiExisteLaIp {
       
    public static boolean comprobarSiExisteLaIp(String ip) throws SQLException, IOException{
        
        boolean existe = false;
        
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from ip;";
        ResultSet resultado = stmt.executeQuery(consulta);
        
        while (resultado.next()){
            String direccion_ip_en_bd = resultado.getString("direccion_ip");
            if (direccion_ip_en_bd.equals(ip)){
                existe = true;
            }else{
                existe = false;
            }
        }//final while
        
        DevolverConexion.cerrarConexion(conexion);
        
        return existe;
    }
}//final clase
