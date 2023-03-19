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
public final class VistaEmpresas extends JFrame {
    
    private JTable vTabla;
    
    private JPanel vPSur;
    
        private JPanel vPSur1;
        private JLabel vTituloCrear;
    
        private JPanel vPSur2;
        
        private JTextField vCuit;
        private JTextField vNombre;
        private JTextField vDireccion;
        private JTextField vRepLegal;
        private JTextField vRepTecnico;
    
        private JPanel vPSur3;
        private JButton vBotonCrear;
        private JButton vBotonAtras;
        
        private JPanel vPSur4;
        private JLabel vLog;
    
    private Sistema vSis;
    
    //--------------------------------------------------------------------------
    
    public VistaEmpresas (Sistema vSis){
        this.vSis = vSis;
        this.IniciarVista();
    }
    
    public void IniciarVista(){
        
        String [] vEncabezado = {"Cuit", "Nombre", "Direccion", "Rep. Legal", "Rep. Tecnico"};
        vTabla = new JTable(vSis.DevolverEmpresasVista(), vEncabezado);
        this.ActualizarTabla();
        
        vTabla.setPreferredSize(new Dimension(600,100));
        vTabla.setPreferredScrollableViewportSize(vTabla.getPreferredSize());
        vTabla.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(vTabla), BorderLayout.CENTER);
        
        //----------------------------------------------------------------------
        
        vPSur = new JPanel();
        vPSur.setLayout(new GridLayout(4, 1));
        
        vPSur1 = new JPanel();

        vTituloCrear = new JLabel ("Ingrese los datos de la nueva empresa aqui abajo");
        vPSur1.add(this.vTituloCrear);

        vPSur.add(vPSur1);

        vPSur2 = new JPanel();
        vPSur2.setLayout(new GridLayout(1, 5));

        vCuit = new JTextField (10);
        vNombre = new JTextField (10);
        vDireccion = new JTextField (10);
        vRepLegal = new JTextField (10);
        vRepTecnico = new JTextField (10);

        vPSur2.add(this.vCuit);
        vPSur2.add(this.vNombre);
        vPSur2.add(this.vDireccion);
        vPSur2.add(this.vRepLegal);
        vPSur2.add(this.vRepTecnico);

        vPSur.add(vPSur2);

        vPSur3 = new JPanel();
        vPSur3.setLayout(new GridLayout(1, 2));

        vBotonAtras = new JButton ("Volver al menu principal");
        vBotonCrear = new JButton ("Crear nueva empresa");

        vPSur3.add(this.vBotonAtras);
        vPSur3.add(this.vBotonCrear);

        vPSur.add(vPSur3);
        
        vPSur4 = new JPanel();

        vLog = new JLabel ("Aqui puede ver las empresas existentes y crear nuevas empresas");
        vPSur4.add(this.vLog);
        
        vPSur.add(vPSur4);
           
        this.add(vPSur, BorderLayout.SOUTH);
        
        //----------------------------------------------------------------------
        
        setTitle("Empresas");
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
        String [] vEncabezado = {"Cuit", "Nombre", "Direccion", "Rep. Legal", "Rep. Tecnico"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverEmpresasVista(), vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    private void EmpresaValida() throws Exception{
        if (vCuit.getText().length() == 0){
            throw new Exception ("ERROR: La empresa debe tener un CUIT");
        }
        
        if(!vAux.isInteger(vCuit.getText())){
            throw new Exception ("ERROR: El CUIT de la empresa debe ser un numero");
        }
        
        if (vNombre.getText().length() == 0){
            throw new Exception ("ERROR: La empresa debe tener un nombre");
        }
        
        if (vDireccion.getText().length() == 0){
            throw new Exception ("ERROR: La empresa debe tener una direccion");
        }
        
        if (vRepLegal.getText().length() == 0){
            throw new Exception ("ERROR: La empresa debe tener un representante legal");
        }

        if (vRepTecnico.getText().length() == 0){
            throw new Exception ("ERROR: La empresa debe tener un representante tecnico");
        }
        
    }
    
    private void CrearEmpresa(){
        System.out.println("Crear Empresa Presionado");
        
        try{
            
            this.EmpresaValida();
            vSis.CrearEmpresa(vAux.toInteger(vCuit.getText()), vNombre.getText(), vDireccion.getText(), vRepLegal.getText(), vRepTecnico.getText());
            this.ActualizarTabla();

            vLog.setText("Empresa " + vNombre.getText() + " creada");

        }catch(Exception e){
            vLog.setText(e.getMessage());
        }
        
    }
    
}
