/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaquetesEnvio;

import java.io.Serializable;

/**
 *
 * @author plcgc
 * esta clase es un objeto de temperaturas de caldera para ser enviados por socket
 */
public class Temperaturas implements Serializable{

    //constructor con parametros
    public Temperaturas(float temperaturaCasa1, float temperaturaDeseada1, float temperaturaCaldera1, float estadoRele1) {
        
        this.temperaturaCasa = temperaturaCasa1;
        this.temperaturaDeseadaCasa = temperaturaDeseada1;
        this.temperaturaCaldera = temperaturaCaldera1;
        this.estadoRele = estadoRele1;
        
    }
    
    public float getTemperaturaCasa() {
        return temperaturaCasa;
    }


    public void setTemperaturaCasa(float temperaturaCasa) {
        this.temperaturaCasa = temperaturaCasa;
    }

    public float getTemperaturaDeseadaCasa() {
        return temperaturaDeseadaCasa;
    }

    public void setTemperaturaDeseadaCasa(float temperaturaDeseadaCasa) {
        this.temperaturaDeseadaCasa = temperaturaDeseadaCasa;
    }

    public float getTemperaturaCaldera() {
        return temperaturaCaldera;
    }

    public void setTemperaturaCaldera(float temperaturaCaldera) {
        this.temperaturaCaldera = temperaturaCaldera;
    }

    public float getEstadoRele() {
        return estadoRele;
    }

    public void setEstadoRele(float estadoRele) {
        this.estadoRele = estadoRele;
    }
    
    private float temperaturaCasa = 0.0f;
    private float temperaturaDeseadaCasa = 0.0f;
    private float temperaturaCaldera = 0.0f;
    private float estadoRele = 0.0f;
    
    
    
}//final clase Temperaturas
