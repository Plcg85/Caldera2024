package Ventanas;

import BasesDeDatos.DevolverConexion;
import Funcionalidades.GuardarErrores;
import Funcionalidades.VariablesTiempo;
import Tareas.ComprobarEquiposConectados;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Pedro Luis
 */
public class VentanaDireccionIp extends javax.swing.JFrame {

    /**
     * Creates new form VentanaDireccionIp
     */
    //se construye el objeto actualizarTabladeIp para que sea accesible a los metodos de crear la tarea y de cerrarla
    DefaultTableModel tablaIps;//el modelo de la tabla que pertenece a esta ventana hay que pasarselo a la tarea

    //Constructor ventana direccionip
    public VentanaDireccionIp() throws SQLException, IOException {

        initComponents();
        this.setTitle("Direccion Ip");

        tablaIps = (DefaultTableModel) tablaIp.getModel();
        
        rellenarTablaDireccionesIp();

    }//final constructor

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaIp = new javax.swing.JTable();
        botonActualizarTablaIp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tablaIp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Direccion Ip", "Fecha actualización"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaIp);
        if (tablaIp.getColumnModel().getColumnCount() > 0) {
            tablaIp.getColumnModel().getColumn(0).setResizable(false);
            tablaIp.getColumnModel().getColumn(1).setResizable(false);
        }

        botonActualizarTablaIp.setText("Actualizar Tabla Ip");
        botonActualizarTablaIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarTablaIpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonActualizarTablaIp)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonActualizarTablaIp)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //este evento es llamado automaticamente cuando se cierra la ventana DireccionIp
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void botonActualizarTablaIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarTablaIpActionPerformed
        try {
            rellenarTablaDireccionesIp();
        } catch (SQLException ex) {
            try {
                GuardarErrores.guardarErrores("VentanaDireccionIp", "error al rellenar la tabla", ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(VentanaDireccionIp.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IOException ex1) {
                Logger.getLogger(VentanaDireccionIp.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaDireccionIp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonActualizarTablaIpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarTablaIp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaIp;
    // End of variables declaration//GEN-END:variables

    //este metodo cierra todas las tareas que se realizan en esta ventana
    private void rellenarTablaDireccionesIp() throws SQLException, IOException {
        
        //hay que saber cuantos elementos tiene la tabla porque este metodo no solo se llama al principio de crear la 
        //ventana, también se puede llamar con un boton que actualiza la tabla y entonces hay que vaciarla
        int numeroPosicionesTabla = tablaIp.getRowCount();

        //se borra la tabla si no esta vacia
        if (numeroPosicionesTabla != 0) {

            borraTablaIp(numeroPosicionesTabla);

        }

        llenaTablaIp();

    }//final metodo rellenarTablaDireccionesIp

    private void borraTablaIp(int numeroPosicionesTabla) {
        
        for (int i = 0; i < numeroPosicionesTabla; i++) {

            tablaIps.removeRow(0);

        }//final bucle borra tabla
    
    }//final metodo borraTablaIp

    private void llenaTablaIp() throws SQLException, IOException {
        
        Connection conexion = DevolverConexion.devolverConexionParaCrearTablas();
        Statement stmt = conexion.createStatement();
        String consulta = "select * from ip;";
        ResultSet resultado = stmt.executeQuery(consulta);

        int contador = 0;

        while (resultado.next()) {

            String direccion_ip = resultado.getString("direccion_ip");
            String fecha = resultado.getString("created_at");
            tablaIps.addRow(new Object[]{direccion_ip, fecha});
            contador++;

        }//final while que recorre el result set

        conexion.close();
        
    }//final metodo llenaTablaIp
}//final clase VentanaDireccion
