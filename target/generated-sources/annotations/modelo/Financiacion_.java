package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Obra;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-01T16:05:20", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Financiacion.class)
public class Financiacion_ { 

    public static volatile SingularAttribute<Financiacion, Integer> vIdFinanciacion;
    public static volatile ListAttribute<Financiacion, Obra> obraList;
    public static volatile SingularAttribute<Financiacion, String> vDescripcion;

}