/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodosIlegales;
import java.io.Serializable;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dany_
 */
public class Auxiliar implements Serializable {
    
     public Integer SumarArray(Integer vArray[]) {
        Integer vSumaTotal = 0;
        for (Integer a = 0; a < vArray.length; a++) {
            vSumaTotal = vSumaTotal +  vArray[a];
        }
        return vSumaTotal;
    }
    
    public boolean ValoresRepetidosArray(Integer vArray[]) {
        for (Integer a = 0; a < vArray.length; a++) {
            for (Integer b = (a+1); b < vArray.length; b++) {
                if (Objects.equals(vArray[a], vArray[b])){
                    return true; 
                }
            }
        }
        return false;
    }
    
    public boolean BuscarValorArray(Integer vArray[], Integer vBuscado){
        for (Integer a = 0; a < vArray.length; a++) {
            if (Objects.equals(vBuscado, vArray[a])){
                    return true; 
            }
        }
        return false;
    }
    
    public Integer[] TransformarFecha(String vFecha){
        
        String vFechaSeparada [] = vFecha.split("/+");
        
        Integer vFechaTransformada[] = new Integer[3];
        
        vFechaTransformada[0] = Integer.valueOf(vFechaSeparada[0]);
        vFechaTransformada[1] = Integer.valueOf(vFechaSeparada[1]);
        vFechaTransformada[2] = Integer.valueOf(vFechaSeparada[2]);
    
        return vFechaTransformada;
    }
    
    public Integer[] DevolverFechaMayor(Integer[] vFechaTransformada1, Integer[] vFechaTransformada2){
       

        //Comparacion de años
        if (vFechaTransformada1[2] > vFechaTransformada2[2]) {      
            return vFechaTransformada1;
        } else if (vFechaTransformada1[2] < vFechaTransformada2[2]) {
            return vFechaTransformada2;
        } else {
            
            //Comparacion de meses
            if (vFechaTransformada1[1] > vFechaTransformada2[1]) {
                return vFechaTransformada1;
            } else if (vFechaTransformada1[1] < vFechaTransformada2[1]) {
                return vFechaTransformada2;
            } else {
                
                //Comparacion de dias
                if (vFechaTransformada1[0] > vFechaTransformada2[0]) {
                    return vFechaTransformada1;
                } else if (vFechaTransformada1[0] < vFechaTransformada2[0]) {
                    return vFechaTransformada2;
                } else {
                    return vFechaTransformada2;
                }
            }
        }
    }
    
    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
    
    public Integer toInteger (String input){
        System.out.println("Viejo texto entero: " + input);
        input = input.replace(".", "");
        System.out.println("Nuevo texto entero: " + input);
        
        Integer number = Integer.valueOf(input);
        return number;
    }
    
    public boolean isDouble (String input){
        try{
            Double.parseDouble(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public Double toDouble (String input){
        System.out.println("Viejo texto double: " + input);
        input = input.replace(",", ".");
        
        while(input.lastIndexOf(".") != input.indexOf(".")){
            input = input.replaceFirst("\\.", "");
        }
        input = input.concat("d");
        
        System.out.println("Nuevo texto double: " + input);
        
        Double numero = Double.parseDouble(input);
        return numero;
    }
    
    public Float toFloat (String input){
        System.out.println("Viejo texto float: " + input);
        input = input.replace(",", ".");
        input = input.concat("f");
        
        System.out.println("Nuevo texto float: " + input);
        
        Float numero = Float.parseFloat(input);
        return numero;
    }
    
    public boolean esFecha (String a){
        if (a.length() == 10){
            if (a.charAt(2) == '/'){
                if (a.charAt(5) == '/'){
                    return true;
                }
            }
        }
        return false;
    }
}
