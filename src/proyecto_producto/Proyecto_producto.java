/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_producto;

import controlador.ControllerLogin;
import modelo.UsuarioJpaController;
import vista.ViewLogin;

/**
 *
 * @author chris
 */
public class Proyecto_producto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ManagerFactory manager = new ManagerFactory();
        ViewLogin vista = new ViewLogin();
        UsuarioJpaController modelo = new UsuarioJpaController(manager.getEntityManagerFactory());

        ControllerLogin log = new ControllerLogin(manager, vista, modelo);

    }

}
