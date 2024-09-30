/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import BasesDeDatos.DevolverConexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author plcgc base de datos que guarda las temperaturas para la pagina web el
 * orden es: //1.temp casa //2.temp seleccionada //3.temp caldera //bomba 30-40
 */
public class GuardarTemperaturasWeb {

    public static void guardaTemperaturasWeb(float temperaturaCasa, float temperaturaSeleccionada, float temperaturaCaldera, float estadoBomba) throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "INSERT INTO temps (temp1,temp2,temp3,temp4) VALUES ('" + temperaturaCasa + "','" + temperaturaSeleccionada + "','" + temperaturaCaldera + "','" + estadoBomba + "');";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);
        
        //copia de seguridad que no sera borrada
        guardaTemperaturasWebPermanente(temperaturaCasa, temperaturaSeleccionada, temperaturaCaldera, estadoBomba);

    }//final metodo guardaTemperaturasWeb

    //este metodo es llamado por guardaTemperaturasWeb para hacer una copia de los mismos datos solo que estos no se borran 
    public static void guardaTemperaturasWebPermanente(float temperaturaCasa, float temperaturaSeleccionada, float temperaturaCaldera, float estadoBomba) throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "INSERT INTO tempspermanente (tempcasa,tempseleccionada,tempcaldera,tempbomba) VALUES ('" + temperaturaCasa + "','" + temperaturaSeleccionada + "','" + temperaturaCaldera + "','" + estadoBomba + "');";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);

    }//final guardaTemperaturasWebPermanente

}//final guardarTemperaturasWeb
