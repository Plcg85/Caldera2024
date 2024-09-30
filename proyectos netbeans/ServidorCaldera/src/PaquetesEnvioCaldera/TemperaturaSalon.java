/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaquetesEnvio;

import java.io.Serializable;

/**
 *
 * @author plcgc
 */
public class TemperaturaSalon implements Serializable{
    
    public TemperaturaSalon(float temperaturaSalon1) {
    
        this.temperaturaSalon = temperaturaSalon1;
        
    }//final constructor
    
    private float temperaturaSalon = 0.0f;

    public float getTemperaturaSalon() {
        return temperaturaSalon;
    }

    public void setTemperaturaSalon(float temperaturaSalon) {
        this.temperaturaSalon = temperaturaSalon;
    }
    
}//final clase
