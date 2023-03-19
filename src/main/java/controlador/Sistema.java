/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Vista.*;
import java.util.ArrayList;
import java.util.Objects;
import modelo.*;
import ControladorBD.*;
import java.util.List;
import metodosIlegales.*;

/**
 *
 * @author dany_
 */
public class Sistema {    
    //--------------------------------------------------------------------------
    
    private ArrayList<Financiacion> vFinanciaciones = new ArrayList(); 
    private ArrayList<Empresa> vEmpresas = new ArrayList(); 
    private ArrayList<Obra> vObras = new ArrayList();
    private ArrayList<Fojamedicion> vFojas = new ArrayList();
    private ArrayList<Certificadopago> vCertificados = new ArrayList();
    
    //--------------------------------------------------------------------------
    
    Auxiliar vAux = new Auxiliar();
    
    VistaMenuPrincipal vVistaA = null;
    VistaEmpresas vVista1 = null;
    VistaFinanciaciones vVista2 = null;
    VistaObras1 vVista3 = null;
    VistaObras2 vVista3A = null;
    VistaObras3 vVista3B = null;
    VistaFojas1 vVista4 = null;
    VistaFojas2 vVista4A = null;
    VistaCertificados1 vVista5 = null;
    VistaCertificados2 vVista5A = null;
    
    EmpresaJpaController empresaJpa = new EmpresaJpaController();
    FinanciacionJpaController financiacionJpa = new FinanciacionJpaController();
    ObraJpaController obraJpa = new ObraJpaController();
    ItemJpaController itemJpa = new ItemJpaController();
    CostoJpaController costoJpa = new CostoJpaController();
    FojamedicionJpaController fojaJpa = new FojamedicionJpaController();
    RenglonfojaJpaController renglonfojaJpa = new RenglonfojaJpaController();
    
    //--------------------------------------------------------------------------
    
    public void CrearEmpresa (Integer vCuit, String vNombre, String vDireccion, String vRepresentanteLegal, String vRepresentanteTecnico) throws Exception{
        Empresa emp = empresaJpa.findEmpresa(vCuit);
        if (emp == null){
            Empresa vNuevaEmpresa = new Empresa (vCuit, vNombre, vDireccion, vRepresentanteLegal, vRepresentanteTecnico);
            empresaJpa.create(vNuevaEmpresa);
            vEmpresas.add(vNuevaEmpresa); //ACORDARSE DE ELIMINAS!!!.            
        }else{
            throw new Exception ("ERROR: Ya existe una empresa con el CUIT ingresado");
        }
    }
  
    //METODO OBSOLETO????
    public Empresa BuscarEmpresaCuit (Integer vCuit){ 
        for (Empresa a: vEmpresas){
            if (Objects.equals(vCuit, a.getVCuit())){
                return a;
            }
        }
        return null;
    }
    
    
    public Empresa BuscarEmpresaNombre (String vNombre){
        for (Empresa a: vEmpresas){
            if (Objects.equals(vNombre, a.getVNombre())){
                return a;
            }
        }
        return null;
    }
    
