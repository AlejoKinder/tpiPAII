/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import controlador.Sistema;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import metodosIlegales.Auxiliar;

/**
 *
 * @author dany_
 */
public class VistaObras1 extends JFrame {
    
    private JTable vTabla;
    
    private JPanel vPSur;
    
        private JPanel vPSur2;
        private JButton vBotonRotarAtributos;
        private JButton vBotonItems;
        
        private JPanel vPSur3;
        private JButton vBotonCrear;
        private JButton vBotonAtras;
        
        private JPanel vPSur4;
        private JLabel vLog;
    
    private Sistema vSis;
    
    boolean vTablaRotada = false;
    
    //--------------------------------------------------------------------------
    
    public VistaObras1 (Sistema vSis){
        this.vSis = vSis;
        this.IniciarVista();
    }
    
    public void IniciarVista(){
        
        String [] vEncabezado = {"Id", "Denominacion", "Localidad", "Cantidad de Viviendas", "Fecha de inicio", "Plazo en meses"};
        vTabla = new JTable(vSis.DevolverObrasVista1(), vEncabezado);
        this.ActualizarTabla1();
        
        vTabla.setPreferredSize(new Dimension(600,100));
        vTabla.setPreferredScrollableViewportSize(vTabla.getPreferredSize());
        vTabla.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(vTabla), BorderLayout.CENTER);
        
        //----------------------------------------------------------------------
        
        vPSur = new JPanel();
        vPSur.setLayout(new GridLayout(3, 1));

        vPSur2 = new JPanel();
        vPSur2.setLayout(new GridLayout(1, 2));
        
        vBotonRotarAtributos = new JButton ("Ver mas info");
        vBotonItems = new JButton ("Ver items de una obra");
        
        vPSur2.add(this.vBotonRotarAtributos);
        vPSur2.add(this.vBotonItems);
        
        vPSur.add(vPSur2);
        
        vPSur3 = new JPanel();
        vPSur3.setLayout(new GridLayout(1, 2));

        vBotonAtras = new JButton ("Volver al menu principal");
        vBotonCrear = new JButton ("Crear nueva obra");

        vPSur3.add(this.vBotonAtras);
        vPSur3.add(this.vBotonCrear);

        vPSur.add(vPSur3);
        
        vPSur4 = new JPanel();

        vLog = new JLabel ("Aqui puede ver las obras existentes y crear nuevas");
        vPSur4.add(this.vLog);
        
        vPSur.add(vPSur4);
           
        this.add(vPSur, BorderLayout.SOUTH);
        
        //----------------------------------------------------------------------
        
        setTitle("Obras");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setLocationRelativeTo(null);
        
        //----------------------------------------------------------------------
        
        this.vBotonRotarAtributos.addActionListener((ActionEvent evt) -> {
            this.RotarTabla();
        });
        
        this.vBotonCrear.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaObras3();
        });
        
        this.vBotonItems.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaObras2();
        });
        
        this.vBotonAtras.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaMenuPrincipal();
        });
        
    }
    
    //--------------------------------------------------------------------------
    
    Auxiliar vAux = new Auxiliar();
    
    public void ActualizarTabla1(){
        String [] vEncabezado = {"Id", "Denominacion", "Localidad", "Cant. de Viviendas", "Fecha de inicio", "Plazo en meses"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverObrasVista1(), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    public void ActualizarTabla2(){
        String [] vEncabezado = {"Id", "Denominacion", "Empresa", "Financiacion", "Progreso", "Dinero restante"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverObrasVista2(), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    private void RotarTabla(){
    
        if (vTablaRotada == false){
            this.ActualizarTabla2();
            vTablaRotada = true;
        }else{
            this.ActualizarTabla1();
            vTablaRotada = false;
        }
    
    }
    
    public void Reset(){
        ActualizarTabla1();
        this.vTablaRotada = false;
    }

    
}
