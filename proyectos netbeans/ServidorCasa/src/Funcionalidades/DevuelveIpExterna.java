/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import Tareas.ComprobarIpExterna;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Luis
 */
public class DevuelveIpExterna {
    //este metodo devuelve la ip externa
    public static String dameIpExterna() {
        URL whatismyip = null;
        try {
            whatismyip = new URL("http://checkip.amazonaws.com");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ComprobarIpExterna.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
        } catch (IOException ex) {
            Logger.getLogger(ComprobarIpExterna.class.getName()).log(Level.SEVERE, null, ex);
        }

        String ip = null;
        try {
            ip = in.readLine(); //you get the IP as a String
        } catch (IOException ex) {
            Logger.getLogger(ComprobarIpExterna.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip;
    }//final metodo dameIpExterna
}
