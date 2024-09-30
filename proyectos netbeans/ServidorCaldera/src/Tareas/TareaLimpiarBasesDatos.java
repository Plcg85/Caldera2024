/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tareas;

import BasesDeDatos.DevolverConexion;
import Funcionalidades.GuardarErrores;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author plcgc
 */
public class TareaLimpiarBasesDatos extends TimerTask {

    Connection conexion;

    @Override
    public void run() {

        try {
            limpiarTempCasa(); //esta es la base de datos donde solo se almacena la temperatura de casa, ninguna otra
            limpiarTemps(); //esta tabla es la que contiene la informacion de la pagina web 
        } catch (SQLException | IOException ex) {
            Logger.getLogger(TareaLimpiarBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
               

    }//final metodo run

    private void limpiarTempCasa() throws SQLException, IOException {

        try {

            conexion = DevolverConexion.devolverConexionParaCrearTablas();
            Statement stmt = conexion.createStatement();
            String consulta = "delete from tempcasa where created_at <= CURRENT_TIMESTAMP - INTERVAL 2 Day;";
            int filasAfectadas = stmt.executeUpdate(consulta);

            System.out.println("Numero de filas afectadas: " + filasAfectadas);

        } catch (Exception e) {

            //si ha dado error lo que hay que hacer es guardar el error
            GuardarErrores.guardarErrores("TareaLimpiarBasesDatos", "Limpiando tabla tempcasa", e.toString());
            
        } finally {
            try {
                DevolverConexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(TareaLimpiarBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//final limpiarTempCasa

    private void limpiarTemps() throws SQLException, IOException {

        try {

            conexion = DevolverConexion.devolverConexionParaCrearTablas();
            Statement stmt = conexion.createStatement();
            String consulta = "delete from temps where created_at <= CURRENT_TIMESTAMP - INTERVAL 2 Day;";
            int filasAfectadas = stmt.executeUpdate(consulta);

            System.out.println("Numero de filas afectadas: " + filasAfectadas);

        } catch (Exception e) {

            //si ha dado error lo que hay que hacer es guardar el error
            GuardarErrores.guardarErrores("TareaLimpiarBasesDatos", "Limpiando tabla temps", e.toString());
            
        } finally {
            try {
                DevolverConexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(TareaLimpiarBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//final limpiarTemps

}//final Clase TareaLimpiarBasesDatos
