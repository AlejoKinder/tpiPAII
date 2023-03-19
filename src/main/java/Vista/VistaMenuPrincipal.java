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
import java.util.*;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author Mauricio
 */
public class VistaMenuPrincipal extends JFrame {
    
    private JPanel sRotulo;
    
        private JLabel sTitulo;
        private JLabel sNombre;
        
    private JPanel sBotones;
    
        private JButton sBot1;
        private JButton sBot2;
        private JButton sBot3;
        private JButton sBot4;
        private JButton sBot5;
        
    private Sistema vSis;
    
    //--------------------------------------------------------------------------
    
    public VistaMenuPrincipal (Sistema vSis){
        this.vSis = vSis;
        this.IniciarVista();
    }
    
    public void IniciarVista(){
        
        //NORTE
        sRotulo = new JPanel();
        sRotulo.setLayout(new GridLayout(2, 1));
        sRotulo.setBorder(new EmptyBorder(10,10,10,10));
        
            sTitulo = new JLabel("TPI", SwingConstants.CENTER);
            sTitulo.setFont(new Font("Cambria Math", Font.BOLD, 50));
            
            sNombre = new JLabel("Programacion avanzada I", SwingConstants.CENTER);
            sNombre.setFont(new Font("Cambria Math", Font.BOLD, 20));
            
            sRotulo.add(sTitulo);
            sRotulo.add(sNombre);
            
            this.add(sRotulo, BorderLayout.NORTH);
        
        
        //SUR
        sBotones = new JPanel();
        sBotones.setLayout(new GridLayout(5, 1, 10, 10));
        sBotones.setBorder(new EmptyBorder(10,10,10,10));
        
            sBot1 = new JButton("Ver Empresas");
            sBot1.setFont(new Font("Cambria Math", Font.BOLD, 20));
            sBot2 = new JButton("Ver Financiaciones");
            sBot2.setFont(new Font("Cambria Math", Font.BOLD, 20));
            sBot3 = new JButton("Ver Obras");
            sBot3.setFont(new Font("Cambria Math", Font.BOLD, 20));
            sBot4 = new JButton("Ver Fojas de medicion");
            sBot4.setFont(new Font("Cambria Math", Font.BOLD, 20));
            sBot5 = new JButton("Ver Certificados de pago");
            sBot5.setFont(new Font("Cambria Math", Font.BOLD, 20));

            sBotones.add(sBot1);
            sBotones.add(sBot2);
            sBotones.add(sBot3);
            sBotones.add(sBot4);
            sBotones.add(sBot5);
            
            this.add(sBotones, BorderLayout.SOUTH);
            
        
        //----------------------------------------------------------------------
        
        setTitle("Menu principal");
        setSize(300,400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        //----------------------------------------------------------------------
        
        this.sBot1.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaEmpresas();
        });
        
        this.sBot2.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaFinanciaciones();
        });
        
        this.sBot3.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaObras1();
        });
        
        this.sBot4.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaFojas1();
        });
        
        this.sBot5.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaCertificados1();
        });
        
    }
    
}
