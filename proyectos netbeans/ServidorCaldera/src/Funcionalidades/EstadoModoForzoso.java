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
 */
public class EstadoModoForzoso {
    
    public static void apagadoModoForzoso (JLabel labelModoForzoso) throws SQLException, IOException{
    
        labelModoForzoso.setText("Off");
        //ahora modifica la base de datos
        ModificarValoresConfCaldera.apagarModoForzoso();
        
    }//final metodo apagado bomba
    
    public static void encendidoModoForzoso(JLabel labelModoForzoso) throws SQLException, IOException{
    
        labelModoForzoso.setText("On");
        //ahora modifica la base de datos
        ModificarValoresConfCaldera.encenderModoForzoso();
        
    }//final metodo encendidobomba
    
}//final clase EstadoModoForzoso
