/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tareas;

import BasesDeDatos.DevolverConexion;
import Ventanas.VentanaTemperaturas;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro Luis
 */
public class ActualizarVentanaTemperaturas extends TimerTask {

    //Modelo de la tabla
    DefaultTableModel tablaDeTemperaturas;

    public ActualizarVentanaTemperaturas(DefaultTableModel tablaDeTemperaturasRecibida) {

        tablaDeTemperaturas = tablaDeTemperaturasRecibida;

    }//final constructor

    @Override
    public void run() {
                
        System.out.println("Actualizando ventana temperaturas");
        try {
            rellenarTablaDeTemperaturas();
        } catch (SQLException | IOException | InterruptedException ex) {
            Logger.getLogger(ActualizarVentanaTemperaturas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//final metodo run

    private  void rellenarTablaDeTemperaturas() throws SQLException, IOException, InterruptedException {

        //hay que saber cuantos elementos tiene la tabla porque este metodo no solo se llama al principio de crear la 
        //ventana, también se puede llamar con un boton que actualiza la tabla y entonces hay que vaciarla
        int numeroPosicionesTabla = tablaDeTemperaturas.getRowCount();

        //se borra la tabla si no esta vacia
        if (numeroPosicionesTabla != 0) {

            borrarTablaDeTemperaturas(numeroPosicionesTabla);

        } else {//si entra aqui es porque la tabla esta vacia

        }//final if

        llenaTablaDeTemperaturas();

    }//final metodo rellenarTablaDeTemperaturas

    private void borrarTablaDeTemperaturas(int numeroPosicionesTabla) throws InterruptedException {

               
        for (int i = 0; i < numeroPosicionesTabla; i++) {

            tablaDeTemperaturas.removeRow(0);
          
        }//final bucle borra tabla

    }//final borrarTablaDeTEmperaturas

    private void llenaTablaDeTemperaturas() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from temperaturas order by created_at desc limit 1000";
        ResultSet resultado = stmt.executeQuery(consulta);

        int contador = 0;

        while (resultado.next()) {

            float temperaturaSalon = resultado.getFloat("temperatura_salon");
            float humedadSalon = resultado.getFloat("humedad_salon");
            float temperaturaCpu = resultado.getFloat("temperatura_cpu");
            String fecha = resultado.getString("created_at");

            tablaDeTemperaturas.addRow(new Object[]{temperaturaSalon, humedadSalon, temperaturaCpu, fecha});

        }//fin while que recorre los resultados de busqueda

        DevolverConexion.cerrarConexion(conexion);

    }//final llenaTablaDeTemperaturas

}//final clase actualizrVentanaTemperaturas
