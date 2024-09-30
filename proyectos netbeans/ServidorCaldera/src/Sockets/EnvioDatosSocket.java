/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import Funcionalidades.EscribirEnInterfaz;
import PaquetesEnvio.PaqueteCaldera;
import PaquetesEnvio.Temperaturas;
import static Ventanas.VentanaPrincipal.ipServidorCasa;
import static Ventanas.VentanaPrincipal.puertoSocketServidorCasa;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author plcgc esta clase se utiliza para enviar datos por sockets
 */
public class EnvioDatosSocket {

    public static void enviarDatosCalderaAServidorCasa(float temperaturaCasa1, float temperaturaDeseadaCasa1, float temperaturaCaldera1, float estadoRele1, float tempSeteo1, int modoLenia1, int modoForzoso1) throws IOException {

        //Se envian los datos de la caldera al servidor casa
        Socket miSocket = new Socket(ipServidorCasa, puertoSocketServidorCasa); //se crea el socket al servidor de la casa¡
        
        PaqueteCaldera datosCaldera = new PaqueteCaldera(temperaturaCasa1, temperaturaDeseadaCasa1, temperaturaCaldera1, estadoRele1, tempSeteo1, modoLenia1, modoForzoso1);

        ObjectOutputStream oos = new ObjectOutputStream(miSocket.getOutputStream());
        oos.writeObject(datosCaldera);

        oos.close();
        miSocket.close();

    }//final enviarDatos a servidorCasa

}//final clase envioDatosSocket
