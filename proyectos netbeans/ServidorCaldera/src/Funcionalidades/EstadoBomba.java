/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JLabel;

/**
 *
 * @author plcgc
 * esta clase modifica la interfaz de la bomba y guarda su estado en la base de datos confcaldera
 */
public class EstadoBomba {
        
    public static void apagadoBomba (JLabel labelEstadoBomba) throws SQLException, IOException{
    
        labelEstadoBomba.setText("Off");
        //ahora modifica la base de datos
        ModificarValoresConfCaldera.apagarBomba();
        //conectar con arduino y apagar el relé
        String comando = "python C:\\ServidorCaldera\\ApagarRele.py";
        Process process =  Runtime.getRuntime().exec(comando);
        
    }//final metodo apagado bomba
    
    public static void encendidoBomba(JLabel labelEstadoBomba) throws SQLException, IOException{
    
        labelEstadoBomba.setText("On");
        //ahora modifica la base de datos
        ModificarValoresConfCaldera.encenderBomba();
        //conectar con arduino y encender el relé
        String comando = "python C:\\ServidorCaldera\\EncenderRele.py";
        Process process =  Runtime.getRuntime().exec(comando);
        
    }//final metodo encendidobomba
    
}//final clase apagado bomba
