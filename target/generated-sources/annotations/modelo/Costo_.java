package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Item;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-18T17:18:16", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Costo.class)
public class Costo_ { 

    public static volatile SingularAttribute<Costo, Item> vIdItem;
    public static volatile SingularAttribute<Costo, String> vFechaInicioVigencia;
    public static volatile SingularAttribute<Costo, Integer> vIdCosto;
    public static volatile SingularAttribute<Costo, Double> vMonto;

}