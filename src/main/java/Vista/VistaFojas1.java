/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import controlador.Sistema;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import metodosIlegales.Auxiliar;

/**
 *
 * @author dany_
 */
public class VistaFojas1 extends JFrame{
    
    private JPanel vNor;
    
        private JComboBox vObras;
        private JButton vBotFiltrar;
        private JButton vBotTodas;
        
    private JTable vTabla;
    
    private JPanel vSur;
    
        private JPanel vSurA;
        
            private JSpinner vFecha;
    
        private JPanel vSurB;
            
            private JButton vBotVolver;
            private JButton vBotRellenar;
            private JButton vBotCrear;
            
        private JLabel vLog;
        
    private Sistema vSis;
    private Auxiliar vAux = new Auxiliar();
        
    //--------------------------------------------------------------------------
            
    public VistaFojas1 (Sistema vSis){
        this.vSis = vSis;
        this.IniciarVista();
    }
    
    public void IniciarVista(){
    
        vNor = new JPanel();
        vNor.setLayout(new GridLayout(1, 3));
        
            vObras = new JComboBox(vSis.DevolverObras());
            vBotFiltrar = new JButton("Filtrar fojas");
            vBotTodas = new JButton("Ver todas las fojas");
            
            vNor.add(vObras);
            vNor.add(vBotFiltrar);
            vNor.add(vBotTodas);
            
            this.add(vNor, BorderLayout.NORTH);
            
        //----------------------------------------------------------------------
        
        String [] vEncabezado = {"Id", "Obra", "Fecha de emision", "Avance total"};
        vTabla = new JTable(vSis.DevolverFojasVista(), vEncabezado);
        
        vTabla.setPreferredSize(new Dimension(600,100));
        vTabla.setPreferredScrollableViewportSize(vTabla.getPreferredSize());
        vTabla.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(vTabla), BorderLayout.CENTER);
        
        //----------------------------------------------------------------------
        
        vSur = new JPanel();
        vSur.setLayout(new GridLayout(3, 1));
            
            vSurA = new JPanel();
            vSurA.setLayout(new GridLayout(1, 2));
            
                vFecha = new JSpinner (new SpinnerDateModel(new Date(), null, null, Calendar.MONTH));
                vFecha.setEditor(new JSpinner.DateEditor(vFecha, "dd/MM/yyyy"));
            
                vSurA.add(new JLabel("Ingrese la fecha de hoy:"));
                vSurA.add(vFecha);
                
                vSur.add(vSurA);
        
            vSurB = new JPanel();
            vSurB.setLayout(new GridLayout(1, 3));
            
                vBotVolver = new JButton("Volver al menu");
                vBotRellenar = new JButton("Rellenar foja seleccionada");
                vBotCrear = new JButton("Crear foja de la obra seleccionada");
            
                vSurB.add(vBotVolver);
                vSurB.add(vBotRellenar);
                vSurB.add(vBotCrear);
                
                vSur.add(vSurB);
                
            vLog = new JLabel ("Aca se mostraran los errores y los cambios",SwingConstants.CENTER);
            vSur.add(vLog);

            this.add(vSur, BorderLayout.SOUTH);
            
        //----------------------------------------------------------------------
        
        setTitle("Fojas de medicion");
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setLocationRelativeTo(null);
        
        //----------------------------------------------------------------------
        
        this.vBotFiltrar.addActionListener((ActionEvent evt) -> {
            FiltrarTabla();
        });
        
        this.vBotTodas.addActionListener((ActionEvent evt) -> {
            ActualizarTabla();
        });
        
        this.vBotVolver.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaMenuPrincipal();
        });
        
        this.vBotRellenar.addActionListener((ActionEvent evt) -> {
            
            if (ComprobarSeleccion()){
                
                try{
                    
                    if (!vSis.ExistenciaCertificadoDeFoja(vSis.BuscarFojaMedicionVista(
                            (String)vTabla.getValueAt(vTabla.getSelectedRow(), 1),
                            (String)vTabla.getValueAt(vTabla.getSelectedRow(), 2)
                    ))){
                        
                        vSis.AbrirVistaFojas2(vSis.BuscarFojaMedicionVista(
                                (String)vTabla.getValueAt(vTabla.getSelectedRow(), 1),
                                (String)vTabla.getValueAt(vTabla.getSelectedRow(), 2)
                        ));
                    
                    }else{
                        vLog.setText("ERROR: La foja ya fue rellenada");
                    }
                    
                }catch (Exception e){
                    vLog.setText("ERROR: No se encontro la foja buscada");
                }
                
            }
        });
        
        this.vBotCrear.addActionListener((ActionEvent evt) -> {
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            try{
                if (vSis.BuscarFojaMedicionVista(vObras.getSelectedItem().toString(), dateFormat.format(vFecha.getValue())) == null){

                    vSis.CrearFojaMedicionVista(vObras.getSelectedItem().toString(), dateFormat.format(vFecha.getValue()));

                    FiltrarTabla();
                }else{
                    vLog.setText("ERROR: La obra ya tiene una foja hecha el dia de hoy");
                }

            }catch (Exception e){
                vLog.setText("ERROR: " + e.getMessage());
            }
            
        });
    }
    
    //--------------------------------------------------------------------------
    
    private boolean ComprobarSeleccion(){
        if (vTabla.getSelectedRowCount() != 1){
            vLog.setText("ERROR: Seleccion no valida");
            return false;
        }else{
            return true;
        }
    }
    
    public void ActualizarTabla(){
        String [] vEncabezado = {"Id", "Obra", "Fecha de emision", "Avance total"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverFojasVista(), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    private void FiltrarTabla(){
        String [] vEncabezado = {"Id", "Obra", "Fecha de emision", "Avance total"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverFojasVistaFiltrado(vObras.getSelectedItem().toString()), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    
    
}
