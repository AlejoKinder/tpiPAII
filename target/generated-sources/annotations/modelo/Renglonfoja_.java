package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Fojamedicion;
import modelo.Item;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-18T17:18:16", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Renglonfoja.class)
public class Renglonfoja_ { 

    public static volatile SingularAttribute<Renglonfoja, Item> vIdItem;
    public static volatile SingularAttribute<Renglonfoja, Fojamedicion> vIdFoja;
    public static volatile SingularAttribute<Renglonfoja, Integer> vIdRenglon;
    public static volatile SingularAttribute<Renglonfoja, Integer> vPorcentajeAcumulado;
    public static volatile SingularAttribute<Renglonfoja, Integer> vPorcentajeAnterior;
    public static volatile SingularAttribute<Renglonfoja, Integer> vPorcentajeActual;

}