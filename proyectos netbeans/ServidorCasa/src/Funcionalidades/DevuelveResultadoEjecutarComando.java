/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Pedro Luis
 */
public class DevuelveResultadoEjecutarComando {

    public static BufferedReader reader = null;
    
    public static BufferedReader devuelveResultadoEjecutarComando(String comando) throws IOException {

                
        Process process = Runtime.getRuntime().exec(comando);

        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        return reader;
        
    }//final metodo devuelveResultadoEjecutarComando
    
}//final clase DevuelveResultadoEjecutarComando
