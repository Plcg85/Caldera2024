/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

/**
 *
 * @author Pedro Luis
 */
public class DevolverSistemaOperativo {
    
    public static String devuelveElSistemaOperativo(){
    
        return System.getProperty("os.name");
        
    }//final metodo estatico
}//final clase
