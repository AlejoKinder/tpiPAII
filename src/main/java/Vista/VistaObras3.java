

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import controlador.Sistema;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import metodosIlegales.Auxiliar;
import modelo.Obra;

/**
 *
 * @author dany_
 */
public class VistaObras3 extends JFrame{
    
    private JPanel vNor1;   //Se divide en 1x5
        
        private JLabel vTxt1;
   
        private JPanel vNor1A;    //Se divide en 4x1
        
            private JLabel vTxt2;
            private JLabel vTxt3;
            private JLabel vTxt4;
            private JLabel vTxt5;
            
        private JPanel vNor1B;   //Se divide en 4x1
        
            private JTextField vTF1;
            private JTextField vTF2;
            private JTextField vTF3;
            private JFormattedTextField vTF4;
            
        private JPanel vNor1C;    //Se divide en 4x1
        
            private JLabel vTxt6;
            private JLabel vTxt7;
            private JLabel vTxt8;
            private JLabel vTxt9;
            
        private JPanel vNor1D;   //Se divide en 4x1
        
            private JSpinner vTF5;
            private JFormattedTextField vTF6;
            private JComboBox vTF7;
            private JComboBox vTF8;
    
    private JPanel vNor2;   //Se divide en 1x8
        
        //vTxt1, vNor1A y vNor1B
    
        private JLabel vTxt10;
    
        private JPanel vNor2A;  //Se divide en 5x1
        
            private JLabel vTxt11;
            private JLabel vTxt12;
            private JLabel vTxt13;
            private JLabel vTxt14;
            private JLabel vTxt15;
            
        private JPanel vNor2B;  //Se divide en 5x1
        
            private JTextField vTF9;
            private JFormattedTextField vTF10;
            private JFormattedTextField vTF11;
            private JFormattedTextField vTF12;
            private JFormattedTextField vTF13;
    
        private JLabel vTxt16;
        
    private JPanel vNor3;   //Se divide en 1x4
    
        //vTxt1, vNor1A y vNor1B
    
        private JLabel vTxt17;
            
    //--------------------------------------------------------------------------
    
    private JTable vTabla2;
    
    //--------------------------------------------------------------------------
    
    
    private JPanel vSur1;   //Se divide en 1x2
    
        private JPanel vSur1A;  //Se divide en 2x1
        
            private JButton vVolver;
            private JButton vContinuar;
            
        private JLabel vLog;
        
    private JPanel vSur2;   //Se divide en 1x3
        
        private JPanel vSur2A;  //Se divide en 2x1
        
            private JButton vCrearItem;
            private JButton vBorrarItem;
            
        private JPanel vSur2B;  //Se divide en 2x1
        
            //vVolver y vContinuar
        
        //vLog
        
    private JPanel vSur3;   //Se divide en 1x2
        
        private JPanel vSur3A;  //Se divide en 3x1
    
            //vVolver y vContinuar
            private JButton vVerificar3;
            
        //vLog
        
    //--------------------------------------------------------------------------
    
    
    
    //--------------------------------------------------------------------------    
        
    private final Sistema vSis;
    private final Auxiliar vAux = new Auxiliar();
    private Integer vEstado;
    private Obra vNuevaObra = null;
    
    //--------------------------------------------------------------------------
    
    public VistaObras3 (Sistema vSis){
        this.vSis = vSis;
        this.IniciarVista();
    }
    
