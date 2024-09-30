/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tareas;

import Funcionalidades.DevolverBusquedasBd;
import Funcionalidades.DevolverFecha;
import Funcionalidades.DevuelveTemperaturaCaldera;
import Funcionalidades.EscribirEnInterfaz;
import Funcionalidades.EstadoBomba;
import Funcionalidades.EstadoModoForzoso;
import Funcionalidades.GuardarErrores;
import Funcionalidades.GuardarTemperaturasWeb;
import Sockets.EnvioDatosSocket;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author plcgc esta es la tarea en si de la caldera. el programa que toma las
 * decisiones en base a los datos que tiene
 */
public class TareaCaldera extends TimerTask {

    private int contadorBucle = 0;
    private int actualizacionesParaBorrarAreaTexto = 0; //esta variable controla las actualizaciones de temperatura del area de texto para borrarla
    private int maximasActualizacionesAreaTexto = 50; // cuando llegue a  50 se borrará el area de texto

    private javax.swing.JLabel labelTemperaturaCasa;
    private javax.swing.JLabel labelTemperaturaCaldera;
    private javax.swing.JLabel labelEstadoBomba;
    private JTextArea areaTextArea;

    public TareaCaldera(JLabel labelTemperaturaCasaRecibida, JLabel labelTemperaturaCalderaRecibida, JLabel labelEstadoBombaRecibida, JTextArea textAreaRecibida) {

        this.labelTemperaturaCasa = labelTemperaturaCasaRecibida;
        this.labelTemperaturaCaldera = labelTemperaturaCalderaRecibida;
        this.labelEstadoBomba = labelEstadoBombaRecibida;
        this.areaTextArea = textAreaRecibida;

    }//final constructor

    //estos son los datos (variables) que se envian al servidor de la casa
    private float temperaturaCasa = 0.0f;
    private float temperaturaDeseada = 0.0f;
    private float temperaturaCaldera = 0.0f;
    private float estadoRele = 0.0f;
    private float tempSeteo = 0.0f;
    private int modoLenia = 0;
    private int modoForzoso = 0;
    ////////////////////////////////////////////////////////////

    //otros datos (variables) que se necesitan para el programa
    private float tempMaximaSeguridad = 0.0f; // la maxima temperatura que se puede permitir en la caldera
    private float tempMaximaModoLenia = 0.0f; // la maxima temperatura permitida con el modo leña
    private float tempEncendidoBomba = 0.0f;
    private float tempApagadoBomba = 0.0f;
    private float tempMinimaFuncionamiento = 0.0f; // la minima a la que va a funcionar el relé

    //variables ayuda
    private float tempCalderaAnterior = 0.0f;
    private String progresionTemp = "mantenida";
    private String estadoCombustion = "no_quemando";
    private float temperaturaQuemado = 0.0f;
    private boolean temperaturaConseguida = false;

