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
 * @author Pedro Luis Esta clase devuelve la temperatura de la caldera llamando
 * a un archivo python que se conecta con arduino y guarda la temperatura en un
 * archivo de texto temporal que recuperamos con java y borramos
 */
public class DevuelveTemperaturaCaldera {

    public static void crearArchivoTemperaturaCaldera() throws IOException {

        String comando = "python C:\\ServidorCaldera\\SensorDs18b20Caldera.py";
        Process process = Runtime.getRuntime().exec(comando);

    }//final crearArchivoTemperaturaCaldera

    public static float compruebaTemperaturaCaldera() throws InterruptedException, FileNotFoundException, IOException {

        Thread.sleep(5000);
        File archivo = new File("C:\\ServidorCaldera\\datos.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea = "";
        linea = br.readLine();

        float temperatura = Float.parseFloat(linea);
        br.close();
        fr.close();

        return temperatura;

    }//final compruebaTemperaturaCaldera

    public static void eliminaArchivoTemperaturaCaldera() {

        File archivo = new File("C:\\ServidorCaldera\\datos.txt");

        try {

            archivo.delete();

        } catch (Exception e) {

            //GuardarErrores.guardarErrores("ComprobarTEmperaturas", "Al borrar el archivo datos.txt", "Parece que python no esta instalado");
        }

    }//final elimninaArchivoTEmperaturaCaldera

}//final DevuelveTemperaturaCaldera
