/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import PaquetesEnvio.PaqueteCaldera;
import Tareas.ComprobarTemperaturas;
import Ventanas.VentanaPrincipal;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Luis el servidor de casa que atiende peticiones
 */
public class ServidorCasa implements Runnable {

    public ServidorCasa() {

        Thread miHilo = new Thread(this);
        miHilo.start();

    }//constructor

    @Override
    public void run() {

        try {

            ServerSocket servidor = new ServerSocket(VentanaPrincipal.puertoSocketServidorCasa);

            PaqueteCaldera datosCaldera;

            while (true) {

                Socket miSocket = servidor.accept();

                ObjectInputStream ois = new ObjectInputStream(miSocket.getInputStream());

                try {

                    float temperaturaCasa, temperaturaDeseadaCasa, temperaturaCaldera, estadoRele, tempSeteo;
                    int modoLenia, modoForzoso;

                    datosCaldera = (PaqueteCaldera) ois.readObject(); ///cast al objeto recibido 
                    String datosRecibidos = "" + datosCaldera.getTemperaturaCasa() + " -- " + datosCaldera.getTemperaturaDeseadaCasa() + " -- " + datosCaldera.getTemperaturaCaldera() + " -- " + datosCaldera.getEstadoRele() + " -- " + datosCaldera.getTempSeteo() + " -- " + datosCaldera.getModoLenia() + " -- " + datosCaldera.getModoForzoso();
                    //System.out.println("Los datos recibidos son: " + datosRecibidos);

                    //si lo que se han recibido son datos de temperaturas de la caldera se le mendan los datos de la temperatura casa
                    ComprobarTemperaturas.crearArchivoTemperaturaSalonWindows();//crea el archivo de temperaturas llamando a Temperaturas.py (en realidad es temperatura y humedad)
                    Thread.sleep(3000); //espera de 3 segundos mientras se crea el archivo de temperaturas y humedad

                    float temperaturaSalon = ComprobarTemperaturas.compruebaTemperaturaSalonWindows();//solo la temperatura
                    ComprobarTemperaturas.eliminarArchivoTemperaturaHumedad();

                    EnvioDatosSockets.enviarTemperaturaSalon(temperaturaSalon);
                    System.out.println("Datos enviados " + temperaturaSalon);

                } catch (Exception e) {

                    float temperaturaSalonEmergencia = ComprobarTemperaturas.compruebaTemperaturaSalonWindowsEmergencia();//solo la temperatura
                    EnvioDatosSockets.enviarTemperaturaSalon(temperaturaSalonEmergencia);
                    System.out.println("Error Servidor Casa" + e.toString());

                } finally {
                    ois.close();
                    miSocket.close();
                }

            }//final bucle infinito

        } catch (IOException ex) {

            //Logger.getLogger(ServidorCasa.class.getName()).log(Level.SEVERE, null, ex);

            try {

                float temperaturaSalonEmergencia;
                temperaturaSalonEmergencia = ComprobarTemperaturas.compruebaTemperaturaSalonWindowsEmergencia(); //solo la temperatura
                EnvioDatosSockets.enviarTemperaturaSalon(temperaturaSalonEmergencia);

            } catch (IOException ex1) {
                Logger.getLogger(ServidorCasa.class.getName()).log(Level.SEVERE, null, ex1);
            }

            System.out.println("Error Servidor Casa" + ex.toString());
        }

    }//final metodo run

}//final ServidorCasa