    public String [][] DevolverEmpresasVista (){
        
        List<Empresa> empresas = new ArrayList<>();
        empresas = empresaJpa.findEmpresaArrayList(); //recuperamos todas las entidades de la BD.
        
        String [][] vArray = new String[empresas.size()][5];
        Integer vPos = 0;
        
        System.out.println("Tamaño del arrayList: " + empresas.size());
        
        for (Empresa a: empresas){
            vArray[vPos][0] = a.getVCuit().toString();
            vArray[vPos][1] = a.getVNombre();
            vArray[vPos][2] = a.getVDireccion();
            vArray[vPos][3] = a.getVRepresentanteLegal();
            vArray[vPos][4] = a.getVRepresentanteTecnico();
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public String [] DevolverEmpresas(){
        
        List<Empresa> empresas = new ArrayList<>();
        empresas = empresaJpa.findEmpresaArrayList(); //recuperamos todas las entidades de la BD.
        
        String [] vArray = new String[empresas.size()];
        Integer vPos = 0;
        
        for (Empresa a: empresas){
        
            vArray[vPos] = a.getVNombre();
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    //--------------------------------------------------------------------------
    
    public void CrearFinanciacion (String vDescripcion) throws Exception{
        Financiacion vNuevaFinanciacion = new Financiacion (financiacionJpa.getFinanciacionCount()+1, vDescripcion);
        financiacionJpa.create(vNuevaFinanciacion);
        vFinanciaciones.add(vNuevaFinanciacion);
    }
    
    public Financiacion BuscarFinanciacionId (Integer vIdFinanciacion){
        for (Financiacion a: vFinanciaciones){
            if (Objects.equals(vIdFinanciacion, a.getVIdFinanciacion())){
                return a;
            }
        }
        return null;
    }
    
    public Financiacion BuscarFinanciacionDescripcion (String vDescripcion){
        for (Financiacion a: vFinanciaciones){
            if (Objects.equals(vDescripcion, a.getVDescripcion())){
                return a;
            }
        }
        return null;
    }
    
    public String [][] DevolverFinanciacionesVista (){
        
        List<Financiacion> financiaciones = new ArrayList<>();
        financiaciones = financiacionJpa.findFinanciacionArrayList(); //recuperamos todas las entidades de la BD.
        
        String [][] vArray = new String[financiaciones.size()][2];
        Integer vPos = 0;
        
        System.out.println("Tamaño del arrayList: " + financiaciones.size());
        
        
        for (Financiacion a: financiaciones){
        
            vArray[vPos][0] = a.getVIdFinanciacion().toString();
            vArray[vPos][1] = a.getVDescripcion();
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public Integer DevolverIdUltimaFinanciacion (){
        return financiacionJpa.getFinanciacionCount();
    }
    
    //SIRVE ESTO??    
    public String [] DevolverFinanciaciones(){
        
        List<Financiacion> financiaciones = new ArrayList<>();
        financiaciones = financiacionJpa.findFinanciacionArrayList(); //recuperamos todas las entidades de la BD.
        
        String [] vArray = new String[financiaciones.size()];
        Integer vPos = 0;
        
        for (Financiacion a: financiaciones){
        
            vArray[vPos] = a.getVDescripcion();
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    //--------------------------------------------------------------------------
    
    public void CrearObra (String vDenominacion, String vLocalidad, Integer vCantidadViviendas, String vFechaInicio, Integer vPlazo, Integer vCuit, String vDescripFinanciacion) throws Exception{
        
        //Financiacion vFinanciacion = BuscarFinanciacionId(vIdFinanciacion);
        Financiacion vFinanciacion = financiacionJpa.findFinanciacionByDescription(vDescripFinanciacion);
        System.out.println(vFinanciacion.getVIdFinanciacion());
        if (vFinanciacion != null){
           
            Empresa vEmpresa = empresaJpa.findEmpresa(vCuit);
            if (vEmpresa != null){
                
                Obra vNuevaObra = new Obra (vObras.size()+1, vDenominacion, vLocalidad, vCantidadViviendas, vFechaInicio, vPlazo, vEmpresa, vFinanciacion);
                vObras.add(vNuevaObra); //ACORDARSE DE BORRAR!!!
                obraJpa.create(vNuevaObra);
            }else{
                throw new Exception ("La empresa ingresada, no existe");
            }
        }else{
            throw new Exception ("La financiacion ingresada, no existe");
        }
    }
    
    public Obra CrearObraVista (String vDenominacion, String vLocalidad, Integer vCantidadViviendas, String vFechaInicio, Integer vPlazo, String vNombre, String vDescripcion) throws Exception{
        
        //Financiacion vFinanciacion = BuscarFinanciacionDescripcion(vDescripcion);
        Financiacion vFinanciacion = financiacionJpa.findFinanciacionByDescription(vDescripcion);
        if (vFinanciacion != null){
           
            Empresa vEmpresa = empresaJpa.findEmpresaByName(vNombre);
            System.out.println(vEmpresa.getVCuit());
            if (vEmpresa != null){                
                Obra vNuevaObra = new Obra (this.DevolverIdUltimaObra()+1, vDenominacion, vLocalidad, vCantidadViviendas, vFechaInicio, vPlazo, vEmpresa, vFinanciacion);
                
                obraJpa.create(vNuevaObra);
                
                return vNuevaObra;
                
            }else{
                throw new Exception ("La empresa ingresada, no existe");
            }
        }else{
            throw new Exception ("La financiacion ingresada, no existe");
        }
    }
    
    //METODO OBSOLETO??
    public Obra BuscarObraId (Integer vIdObra){
        return obraJpa.findObra(vIdObra);
    }
    
    //METODO OBSOLETO??
    public Obra BuscarObraDenominacion (String vDenominacion){
        for (Obra a: vObras){
            if (Objects.equals(vDenominacion, a.getVDenominacion())){
                return a;
            }
        }
        return null;
    }
    
    public void RedefinirIncidenciaItems (Obra vObra, Integer vValores[]) throws Exception{

        if (!vObra.getItemList().isEmpty()){

            if (vValores.length == vObra.getItemList().size()){

                Integer vValorSumado = vAux.SumarArray(vValores);
                if (vValorSumado == 100){

                    Integer vPos = 0;
                    for (Item a: vObra.getItemList()){
                        a.setVIncidencia(vValores[vPos]);
                        itemJpa.edit(a);
                        vPos = vPos + 1;
                    }
                    
                    obraJpa.edit(vObra);
                }else{
                    throw new Exception ("La suma total de los valores ingresados, no da 100%, da " + vValorSumado + "%");
                }
            }else{
                throw new Exception ("La cantidad de valores ingresada, no es igual a la cantidad de items de la obra");
            }
        }else{
            throw new Exception ("La obra ingresada, no tiene items incluidos");
        }
    }
    
    public void RedefinirOrdenItems (Obra vObra, Integer vValores[]) throws Exception {
            
        if (!vObra.getItemList().isEmpty()){

            if (vValores.length == vObra.getItemList().size()){

                if (vAux.BuscarValorArray(vValores, null) == false){

                    if (vAux.ValoresRepetidosArray(vValores) == false){

                        Integer vPos = 0;
                        for (Item a: vObra.getItemList()){
                            a.setVOrden(vValores[vPos]);
                            itemJpa.edit(a);
                            vPos = vPos + 1;
                        }
                        
                        obraJpa.edit(vObra);
                    }else{
                        throw new Exception ("Hay dos o mas items con el mismo numero de orden");
                    }
                }else{
                    throw new Exception ("No se puede asignar orden null a un item");
                }
            }else{
                throw new Exception ("La cantidad de valores ingresada, no es igual a la cantidad de items de la obra");
            }
        }else{
            throw new Exception ("La obra ingresada, no tiene items incluidos");
        }
        
    }
    
    public Integer DevolverAvanceObra (Integer vIdObra) throws Exception{
        //Obra vObra = BuscarObraId(vIdObra);
        Obra vObra = obraJpa.findObra(vIdObra);
        if (vObra != null){
            
            if (!vObra.getItemList().isEmpty()){
                
                Fojamedicion vUltimaFoja = this.BuscarUltimaFoja(vIdObra);
                if (vUltimaFoja != null){
                    
                    Double vAvance = 0.0d;
                    Integer vPos = 0;
                    for (Renglonfoja a: vUltimaFoja.getRenglonfojaList()){
                        
                        System.out.println("Avance = " + a.getVPorcentajeAcumulado() + " * " + (vObra.getItemList().get(vPos).getVIncidencia()) + " /100");
                        vAvance = vAvance + ((a.getVPorcentajeAcumulado()*vObra.getItemList().get(vPos).getVIncidencia())/100);
                        vPos = vPos + 1;
                    }
                    
                    return (int)Math.floor(vAvance);
                }else{
                    return 0;
                }
            }else{
                throw new Exception ("La obra ingresada, no tiene items incluidos");
            }
        }else{
            throw new Exception ("La obra ingresada, no existe");
        }
    }
    
    public void DevolverCostosItems (Integer vIdObra) throws Exception{
        //Obra vObra = BuscarObraId(vIdObra);
        Obra vObra = obraJpa.findObra(vIdObra);
        if (vObra != null){
            
            if (!vObra.getItemList().isEmpty()){
                
                for (Item a: vObra.getItemList()) {
                    System.out.println("El costo vigente de el item " + a.getVDenominacion() + " es de: " + a.DevolverCostoVigente().getVMonto());
                }
                
            }else{
                throw new Exception ("La obra ingresada, no tiene items incluidos");
            }
        }else{
            throw new Exception ("La obra ingresada, no existe");
        }
    }
    
    public Integer DevolverIdUltimaObra (){
        return obraJpa.getObraCount();
    }
    
    public String [][] DevolverObrasVista1 (){
        
        List<Obra> obras = new ArrayList<Obra>();
        obras = obraJpa.findObraEntities();
        
        String [][] vArray = new String[obras.size()][6];
        
        Integer vPos = 0;
        
        for (Obra a: obras){
        
            vArray[vPos][0] = a.getVIdObra().toString();
            vArray[vPos][1] = a.getVDenominacion();
            vArray[vPos][2] = a.getVLocalidad();
            vArray[vPos][3] = a.getVCantidadViviendas() + "";
            vArray[vPos][4] = a.getVFechaInicio();
            vArray[vPos][5] = a.getVPlazo() + "";
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public String [][] DevolverObrasVista2 (){
        List<Obra> obras = new ArrayList<Obra>();
        obras = obraJpa.findObraEntities();
        
        String [][] vArray = new String[obras.size()][6];
        
        Integer vPos = 0;
        
        for (Obra a: obras){
        
            vArray[vPos][0] = a.getVIdObra().toString();
            vArray[vPos][1] = a.getVDenominacion();
            vArray[vPos][2] = a.getVEmpresa().getVNombre();
            vArray[vPos][3] = a.getVFinanciacion().getVDescripcion();
            
            try{
                vArray[vPos][4] = this.DevolverAvanceObra(a.getVIdObra()).toString() + "%";
            }catch (Exception e){
                System.out.println(e.getMessage());
                vArray[vPos][4] = "Error";
            }
            
            vArray[vPos][5] = "IMPLEMENTAR";   
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public String [] DevolverObras(){
        
        List<Obra> obras = new ArrayList<Obra>();
        obras = obraJpa.findObraEntities();
        
        String [] vArray = new String[obras.size()];
        
        Integer vPos = 0;
        
        for (Obra a: obras){
        
            vArray[vPos] = a.getVDenominacion();
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public void CargarObra (Obra vObra){
        vObras.add(vObra);  //ACORDARSE DE BORRAR!!!.
        obraJpa.create(vObra);
    }
    
    //--------------------------------------------------------------------------
    
    public void CrearItem (Integer vIdObra, String vDenominacion, Float vImpuestoFlete, Float vImpuestoGastos, Float vImpuestoUtilidad, Double vMontoInicial, String vFechaInicioVigencia) throws Exception{
        //Obra vObra = BuscarObraId(vIdObra);
        Obra vObra = obraJpa.findObra(vIdObra);
        System.out.println("1");
        System.out.println(vObra.getVDenominacion());
        
        if (vObra != null){
            Item vNuevoItem = new Item (1, vDenominacion, 0, 0, vImpuestoFlete, vImpuestoGastos, vImpuestoUtilidad,vObra);
            vObra.AgregarItem(vNuevoItem);
            System.out.println("2");
            itemJpa.create(vNuevoItem);            
            
            Costo vCostoInicial = new Costo (1, vMontoInicial, vFechaInicioVigencia, vNuevoItem);
            vNuevoItem.AgregarCosto(vCostoInicial);
            System.out.println("3");
            costoJpa.create(vCostoInicial);
            itemJpa.edit(vNuevoItem); //edito el item porque se agrego un nuevo item a su lista.           
            obraJpa.edit(vObra);  //edito la obra porque se agrego un nuevo item a su lista.
            System.out.println("LLEGO MOSTRO");
        }else{
            throw new Exception ("La obra ingresada, no existe");
        }
    }
     
    public void CrearItemVista (Obra vObra, String vDenominacion, Float vImpuestoFlete, Float vImpuestoGastos, Float vImpuestoUtilidad, Double vMontoInicial, String vFechaInicioVigencia) throws Exception{

        Item vNuevoItem = new Item (vObra.getItemList().size()+1, vDenominacion, 0, 0, vImpuestoFlete, vImpuestoGastos, vImpuestoUtilidad, vObra);
        itemJpa.create(vNuevoItem);
        vObra.AgregarItem(vNuevoItem);
        

        Costo vCostoInicial = new Costo (1, vMontoInicial, vFechaInicioVigencia, vNuevoItem);
        costoJpa.create(vCostoInicial);
        vNuevoItem.AgregarCosto(vCostoInicial);
        itemJpa.edit(vNuevoItem);
        obraJpa.edit(vObra);
    }
     
    
    public String [][] DevolverItemsVista (Obra vObra) throws Exception{ //devuelvee las listas en strings
        String [][] vArray = new String[vObra.getItemArrayList().size()][6];
        
        Integer vPos = 0;
        for (Item a: vObra.getItemList()){
        
            vArray[vPos][0] = a.getVIdItem().toString();
            vArray[vPos][1] = a.getVDenominacion();
            vArray[vPos][2] = a.getVImpuestoFlete() + "%";
            vArray[vPos][3] = a.getVImpuestoGastos() + "%";
            vArray[vPos][4] = a.getVImpuestoUtilidad() + "%";
            vArray[vPos][5] = "$" + a.DevolverCostoVigente().getVMonto();
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public String [][] DevolverItemsVista1 (Obra vObra) throws Exception{
        String [][] vArray = new String[vObra.getItemList().size()][6];
        
        Integer vPos = 0;
        for (Item a: vObra.getItemList()){
        
            vArray[vPos][0] = a.getVIdItem().toString();
            vArray[vPos][1] = a.getVDenominacion();
            vArray[vPos][2] = a.getVOrden() + "";
            vArray[vPos][3] = a.getVIncidencia() + "%";
            vArray[vPos][4] = "$" + a.DevolverCostoVigente().getVMonto();
            vArray[vPos][5] = a.DevolverCostoVigente().getVFechaInicioVigencia();
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public String [][] DevolverItemsVista2 (Obra vObra) throws Exception{
        String [][] vArray = new String[vObra.getItemList().size()][5];
        
        Integer vPos = 0;
        for (Item a: vObra.getItemList()){
        
            vArray[vPos][0] = a.getVIdItem().toString();
            vArray[vPos][1] = a.getVDenominacion();
            vArray[vPos][2] = a.getVImpuestoFlete() + "%";
            vArray[vPos][3] = a.getVImpuestoGastos() + "%";
            vArray[vPos][4] = a.getVImpuestoUtilidad() + "%";
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public String [][] DevolverItemsVista3 (Obra vObra) throws Exception{
        String [][] vArray = new String[vObra.getItemList().size()][3];
        
        Integer vPos = 0;
        for (Item a: vObra.getItemList()){
        
            vArray[vPos][0] = a.getVIdItem().toString();
            vArray[vPos][1] = a.getVOrden() + "";
            vArray[vPos][2] = a.getVIncidencia() + "%";
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    //--------------------------------------------------------------------------
    
    public void CrearCosto (Integer vIdObra, Integer vIdItem, Double vMonto, String vFechaInicioVigencia) throws Exception{ //VER

        Obra vObra = obraJpa.findObra(vIdObra);
        
        if (vObra != null){
            
            Item vItem = vObra.BuscarItemId(vIdItem);
            
            if (vItem != null){
                Costo vNuevoCosto = new Costo (vItem.getCostoList().size()+1, vMonto, vFechaInicioVigencia, vItem);
                costoJpa.create(vNuevoCosto);

                vItem.AgregarCosto(vNuevoCosto);
                itemJpa.edit(vItem);

                obraJpa.edit(vObra);
            }else{
            
                throw new Exception ("ERROR: No se encontro el item");
            }
        }else{
        
            throw new Exception ("ERROR: No se encontro la obra");
        }

    }

    //--------------------------------------------------------------------------
    
    public void CrearFojaMedicion (Obra vObra, String vFechaEmision) throws Exception{

        if (!vObra.getItemList().isEmpty()){

            Fojamedicion vNuevaFoja = new Fojamedicion (vFojas.size()+1, vFechaEmision, vObra);

            fojaJpa.create(vNuevaFoja);
            
            Fojamedicion vUltimaFoja = this.BuscarUltimaFoja(vObra.getVIdObra());

            //Agregar condicion para cuando la fecha emision de la nueva foja es menor que el de la ultima foja

            vNuevaFoja.CrearRenglones(vUltimaFoja);
            
            fojaJpa.edit(vNuevaFoja);

            this.vFojas.add(vNuevaFoja);

        }else{
            throw new Exception ("La obra ingresada, no tiene items incluidos");
        }
    }
    
    public void CrearFojamedicionVista (String vDenominacion, String vFechaEmision) throws Exception{
        
        Obra vObra = obraJpa.findObraByName(vDenominacion);
        
        if (vObra != null){
            
            if (!vObra.getItemList().isEmpty()){

                Fojamedicion vNuevaFoja = new Fojamedicion (vFojas.size()+1, vFechaEmision, vObra);

                fojaJpa.create(vNuevaFoja);

                Fojamedicion vUltimaFoja = this.BuscarUltimaFoja(vObra.getVIdObra());

                //Agregar condicion para cuando la fecha emision de la nueva foja es menor que el de la ultima foja

                vNuevaFoja.CrearRenglones(vUltimaFoja);

                fojaJpa.edit(vNuevaFoja);

                this.vFojas.add(vNuevaFoja);

            }else{
                throw new Exception ("La obra ingresada, no tiene items incluidos");
            }
        }else{
            throw new Exception ("La obra ingresada, no existe");
        }
    }
    
    public Fojamedicion BuscarFojamedicion (Integer vIdObra, String vFechaEmision) throws Exception{
        Obra vObra = obraJpa.findObra(vIdObra);
        if (vObra != null){
            
            return fojaJpa.findFojamedicionPorObraYFecha(vObra.getVIdObra(), vFechaEmision);

        }else{
            throw new Exception ("La obra ingresada, no existe");
        }
    }
    
    public Fojamedicion BuscarFojamedicionVista (String vDenominacion, String vFechaEmision) throws Exception{
        Obra vObra = obraJpa.findObraByName(vDenominacion);
        if (vObra != null){
 
            return fojaJpa.findFojamedicionPorObraYFecha(vObra.getVIdObra(), vFechaEmision);

        }else{
            throw new Exception ("La obra ingresada, no existe");
        }
    }
    
    public Fojamedicion BuscarUltimaFoja (Integer vIdObra) throws Exception{

        Obra vObra = obraJpa.findObra(vIdObra);
        if (vObra != null){
            
            if (!vObra.getItemList().isEmpty()){
                
                try{
                    return fojaJpa.findUltimaFoja(vObra);
                }catch(Exception e){
                    return null;
                }
            }else{
                throw new Exception ("La obra ingresada, no tiene items incluidos");
            }
        }else{
            throw new Exception ("La obra ingresada, no existe");
        }
        
    }
    
    public void ActualizarFojamedicion (Fojamedicion vFoja, Integer vPorcentajes[]) throws Exception {
        
        if (vPorcentajes.length == vFoja.getRenglonfojaList().size()){

            if (vAux.BuscarValorArray(vPorcentajes, null) == false){

                Integer vPos = 0;
                for (Renglonfoja a: vFoja.getRenglonfojaList()){
                    a.DefinirPorcentajeActual(vPorcentajes[vPos]);
                    vPos = vPos + 1;
                    
                }

            }else{
                throw new Exception ("No se puede asignar orden null a un porcentaje de avance");
            }
        }else{
            throw new Exception ("La cantidad de valores ingresada, no es igual a la cantidad de items de la foja");
        }
    }
    
    public Integer DevolverAvanceFoja (Fojamedicion vFoja){

        if (vFoja != null){

            Double vAvance = 0.0;
            Integer vPos = 0;
            for (Renglonfoja a: vFoja.getRenglonfojaList()){
                
                vAvance = vAvance + ((a.getVPorcentajeAcumulado()*vFoja.getVObra().getItemList().get(vPos).getVIncidencia())/100);
                vPos = vPos + 1;
            }
            
            return (int)Math.floor(vAvance);
        }else{
            return 0;
        }
    }
    
    public boolean ExistenciaCertificadoDeFoja (Fojamedicion vFoja){
    
        for (Certificadopago a: vCertificados){
            if (a.getVFoja() == vFoja){
                return true;
            }
        }
        return false;
    }
    
    public String [][] DevolverFojasVista (){
        
        List<Fojamedicion> fojas = new ArrayList<Fojamedicion>();
        fojas = fojaJpa.findFojamedicionEntities();
        
        String [][] vArray = new String[fojas.size()][4];
        
        Integer vPos = 0;
        
        for (Fojamedicion a: fojas){
        
            vArray[vPos][0] = a.getVIdFoja().toString();
            vArray[vPos][1] = a.getVObra().getVDenominacion();
            vArray[vPos][2] = a.getVFechaEmision();
            vArray[vPos][3] = (this.DevolverAvanceFoja(a).toString() + "%");
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public String [][] DevolverFojasVistaFiltrado(String vDenominacion){
        
        Obra vObra = obraJpa.findObraByName(vDenominacion);
        
        Integer vContador = fojaJpa.findCantidadFoja(vObra);
        System.out.println("Cantidad de fojas: " + vContador);
        
        if (vContador != 0){
            String [][] vArray = new String[vContador][4];
        
            Integer vPos = 0;
            for (Fojamedicion a: vFojas){

                if (a.getVObra().getVDenominacion().equals(vDenominacion)){
                    vArray[vPos][0] = a.getVIdFoja().toString();
                    vArray[vPos][1] = a.getVObra().getVDenominacion();
                    vArray[vPos][2] = a.getVFechaEmision();
                    vArray[vPos][3] = (this.DevolverAvanceFoja(a).toString() + "%");

                    vPos = vPos + 1;
                }
            }
            System.out.println("Se devolvio");
            return vArray;
            
        }else{
            String[][] vArray = null;
            return vArray;
        }
        
        
    }
    
    public String [][] DevolverRenglonesFojaVista (Fojamedicion vFoja){
        String [][] vArray = new String[vFoja.getRenglonfojaList().size()][4];
        
        Integer vPos = 0;
        for (Renglonfoja a: vFoja.getRenglonfojaList()){
        
            vArray[vPos][0] = a.getVIdItem().getVDenominacion();
            vArray[vPos][1] = a.getVPorcentajeAnterior() + "%";
            vArray[vPos][2] = a.getVPorcentajeActual() + "%";
            vArray[vPos][3] = a.getVPorcentajeAcumulado() + "%";
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    
    
    //--------------------------------------------------------------------------
    
    public void CrearCertificadoPago (Integer vIdObra, String vFechaEmisionFoja, String vFechaEmisionCertificado) throws Exception{
        Fojamedicion vFoja = this.BuscarFojamedicion(vIdObra, vFechaEmisionFoja);
        if (vFoja != null){
            
            Certificadopago vNuevoCertificado = new Certificadopago (this.vCertificados.size()+1, vFechaEmisionCertificado, /*vFoja.getVObra(),*/ vFoja);
            
            vNuevoCertificado.CrearRenglones();
            vCertificados.add(vNuevoCertificado);
            
        }else{
            throw new Exception ("La foja ingresada, no existe");
        }
    }
    
    public void CrearCertificadoPagoVista (Fojamedicion vFoja, String vFechaEmisionCertificado) throws Exception{

        Certificadopago vNuevoCertificado = new Certificadopago (this.vCertificados.size()+1, vFechaEmisionCertificado, vFoja);
            
        vNuevoCertificado.CrearRenglones();
        vCertificados.add(vNuevoCertificado);

    }
    
    public Certificadopago BuscarCertificadoId (Integer vIdCertificado){
        for (Certificadopago a: vCertificados){
            if (Objects.equals(a.getVIdCertificado(), vIdCertificado)){
                return a;
            }
        }
        return null;
    }
    
    public Double DevolverCostoTotalCertificado (Integer vIdCertificado) throws Exception{
        Certificadopago vCertificado = this.BuscarCertificadoId(vIdCertificado);
        if (vCertificado != null){
        
            return vCertificado.getVCostoTotal();
            
        }else{
            throw new Exception ("El certificado ingresado, no existe");
        }
    }

    public String [][] DevolverCertificadosVista (){
        String [][] vArray = new String[vCertificados.size()][5];
        
        Integer vPos = 0;
        for (Certificadopago a: vCertificados){
        
            vArray[vPos][0] = a.getVIdCertificado().toString();
            vArray[vPos][1] = a.getVFoja().getVObra().getVDenominacion();
            vArray[vPos][2] = a.getVFechaEmision();
            vArray[vPos][3] = a.getVCostoTotal() + "";
            
            if (a.getVPagado()){
                vArray[vPos][4] = "Pagado";
            }else{
                vArray[vPos][4] = "Sin pagar";
            }
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    public String [][] DevolverCertificadosVistaFiltrado(String vDenominacion){
        
        Integer vContador = 0;
        for (Certificadopago a: vCertificados){
            if (a.getVFoja().getVObra().getVDenominacion().equals(vDenominacion)){
                vContador = vContador + 1; 
            }
        }
        
        if (vContador != 0){
            String [][] vArray = new String[vContador][5];
        
            Integer vPos = 0;
            for (Certificadopago a: vCertificados){

                if (a.getVFoja().getVObra().getVDenominacion().equals(vDenominacion)){
                    
                    vArray[vPos][0] = a.getVIdCertificado().toString();
                    vArray[vPos][1] = a.getVFoja().getVObra().getVDenominacion();
                    vArray[vPos][2] = a.getVFechaEmision();
                    vArray[vPos][3] = a.getVCostoTotal() + "";

                    if (a.getVPagado()){
                        vArray[vPos][4] = "Pagado";
                    }else{
                        vArray[vPos][4] = "Sin pagar";
                    }

                    vPos = vPos + 1;
                }
            }
            System.out.println("Se devolvio");
            return vArray;
            
        }else{
            String[][] vArray = null;
            return vArray;
        }
        
        
    }
    
    public String [][] DevolverRenglonesCertificadoVista (Certificadopago vCertificado){
        String [][] vArray = new String[vCertificado.getRengloncertificadoList().size()][4];
        
        Integer vPos = 0;
        for (Rengloncertificado a: vCertificado.getRengloncertificadoList()){
        
            vArray[vPos][0] = vCertificado.getVFoja().getRenglonfojaList().get(vPos).getVIdItem().getVDenominacion();
            vArray[vPos][1] = a.getVCostoActual() + "$";
            vArray[vPos][2] = a.getVAvance() + "%";
            vArray[vPos][3] = a.getVCostoAPagar() + "$";
            
            vPos = vPos + 1;
        }
        
        return vArray;
    }
    
    
    //--------------------------------------------------------------------------
    //--VISTAS------------------------------------------------------------------
    
    private void CerrarVistas(){
        
        if (vVistaA != null){
            if (vVistaA.isShowing()){
                vVistaA.setVisible(false);
            }
        }
        
        if (vVista1 != null){
            if (vVista1.isShowing()){
                vVista1.setVisible(false);
                vVista1 = null;
            }
        }
        
        if (vVista2 != null){
            if (vVista2.isShowing()){
                vVista2.setVisible(false);
                vVista2 = null;
            }
        }
        
        if (vVista3 != null){
            if (vVista3.isShowing()){
                vVista3.setVisible(false);
                vVista3 = null;
            }
        }
        
        if (vVista3A != null){
            if (vVista3A.isShowing()){
                vVista3A.setVisible(false);
                vVista3A = null;
            }
        }
        
        if (vVista3B != null){
            if (vVista3B.isShowing()){
                vVista3B.setVisible(false);
                vVista3B = null;
            }
        }
        
        if (vVista4 != null){
            if (vVista4.isShowing()){
                vVista4.setVisible(false);
                vVista4 = null;
            }
        }
        
        if (vVista4A != null){
            if (vVista4A.isShowing()){
                vVista4A.setVisible(false);
                vVista4A = null;
            }
        }
        
        if (vVista5 != null){
            if (vVista5.isShowing()){
                vVista5.setVisible(false);
                vVista5 = null;
            }
        }
        
        if (vVista5A != null){
            if (vVista5A.isShowing()){
                vVista5A.setVisible(false);
                vVista5A = null;
            }
        }
        
    }
    
    public void AbrirVistaMenuPrincipal(){
        
        CerrarVistas();
        
        if (vVistaA == null){
            vVistaA = new VistaMenuPrincipal(this);
        }
                
        vVistaA.setVisible(true);
        
    }
    
    public void AbrirVistaEmpresas(){
        
        CerrarVistas();
        
        if (vVista1 == null){
            vVista1 = new VistaEmpresas(this);
        }
        
        vVista1.setVisible(true);
        
    }
    
    public void AbrirVistaFinanciaciones(){
        
        CerrarVistas();
        
        if (vVista2 == null){
            vVista2 = new VistaFinanciaciones(this);
        }
        
        vVista2.setVisible(true);
        
    }
    
    public void AbrirVistaObras1(){
        
        CerrarVistas();
        
        if (vVista3 == null){
            vVista3 = new VistaObras1(this);
        }
        
        vVista3.setVisible(true);
        
    }
    
    public void AbrirVistaObras2(){
    
        CerrarVistas();
        
        if (vVista3A == null){
            vVista3A = new VistaObras2(this);
        }
        
        vVista3A.setVisible(true);
    
    }
    
    public void AbrirVistaObras3(){
    
        CerrarVistas();
        
        if (vVista3B == null){
            vVista3B = new VistaObras3(this);
        }
        
        vVista3B.setVisible(true);
        
    }
    
    public void AbrirVistaFojas1(){
    
        CerrarVistas();
        
        if (vVista4 == null){
            vVista4 = new VistaFojas1(this);
        }
        
        vVista4.setVisible(true);
        
    }
    
    public void AbrirVistaFojas2(Fojamedicion vFoja){
    
        CerrarVistas();
        
        if (vVista4A == null){
            vVista4A = new VistaFojas2(this, vFoja);
        }
        
        vVista4A.setVisible(true);
        
    }
    
    public void AbrirVistaCertificados1(){
    
        CerrarVistas();
        
        if (vVista5 == null){
            vVista5 = new VistaCertificados1(this);
        }
        
        vVista5.setVisible(true);
        
    }
    
    public void AbrirVistaCertificados2(Certificadopago vCertificado){
    
        CerrarVistas();
        
        if (vVista5A == null){
            vVista5A = new VistaCertificados2(this, vCertificado);
        }
        
        vVista5A.setVisible(true);
        
    }
    
    //--------------------------------------------------------------------------

    public ArrayList<Financiacion> getvFinanciaciones() {
        return vFinanciaciones;
    }

    public ArrayList<Empresa> getvEmpresas() {
        return vEmpresas;
    }

    public ArrayList<Obra> getvObras() {
        return vObras;
    }

    public ArrayList<Fojamedicion> getvFojas() {
        return vFojas;
    }

    public ArrayList<Certificadopago> getvCertificados() {
        return vCertificados;
    }

    //Esto creo el netbeans xd.
    public Fojamedicion BuscarFojaMedicionVista(String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Esto creo el netbeans xd.
    public void CrearFojaMedicionVista(String toString, String format) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Esto creo el netbeans xd.
    public void ActualizarFojaMedicion(Fojamedicion vFoja, Integer[] vPorcentajes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Esto creo el netbeans xd.
    public Fojamedicion BuscarFojaMedicion(int i, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}