    @Override
    public void run() {

        try {

            actualizarVariables(); //se recogen de las bases de datos y de los sensores

        } catch (Exception e) {
            try {
                GuardarErrores.guardarErrores("TareaCaldera", "Al actualizarVariables", e.toString());
            } catch (SQLException | IOException ex) {
                Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            temperaturaConseguida = comprobarTemperaturaConseguida();
        } catch (SQLException | IOException ex) {
            try {
                GuardarErrores.guardarErrores("TareaCaldera", "Al comprobarTemperaturaConseguida", ex.toString());
            } catch (SQLException | IOException ex1) {
                Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex);
        }

        calcularProgresionTempCaldera();

        try {

            estadoCombustion = comprobarEstadoCombustion();

        } catch (Exception e) {

            try {
                GuardarErrores.guardarErrores("TareaCaldera", "Al comprobarEstadoCombustion()", e.toString());
            } catch (SQLException | IOException ex) {
                Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //toma de decisiones
        try {

            tomaDeDecisiones(); //aqui puede cambiar el estado del relé por eso se comprueba su estado dentro de actualizar interfaz grafica

        } catch (SQLException | IOException ex) {
            try {
                GuardarErrores.guardarErrores("TareaCaldera", "Al tomar Decisiones", ex.toString());
            } catch (SQLException | IOException ex1) {
                Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex);
        }

        //se guardan las variables en las bases de datos para la pagina web (tabla temps)
        try {

            if (contadorBucle == 0 || contadorBucle % 3 == 0) {

                guardarTemperaturas(); //para la web

            }

        } catch (SQLException | IOException ex) {
            try {

                GuardarErrores.guardarErrores("TareaCaldera", "Al guardarTemperaturas para la web", ex.toString());

            } catch (SQLException | IOException ex1) {
                Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex);
        }

        //se actualiza la interfaz grafica
        try {

            actualizarInterfazGrafica(); //se debe actualizar la temperatua caldera y la temperatura de casa y el estado bomba

        } catch (SQLException | IOException ex) {
            try {

                GuardarErrores.guardarErrores("TareaCaldera", "Al actualizarInterfazGrafica", ex.toString());

            } catch (SQLException | IOException ex1) {
                Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex);
        }

        //se envian los datos al servidor del salón
        try {

            envioDatosServidorCasa();

        } catch (IOException ex) {
            try {
                //aqui entra cuando la conexion no se establece
                //se cogen las mismas variables
                GuardarErrores.guardarErrores("TareaCaldera", "Al enviarDatosServidorCasa", ex.toString());
            } catch (SQLException | IOException ex1) {
                Logger.getLogger(TareaCaldera.class.getName()).log(Level.SEVERE, null, ex1);
            }
            EscribirEnInterfaz.escribirFrase("Fallo al conectar con servidor salón", areaTextArea);
        }

        contadorBucle++;
        //hay que resetear el contador del bucle
        if (contadorBucle == 15) {
            contadorBucle = 0;
        }

    }//final del metodo run

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void apagarBomba() throws SQLException, IOException {

        //simplemente se llama al metodo apagar bomba de la clase Estado bomba, y alli se modifica la interfaz y la base de datows
        EstadoBomba.apagadoBomba(labelEstadoBomba);

    }//final metodo apagar bomba

    private void encenderBomba() throws SQLException, IOException {

        //simplemente se llama al metodo apagar bomba de la clase Estado bomba, y alli se modifica la interfaz y la base de datows
        EstadoBomba.encendidoBomba(labelEstadoBomba);

    }//final metodo apagar bomba

    //recoge los valores de todas las variables que necesita el programa
    private void actualizarVariables() {

        System.out.println("Actualizando variables " + contadorBucle);

        try {

            //la temperatura de la casa solo se actualiza cada 15 ciclos de 10 segundos
            if ((contadorBucle == 0)) {

                //este if y esta variable solo se usan para borrar el area de texto
                actualizacionesParaBorrarAreaTexto++;

                if (actualizacionesParaBorrarAreaTexto >= maximasActualizacionesAreaTexto) {

                    actualizacionesParaBorrarAreaTexto = 0;
                    areaTextArea.setText("");

                }//final de las 5 actualizaciones para borrar

                String frase = "**----------------------------------------------**";
                EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

                temperaturaCasa = DevolverBusquedasBd.devuelveUltimaTemperaturaCasa();
                System.out.println("Actualizando temperatura de casa " + temperaturaCasa);

                //se escribe en el textArea de la interfaz grafica
                frase = "Temperatura Casa: " + temperaturaCasa;
                EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

                frase = "**----------------------------------------------**";
                EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

            }

            temperaturaDeseada = DevolverBusquedasBd.devuelveTemperaturaSeleccionada();
            estadoRele = DevolverBusquedasBd.devuelveEstadoRele();
            tempSeteo = DevolverBusquedasBd.devuelveTemperaturaSeteo();
            modoLenia = DevolverBusquedasBd.estadoModoLenia();
            modoForzoso = DevolverBusquedasBd.estadoModoForzoso();

            try {
                DevuelveTemperaturaCaldera.crearArchivoTemperaturaCaldera();
                temperaturaCaldera = DevuelveTemperaturaCaldera.compruebaTemperaturaCaldera();
                DevuelveTemperaturaCaldera.eliminaArchivoTemperaturaCaldera();
            } catch (Exception e) {
                //si da error al leer el archivo de la temperatura
                //la temperatura  de la Caldera pasa a ser 80 grados si esta en modo leña
                if (modoLenia == 1) {
                    temperaturaCaldera = 80;
                }
            }

            tempEncendidoBomba = DevolverBusquedasBd.devuelveTempEncendidoBomba();
            tempApagadoBomba = DevolverBusquedasBd.devuelveTempApagadoBomba();
            tempMaximaModoLenia = DevolverBusquedasBd.devuelveTempMaximaModoLenia();
            tempMaximaSeguridad = DevolverBusquedasBd.devuelveTempMaximaSeguridad();
            tempMinimaFuncionamiento = DevolverBusquedasBd.devuelveTempMinimaFuncionamiento();

        } catch (SQLException | IOException ex) {

            System.out.println("Error al buscar la ultima temperatura de casa en la clase TareaCaldera");
            System.out.println(ex.toString());
        }

    }//final actualizar las variables

    private void envioDatosServidorCasa() throws IOException {

        EnvioDatosSocket.enviarDatosCalderaAServidorCasa(temperaturaCasa, temperaturaDeseada, temperaturaCaldera, estadoRele, tempSeteo, modoLenia, modoForzoso);

    }//final metodo envioDatosServidorCasa

    private void tomaDeDecisiones() throws SQLException, IOException {

        //condiciones de encendido de la bomba-------------------------------------------------------------------------------------------------
        //por temperaturas idoneas
        if ((temperaturaConseguida == false) && (temperaturaCaldera > tempEncendidoBomba) && (estadoRele == 30.0f)) {

            EstadoBomba.encendidoBomba(labelEstadoBomba);
            String frase = "Encendido bomba";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }//final por temperaturas adecuadas

        //por modo forzoso
        if ((modoForzoso == 1) && (temperaturaCaldera >= tempApagadoBomba) && (estadoRele == 30.0f)) {

            EstadoBomba.encendidoBomba(labelEstadoBomba);
            String frase = "Encendido bomba --> Modo Forzoso Activado";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }

        //por altas temperaturas de la caldera sin modo leña
        if ((temperaturaCaldera >= tempMaximaSeguridad) && (estadoRele == 30.0f)) {

            EstadoBomba.encendidoBomba(labelEstadoBomba);
            String frase = "Encendido bomba --> Temperaturas caldera muy altas";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }

        //por altas temperaturas de la caldera con modo leña
        if ((temperaturaCaldera >= tempMaximaModoLenia) && (estadoRele == 30.0f) && (modoLenia == 1)) {

            EstadoBomba.encendidoBomba(labelEstadoBomba);
            String frase = "Encendido bomba --> Temperaturas caldera muy altas Modo Leña";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }

        //condiciones de apagado de la bomba-----------------------------------------------------------------------------------------------------
        //por temperatura de casa conseguida
        if ((temperaturaConseguida == true) && (temperaturaCaldera < tempMaximaSeguridad) && (estadoRele == 40.0f) && (modoLenia == 0)) {

            EstadoBomba.apagadoBomba(labelEstadoBomba);
            String frase = "Apagado bomba --> Temperaturas casa conseguida";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }

        //por temperatura de casa conseguida en modo leña
        if ((temperaturaConseguida == true) && (temperaturaCaldera < tempMaximaModoLenia) && (estadoRele == 40.0f) && (modoLenia == 1)) {

            EstadoBomba.apagadoBomba(labelEstadoBomba);
            String frase = "Apagado bomba --> Temperaturas casa conseguida en Modo Leña";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }

        //por apagado del modo forzoso sin modo Leña
        if ((modoForzoso == 0) && (temperaturaCaldera < tempMaximaSeguridad) && (modoLenia == 0) && (estadoRele == 40.0f)) {

            EstadoBomba.apagadoBomba(labelEstadoBomba);
            String frase = "Apagado bomba --> Modo Forzoso apagado";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }

        //por apagado del modo forzoso en modo Leña
        if ((modoForzoso == 0) && (temperaturaCaldera < tempMaximaModoLenia) && (modoLenia == 1) && (estadoRele == 40.0f)) {

            EstadoBomba.apagadoBomba(labelEstadoBomba);
            String frase = "Apagado bomba --> Modo Forzoso apagado en Modo Leña";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }

        //por temperatura de la caldera demasiado baja
        if ((temperaturaCaldera < tempMinimaFuncionamiento) && (estadoRele == 40.0f)) {

            EstadoBomba.apagadoBomba(labelEstadoBomba);
            String frase = "Apagado bomba --> Temperatura Caldera muy baja";
            EscribirEnInterfaz.escribirFrase(frase, areaTextArea);

        }

        //---------------------------------------------------------------------------------------------------------------------------------------
    }//final tomaDeDecisiones

    private void guardarTemperaturas() throws SQLException, IOException {

        //guarda las temperaturas para la pagina web
        //Orden de guardado de valores:  1.temp casa  //2.temp seleccionada  //3.temp caldera //bomba 30-40
        GuardarTemperaturasWeb.guardaTemperaturasWeb(temperaturaCasa, temperaturaDeseada, temperaturaCaldera, estadoRele);

    }//final guardarTemperaturas

    private void actualizarInterfazGrafica() throws SQLException, IOException {

        labelTemperaturaCasa.setText("" + temperaturaCasa + "º");
        labelTemperaturaCaldera.setText("" + temperaturaCaldera + "º");

        //el estado del relé si hay que volver a buscarlo porque es el único que cambia entre el principio y hasta que se actualiza la interfaz
        estadoRele = DevolverBusquedasBd.devuelveEstadoRele();

        if (estadoRele == 30.0f) {
            labelEstadoBomba.setText("Off");
        } else {
            labelEstadoBomba.setText("On");
        }

        //se escribe en el textArea de la interfaz grafica
        String frase = "Temperatura Caldera: " + temperaturaCaldera;

        EscribirEnInterfaz.escribirFrase(frase, areaTextArea);
        int len = areaTextArea.getDocument().getLength();
        areaTextArea.setCaretPosition(len);

    }//actualiza la interfaz grafica

    //este metodo calcula la progresin de la temperatura de la caldera
    private void calcularProgresionTempCaldera() {

        if (tempCalderaAnterior > temperaturaCaldera) {

            progresionTemp = "descenso";

        }
        if (tempCalderaAnterior < temperaturaCaldera) {

            progresionTemp = "ascenso";

        }
        if (tempCalderaAnterior == temperaturaCaldera) {

            progresionTemp = "mantenida";

        }

        tempCalderaAnterior = temperaturaCaldera; //despues de las comprobaciones la aterior pasa a ser la actual

        System.out.println("Progresion " + progresionTemp);

    }//final calcularProgresionTemperaturaCaldera

    //se comprueba si la caldera esta quemando o no
    private String comprobarEstadoCombustion() {

        temperaturaQuemado = tempSeteo - 3f;

        if (temperaturaCaldera <= temperaturaQuemado) {

            estadoCombustion = "quemando";

        } else if (temperaturaCaldera >= tempSeteo) {

            estadoCombustion = "no_quemando";

        } else if ((temperaturaCaldera >= temperaturaQuemado) && (temperaturaCaldera <= tempSeteo)) {

            if (progresionTemp == "ascenso") {

                estadoCombustion = "quemando";

            }
            if (progresionTemp == "descenso") {

                estadoCombustion = "no_quemando";

            }

        }

        return estadoCombustion;

    }//final comprobar Estado Combustion

    //este metodo comprueba si se ha conseguido llegar a la temperatura deseada
    private boolean comprobarTemperaturaConseguida() throws SQLException, IOException {

        boolean tempConseguida = false;

        if (temperaturaCasa > temperaturaDeseada) {

            tempConseguida = true;
            // se apaga el modo forzoso si estuviese encendido
            EstadoModoForzoso.apagadoModoForzoso(labelEstadoBomba);

        } else if (temperaturaCasa <= temperaturaDeseada) {

            tempConseguida = false;

        }

        return tempConseguida;

    }//final comprobarTemperaturaConseguida

}//final tarea
