/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Dimension;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Persona;
import modelo.PersonaJpaController;
import modelo.Usuario;
import modelo.UsuarioJpaController;
import modelo.exceptions.NonexistentEntityException;
import proyecto_producto.ManagerFactory;
import rojerusan.*;
import vista.interna.ViewUsuario;

/**
 *
 * @author chris
 */
public class ControllerUsuario {

    ViewUsuario vista;
    ManagerFactory manager;
    UsuarioJpaController modeloUsuario;
    Usuario usuario;
    ModeloTablaUsuario modeloTabla;
    JDesktopPane escritorio;
    ListSelectionModel listaUsuarioModel;
    Resouces notificacion;

    public ControllerUsuario(ViewUsuario vista, ManagerFactory manager, UsuarioJpaController modeloUsuario, JDesktopPane escritorio) {
        this.vista = vista;
        this.manager = manager;
        this.modeloUsuario = modeloUsuario;
        this.escritorio = escritorio;
        if (ControllerAdministrador.vistaUsuario == null) {
            ControllerAdministrador.vistaUsuario = new ViewUsuario();
            this.vista = ControllerAdministrador.vistaUsuario;
            this.escritorio.add(this.vista);
            cargarCombobox();
            cargarDatosUsuarioTbl();
            //Para centar la vista en la ventana
            Dimension desktopSize = this.escritorio.getSize();
            Dimension FrameSize = this.vista.getSize();
            this.vista.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            this.vista.getTblUsuario().setModel(modeloTabla);
            ControllerAdministrador.vistaUsuario.show();
            controlEvento();
        } else {
            ControllerAdministrador.vistaUsuario.show();
        }
    }

    public void controlEvento() {
        this.vista.getBtnCrear().addActionListener(l -> guardarUsuario());
        this.vista.getBtnActualizar().addActionListener(l -> editarUsuario());
        this.vista.getBtnBorrar().addActionListener(l -> eliminarUsuario());
        this.vista.getBtnLimpiar().addActionListener(l -> limpiarC());
        this.vista.getBtnLimpiarBus().addActionListener(l -> limpiarB());
        this.vista.getBtnCancelar().addActionListener(l -> cancelar());

        this.vista.getTblUsuario().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaUsuarioModel = this.vista.getTblUsuario().getSelectionModel();
        listaUsuarioModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    usuarioSeleccionada();
                }
            }
        });
        this.vista.getBtnBuscar().addActionListener(l -> buscarUsuario());
        this.vista.getChkbxMostrarTodos().addActionListener(l -> mostrarTodos());
    
    }

    public void usuarioSeleccionada() {
        if (this.vista.getTblUsuario().getSelectedRow() != -1) {
            usuario = modeloTabla.getFilas().get(this.vista.getTblUsuario().getSelectedRow());
            this.vista.getTxtUsuario().setText(usuario.getUsuario());
            this.vista.getTxtContrasenia().setText(usuario.getClave());
            this.vista.getCbxIdPersona().setSelectedItem(usuario.getIdpersona());

            //Acceso de Botones
            this.vista.getBtnCrear().setEnabled(false);
            this.vista.getBtnBorrar().setEnabled(true);
            this.vista.getBtnActualizar().setEnabled(true);
        }
    }

    public void cargarDatosUsuarioTbl() {
        this.modeloTabla = new ModeloTablaUsuario();
        this.modeloTabla.setFilas(modeloUsuario.findUsuarioEntities());
        this.vista.getTblUsuario().setModel(modeloTabla);
    }

    public void cargarCombobox() {
        try {
            Vector v = new Vector();
            v.addAll(new PersonaJpaController(manager.getEntityManagerFactory()).findPersonaEntities());
            this.vista.getCbxIdPersona().setModel(new DefaultComboBoxModel(v));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Capturando errores cargarCombobox()");
        }
    }

    public void guardarUsuario() {
        usuario = new Usuario();
        usuario.setUsuario(this.vista.getTxtUsuario().getText());
        usuario.setClave(this.vista.getTxtContrasenia().getPassword().toString());
        usuario.setIdpersona((Persona) this.vista.getCbxIdPersona().getSelectedItem());
        modeloUsuario.create(usuario);
      Resouces.success("!Usuario Creado!", " Se ha creado con exito \n Usuario con Nombre: " + usuario.getUsuario());
        cargarDatosUsuarioTbl();
        limpiarC();
    }

    public void editarUsuario() {
        if (usuario != null) {
            try {
                usuario.setUsuario(this.vista.getTxtUsuario().getText());
                usuario.setClave(String.valueOf(this.vista.getTxtContrasenia().getPassword()));
                usuario.setIdpersona((Persona) this.vista.getCbxIdPersona().getSelectedItem());

                modeloUsuario.edit(usuario);
                modeloTabla.eliminar(usuario);
                modeloTabla.actualizar(usuario);
                Resouces.success("!Usuario Editado!", " Se ha editado con exito \n Usuario con Nombre: " + usuario.getUsuario());
                cargarDatosUsuarioTbl();
                limpiarC();
            } catch (Exception ex) {
                Logger.getLogger(ControllerPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void eliminarUsuario() {
        if (usuario != null) {
            try {
                modeloUsuario.destroy(usuario.getIdusuario());
                modeloTabla.eliminar(usuario);
                Resouces.success("!Usuario Eliminado!", " Se ha eliminado con exito \n Usuario con Nombre: " + usuario.getUsuario());
                cargarDatosUsuarioTbl();
                limpiarC();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControllerPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void mostrarTodos() {
        if (this.vista.getChkbxMostrarTodos().isSelected()) {
            cargarDatosUsuarioTbl();
        } else {
            buscarUsuario();
        }
    }
    
     public void buscarUsuario() {
        if (!this.vista.getTxtCriterio().getText().isEmpty()) {
            modeloTabla.setFilas(modeloUsuario.buscarNombreUsuario(this.vista.getTxtCriterio().getText()));
            modeloTabla.fireTableDataChanged();
        } else {
            limpiarB();
        }
    }
    
    public void limpiarC() {
        this.vista.getTxtUsuario().setText("");
        this.vista.getTxtContrasenia().setText("");
        this.vista.getCbxIdPersona().setSelectedIndex(0);
        //Acciones de Botones
        this.vista.getBtnCrear().setEnabled(true);
        this.vista.getBtnBorrar().setEnabled(false);
        this.vista.getBtnActualizar().setEnabled(false);
        //Limpiar Seleccion de Tabla
        this.vista.getTblUsuario().clearSelection();
    }

    public void limpiarB() {
        this.vista.getTxtCriterio().setText("");
        modeloTabla.setFilas(modeloUsuario.findUsuarioEntities());
        modeloTabla.fireTableDataChanged();
    }

    public void cancelar() {
        this.vista.hide();
    }
}
