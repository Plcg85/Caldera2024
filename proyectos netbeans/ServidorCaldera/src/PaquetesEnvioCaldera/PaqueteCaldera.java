/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaquetesEnvio;

import java.io.Serializable;

/**
 *
 * @author plcgc
 * esta clase es un objeto que guarda todos los datos que envia el socket hacia el servidor del salón
 * envia tanto las temperaturas como la configuracion de la caldera
 * 
 */
public class PaqueteCaldera implements Serializable{
 
    private float temperaturaCasa = 0.0f;
    private float temperaturaDeseadaCasa = 0.0f;
    private float temperaturaCaldera = 0.0f;
    private float estadoRele = 0.0f;
    
    private float tempSeteo = 0.0f;
    private int modoLenia = 0;
    private int modoForzoso = 0;

    public PaqueteCaldera(float temperaturaCasa1, float temperaturaDeseadaCasa1, float temperaturaCaldera1, float estadoRele1,float tempSeteo1, int modoLenia1, int modoForzoso1) {
    
        this.temperaturaCasa = temperaturaCasa1;
        this.temperaturaDeseadaCasa = temperaturaDeseadaCasa1;
        this.temperaturaCaldera = temperaturaCaldera1;
        this.estadoRele = estadoRele1;
        
        this.tempSeteo = tempSeteo1;
        this.modoLenia = modoLenia1;
        this.modoForzoso = modoForzoso1;
    
    }//final constructor

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

    public float getTempSeteo() {
        return tempSeteo;
    }

    public void setTempSeteo(float tempSeteo) {
        this.tempSeteo = tempSeteo;
    }

    public int getModoLenia() {
        return modoLenia;
    }

    public void setModoLenia(int modoLenia) {
        this.modoLenia = modoLenia;
    }

    public int getModoForzoso() {
        return modoForzoso;
    }

    public void setModoForzoso(int modoForzoso) {
        this.modoForzoso = modoForzoso;
    }
    
    
    
}//final clase paquetecaldera
