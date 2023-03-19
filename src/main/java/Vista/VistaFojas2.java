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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import metodosIlegales.Auxiliar;
import modelo.Fojamedicion;
import modelo.Obra;

/**
 *
 * @author dany_
 */
public class VistaFojas2 extends JFrame{
    
    private JPanel vNor;
        
        private JPanel vNorA;
        
        private JPanel vNorB;
    
            private JTextField vTF1;
            private JTextField vTF2;
            private JTextField vTF3;
    
    private JTable vTabla;
    
    private JPanel vSur;
    
        private JPanel vSurA;
            
            private JButton vBotVolver;
            private JButton vBotVerificar;
            private JButton vBotContinuar;
            
        private JLabel vLog;
        
    private Sistema vSis;
    private Auxiliar vAux = new Auxiliar();
    private Fojamedicion vFoja;
        
    //--------------------------------------------------------------------------
            
    public VistaFojas2 (Sistema vSis, Fojamedicion vFoja){
        this.vSis = vSis;
        this.vFoja = vFoja;
        this.IniciarVista();
    }
    
    public void IniciarVista(){
    
        vNor = new JPanel();
        vNor.setLayout(new GridLayout(3, 1));
            
            vNorA = new JPanel();
            vNorA.setLayout(new GridLayout(1, 3));
        
                vNorA.add(new JLabel("Id de foja", SwingConstants.CENTER));
                vNorA.add(new JLabel("Denominacion de obra", SwingConstants.CENTER));
                vNorA.add(new JLabel("Fecha de emision", SwingConstants.CENTER));
                
                vNor.add(vNorA);
                
            vNorB = new JPanel();
            vNorB.setLayout(new GridLayout(1, 3));
        
                vTF1 = new JTextField();
                vTF1.setText(vFoja.getVIdFoja().toString());
                vTF1.setEditable(false);
                vTF2 = new JTextField();
                vTF2.setText(vFoja.getVObra().getVDenominacion());
                vTF2.setEditable(false);
                vTF3 = new JTextField();
                vTF3.setText(vFoja.getVFechaEmision());
                vTF3.setEditable(false);
                
                vNorB.add(vTF1);
                vNorB.add(vTF2);
                vNorB.add(vTF3);
                
                vNor.add(vNorB);

            vNor.add(new JLabel("ingrese los nuevos valores de avance"), SwingConstants.CENTER);
            
            this.add(vNor, BorderLayout.NORTH);
            
        //----------------------------------------------------------------------
        
        String [] vEncabezado = {"Item", "Avance anterior", "Avance actual", "Avance acumulado"};
        vTabla = new JTable(vSis.DevolverRenglonesFojaVista(vFoja), vEncabezado);
        
        vTabla.setPreferredSize(new Dimension(600,100));
        vTabla.setPreferredScrollableViewportSize(vTabla.getPreferredSize());
        vTabla.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(vTabla), BorderLayout.CENTER);
        
        //----------------------------------------------------------------------
        
        vSur = new JPanel();
        vSur.setLayout(new GridLayout(3, 1));
    
            vSur.add(new JLabel ("AVISO: Cuando se termine de rellenar la foja, se creara un certificado de pago de esta foja", SwingConstants.CENTER));
        
            vSurA = new JPanel();
            vSurA.setLayout(new GridLayout(1, 3));
            
                vBotVolver = new JButton("Volver al menu");
                vBotVerificar = new JButton("Escribir valores en foja");
                vBotContinuar = new JButton("Terminar de cargar datos");
                vBotContinuar.setEnabled(false);
            
                vSurA.add(vBotVolver);
                vSurA.add(vBotVerificar);
                vSurA.add(vBotContinuar);
                
                vSur.add(vSurA);
                
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
        
        this.vBotVolver.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaFojas1();
        });
        
        this.vBotVerificar.addActionListener((ActionEvent evt) -> {
            
            try{
                CargarDatos();
                ActualizarTabla();
                vBotContinuar.setEnabled(true);
                
            }catch (Exception e){
                vLog.setText("ERROR: " + e.getMessage());
                vBotContinuar.setEnabled(false);
            }
            
        });
        
        this.vBotContinuar.addActionListener((ActionEvent evt) -> {
            
            try{
                CargarDatos();
                ActualizarTabla();
                
                vSis.CrearCertificadoPagoVista(vFoja, vFoja.getVFechaEmision());
                
                vSis.AbrirVistaFojas1();
                
            }catch (Exception e){
                vLog.setText("ERROR: " + e.getMessage());
                vBotContinuar.setEnabled(false);
            }
            
        });
    }
    
    //--------------------------------------------------------------------------
    
    private void CargarDatos() throws Exception{
        
        Integer[] vPorcentajes = ArmarPorcentajes();

        vSis.ActualizarFojaMedicion(vFoja, vPorcentajes);

        vLog.setText("Porcentajes cargados correctamente");
        
    }
    
    private Integer[] ArmarPorcentajes() throws Exception{
        Integer[] vIncidencia = new Integer[vTabla.getRowCount()];
        
        Integer vPos = 0;
        
        while (vPos != vTabla.getRowCount()){
            
            String vAuxiliar = vTabla.getValueAt(vPos, 2).toString().replace("%", "");
            
            try{
                vIncidencia[vPos] = vAux.toInteger(vAuxiliar);
            }catch(Exception e){
                throw new Exception ("Uno de los valores de la columna avance actual, es invalido");
            }
            
            vPos = vPos + 1;
        }
        
        return vIncidencia;
    }
    
    private void ActualizarTabla() throws Exception{
        String [] vEncabezado = {"Item", "Avance anterior", "Avance actual", "Avance acumulado"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverRenglonesFojaVista(vFoja), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
} 
   
    
    
    
