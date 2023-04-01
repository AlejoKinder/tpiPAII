package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Fojamedicion;
import modelo.Rengloncertificado;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-01T16:05:20", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Certificadopago.class)
public class Certificadopago_ { 

    public static volatile SingularAttribute<Certificadopago, Integer> vIdCertificado;
    public static volatile SingularAttribute<Certificadopago, Boolean> vPagado;
    public static volatile SingularAttribute<Certificadopago, Fojamedicion> vFoja;
    public static volatile ListAttribute<Certificadopago, Rengloncertificado> rengloncertificadoList;
    public static volatile SingularAttribute<Certificadopago, String> vFechaEmision;
    public static volatile SingularAttribute<Certificadopago, Double> vCostoTotal;

}