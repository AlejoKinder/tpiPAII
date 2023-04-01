package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Certificadopago;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-01T16:05:20", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Rengloncertificado.class)
public class Rengloncertificado_ { 

    public static volatile SingularAttribute<Rengloncertificado, Integer> vIdRenglon;
    public static volatile SingularAttribute<Rengloncertificado, Certificadopago> vIdCertificado;
    public static volatile SingularAttribute<Rengloncertificado, Double> vCostoActual;
    public static volatile SingularAttribute<Rengloncertificado, Integer> vAvance;
    public static volatile SingularAttribute<Rengloncertificado, Double> vCostoAPagar;

}