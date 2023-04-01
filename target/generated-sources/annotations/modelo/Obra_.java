package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Empresa;
import modelo.Financiacion;
import modelo.Fojamedicion;
import modelo.Item;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-01T16:05:20", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Obra.class)
public class Obra_ { 

    public static volatile SingularAttribute<Obra, Financiacion> vFinanciacion;
    public static volatile SingularAttribute<Obra, Integer> vPlazo;
    public static volatile SingularAttribute<Obra, Integer> vIdObra;
    public static volatile ListAttribute<Obra, Item> itemList;
    public static volatile ListAttribute<Obra, Fojamedicion> fojamedicionList;
    public static volatile SingularAttribute<Obra, Empresa> vEmpresa;
    public static volatile SingularAttribute<Obra, String> vLocalidad;
    public static volatile SingularAttribute<Obra, Integer> vCantidadViviendas;
    public static volatile SingularAttribute<Obra, String> vDenominacion;
    public static volatile SingularAttribute<Obra, String> vFechaInicio;

}