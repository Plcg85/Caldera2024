/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tareas;

import Funcionalidades.DevolverSistemaOperativo;
import Funcionalidades.DevuelveResultadoEjecutarComando;
import Funcionalidades.GuardarEquipoConectado;
import Funcionalidades.GuardarErrores;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Luis
 */
public class ComprobarEquiposConectados extends TimerTask {

    int contadorEquipo = 0; //cuando esta variable llegue a 3 tenemos un nuevo equipo a guardar en la base de datos y se pondra a 0
    String direccionMac = "";
    String marca = "";
    String nombre = "Desconocido";//esto tambien hay que guardarlo para luego poder darle nombre
    String direccionIp = "";

    @Override
    public void run() {
        
        //nmap -sP 192.168.1.1-254
        String sistemaOperativo = DevolverSistemaOperativo.devuelveElSistemaOperativo();

        if (sistemaOperativo.startsWith("W")) {

            BufferedReader lectura = null;//aqui se almacenará el resultado del comando que llega desde el metodo

            //estamos en windows
            String comando = "C:\\Program Files (x86)\\Nmap\\nmap.exe nmap -sn 192.168.1.0/24";

            try {
                lectura = DevuelveResultadoEjecutarComando.devuelveResultadoEjecutarComando(comando);
                String linea;

                while ((linea = lectura.readLine()) != null) {
                    separaInformacionEnEquipos(linea);
                }

                lectura.close(); //se cierra el buffer
                DevuelveResultadoEjecutarComando.reader.close(); //se cierra el buffer de la clase DevuelveResultadoEjecutarComando

            } catch (IOException ex) {
                try {
                    GuardarErrores.guardarErrores("ComprobarEquiposConectados", "error si es en windows", ex.toString());
                } catch (SQLException ex1) {
                    Logger.getLogger(ComprobarEquiposConectados.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (IOException ex1) {
                    Logger.getLogger(ComprobarEquiposConectados.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(ComprobarEquiposConectados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ComprobarEquiposConectados.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //estamos en otra plataforma, actuaré como si fuera linux
            BufferedReader lectura = null;//aqui se almacenará el resultado del comando que llega desde el metodo

            //estamos en windows
            String comando = "sudo nmap -sn 192.168.1.0/24";

            try {
                lectura = DevuelveResultadoEjecutarComando.devuelveResultadoEjecutarComando(comando);
                String linea;

                while ((linea = lectura.readLine()) != null) {
                    //System.out.println(linea);
                    separaInformacionEnEquipos(linea);
                }
                contadorEquipo = 0;//se resetea el contador
                lectura.close(); //se cierra el buffer
                DevuelveResultadoEjecutarComando.reader.close(); //se cierra el buffer de la clase DevuelveResultadoEjecutarComando

            } catch (IOException ex) {
                try {
                    GuardarErrores.guardarErrores("ComprobarEquiposConectados", "error si es en windows", ex.toString());
                } catch (SQLException ex1) {
                    Logger.getLogger(ComprobarEquiposConectados.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (IOException ex1) {
                    Logger.getLogger(ComprobarEquiposConectados.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(ComprobarEquiposConectados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ComprobarEquiposConectados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//final else

    }//final clase ComprobarEquiposConectados

    //este metodo es llamado por el metodo run de esta tarea y separa la información creando conjuntos de equipos con su
    //direccion mac , direccion ip y su nombre generico
    private void separaInformacionEnEquipos(String linea) throws SQLException, IOException {
        
        if (linea.startsWith("Nmap scan report for")) { //de aqui se coge la direccion ip

            direccionIp = linea.substring(21, linea.length());
            contadorEquipo++;

        }//final if
        if (linea.startsWith("MAC Address")) { //de aqui se coge la direccion la direccion mac y la marca

            direccionMac = linea.substring(13, 30);
            contadorEquipo++;
            marca = linea.substring(32, linea.length() - 1);
            contadorEquipo++;

        }//final if
        if (contadorEquipo >= 3) {

            contadorEquipo = 0;//se resetea
            GuardarEquipoConectado.guardarEquipoConectado(direccionIp, direccionMac, marca, nombre);
            
        }//si hay 3 es porque ya se han conseguido todos los datos del equipo
    }//final separaInformacionEnEquipos

}//final clase equipos conectados
