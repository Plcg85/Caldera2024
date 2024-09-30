/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tareas;

import Funcionalidades.GuardarErrorConexionInternet;
import Funcionalidades.GuardarErrores;
import Funcionalidades.GuardarIpExterna;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Luis esta clase es una tarea que comprueba si hay conexion a
 * internet y guarda en la base de datos cuando no hay
 */
public class ComprobarConexion extends TimerTask {

    String direccionWeb = "www.google.es";
    int puerto = 80;

    @Override
    public void run() {

        try {
            Socket s = new Socket(direccionWeb, puerto);
            if (s.isConnected()) {
                //System.out.println("Hay internet");
            } else {
                //System.out.println("No hay internet");
            }
        } catch (IOException ex) {
            try {
                GuardarErrorConexionInternet.guardarErrorConexion();
                GuardarErrores.guardarErrores("ComprobarConexion", "No hay internet", ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(ComprobarConexion.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IOException ex1) {
                Logger.getLogger(ComprobarConexion.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("No hay internet");
        }

    }//final run

}//final tarea ComrpobarConexion
