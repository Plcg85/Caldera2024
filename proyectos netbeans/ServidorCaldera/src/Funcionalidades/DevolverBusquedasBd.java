/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import BasesDeDatos.DevolverConexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pedro Luis Esta clase devuelve las busquedas que se realizan sobre
 * las bases de datos
 */
public class DevolverBusquedasBd {

    public static float devuelveUltimaTemperaturaCasa() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from tempcasa order by created_at desc limit 1";
        ResultSet resultado = stmt.executeQuery(consulta);

        resultado.next();//hay que hacerlo para acceder al primer resultado
        float ultimaTemperaturaCasa = resultado.getFloat("temp");

        DevolverConexion.cerrarConexion(conexion);

        return ultimaTemperaturaCasa;

    }//final metodo devuelveTemperaturaCasa

    //Este metodo devuelve la temperatura de seteo que hay en la base de datos
    public static float devuelveTemperaturaSeteo() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);

        resultado.next();//hay que hacerlo para acceder al primer resultado
        float temperaturaSeteo = resultado.getFloat("tempSeteo");

        DevolverConexion.cerrarConexion(conexion);

        return temperaturaSeteo;

    }//final devuelveTemperaturaSeteo

    //este metodo devuelve la temperatura seleccionada que esta en la bd
    //para la casa, la que queremos tener
    public static float devuelveTemperaturaSeleccionada() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);

        resultado.next();//hay que hacerlo para acceder al primer resultado
        float temperaturaSeleccionada = resultado.getFloat("tempSeleccionada");

        DevolverConexion.cerrarConexion(conexion);
        return temperaturaSeleccionada;
    }//final devuelveTemperaturaSeleccionada

    public static int estadoModoLenia() throws SQLException, IOException {

        //devuelve el estado del modoLenia
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);
        resultado.next();
        int modoLenia = resultado.getInt("modoLenia");
        DevolverConexion.cerrarConexion(conexion);

        return modoLenia;

    }//final metodo estadoModoLenia

    public static float devuelveEstadoRele() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);
        resultado.next();
        float estadoRele = resultado.getFloat("estadoRele");
        DevolverConexion.cerrarConexion(conexion);

        return estadoRele;

    }//final devuelveEstadoRele 

    public static int estadoModoForzoso() throws SQLException, IOException {

        //devuelve el estado del modoForzoso
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);
        resultado.next();
        int modoForzoso = resultado.getInt("modoForzoso");
        DevolverConexion.cerrarConexion(conexion);

        return modoForzoso;

    }//devuelve el estado del modo forzoso

    public static float devuelveTempEncendidoBomba() throws SQLException, IOException {

        //devuelve la temperatura a la que se debe encender la bomba
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);
        resultado.next();
        float tempEncendidoBomba = resultado.getFloat("tempEncendidoBomba");
        DevolverConexion.cerrarConexion(conexion);

        return tempEncendidoBomba;

    }//final del metodo devuelveTempEncendidoBomba

    public static float devuelveTempApagadoBomba() throws SQLException, IOException {

        //devuelve la temperatura a la que se debe apagar la bomba
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);
        resultado.next();
        float tempApagadoBomba = resultado.getFloat("tempApagadoBomba");
        DevolverConexion.cerrarConexion(conexion);

        return tempApagadoBomba;

    }//final metodo devuelveTempApagadoBomba

    public static float devuelveTempMaximaModoLenia() throws SQLException, IOException {

        //devuelve la temperatura maxima de la caldera hasta que se enciende el relé
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);
        resultado.next();
        float tempMaxModoLenia = resultado.getFloat("tempMaxModoLenia");
        DevolverConexion.cerrarConexion(conexion);

        return tempMaxModoLenia;

    }//final devuelveTempMaximaModoLenia

    public static float devuelveTempMaximaSeguridad() throws SQLException, IOException {

        //devuelve la temperatura maxima que debe operar la caldera en modo normal (no leña)
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);
        resultado.next();
        float tempMaximaSeguridad = resultado.getFloat("tempMaximaSeguridad");
        DevolverConexion.cerrarConexion(conexion);

        return tempMaximaSeguridad;

    }//final devuelveTempMaximaSeguridad

    public static float devuelveTempMinimaFuncionamiento() throws SQLException, IOException {

        //devuelve la temperatura minima de funcionamiento, por debajo de esta, la bomba nunca se activa
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from confcaldera";
        ResultSet resultado = stmt.executeQuery(consulta);
        resultado.next();
        float tempMinimaFuncionamiento = resultado.getFloat("tempMinimaFuncionamiento");
        DevolverConexion.cerrarConexion(conexion);

        return tempMinimaFuncionamiento;

    }//final devuelveTempMinimaFuncionamiento

    public static float ultimaTempCaldera() throws SQLException, IOException {
        
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from temps order by created_at desc limit 1";
        ResultSet resultado = stmt.executeQuery(consulta);

        resultado.next();//hay que hacerlo para acceder al primer resultado
        float ultimaTemperaturaCaldera = resultado.getFloat("temp3");

        DevolverConexion.cerrarConexion(conexion);

        return ultimaTemperaturaCaldera;

    }//final ultimaTempCaldera

}//final DevolverBusquedasBd
