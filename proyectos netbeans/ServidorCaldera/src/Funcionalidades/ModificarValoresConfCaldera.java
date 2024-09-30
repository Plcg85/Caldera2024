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
 * @author Pedro Luis
 */
public class ModificarValoresConfCaldera {

    //Este procedimiento es llamado por un boton cuando quieres setear una nueva temperatura de la
    //caldera
    public static void modificarTemperaturaSeteo(float nuevaTemperaturaSeteo) throws SQLException, IOException {

        float superiorACero = 0;// condicion para que se cumpla siempre

        //cuando se cambia la temperatura de seteo de la caldera tambien se cambian los valores
        //de temperaturade apagado y de temperatura de encendido bomba
        float temperaturaApagadoBomba = nuevaTemperaturaSeteo - 3;
        float temperaturaEncendidoBomba = nuevaTemperaturaSeteo + 3;

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set tempSeteo = '" + nuevaTemperaturaSeteo + "' where tempSeteo >='" + superiorACero + "'";
        int filasAfectadas = stmt.executeUpdate(consulta);

        String consulta2 = "UPDATE confcaldera set tempEncendidoBomba = '" + temperaturaEncendidoBomba + "' where tempSeteo >='" + superiorACero + "'";
        filasAfectadas = stmt.executeUpdate(consulta2);

        String consulta3 = "UPDATE confcaldera set tempApagadoBomba = '" + temperaturaApagadoBomba + "' where tempSeteo >='" + superiorACero + "'";
        filasAfectadas = stmt.executeUpdate(consulta3);

        DevolverConexion.cerrarConexion(conexion);

    }//final procedimiento modificarTablaCaldera

    public static void modificarTemperaturaSeleccionada(float temperaturaSeleccionadaCasa) throws SQLException, IOException {

        float superiorACero = 0;// condicion para que se cumpla siempre

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set tempSeleccionada = '" + temperaturaSeleccionadaCasa + "' where tempSeteo >='" + superiorACero + "'";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);

    }//final metodo modificarTemperaturaSeleccionada

    public static void encenderModoLenia() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set modoLenia = 1";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);

    }//final encender modo Leña

    public static void apagarModoLenia() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set modoLenia = 0";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);

    }//final apagar modo leña

    public static void encenderModoForzoso() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set modoForzoso = 1";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);

    }//final metodo encenderModoForzoso

    public static void apagarModoForzoso() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set modoForzoso = 0";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);

    }//final apagarModoForzoso

    public static void encenderBomba() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set estadoRele = 40.00";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);

    }//final metodo encenderBomba

    public static void apagarBomba() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "UPDATE confcaldera set estadoRele = 30.00";
        int filasAfectadas = stmt.executeUpdate(consulta);
        
        DevolverConexion.cerrarConexion(conexion);
        
    }//final metodo apagarBomba

}//en esta clase se modifican los valores de la base de datos confCaldera