    private void IniciarVista(){
        
        vEstado = 1;
        
        vNor1 = new JPanel();
        vNor1.setLayout(new GridLayout(5, 1));
        
            vTxt1 = new JLabel ("Ingrese los datos de la nueva obra",SwingConstants.CENTER);
            vNor1.add(vTxt1);
   
            vNor1A = new JPanel();
            vNor1A.setLayout(new GridLayout(1, 4));
        
                vTxt2 = new JLabel ("Id de obra",SwingConstants.CENTER);
                vTxt3 = new JLabel ("Denominacion",SwingConstants.CENTER);
                vTxt4 = new JLabel ("Localidad",SwingConstants.CENTER);
                vTxt5 = new JLabel ("Cant. viviendas",SwingConstants.CENTER);
                
                vNor1A.add(vTxt2);
                vNor1A.add(vTxt3);
                vNor1A.add(vTxt4);
                vNor1A.add(vTxt5);

                vNor1.add(vNor1A);
            
            vNor1B = new JPanel();
            vNor1B.setLayout(new GridLayout(1, 4));
       
               
            
                vTF1 = new JTextField (10);
                vTF1.setText(String.valueOf(vSis.DevolverIdUltimaObra()+1));
                vTF1.setEditable(false);
                vTF2 = new JTextField (10);
                vTF3 = new JTextField (10);
                vTF4 = new JFormattedTextField();
                vTF4.setValue(0);
                
                vNor1B.add(vTF1);
                vNor1B.add(vTF2);
                vNor1B.add(vTF3);
                vNor1B.add(vTF4);
                
                vNor1.add(vNor1B);
                
            vNor1C= new JPanel();
            vNor1C.setLayout(new GridLayout(1, 4));
        
                vTxt6 = new JLabel ("Fecha inicio",SwingConstants.CENTER);
                vTxt7 = new JLabel ("Plazo en meses",SwingConstants.CENTER);
                vTxt8 = new JLabel ("Empresa",SwingConstants.CENTER);
                vTxt9 = new JLabel ("Financiacion",SwingConstants.CENTER);
                
                vNor1C.add(vTxt6);
                vNor1C.add(vTxt7);
                vNor1C.add(vTxt8);
                vNor1C.add(vTxt9);

                vNor1.add(vNor1C);
            
            vNor1D = new JPanel();
            vNor1D.setLayout(new GridLayout(1, 4));
       
                vTF5 = new JSpinner (new SpinnerDateModel(new Date(), null, null, Calendar.MONTH));
                vTF5.setEditor(new JSpinner.DateEditor(vTF5, "dd/MM/yyyy"));
                vTF6 = new JFormattedTextField ();
                vTF6.setValue(0);
                vTF7 = new JComboBox (vSis.DevolverEmpresas());
                vTF8 = new JComboBox (vSis.DevolverFinanciaciones());
                
                vNor1D.add(vTF5);
                vNor1D.add(vTF6);
                vNor1D.add(vTF7);
                vNor1D.add(vTF8);
                
                vNor1.add(vNor1D);
                
            this.add(vNor1, BorderLayout.NORTH);
            
        vSur1 = new JPanel();
        vSur1.setLayout(new GridLayout(2, 1));
        
            vSur1A = new JPanel();
            vSur1A.setLayout(new GridLayout(1, 2));
        
                vVolver = new JButton("Cancelar creado");
                vContinuar = new JButton("Proximo paso");

                vSur1A.add(vVolver);
                vSur1A.add(vContinuar);
                
                vSur1.add(vSur1A);
                
            vLog = new JLabel("Aca se mostraran los errores y los cambios",SwingConstants.CENTER);
            vSur1.add(vLog);
            
            this.add(vSur1, BorderLayout.SOUTH);
        
        //----------------------------------------------------------------------
        
        setTitle("Crear nueva obra");
        setSize(700,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setLocationRelativeTo(null);
        
        //----------------------------------------------------------------------
        
        this.vVolver.addActionListener((ActionEvent evt) -> {
            vSis.AbrirVistaObras1();
        });
        
        this.vContinuar.addActionListener((ActionEvent evt) -> {
            if (vEstado == 1){
                
                try{
                    ComprobarObra();
                    
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
                    
                    vNuevaObra = vSis.CrearObraVista(
                            vTF2.getText(), 
                            vTF3.getText(), 
                            vAux.toInteger(vTF4.getText()), 
                            dateFormat.format(vTF5.getValue()), 
                            vAux.toInteger(vTF6.getText()), 
                            vTF7.getSelectedItem().toString(), 
                            vTF8.getSelectedItem().toString()
                    );
                    
                    vEstado = 2;
                    Estado2();
                    this.setLocationRelativeTo(null);
                }catch (Exception e){
                    vLog.setText("ERROR: " + e.getMessage());
                }
                
            }else if (vEstado == 2){
                
                try{
                    ComprobarItemsObra();
                    
                    vEstado = 3;
                    Estado3();
                    this.setLocationRelativeTo(null);
                }catch (Exception e){
                    vLog.setText("ERROR: " + e.getMessage());
                }
            }else if (vEstado == 3){
            
                try{

                    CargarDatos();
                    vSis.AbrirVistaObras1();

                }catch (Exception e){
                    vLog.setText("ERROR: " + e.getMessage());
                    vContinuar.setEnabled(false);
                }

            }
        });
        
    }
    
    private void Estado2(){
        
        this.remove(vNor1);
        this.remove(vSur1);

        //------------------------------------------------------------------

        vNor2 = new JPanel();
        vNor2.setLayout(new GridLayout(10, 1));

            vTxt1.setText("Datos de la obra");
            vNor2.add(vTxt1);

            vNor2.add(vNor1A);

            vTF2.setEditable(false);
            vTF3.setEditable(false);
            vTF4.setEditable(false);
            vNor2.add(vNor1B);

            vNor2.add(vNor1C);

            vTF5.setEnabled(false);
            vTF6.setEditable(false);
            vTF7.setEnabled(false);
            vTF8.setEnabled(false);
            vNor2.add(vNor1D);

            vNor2.add(new JLabel(""));

            vTxt10 = new JLabel("Ingrese los items de la obra", SwingConstants.CENTER);
            vNor2.add(vTxt10);

            vNor2A = new JPanel();
            vNor2A.setLayout(new GridLayout(1, 5));

                vTxt11 = new JLabel ("Denominacion", SwingConstants.CENTER);
                vTxt12 = new JLabel ("Impuesto por flete", SwingConstants.CENTER);
                vTxt13 = new JLabel ("Impuesto por gastos", SwingConstants.CENTER);
                vTxt14 = new JLabel ("Impuesto por utilidad", SwingConstants.CENTER);
                vTxt15 = new JLabel ("Monto inicial", SwingConstants.CENTER);

                vNor2A.add(vTxt11);
                vNor2A.add(vTxt12);
                vNor2A.add(vTxt13);
                vNor2A.add(vTxt14);
                vNor2A.add(vTxt15);

                vNor2.add(vNor2A);

            vNor2B = new JPanel();
            vNor2B.setLayout(new GridLayout(1, 5));
            
                vTF9 = new JTextField(10);
                vTF10 = new JFormattedTextField ();
                vTF10.setValue(0f);
                vTF11 = new JFormattedTextField ();
                vTF11.setValue(0f);
                vTF12 = new JFormattedTextField ();
                vTF12.setValue(0f);
                vTF13 = new JFormattedTextField ();
                vTF13.setValue(0d);

                vNor2B.add(vTF9);
                vNor2B.add(vTF10);
                vNor2B.add(vTF11);
                vNor2B.add(vTF12);
                vNor2B.add(vTF13);

                vNor2.add(vNor2B);

            vTxt16 = new JLabel("Items creados", SwingConstants.CENTER);
            vNor2.add(vTxt16);

            this.add(vNor2, BorderLayout.NORTH);


        String [] vEncabezado = {"Id", "Denominacion", "Imp. flete", "Imp. gastos", "Imp. gastos", "Monto inicial"};
        String[][] vArray = new String[0][6];
        vTabla2 = new JTable(vArray, vEncabezado);

        vTabla2.setPreferredSize(new Dimension(600,100));
        vTabla2.setPreferredScrollableViewportSize(vTabla2.getPreferredSize());
        vTabla2.setFillsViewportHeight(true);

            this.add(new JScrollPane(vTabla2), BorderLayout.CENTER);


        vSur2 = new JPanel();
        vSur2.setLayout(new GridLayout(3, 1));   

            vSur2A = new JPanel();
            vSur2A.setLayout(new GridLayout(1, 2));   

                vCrearItem = new JButton("Crear nuevo item");
                vBorrarItem = new JButton("Borrar item seleccionado");

                vSur2A.add(vCrearItem);
                vSur2A.add(vBorrarItem);

                vSur2.add(vSur2A);

            vSur2B = new JPanel();
            vSur2B.setLayout(new GridLayout(1, 2));   

                vSur2B.add(vVolver);
                vSur2B.add(vContinuar);

                vSur2.add(vSur2B);
            
            vLog.setText("Obra creada correctamente");
            vSur2.add(vLog);

            this.add(vSur2, BorderLayout.SOUTH);

        //----------------------------------------------------------------------

        this.setSize(700,600);
        this.revalidate();
        this.repaint();
        
        //----------------------------------------------------------------------
        
        this.vCrearItem.addActionListener((ActionEvent evt) -> {
            
            try{
                ComprobarItem();
                vSis.CrearItemVista(vNuevaObra, vTF9.getText(), vAux.toFloat(vTF10.getValue().toString()), vAux.toFloat(vTF11.getValue().toString()), vAux.toFloat(vTF12.getValue().toString()), vAux.toDouble(vTF13.getValue().toString()), vNuevaObra.getVFechaInicio());
                System.out.println("se crea el item");
                ActualizarTabla();
                vLog.setText("Item creado correctamente");
                
            }catch (Exception e){
                vLog.setText("ERROR: " + e.getMessage());
            }
            
        });
        
        this.vBorrarItem.addActionListener((ActionEvent evt) -> {
            
            vLog.setText("Falta implementar");
            
        });
    
    }
    
    private void Estado3() throws Exception{
    
        this.remove(vNor2);
        this.remove(vSur2);

        //------------------------------------------------------------------

        vNor3 = new JPanel();
        vNor3.setLayout(new GridLayout(7, 1));   

            vNor3.add(vTxt1);
            vNor3.add(vNor1A);  
            vNor3.add(vNor1B);
            vNor3.add(vNor1C);
            vNor3.add(vNor1D);

            vNor3.add(new JLabel(""));

            vTxt17 = new JLabel("Ingrese el orden y la incidencia de los items", SwingConstants.CENTER);
            vNor3.add(vTxt17);

            this.add(vNor3, BorderLayout.NORTH);
            

        String [] vEncabezado = {"Item", "Orden", "Incidencia"};

        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverItemsVista3(vNuevaObra), vEncabezado);

        vTabla2.setModel(vTablaNueva);

        if ((vTabla2.getRowCount()*vTabla2.getRowHeight())>100){
            vTabla2.setPreferredSize(new Dimension(600,(vTabla2.getRowCount()*vTabla2.getRowHeight())));
        }
        
        //----------------------------------------------------------------------

        vSur3 = new JPanel();
        vSur3.setLayout(new GridLayout(2, 1));   

            vSur3A = new JPanel();
            vSur3A.setLayout(new GridLayout(1, 3));   

                vSur3A.add(vVolver);

                vVerificar3 = new JButton("Escribir valores en obra");
                vSur3A.add(vVerificar3);

                vContinuar.setText("Terminar creado de obra");
                vContinuar.setEnabled(false);
                vSur3A.add(vContinuar);

                vSur3.add(vSur3A);
                
            vLog.setText("Items cargados correctamente");
            vSur3.add(vLog);

            this.add(vSur3, BorderLayout.SOUTH);
        
        this.revalidate();
        this.repaint();
        
        //----------------------------------------------------------------------
        
        this.vVerificar3.addActionListener((ActionEvent evt) -> {
            
            try{
                
                CargarDatos();
                vContinuar.setEnabled(true);
                
            }catch (Exception e){
                vLog.setText("ERROR: " + e.getMessage());
                vContinuar.setEnabled(false);
            }
            
        });
        
    }
    
    
    private void ActualizarTabla() throws Exception{
        String [] vEncabezado = {"Id", "Denominacion", "Imp. flete", "Imp. gastos", "Imp. gastos", "Monto inicial"};
        DefaultTableModel vTablaNueva = new DefaultTableModel(vSis.DevolverItemsVista(vNuevaObra), vEncabezado);
    
        vTabla2.setModel(vTablaNueva);
        
        if ((vTabla2.getRowCount()*vTabla2.getRowHeight())>100){
            vTabla2.setPreferredSize(new Dimension(600,(vTabla2.getRowCount()*vTabla2.getRowHeight())));
        }
    }
    
    
    private void ComprobarObra() throws Exception{
        
        if (vTF2.getText().length() == 0){
            throw new Exception ("La obra necesita una denominacion");
        }else if (vTF3.getText().length() == 0){
            throw new Exception ("La obra necesita una localidad");
        }else if (vAux.toInteger(vTF4.getText())<=0){
            throw new Exception ("La cantidad de viviendas ingresada es invalida");
        }else if (vAux.toInteger(vTF6.getText())<=0){
            throw new Exception ("El plazo ingresado es invalido");
        }
    }
    
