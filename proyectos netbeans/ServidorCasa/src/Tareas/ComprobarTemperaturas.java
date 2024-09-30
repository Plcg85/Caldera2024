/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tareas;

import Funcionalidades.DevolverSistemaOperativo;
import Funcionalidades.GuardarErrores;
import Funcionalidades.GuardarTemperaturas;
import Funcionalidades.MensajesPopUp;
import Funcionalidades.NumeroComprobacionesTemperatura;
import Sockets.EnvioDatosSockets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Pedro Luis
 */
public class ComprobarTemperaturas extends TimerTask {

    float temperaturaSalon = 0.0f;
    float temperaturaCpu = 0.0f;
    float humedadSalon = 0.0f; //valor por defecto, en linux será 0.0f

    @Override
    public void run() {

        String sistemaOperativo = DevolverSistemaOperativo.devuelveElSistemaOperativo();

        if (sistemaOperativo.startsWith("W")) {

            try {
                //estamos en windows
                crearArchivoTemperaturaSalonWindows();//crea el archivo de temperaturas llamando a Temperaturas.py (en realidad es temperatura y humedad)
                try {
                    Thread.sleep(5000); //espera de 5 segundos mientras se crea el archivo de temperaturas y humedad
                    temperaturaSalon = compruebaTemperaturaSalonWindows();//solo la temperatura

                    humedadSalon = compruebaHumedadSalonWindows(); //solo la humedad
                    eliminarArchivoTemperaturaHumedad();

                    GuardarTemperaturas.guardarTemperaturas(temperaturaSalon, humedadSalon, temperaturaCpu);

                    //se resetea el valor
                    NumeroComprobacionesTemperatura.numeroDeComprobaciones = 0;

                } catch (SQLException | InterruptedException ex) {
                    Logger.getLogger(ComprobarTemperaturas.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) { //si falla lo que hace es conseguir la  temperatura del archivo de emergencia

                try {

                    GuardarErrores.guardarErrores("ComprobarTemperaturas", "Windows", "No se encuentra el archivo datos.txt(arduino)");
                    temperaturaSalon = ComprobarTemperaturas.compruebaTemperaturaSalonWindowsEmergencia();//solo la temperatura

                    humedadSalon = compruebaHumedadSalonWindowsEmergencia(); //solo la humedad
                    eliminarArchivoTemperaturaHumedad();

                    EnvioDatosSockets.enviarTemperaturaSalon(temperaturaSalon);
                    GuardarTemperaturas.guardarTemperaturas(temperaturaSalon, humedadSalon, temperaturaCpu);
                    
                    System.out.println("Guardado de archivo aux");

                    //MensajesPopUp.muestraMensajeInformacion("No se encuentra el archivo datos.txt (arduino)");
                } catch (SQLException | IOException ex1) {
                    Logger.getLogger(ComprobarTemperaturas.class.getName()).log(Level.SEVERE, null, ex1);
                }

            }
        } else {
            try {
                //estamos en linux que es donde se comprueba la temperatura.
                temperaturaSalon = compruebaTemperaturaSalon();
                temperaturaCpu = compruebaTemperaturaCpu();
                GuardarTemperaturas.guardarTemperaturas(temperaturaSalon, humedadSalon, temperaturaCpu);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(ComprobarTemperaturas.class.getName()).log(Level.SEVERE, null, ex);
            }
            //aqui es donde se guardan las temperaturas

            //aqui es donde se guardan las temperaturas
        }//final if

    }//final metodo run

    private float compruebaTemperaturaSalon() throws FileNotFoundException, IOException, SQLException {

        File archivo = new File("/sys/bus/w1/devices/28-0000072aaf0f/w1_slave");

        if (archivo.exists()) { //si existe el archivo

            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int numeroLineaArchivo = 0;//Para saber en que linea del archivo se está

            while ((linea = br.readLine()) != null) {

                numeroLineaArchivo++;

                if (numeroLineaArchivo == 2) {

                    String temperatura = linea.substring(29, 33);
                    temperaturaSalon = Float.parseFloat(temperatura) / 100;

                }//final if
            }//final de recorrer el archivo de la temperatura

            br.close();
            fr.close();

            return temperaturaSalon;
        } else {//si el archivo del sensor no existe

            GuardarErrores.guardarErrores("ComprobarTemperaturas", "No existe el archivo del sensor", "No hay archivo donde mirar");
            return 0;

        }

    }//final compruebaTemperaturaSalon

    private float compruebaTemperaturaCpu() throws FileNotFoundException, IOException, SQLException {

        File archivo1 = new File("/sys/class/thermal/thermal_zone0/temp");

        if (archivo1.exists()) {//si el archivo existe se hace el proceso

            FileReader fr1 = new FileReader(archivo1);
            BufferedReader br1 = new BufferedReader(fr1);
            String linea = "";

            while ((linea = br1.readLine()) != null) {

                temperaturaCpu = (Float.parseFloat(linea)) / 1000;

            }//final de recorrer el archivo de la temperatura

            br1.close();
            fr1.close();

            return temperaturaCpu;
        } else {//si el archivo de temperaturacpu no existe

            GuardarErrores.guardarErrores("ComprobarTemperaturas", "Error al comprobar archivo Cpu", "No existe el archivo");
            return 0;
        }

    }//final compruebaTemperaturaCpu

    public static float compruebaTemperaturaSalonWindows() throws FileNotFoundException, IOException {

        File archivo = new File("C:\\ServidorCasa\\datos.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea = "";
        linea = br.readLine();

        float temperatura = Float.parseFloat(linea);
        br.close();
        fr.close();

        return temperatura;

    }//dinal compruebaTemperaturaSalonWindows

    public static float compruebaTemperaturaSalonWindowsEmergencia() throws FileNotFoundException, IOException {

        //se ejecuta cuando falla compruebaTemperaturaSalonWindows
        File archivo = new File("C:\\ServidorCasa\\datosaux.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea = "";
        linea = br.readLine();

        float temperatura = Float.parseFloat(linea);
        br.close();
        fr.close();

        return temperatura;

    }//final compruebaTemperaturaSalonWindowsEmergencia

    public static void crearArchivoTemperaturaSalonWindows() throws IOException {

        String comando = "python C:\\ServidorCasa\\Temperatura.py";
        Process process = Runtime.getRuntime().exec(comando);

    }//final crearArchivoTemperaturasSalonWindows

    private float compruebaHumedadSalonWindows() throws FileNotFoundException, IOException {

        File archivo = new File("C:\\ServidorCasa\\datos.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea = "";
        linea = br.readLine();
        linea = br.readLine();

        float humedad = Float.parseFloat(linea);

        br.close();
        fr.close();

        return humedad;

    }//final compruebaHumedadSalonWindows

    //aqui se elimina el archivo temporal de temperatura y humedad
    public static void eliminarArchivoTemperaturaHumedad() throws SQLException, IOException {

        File archivo = new File("C:\\ServidorCasa\\datos.txt");

        try {

            archivo.delete();

        } catch (Exception e) {

            GuardarErrores.guardarErrores("ComprobarTEmperaturas", "Al borrar el archivo datos.txt", "Parece que python no esta instalado");
        }

    }//final eliminarArchivoTemperaturaHumedad

    private float compruebaHumedadSalonWindowsEmergencia() throws FileNotFoundException, IOException {

        File archivo = new File("C:\\ServidorCasa\\datosaux.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea = "";
        linea = br.readLine();
        linea = br.readLine();

        float humedad = Float.parseFloat(linea);

        br.close();
        fr.close();

        return humedad;

    }//final compruebaHumedadSalonWindowsEmergencia

}//final clase ComprobarTemperaturaCasa
