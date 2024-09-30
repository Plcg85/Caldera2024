/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import java.time.LocalDateTime;

/**
 *
 * @author plcgc esta clase devuelve la fecha en formato:
 */
public class DevolverFecha {

    public static String devolverFecha() {
        
        LocalDateTime hoy = LocalDateTime.now();

        String fecha = hoy.getDayOfMonth() + "/" + hoy.getMonthValue() + "/" + hoy.getYear() + "--" + hoy.getHour() + ":" + hoy.getMinute() + ":" + hoy.getSecond();
        return fecha;
        
    }//final devolver fecha

}//devuelve la fecha en formato:
