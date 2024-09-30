/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

/**
 *
 * @author Pedro Luis
 * alberga una variable que controla el numero dee comprobaciones de tempertura del sensor y se manda a la caldera, cada 10 comprobaciones se guarda en la base de datos
 * pero se envia a la caldera todas las veces
 */
public class NumeroComprobacionesTemperatura {
    
    public static int numeroDeComprobaciones = 0;    
    public static int numeroDeComprobacionesParaGuardar = 10; //teniendo en cuenta  que las comprobaciones se realizan cada 30 segundos tardaria en guardarse 5 minutos
    
}//final clase numeroComprobacionesTemperatura
