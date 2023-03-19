/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import controlador.*;
import metodosIlegales.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class VistaFinanciaciones extends JFrame {
    
    private JPanel vPCentro;
    
        private JTable vTabla;
    
    private JPanel vPSur;
    
        private JPanel vPSur1;
        private JLabel vTituloCrear;
    
        private JPanel vPSur2;
        
        private JTextField vIdFinanciacion;
        private JTextField vDenominacion;
    
        private JPanel vPSur3;
        private JButton vBotonCrear;
        private JButton vBotonAtras;
        
        private JPanel vPSur4;
        private JLabel vLog;
    
    private Sistema vSis;
    
    //--------------------------------------------------------------------------
    
    public VistaFinanciaciones (Sistema vSis){
        this.vSis = vSis;
        this.IniciarVista();
    }
    
    public void IniciarVista(){
        
        vPCentro = new JPanel();
        
            String [] vEncabezado = {"Id", "Denominacion"};
            vTabla = new JTable(vSis.DevolverFinanciacionesVista(), vEncabezado);
            this.ActualizarTabla();

            vTabla.setPreferredSize(new Dimension(300,230));
            vTabla.setPreferredScrollableViewportSize(vTabla.getPreferredSize());
            vTabla.setFillsViewportHeight(true);

            vPCentro.add(new JScrollPane(vTabla), BorderLayout.CENTER);
            vPCentro.setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 0));
            
            this.add(vPCentro, BorderLayout.CENTER);
        
        //----------------------------------------------------------------------
        
        vPSur = new JPanel();
        vPSur.setLayout(new GridLayout(4, 1));
        
        vPSur1 = new JPanel();

        vTituloCrear = new JLabel ("Ingrese los datos de la nueva financiacion aqui abajo");
        vPSur1.add(this.vTituloCrear);

        vPSur.add(vPSur1);

        vPSur2 = new JPanel();
        vPSur2.setLayout(new GridLayout(1, 5));

            vIdFinanciacion = new JTextField (10);
            vIdFinanciacion.setText(String.valueOf(vSis.DevolverIdUltimaFinanciacion()+1));
            vIdFinanciacion.setEditable(false);
            vDenominacion = new JTextField (10);

            vPSur2.add(this.vIdFinanciacion);
            vPSur2.add(this.vDenominacion);

            vPSur2.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 140));

            vPSur.add(vPSur2);

        vPSur3 = new JPanel();
        vPSur3.setLayout(new GridLayout(1, 2));

        vBotonAtras = new JButton ("Volver al menu principal");
        vBotonCrear = new JButton ("Crear nueva financiacion");

        vPSur3.add(this.vBotonAtras);
        vPSur3.add(this.vBotonCrear);

        vPSur.add(vPSur3);
        
        vPSur4 = new JPanel();

        vLog = new JLabel ("Aqui puede ver las financiaciones existentes y crear nuevas");
        vPSur4.add(this.vLog);
        
        vPSur.add(vPSur4);
           
        this.add(vPSur, BorderLayout.SOUTH);
        
        //----------------------------------------------------------------------
        
        setTitle("Financiaciones");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setLocationRelativeTo(null);
        
        //----------------------------------------------------------------------
        
        this.vBotonCrear.addActionListener((ActionEvent evt) -> {
            this.CrearEmpresa();
        });
        
        this.vBotonAtras.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaMenuPrincipal();
        });
        
    }
    
    //--------------------------------------------------------------------------
    
    Auxiliar vAux = new Auxiliar();
    
    public void ActualizarTabla(){
        String [] vEncabezado = {"Id", "Denominacion"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverFinanciacionesVista(), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
        
        if (vIdFinanciacion != null){
            vIdFinanciacion.setText(String.valueOf(vSis.getvFinanciaciones().size()+1));
        }
        
    }
    
    private void FinanciacionValida() throws Exception{
        if (vDenominacion.getText().length() == 0){
            throw new Exception ("ERROR: La financiacion debe tener una denominacion");
        }
    }
    
    private void CrearEmpresa(){
        System.out.println("Crear Empresa Presionado");
        
        try{
            
            this.FinanciacionValida();
            vSis.CrearFinanciacion(vDenominacion.getText());
            this.ActualizarTabla();

            vLog.setText("Financiacion " + vDenominacion.getText() + " creada");

        }catch(Exception e){
            vLog.setText(e.getMessage());
        }
        
    }

}
