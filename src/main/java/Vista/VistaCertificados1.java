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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import metodosIlegales.Auxiliar;

/**
 *
 * @author dany_
 */
public class VistaCertificados1 extends JFrame{
    
    private JPanel vNor;
    
        private JComboBox vObras;
        private JButton vBotFiltrar;
        private JButton vBotTodas;
        
    private JTable vTabla;
    
    private JPanel vSur;
    
        private JPanel vSurB;
            
            private JButton vBotVolver;
            private JButton vBotDetalle;
            
        private JLabel vLog;
        
    private Sistema vSis;
    private Auxiliar vAux = new Auxiliar();
        
    //--------------------------------------------------------------------------
            
    public VistaCertificados1 (Sistema vSis){
        this.vSis = vSis;
        this.IniciarVista();
    }
    
    public void IniciarVista(){
    
        vNor = new JPanel();
        vNor.setLayout(new GridLayout(1, 3));
        
            vObras = new JComboBox(vSis.DevolverObras());
            vBotFiltrar = new JButton("Filtrar certificados");
            vBotTodas = new JButton("Ver todas los certificados");
            
            vNor.add(vObras);
            vNor.add(vBotFiltrar);
            vNor.add(vBotTodas);
            
            this.add(vNor, BorderLayout.NORTH);
            
        //----------------------------------------------------------------------
        
        String [] vEncabezado = {"Certificado Nro", "Obra", "Fecha de emision", "Costo total", "Estado"};
        vTabla = new JTable(vSis.DevolverCertificadosVista(), vEncabezado);
        
        vTabla.setPreferredSize(new Dimension(600,100));
        vTabla.setPreferredScrollableViewportSize(vTabla.getPreferredSize());
        vTabla.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(vTabla), BorderLayout.CENTER);
        
        //----------------------------------------------------------------------
        
        vSur = new JPanel();
        vSur.setLayout(new GridLayout(2, 1));
        
            vSurB = new JPanel();
            vSurB.setLayout(new GridLayout(1, 2));
            
                vBotVolver = new JButton("Volver al menu");
                vBotDetalle = new JButton("Ver detalle del certificado");
            
                vSurB.add(vBotVolver);
                vSurB.add(vBotDetalle);
                
                vSur.add(vSurB);
                
            vLog = new JLabel ("Aca se mostraran los errores y los cambios",SwingConstants.CENTER);
            vSur.add(vLog);

            this.add(vSur, BorderLayout.SOUTH);
            
        //----------------------------------------------------------------------
        
        setTitle("Certificados de pago");
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
        
        this.vBotDetalle.addActionListener((ActionEvent evt) -> {
            
            if (ComprobarSeleccion()){
                
                try{
                    
                    vSis.AbrirVistaCertificados2(vSis.BuscarCertificadoId(
                            vAux.toInteger((String)vTabla.getValueAt(vTabla.getSelectedRow(), 0)))
                    );
                    
                }catch (Exception e){
                    vLog.setText("ERROR: No se encontro el certificado buscado");
                }
                
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
        String [] vEncabezado = {"Certificado Nro", "Obra", "Fecha de emision", "Costo total", "Estado"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverCertificadosVista(), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    private void FiltrarTabla(){
        String [] vEncabezado = {"Certificado Nro", "Obra", "Fecha de emision", "Costo total", "Estado"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverCertificadosVistaFiltrado(vObras.getSelectedItem().toString()), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    
    
}