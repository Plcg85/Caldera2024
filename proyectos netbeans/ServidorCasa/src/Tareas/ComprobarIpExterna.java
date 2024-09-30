/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tareas;

import Funcionalidades.DevuelveIpExterna;
import Funcionalidades.GuardarErrores;
import Funcionalidades.GuardarIpExterna;
import java.io.IOException;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase es llamada desde la ventana principal cada cierto tiempo
 * Comprueba la direccion ip externa y también la guarda si no es la actual en la base de datos
 * @author Pedro Luis
 */
public class ComprobarIpExterna extends TimerTask {

    @Override
    public void run() {
        
        String ip = DevuelveIpExterna.dameIpExterna();
                
        try {
            GuardarIpExterna.guardarIp(ip);
        } catch (SQLException ex) {
            try {
                Logger.getLogger(ComprobarIpExterna.class.getName()).log(Level.SEVERE, null, ex);
                GuardarErrores.guardarErrores("ComprobarIpExterna", "SQL", ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(ComprobarIpExterna.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IOException ex1) {
                Logger.getLogger(ComprobarIpExterna.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(ComprobarIpExterna.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//final run de la tarea Comprobar Ip Externa  
}//final clase ComprobarIpExterna
