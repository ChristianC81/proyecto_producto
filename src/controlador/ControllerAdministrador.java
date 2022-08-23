/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import modelo.PersonaJpaController;
import modelo.ProductoJpaController;
import modelo.UsuarioJpaController;
import proyecto_producto.ManagerFactory;
import vista.ViewAdministrador;
import vista.ViewLogin;
import vista.interna.ViewPersona;
import vista.interna.ViewProducto;
import vista.interna.ViewUsuario;

/**
 *
 * @author chris
 */
public class ControllerAdministrador extends javax.swing.JFrame {

    ViewAdministrador vista;
    ManagerFactory manager;

    public ControllerAdministrador(ViewAdministrador vista, ManagerFactory manager) {
        this.vista = vista;
        this.manager = manager;
        this.vista.setExtendedState(MAXIMIZED_BOTH);
        this.vista.setVisible(true);
        controlEvento();

    }

    public void controlEvento() {
        this.vista.getjMenuItemPersona().addActionListener(l -> cargarVistaPersona());
        this.vista.getjMenuItemProducto().addActionListener(l -> cargarVistaProducto());
        this.vista.getjMenuItemUsuario().addActionListener(l -> cargarVistaUsuario());
        this.vista.getBtnSalir().addActionListener(l -> salirVistaAdmin());
    }
    public static ViewPersona vistaPersona;
    public static ViewProducto vistaProducto;
       public static ViewUsuario vistaUsuario;


    public void cargarVistaPersona() {
        new ControllerPersona(vistaPersona, manager, new PersonaJpaController(manager.getEntityManagerFactory()), this.vista.getDpnFondoAdministrador());
    }
    public void cargarVistaProducto() {
        new ControllerProducto(vistaProducto, manager, new ProductoJpaController(manager.getEntityManagerFactory()), this.vista.getDpnFondoAdministrador());
    }
     public void cargarVistaUsuario() {
        new ControllerUsuario(vistaUsuario, manager, new UsuarioJpaController(manager.getEntityManagerFactory()), this.vista.getDpnFondoAdministrador());
    }
    public void salirVistaAdmin() {
        ManagerFactory managerLog = new ManagerFactory();
        ViewLogin vistaLog = new ViewLogin();
        UsuarioJpaController modeloUsu = new UsuarioJpaController(managerLog.getEntityManagerFactory());
        this.vista.dispose();
        new ControllerLogin(managerLog, vistaLog, modeloUsu);
    }
}
