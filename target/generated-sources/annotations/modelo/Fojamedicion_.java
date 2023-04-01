package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Certificadopago;
import modelo.Obra;
import modelo.Renglonfoja;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-01T16:05:20", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Fojamedicion.class)
public class Fojamedicion_ { 

    public static volatile SingularAttribute<Fojamedicion, Certificadopago> certificadopago;
    public static volatile SingularAttribute<Fojamedicion, Integer> vIdFoja;
    public static volatile ListAttribute<Fojamedicion, Renglonfoja> renglonfojaList;
    public static volatile SingularAttribute<Fojamedicion, String> vFechaEmision;
    public static volatile SingularAttribute<Fojamedicion, Obra> vObra;

}