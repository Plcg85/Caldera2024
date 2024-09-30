/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import Funcionalidades.GuardarTemperaturaCasa;
import PaquetesEnvio.TemperaturaSalon;
import Ventanas.VentanaPrincipal;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author plcgc
 */
public class ServidorCaldera implements Runnable {

    public ServidorCaldera() {

        Thread miHilo = new Thread(this);
        miHilo.start();

    }//final constructor

    @Override
    public void run() {

        try {

            ServerSocket servidor = new ServerSocket(VentanaPrincipal.puertoSocketServidorCaldera);
            float temperaturaSalon = 0.0f;

            TemperaturaSalon datosTemperaturaSalon;

            while (true) {

                Socket miSocket = servidor.accept();
                ObjectInputStream ois = new ObjectInputStream(miSocket.getInputStream());

                try {

                    datosTemperaturaSalon = (TemperaturaSalon) ois.readObject();
                    float datosRecibidos = datosTemperaturaSalon.getTemperaturaSalon();
                    
                    GuardarTemperaturaCasa.guardarTemperaturaCasa(datosRecibidos);
                    System.out.println(datosRecibidos);

                } catch (Exception e) {
                    System.out.println("Error al guardar " + e.toString());
                } finally {
                    ois.close();
                    miSocket.close();
                }//final try datosTemperaturas
                
            }//final  bucle infinito

        } catch (IOException ex) {
            Logger.getLogger(ServidorCaldera.class.getName()).log(Level.SEVERE, null, ex);
        }
        //final catch
        //final catch

    }//final metodo run

}//final clase ServidorCaldera
