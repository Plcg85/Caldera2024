/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import BasesDeDatos.CrearEstructuraBaseDeDatos;
import BasesDeDatos.DevolverConexion;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Esta clase comprueba si es la primera ejecución del programa a traves de un archivo. si es la primera vez crea dicho archivo
 * y ademas crea la estructura de las bases de datos
 * @author Pedro Luis
 */
public class ComprobarPrimeraEjecucion {
    
    public static void esPrimeraEjecucion() throws IOException, SQLException{
        
        File archivo = new File("C:\\ServidorCasa\\PExec.dat");//este es el archivo que indica si es la primera ejecucion del programa
        
        if (archivo.exists()){
            
            System.out.println("El archivo de iniciacion existe, no hace falta crear las bases de datos");
            
        }else{
            
            System.out.println("El archivo de iniciacion no existe, se creará junto a las bases de datos");
            
            //es la primera ejecucion y hay que crear toda la estructura de la base de datos
            CrearArchivoIniciacion.crearArchivoInicial();
            CrearEstructuraBaseDeDatos.crearNuevaBd();
            
        }//final comprobacion si existe el archivo
                
    }//final metodo estatico
}//final clase ComprobarPrimeraEjecucion
