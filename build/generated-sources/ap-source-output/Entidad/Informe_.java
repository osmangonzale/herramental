package Entidad;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-10-03T15:03:47")
@StaticMetamodel(Informe.class)
public class Informe_ { 

    public static volatile SingularAttribute<Informe, String> descripcion;
    public static volatile SingularAttribute<Informe, String> dimensiones;
    public static volatile SingularAttribute<Informe, Date> fchInicio;
    public static volatile SingularAttribute<Informe, Date> fchFin;
    public static volatile SingularAttribute<Informe, String> material;
    public static volatile SingularAttribute<Informe, Integer> idInforme;
    public static volatile SingularAttribute<Informe, Integer> num;
    public static volatile SingularAttribute<Informe, Date> fchRegistro;
    public static volatile SingularAttribute<Informe, Integer> duracion;
    public static volatile SingularAttribute<Informe, String> usuRegistro;
    public static volatile SingularAttribute<Informe, Integer> cantidad;

}