/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ControllerLogin;
import java.awt.Toolkit;
import javax.accessibility.AccessibleContext;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;
import modelo.UsuarioJpaController;
import proyecto_producto.ManagerFactory;

/**
 *
 * @author chris
 */
public class ViewAdministrador extends javax.swing.JFrame {

    ManagerFactory manager = new ManagerFactory();
    ViewLogin vista = new ViewLogin();
    UsuarioJpaController modelo = new UsuarioJpaController(manager.getEntityManagerFactory());

    /**
     * Creates new form VistaAdministrador
     */
    public ViewAdministrador() {
        initComponents();
        float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
        int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalar);
        int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalar);
        System.out.println("Ancho: "+ancho+" /Alto:"+alto);
    }

    public ManagerFactory getManager() {
        return manager;
    }

    public void setManager(ManagerFactory manager) {
        this.manager = manager;
    }

    public ViewLogin getVista() {
        return vista;
    }

    public void setVista(ViewLogin vista) {
        this.vista = vista;
    }

    public UsuarioJpaController getModelo() {
        return modelo;
    }

    public void setModelo(UsuarioJpaController modelo) {
        this.modelo = modelo;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JDesktopPane getDpnFondoAdministrador() {
        return dpnFondoAdministrador;
    }

    public void setDpnFondoAdministrador(JDesktopPane dpnFondoAdministrador) {
        this.dpnFondoAdministrador = dpnFondoAdministrador;
    }

    public JMenuBar getMbrPrincipal() {
        return mbrPrincipal;
    }

    public void setMbrPrincipal(JMenuBar mbrPrincipal) {
        this.mbrPrincipal = mbrPrincipal;
    }

    public JMenu getMnConfiguraciones() {
        return mnConfiguraciones;
    }

    public void setMnConfiguraciones(JMenu mnConfiguraciones) {
        this.mnConfiguraciones = mnConfiguraciones;
    }

    public JMenu getMnReportes() {
        return mnReportes;
    }

    public void setMnReportes(JMenu mnReportes) {
        this.mnReportes = mnReportes;
    }

    public JMenuItem getjMenuItemPersona() {
        return jMenuItemPersona;
    }

    public void setjMenuItemPersona(JMenuItem jMenuItemPersona) {
        this.jMenuItemPersona = jMenuItemPersona;
    }

    public JMenuItem getjMenuItemProducto() {
        return jMenuItemProducto;
    }

    public void setjMenuItemProducto(JMenuItem jMenuItemProducto) {
        this.jMenuItemProducto = jMenuItemProducto;
    }

    public JMenuItem getjMenuItemUsuario() {
        return jMenuItemUsuario;
    }

    public void setjMenuItemUsuario(JMenuItem jMenuItemUsuario) {
        this.jMenuItemUsuario = jMenuItemUsuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpnFondoAdministrador = new javax.swing.JDesktopPane();
        btnSalir = new javax.swing.JButton();
        mbrPrincipal = new javax.swing.JMenuBar();
        mnConfiguraciones = new javax.swing.JMenu();
        jMenuItemPersona = new javax.swing.JMenuItem();
        jMenuItemProducto = new javax.swing.JMenuItem();
        jMenuItemUsuario = new javax.swing.JMenuItem();
        mnReportes = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        dpnFondoAdministrador.setBackground(new java.awt.Color(255, 255, 255));

        btnSalir.setBackground(new java.awt.Color(0, 79, 140));
        btnSalir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("SALIR");
        btnSalir.setBorder(null);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        dpnFondoAdministrador.setLayer(btnSalir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dpnFondoAdministradorLayout = new javax.swing.GroupLayout(dpnFondoAdministrador);
        dpnFondoAdministrador.setLayout(dpnFondoAdministradorLayout);
        dpnFondoAdministradorLayout.setHorizontalGroup(
            dpnFondoAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpnFondoAdministradorLayout.createSequentialGroup()
                .addContainerGap(988, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        dpnFondoAdministradorLayout.setVerticalGroup(
            dpnFondoAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpnFondoAdministradorLayout.createSequentialGroup()
                .addContainerGap(522, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        getContentPane().add(dpnFondoAdministrador, java.awt.BorderLayout.CENTER);

        mbrPrincipal.setBackground(new java.awt.Color(0, 79, 140));

        mnConfiguraciones.setBackground(new java.awt.Color(0, 79, 140));
        mnConfiguraciones.setForeground(new java.awt.Color(255, 255, 255));
        mnConfiguraciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/image/btnconfig.png"))); // NOI18N

        jMenuItemPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/image/icons8-person-48.png"))); // NOI18N
        jMenuItemPersona.setText("Persona");
        jMenuItemPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPersonaActionPerformed(evt);
            }
        });
        mnConfiguraciones.add(jMenuItemPersona);

        jMenuItemProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/image/icons8-product-48.png"))); // NOI18N
        jMenuItemProducto.setText("Producto");
        jMenuItemProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProductoActionPerformed(evt);
            }
        });
        mnConfiguraciones.add(jMenuItemProducto);

        jMenuItemUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/image/icons8-user-48.png"))); // NOI18N
        jMenuItemUsuario.setText("Usuario");
        mnConfiguraciones.add(jMenuItemUsuario);

        mbrPrincipal.add(mnConfiguraciones);

        mnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/image/btnReportes.png"))); // NOI18N
        mbrPrincipal.add(mnReportes);

        setJMenuBar(mbrPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSalirActionPerformed

    private void jMenuItemProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemProductoActionPerformed

    private void jMenuItemPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemPersonaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JDesktopPane dpnFondoAdministrador;
    private javax.swing.JMenuItem jMenuItemPersona;
    private javax.swing.JMenuItem jMenuItemProducto;
    private javax.swing.JMenuItem jMenuItemUsuario;
    private javax.swing.JMenuBar mbrPrincipal;
    private javax.swing.JMenu mnConfiguraciones;
    private javax.swing.JMenu mnReportes;
    // End of variables declaration//GEN-END:variables
}
