/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import javax.swing.JTextArea;

/**
 *
 * @author plcgc
 */
public class EscribirEnInterfaz {
    
    public static void escribirFrase(String frase, JTextArea textArea){
    
        textArea.append(frase + "--> Fecha: " + DevolverFecha.devolverFecha());
        textArea.append("\n");
           
    }//final procedimiento escribirFrase
    
}//final Clase EscribirEnInterfaz
