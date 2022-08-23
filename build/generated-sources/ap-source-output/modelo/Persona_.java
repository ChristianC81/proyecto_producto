package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Usuario;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-22T19:10:27")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile ListAttribute<Persona, Usuario> usuarioList;
    public static volatile SingularAttribute<Persona, String> cedula;
    public static volatile SingularAttribute<Persona, Integer> idpersona;
    public static volatile SingularAttribute<Persona, String> apellido;
    public static volatile SingularAttribute<Persona, String> correo;
    public static volatile SingularAttribute<Persona, String> direccion;
    public static volatile SingularAttribute<Persona, String> celular;
    public static volatile SingularAttribute<Persona, String> nombre;

}