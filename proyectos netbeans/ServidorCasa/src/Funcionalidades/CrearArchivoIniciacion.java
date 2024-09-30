/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * este archivo se crea la primera vez que se ejecuta el programa. luego solo se
 * comprueba su existencia
 *
 * @author Pedro Luis
 */
public class CrearArchivoIniciacion {

    public static void crearArchivoInicial() throws IOException {

        String contrasena = "";
        String contrasena1 = "1";
        String usuario = "";

        //este bucle se va a repetir hasta que las contraseñas sean las mismas
        while (!contrasena.equals(contrasena1)) {

            usuario = JOptionPane.showInputDialog(null, "Introduce el usuario de la base de datos", "Usuario", 0);
            contrasena = JOptionPane.showInputDialog(null, "Introduce la contraseña de la base de datos", "Usuario", 0);
            contrasena1 = JOptionPane.showInputDialog(null, "Vuelve a introducir la contraseña", "Usuario", 0);

        }//final while

        FileWriter fw = new FileWriter("C:\\ServidorCasa\\PExec.dat"); //se crea el archivo para que ya no se creen mas veces las bd
        PrintWriter pw = new PrintWriter(fw);
        pw.write(usuario + "\n");
        pw.write(contrasena);
        pw.close();
        fw.close();

    }//final metodo crearArchivoInicial
        
}//final CrearArchivoIniciacion
