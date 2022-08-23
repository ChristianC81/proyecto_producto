/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Producto;
import modelo.ProductoJpaController;
import modelo.exceptions.NonexistentEntityException;
import proyecto_producto.ManagerFactory;
import vista.interna.ViewProducto;

/**
 *
 * @author chris
 */
public class ControllerProducto {

    ViewProducto vista;
    ManagerFactory manager;
    ProductoJpaController modeloProducto;
    Producto producto;
    ModeloTablaProducto modeloTabla;
    JDesktopPane escritorio;
     ListSelectionModel listaProductoModel;
     Resouces notificacion;

    public ControllerProducto(ViewProducto vista, ManagerFactory manager, ProductoJpaController modeloProducto, JDesktopPane escritorio) {
        this.vista = vista;
        this.manager = manager;
        this.modeloProducto = modeloProducto;
        this.escritorio = escritorio;
        if (ControllerAdministrador.vistaProducto == null) {
            ControllerAdministrador.vistaProducto = new ViewProducto();
            this.vista = ControllerAdministrador.vistaProducto;
            this.escritorio.add(this.vista);
            cargarDatosProductoTbl();
            //Para centar la vista en la ventana
            Dimension desktopSize = this.escritorio.getSize();
            Dimension FrameSize = this.vista.getSize();
            this.vista.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);  
           this.vista.getBtnBorrar().setEnabled(false);
            this.vista.getBtnActualizar().setEnabled(false);
            ControllerAdministrador.vistaProducto.show();
            controlEvento();
        } else {
            ControllerAdministrador.vistaProducto.show();
        }
    }

    public void controlEvento() {
        this.vista.getBtnCrear().addActionListener(l -> guardarProducto());
          this.vista.getBtnActualizar().addActionListener(l -> editarProducto());
        this.vista.getBtnBorrar().addActionListener(l -> eliminarProducto());
        this.vista.getBtnLimpiar().addActionListener(l -> limpiarC());
        this.vista.getBtnLimpiarBus().addActionListener(l -> limpiarB());
        this.vista.getBtnCancelar().addActionListener(l -> cancelar());
         this.vista.getTblProducto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProductoModel = this.vista.getTblProducto().getSelectionModel();
        listaProductoModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    productoSeleccionada();
                }
            }
        });

        this.vista.getBtnBuscar().addActionListener(l -> buscarProducto());
        this.vista.getChkbxMostrarTodos().addActionListener(l -> mostrarTodos());
    
    }
 public void productoSeleccionada() {
        if (this.vista.getTblProducto().getSelectedRow() != -1) {
            producto = modeloTabla.getFilas().get(this.vista.getTblProducto().getSelectedRow());
            this.vista.getTxtNombre().setText(producto.getNombre());
            this.vista.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            this.vista.getTxtCantidad().setText(String.valueOf(producto.getCantidad()));
        
            //Acceso de Botones
            this.vista.getBtnCrear().setEnabled(false);
            this.vista.getBtnBorrar().setEnabled(true);
            this.vista.getBtnActualizar().setEnabled(true);

        }
    }
 
    public void cargarDatosProductoTbl() {
        this.modeloTabla = new ModeloTablaProducto();
        this.modeloTabla.setFilas(modeloProducto.findProductoEntities());
        this.vista.getTblProducto().setModel(modeloTabla);
    }
    
    public void guardarProducto() {
        producto = new Producto();
        producto.setNombre(this.vista.getTxtNombre().getText());
        producto.setPrecio(Double.valueOf(this.vista.getTxtPrecio().getText()));
        producto.setCantidad(Integer.valueOf(this.vista.getTxtCantidad().getText()));
        modeloProducto.create(producto);
        Resouces.success("!Producto Creado!", " Se ha creado con exito \n Producto con Nombre: " + producto.getNombre());
        cargarDatosProductoTbl();
        limpiarC();
        
    }
    
 public void editarProducto() {
        if (producto != null) {
            try {
                producto.setNombre(this.vista.getTxtNombre().getText());
                producto.setPrecio(Double.valueOf(this.vista.getTxtPrecio().getText()));
                producto.setCantidad(Integer.valueOf(this.vista.getTxtCantidad().getText()));
               

                modeloProducto.edit(producto);
                modeloTabla.eliminar(producto);
                modeloTabla.actualizar(producto);
                Resouces.success("!Producto Editado!", " Se ha editado con exito \n Producto con Nombre: " + producto.getNombre());
                limpiarC();
            } catch (Exception ex) {
                Logger.getLogger(ControllerPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void eliminarProducto() {
        if (producto != null) {
            try {
                modeloProducto.destroy(producto.getIdproducto());
                modeloTabla.eliminar(producto);
                Resouces.success("!Producto Eliminado!", " Se ha eliminado con exito \n Producto con Nombre: " + producto.getNombre());
                cargarDatosProductoTbl();
                   limpiarC();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControllerPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public void mostrarTodos() {
        if (this.vista.getChkbxMostrarTodos().isSelected()) {
            cargarDatosProductoTbl();
        } else {
            buscarProducto();
        }
    }
     
     public void buscarProducto() {
        if (!this.vista.getTxtCriterio().getText().isEmpty()) {
            modeloTabla.setFilas(modeloProducto.buscarNombreProducto(this.vista.getTxtCriterio().getText()));
            modeloTabla.fireTableDataChanged();
        } else {
            limpiarB();
        }
    }

    public void limpiarC() {
        this.vista.getTxtNombre().setText("");
        this.vista.getTxtPrecio().setText("");
        this.vista.getTxtCantidad().setText("");
         //Acciones de Botones
        this.vista.getBtnCrear().setEnabled(true);
        this.vista.getBtnBorrar().setEnabled(false);
        this.vista.getBtnActualizar().setEnabled(false);
        //Limpiar Seleccion de Tabla
        this.vista.getTblProducto().clearSelection();
    }

    public void limpiarB() {
        this.vista.getTxtCriterio().setText("");
        modeloTabla.setFilas(modeloProducto.findProductoEntities());
        modeloTabla.fireTableDataChanged();
    }
    public void cancelar() {
        this.vista.hide();
    }
}
