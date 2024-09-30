/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Pedro Luis
 */
public class DevuelveDatosDeIniciacion {
    
//este metodo devuelve el usuario dado cuando se inicia el programa por primera vez
    public static String devuelveDatos(int opcion) throws FileNotFoundException, IOException {
        
        File f = new File("C:\\ServidorCasa\\PExec.dat");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        String usuario = br.readLine();
        String contrasena = br.readLine();
        
        br.close();
        fr.close();
        
        if(opcion == 0){
            return usuario;
        }else return contrasena;    
    
    }//final metodo devuelveDatos
}//final de la clase
