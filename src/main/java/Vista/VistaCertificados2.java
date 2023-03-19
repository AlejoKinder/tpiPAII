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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import metodosIlegales.Auxiliar;
import modelo.Certificadopago;
import modelo.Fojamedicion;

/**
 *
 * @author dany_a
 */
public class VistaCertificados2 extends JFrame{
    
    private JPanel vNor;
        
        private JPanel vNorA;
        
        private JPanel vNorB;
    
            private JTextField vTF1;
            private JTextField vTF2;
            private JTextField vTF3;
            private JTextField vTF4;
    
    private JTable vTabla;
    
    private JPanel vSur;
    
        private JPanel vSurA;
            
            private JButton vBotVolver;
            private JButton vBotEstado;
            
        private JLabel vLog;
        
    private Sistema vSis;
    private Auxiliar vAux = new Auxiliar();
    private Certificadopago vCertificado;
        
    //--------------------------------------------------------------------------
            
    public VistaCertificados2 (Sistema vSis, Certificadopago vCertificado){
        this.vSis = vSis;
        this.vCertificado = vCertificado;
        this.IniciarVista();
    }
    
    public void IniciarVista(){
    
        vNor = new JPanel();
        vNor.setLayout(new GridLayout(3, 1));
            
            vNorA = new JPanel();
            vNorA.setLayout(new GridLayout(1, 4));
        
                vNorA.add(new JLabel("Id de certificado", SwingConstants.CENTER));
                vNorA.add(new JLabel("Denominacion de obra", SwingConstants.CENTER));
                vNorA.add(new JLabel("Fecha de emision", SwingConstants.CENTER));
                vNorA.add(new JLabel("Estado", SwingConstants.CENTER));
                
                vNor.add(vNorA);
                
            vNorB = new JPanel();
            vNorB.setLayout(new GridLayout(1, 4));
        
                vTF1 = new JTextField();
                vTF1.setText(vCertificado.getVIdCertificado().toString());
                vTF1.setEditable(false);
                vTF2 = new JTextField();
                vTF2.setText(vCertificado.getVFoja().getVObra().getVDenominacion());
                vTF2.setEditable(false);
                vTF3 = new JTextField();
                vTF3.setText(vCertificado.getVFechaEmision());
                vTF3.setEditable(false);
                vTF4 = new JTextField();
                
                if (vCertificado.getVPagado()){
                    vTF4.setText("Pagado");
                }else{
                    vTF4.setText("Sin pagar");
                }
                
                vTF4.setEditable(false);
                
                vNorB.add(vTF1);
                vNorB.add(vTF2);
                vNorB.add(vTF3);
                vNorB.add(vTF4);
                
                vNor.add(vNorB);

            vNor.add(new JLabel("Detalle del certificado"), SwingConstants.CENTER);
            
            this.add(vNor, BorderLayout.NORTH);
            
        //----------------------------------------------------------------------
        
        String [] vEncabezado = {"Item", "Costo actual", "Avance", "Monto a pagar"};
        vTabla = new JTable(vSis.DevolverRenglonesCertificadoVista(vCertificado), vEncabezado);
        
        vTabla.setPreferredSize(new Dimension(600,100));
        vTabla.setPreferredScrollableViewportSize(vTabla.getPreferredSize());
        vTabla.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(vTabla), BorderLayout.CENTER);
        
        //----------------------------------------------------------------------
        
        vSur = new JPanel();
        vSur.setLayout(new GridLayout(3, 1));
    
            vSur.add(new JLabel (("Monto total: " + vCertificado.getVCostoTotal() + "$"), SwingConstants.CENTER));
        
            vSurA = new JPanel();
            vSurA.setLayout(new GridLayout(1, 2));
            
                vBotVolver = new JButton("Volver al menu");
                vBotEstado = new JButton("Marcar certificado como pagado");
            
                vSurA.add(vBotVolver);
                vSurA.add(vBotEstado);
                
                vSur.add(vSurA);
                
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
        
        this.vBotVolver.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaCertificados1();
        });
        
        this.vBotEstado.addActionListener((ActionEvent evt) -> {
            
            if (!vCertificado.getVPagado()){
                
                vCertificado.Pagar();
                vTF4.setText("Pagado");
                
                vLog.setText("El certificado se guardo como pagado");
                
            }else{
                vLog.setText("ERROR: El certificado ya esta pagado");
            }
            
        });
    }
} 