    private void ComprobarItem() throws Exception{
        
        if (vTF9.getText().length() == 0){
            throw new Exception ("El item necesita una denominacion");
        }else if (vAux.toFloat(vTF10.getText())<=0){
            throw new Exception ("El impuesto por flete ingresado no es valido");
        }else if (vAux.toFloat(vTF11.getText())<=0){
            throw new Exception ("El impuesto por gastos ingresado no es valido");
        }else if (vAux.toFloat(vTF12.getText())<=0){
            throw new Exception ("El impuesto por utilidad ingresado no es valido");
        }else if (vAux.toDouble(vTF13.getText())<=0){
            throw new Exception ("El monto inicial ingresado no es valido");
        }else if (vNuevaObra.getItemList().size() == 30){
            throw new Exception ("Se ha alcanzado el numero maximo de items");
        }
    }
    
    private void ComprobarItemsObra() throws Exception{
        
        if (vNuevaObra.getItemList().isEmpty()){
            throw new Exception ("Se necesitan por lo menos 1 item en la obra");
        }
    }
   
    
    private Integer[] ArmarOrden() throws Exception{
        Integer[] vOrden = new Integer[vTabla2.getRowCount()];
        
        Integer vPos = 0;
        
        while (vPos != vTabla2.getRowCount()){
            
            try{
                vOrden[vPos] = vAux.toInteger(vTabla2.getValueAt(vPos, 1).toString());
            }catch(Exception e){
                throw new Exception ("Uno de los valores de la columna orden, es invalido");
            }
            
            vPos = vPos + 1;
        }
        
        return vOrden;
    }
    
    private Integer[] ArmarIncidencia() throws Exception{
        Integer[] vIncidencia = new Integer[vTabla2.getRowCount()];
        
        Integer vPos = 0;
        
        while (vPos != vTabla2.getRowCount()){
            
            String vAuxiliar = vTabla2.getValueAt(vPos, 2).toString().replace("%", "");
            
            try{
                vIncidencia[vPos] = vAux.toInteger(vAuxiliar);
            }catch(Exception e){
                throw new Exception ("Uno de los valores de la columna Incidencia, es invalido");
            }
            
            vPos = vPos + 1;
        }
        
        return vIncidencia;
    }
    
    private void CargarDatos() throws Exception{
        
        Integer[] vOrden = ArmarOrden();
        Integer[] vIncidencia = ArmarIncidencia();

        vSis.RedefinirOrdenItems(vNuevaObra, vOrden);
        vSis.RedefinirIncidenciaItems(vNuevaObra, vIncidencia);
        

        vLog.setText("Orden y incidencia cargados correctamente");
        
    }
}
