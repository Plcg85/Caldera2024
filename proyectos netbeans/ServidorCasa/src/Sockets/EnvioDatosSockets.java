/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import PaquetesEnvio.TemperaturaSalon;
import Ventanas.VentanaPrincipal;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Pedro Luis
 * esta clase sirve para enviar datos por sockets
 */
public class EnvioDatosSockets {
    
    public static void enviarTemperaturaSalon(float temperaturaSalon) throws IOException{
    
        Socket miSocket = new Socket(VentanaPrincipal.ipServidorCaldera,VentanaPrincipal.puertoSocketServidorCaldera);
        TemperaturaSalon tempSalon = new TemperaturaSalon(temperaturaSalon);
        
        ObjectOutputStream oos = new ObjectOutputStream(miSocket.getOutputStream());
        oos.writeObject(tempSalon);
        
        oos.close();
        miSocket.close();
    
    }//final metodo enviarTemperaturaSalon
    
}//final clase envioDatos Sockets
