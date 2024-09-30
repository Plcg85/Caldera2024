/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BasesDeDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pedro Luis esta clase es llamada desde comprobarPrimeraEjecucion si
 * es la primera ejecucion para rellenar los datos de configuracion inicial de
 * la caldera
 */
public class IniciarTablaCaldera {

    //consulta = "CREATE TABLE confcaldera (tempSeteo float,tempMaximaSeguridad float,modoLenia int,tempMaxModoLenia float,tempEncendidoBomba float,tempApagadoBomba float);";
    static float tempSeteo = 65.00f;
    static float tempSeleccionada = 20.00f;
    static float tempMaximaSeguridad = 80.00f;
    static int modoLenia = 0;
    static float tempMaxModoLenia = 72.00f;
    static float tempEncendidoBomba = tempSeteo + 1f;
    static float tempApagadoBomba = tempSeteo - 3f;
    static int modoForzoso = 0;
    static float tempMinimaFuncionamiento = tempSeteo - 7.00f;
    static float estadoRele = 30.00f;

    public static void iniciarTablaCaldera() throws SQLException, IOException {

        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "INSERT INTO confcaldera (tempSeteo, tempSeleccionada, tempMaximaSeguridad, modoLenia, tempMaxModoLenia, tempEncendidoBomba, tempApagadoBomba, modoForzoso, tempMinimaFuncionamiento, estadoRele) VALUES ('" + tempSeteo + "','" + tempSeleccionada + "','" + tempMaximaSeguridad + "','" + modoLenia + "','" + tempMaxModoLenia + "','" + tempEncendidoBomba + "','" + tempApagadoBomba + "','" + modoForzoso + "','" + tempMinimaFuncionamiento + "','" + estadoRele + "');";
        int filasAfectadas = stmt.executeUpdate(consulta);

        DevolverConexion.cerrarConexion(conexion);

    }//final metodo iniciarTablaCaldera
    
}//final clase iniciar tabla Caldera
