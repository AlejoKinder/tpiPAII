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
import modelo.Obra;

/**
 *
 * @author dany_
 */
public class VistaObras2 extends JFrame {
    
    private JPanel vPNorte;
    
        private JLabel vTexto1;
        
        private JPanel vPNorte2;
        private JTextField vIdObra;
        private JComboBox vObras;
        private JButton vBotonBuscarItems;
        
        private JLabel vTexto2;
        
    private JTable vTabla;
    
    private JPanel vPSur;
        
        private JLabel vTexto3;
        
        private JPanel vPSur2;
        private JLabel vTexto4A;
        private JLabel vTexto4B;
        private JLabel vTexto4C;
    
        private JPanel vPSur3;
        private JTextField vIdItem;
        private JTextField vFecha;
        private JTextField vMonto;
        
        private JPanel vPSur4;
        private JButton vBotonAtras;
        private JButton vBotonInfo;
        private JButton vBotonCosto;
        
        private JLabel vLog;
    
    private Sistema vSis;
    
    private String[][] vArray1;
    private String[][] vArray2;
    boolean vTablaRotada = false;
    private Obra vObraActual = null;
    
    //--------------------------------------------------------------------------
    
    public VistaObras2 (Sistema vSis){
        this.vSis = vSis;
        IniciarVista();
    }
    
    public void IniciarVista(){
        
        vPNorte = new JPanel();
        vPNorte.setLayout(new GridLayout(3, 1));
        
            vTexto1 = new JLabel("Ingrese el id de la obra abajo");
            vPNorte.add(vTexto1);
        
            vPNorte2 = new JPanel();
            vPNorte2.setLayout(new GridLayout(1, 3));
            
                vObras = new JComboBox();
            
                vIdObra = new JTextField(10);
                vPNorte2.add(vIdObra);
                vBotonBuscarItems = new JButton("Buscar Items");
                vPNorte2.add(vBotonBuscarItems);
                
                vPNorte.add(vPNorte2);
                
            vTexto2 = new JLabel("Aqui abajo se muestran los items");
            vPNorte.add(vTexto2);
            
            this.add(vPNorte, BorderLayout.NORTH);
        
        
        //----------------------------------------------------------------------
        
        String [] vEncabezado = {"Id", "Denominacion", "Orden", "Incidencia", "Costo vigente", "Fecha ult. costo"};
        vArray1 = new String[0][6];
        vTabla = new JTable(vArray1, vEncabezado);
        
        vTabla.setPreferredSize(new Dimension(600,100));
        vTabla.setPreferredScrollableViewportSize(vTabla.getPreferredSize());
        vTabla.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(vTabla), BorderLayout.CENTER);
        
        //----------------------------------------------------------------------
        
        vPSur = new JPanel();
        vPSur.setLayout(new GridLayout(5, 1));
        
        vTexto3 = new JLabel ("Aqui abajo puede ingresar nuevos costos");
        vPSur.add(vTexto3);
        
        vPSur2 = new JPanel();
        vPSur2.setLayout(new GridLayout(1,3));
        
            vTexto4A = new JLabel ("Id del item");
            vPSur2.add(vTexto4A);
            vTexto4B = new JLabel ("Fecha actual");
            vPSur2.add(vTexto4B);
            vTexto4C = new JLabel ("Nuevo monto");
            vPSur2.add(vTexto4C);
            
            vPSur.add (vPSur2);
    
        vPSur3 = new JPanel();
        vPSur3.setLayout(new GridLayout(1,3));
        
            vIdItem = new JTextField (10);
            vPSur3.add(vIdItem);
            vFecha  = new JTextField (10);
            vPSur3.add(vFecha);
            vMonto  = new JTextField (10);
            vPSur3.add(vMonto);
        
            vPSur.add (vPSur3);
        
        vPSur4 = new JPanel();
        vPSur4.setLayout(new GridLayout(1,3));
        
