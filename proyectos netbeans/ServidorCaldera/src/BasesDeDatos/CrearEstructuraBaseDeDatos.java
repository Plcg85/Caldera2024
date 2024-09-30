/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BasesDeDatos;

import BasesDeDatos.DevolverConexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pedro Luis
 */
public class CrearEstructuraBaseDeDatos {

    public static void crearNuevaBd() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearBd();
        Statement stmt = conexion.createStatement();

        String consulta = "create database Temperaturas;";
        int numeroFilasAfectadas = stmt.executeUpdate(consulta);
        DevolverConexion.cerrarConexion(conexion);

        //ahora se crean las tablas observar que la conexion es diferente
        Connection conexion2 = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt2 = conexion2.createStatement();

        //tabla temps esta es para la pagina web solamente 
        //1.temp casa  //2.temp seleccionada  //3.temp caldera //bomba 30-40
        consulta = "CREATE TABLE temps (temp1 float,temp2 float,temp3 float,temp4 float,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla tempspermanente esta es para la pagina web solamente 
        //1.temp casa  //2.temp seleccionada  //3.temp caldera //bomba 30-40
        consulta = "CREATE TABLE tempspermanente (tempcasa float,tempseleccionada float,tempcaldera float,tempbomba float,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla caldera
        consulta = "CREATE TABLE confcaldera (tempSeteo float,tempSeleccionada float,tempMaximaSeguridad float,modoLenia int,tempMaxModoLenia float,tempEncendidoBomba float,tempApagadoBomba float, modoForzoso int, tempMinimaFuncionamiento float, estadoRele float);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla temperatura casa aqui se guarda la temperatura que llega desde el servidor del salon
        consulta = "CREATE TABLE tempcasa (temp float,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //tabla de  errores
        consulta = "CREATE TABLE errores (ubicacion_error  varchar(50),tipo_error varchar(50),descripcion_error varchar(255),created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        numeroFilasAfectadas = stmt2.executeUpdate(consulta);
        
        //se ciierra la conexion al final
        DevolverConexion.cerrarConexion(conexion);

    }//final procedimiento crearNuevaBd

}//final clase CrearEstructuraBaseDeDatos
