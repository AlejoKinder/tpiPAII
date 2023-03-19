package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Costo;
import modelo.Obra;
import modelo.Renglonfoja;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-18T17:18:16", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Integer> vIdItem;
    public static volatile SingularAttribute<Item, Integer> vIncidencia;
    public static volatile SingularAttribute<Item, Double> vImpuestoFlete;
    public static volatile SingularAttribute<Item, Obra> vIdObra;
    public static volatile SingularAttribute<Item, Renglonfoja> renglonfoja;
    public static volatile SingularAttribute<Item, Double> vImpuestoGastos;
    public static volatile ListAttribute<Item, Costo> costoList;
    public static volatile SingularAttribute<Item, Integer> vOrden;
    public static volatile SingularAttribute<Item, String> vDenominacion;
    public static volatile SingularAttribute<Item, Double> vImpuestoUtilidad;

}