/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tpipaii;

import controlador.*;
import java.util.*;
import java.lang.*;
//import vista.*;

/**
 *
 * @author dany_
 */
public class TpiPAII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Sistema vSis = new Sistema();
        
        try{
            //vSis.CrearEmpresa(111, "Thai Cuisine", "Newbery 3480", "Velazquez Mauricio", "Kinder Alejo");            
            //vSis.CrearEmpresa(222, "TecnoMisiones", "Florencio 3762", "Echeverria Celeste", "Pereyra Alejandro");
            
            
            //vSis.CrearFinanciacion("Ni idea");
            
            //vSis.CrearObra("Itambe Guazu", "Posadas", 10, "13/10/2022", 12, 251100, "Ni idea");
            //vSis.CrearObra("Itambe Mini", "Posadas", 20, "14/10/2022", 12, 251100, "Ni idea");
            
            /*vSis.CrearItem(2, "Piso", (float)2.5, (float)3.5, (float)4.5, (double)5000, "13/10/2022");
            ////vSis.CrearItem(1, "Techo", (float)2.5, (float)3.5, (float)4.5, (double)8000, "13/10/2022");
            
            vSis.CrearCosto(1, 1, (double)7500, "14/10/2022");

            vSis.DevolverCostosItems(1);
            
            
            Integer[] vIncidencia = new Integer[3];
            
            vIncidencia[0] = 30;
            vIncidencia[1] = 20;
            vIncidencia[2] = 50;
            
            vSis.RedefinirIncidenciaItems(vSis.BuscarObraId(1), vIncidencia);
            
            Integer[] vOrden = new Integer[3];
            
            vOrden[0] = 1;
            vOrden[1] = 2;
            vOrden[2] = 3;
            
            vSis.RedefinirOrdenItems(vSis.BuscarObraId(1), vOrden);
            
            
            
            vSis.CrearFojaMedicion(1, "15/10/2022");
            
            Integer[] vPorcentajes = new Integer[3];
            
            vPorcentajes[0] = 25;
            vPorcentajes[1] = 15;
            vPorcentajes[2] = 10;
            
            vSis.ActualizarFojaMedicion(vSis.BuscarFojaMedicion(1, "15/10/2022"), vPorcentajes);
            
            System.out.println("El avance de la obra 1 es: " + vSis.DevolverAvanceObra(1) + "%");
            
            
            
            vSis.CrearCertificadoPago(1, "15/10/2022", "17/10/2022");
            
            System.out.println("El costo total de el certificado es: " + vSis.DevolverCostoTotalCertificado(1));*/
            
            //------------------------------------------------------------------
            
            vSis.AbrirVistaMenuPrincipal();
            
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