            vBotonAtras = new JButton ("Volver atras");
            vPSur4.add(vBotonAtras);
            vBotonInfo = new JButton ("Mostrar mas info");
            vPSur4.add(vBotonInfo);
            vBotonCosto = new JButton ("Crear nuevo costo");
            vPSur4.add(vBotonCosto);
        
            vPSur.add (vPSur4);
        
        vLog = new JLabel ("log");
        vPSur.add(vLog);
        
        this.add(vPSur, BorderLayout.SOUTH);
        
        //----------------------------------------------------------------------
        
        setTitle("Obras");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setLocationRelativeTo(null);
        
        //----------------------------------------------------------------------
        
        this.vBotonBuscarItems.addActionListener((ActionEvent evt) -> {
            this.BuscarItems();
        });
        
        this.vBotonAtras.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaObras1();
        });
        
        this.vBotonInfo.addActionListener((ActionEvent evt) -> {
            this.RotarTabla();
        });
        
        this.vBotonCosto.addActionListener((ActionEvent evt) -> {
            this.AgregarCosto();
        });
    }
    
    //--------------------------------------------------------------------------
    
    Auxiliar vAux = new Auxiliar();
    
    private void ActualizarTabla1(){
        String [] vEncabezado = {"Id", "Denominacion", "Orden", "Incidencia", "Costo vigente", "Fecha ult. costo"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vArray1, vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    private void ActualizarTabla2(){
        String [] vEncabezado = {"Id", "Denominacion", "Imp. flete", "Imp. gastos", "Imp. utilidad"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vArray2, vEncabezado);
    
        vTabla.setModel(vTablaNueva);
        
        if ((vTabla.getRowCount()*vTabla.getRowHeight())>100){
            vTabla.setPreferredSize(new Dimension(600,(vTabla.getRowCount()*vTabla.getRowHeight())));
        }
    }
    
    private void BuscarItems(){
        
        if (vAux.isInteger(this.vIdObra.getText())){
        
            vObraActual = vSis.BuscarObraId(vAux.toInteger(this.vIdObra.getText()));
            if (vObraActual != null){
                
                try{
                    vArray1 = vSis.DevolverItemsVista1(vObraActual);
                    vArray2 = vSis.DevolverItemsVista2(vObraActual);

                    ActualizarTabla1();
                    vTablaRotada = false;
                    
                    vLog.setText("Se cargaron exitosamente los items");
                    
                }catch (Exception e){
                    vLog.setText("Error: " + e.getMessage());
                }
            }else{
                vLog.setText("Error: El id de la obra no existe");
            }
        }else{
            vLog.setText("Error: El id de la obra ingresado, no es un numero");
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
    
    private void Condiciones() throws Exception{
    
        if (this.vIdItem.getText().length() == 0){
            throw new Exception ("Tiene que haber un numero de id de item");
        }else if (!vAux.isInteger(this.vIdItem.getText())){
            throw new Exception ("El id de item tiene que ser un numero");
        }else if (this.vFecha.getText().length() == 0){
            throw new Exception ("Tiene que haber una fecha de creado del costo");
        }else if (!vAux.esFecha(this.vFecha.getText())){
            throw new Exception ("La fecha no tiene el formato correcto");
        }else if (this.vMonto.getText().length() == 0){
            throw new Exception ("Tiene que haber un nuevo monto");
        }else if (!vAux.isDouble(this.vMonto.getText())){
            throw new Exception ("El nuevo monto no tiene el formato correcto");
        }
        
    }
    
    private void AgregarCosto(){
        try{
            Condiciones();
            
            vSis.CrearCosto(vObraActual.getVIdObra(), vAux.toInteger(this.vIdItem.getText()), vAux.toDouble(this.vMonto.getText()), this.vFecha.getText());
            
            this.BuscarItems();

            ActualizarTabla1();
            vTablaRotada = false;
            
            vLog.setText("Costo nuevo creado exitosamente");
            
        }catch(Exception e){
            vLog.setText("Error: " + e.getMessage());
        }
    }
 
    public void Reset(){
        ActualizarTabla1();
        vTablaRotada = false;
    }
}

