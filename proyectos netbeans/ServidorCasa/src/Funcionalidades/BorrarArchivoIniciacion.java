/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import java.io.File;

/**
 * Esta clase borra el archivo de iniciacion para que se puedan crear las bases de datos automaticamente
 * @author Pedro Luis
 */
public class BorrarArchivoIniciacion {
   
    public static void borrarArchivoIniciacion(){
        
        File archivo = new File("C:\\ServidorCasa\\PExec.dat");
        
        try{
            archivo.delete();
            System.out.println("borrado con exito");
        }catch(Exception e){
            System.out.println("error al borrar el archivo de iniciacion " + e.toString());
        }
        
    }//final metodo borrarArchivoIniciacion
    
}//final clase BorrarArchivoIniciacion
