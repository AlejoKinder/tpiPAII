package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Obra;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-01T16:05:20", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> vRepresentanteTecnico;
    public static volatile SingularAttribute<Empresa, String> vNombre;
    public static volatile ListAttribute<Empresa, Obra> obraList;
    public static volatile SingularAttribute<Empresa, Integer> vCuit;
    public static volatile SingularAttribute<Empresa, String> vDireccion;
    public static volatile SingularAttribute<Empresa, String> vRepresentanteLegal;

}