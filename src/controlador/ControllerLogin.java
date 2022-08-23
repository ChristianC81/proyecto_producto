/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.PersistenceException;
import modelo.Usuario;
import modelo.UsuarioJpaController;
import proyecto_producto.ManagerFactory;
import vista.ViewAdministrador;
import vista.ViewLogin;

/**
 *
 * @author chris
 */
public class ControllerLogin {

    private ManagerFactory manager;
    private ViewLogin vista;
    private UsuarioJpaController modelo;
    ViewAdministrador vistaAdmin = new ViewAdministrador();
    Usuario usuario;

    public ControllerLogin(ManagerFactory manager, ViewLogin vista, UsuarioJpaController modelo) {
        this.manager = manager;
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        iniciarControl();
    }

    public void iniciarControl() {
        vista.getBtnEntrar().addActionListener(l -> controlLogin());
        vista.getBtnCerrar().addActionListener(l -> System.exit(0));
    }

    public void controlLogin() {
        try {
            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            vistaAdmin.setLocationRelativeTo(null);
            String usuario = vista.getTxtUsuario().getText();
            String contrasenia = new String(vista.getTxtContrasenia().getPassword());
            Usuario user = modelo.buscarUsuario(usuario, contrasenia);

            if (user != null) {
                Resouces.success("!BIENVENIDO!", " Inicio de Sesión exitoso \n Fecha de Inicio: "+dtf4.format(LocalDateTime.now())+"\n Usuario con Nombre: " +usuario);
                new ControllerAdministrador(vistaAdmin, manager);
                vista.dispose();
            } else {
                Resouces.error("Usuario Incorrecto", "Ingrese correctamente sus credenciales :D");
            }
        } catch (PersistenceException e) {
            Resouces.error("Base de Datos desconectada D:", "Contactese con el Administrador");
        }
    }
}
