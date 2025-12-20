package app.view;

public class frmMain extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmMain.class.getName());

    public frmMain() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCliente = new javax.swing.JButton();
        btnEmpleados = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        btnCliente.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        btnCliente.setText("Clientes");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnEmpleados.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        btnEmpleados.setText("Empleados");
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });

        btnProveedores.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        btnProveedores.setText("Proveedores");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });

        btnProductos.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnUsuarios.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel1.setText("¡Hola! Te damos la bievenida a este sistema de administracion para farmacias.");

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel2.setText("¿A qué catálogo deseas ingresar?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(btnCliente)
                                .addGap(18, 18, 18)
                                .addComponent(btnEmpleados)
                                .addGap(18, 18, 18)
                                .addComponent(btnProveedores)
                                .addGap(18, 18, 18)
                                .addComponent(btnProductos)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsuarios))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(btnSalir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCliente)
                    .addComponent(btnEmpleados)
                    .addComponent(btnProveedores)
                    .addComponent(btnProductos)
                    .addComponent(btnUsuarios))
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        this.setVisible(false);
        
        FrmCliente frmCliente = new FrmCliente();
        frmCliente.setVisible(true);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed
        this.setVisible(false);
        
        FrmEmpleado frmEmpleado = new FrmEmpleado();
        frmEmpleado.setVisible(true);
    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        this.setVisible(false);
        
        FrmProveedor frmProveedor = new FrmProveedor ();
        frmProveedor.setVisible(true);
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        this.setVisible(false);
        
        FrmProducto frmProducto = new FrmProducto();
        frmProducto.setVisible(true);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        this.setVisible(false);
        
        FrmUsuario frmUsuario = new FrmUsuario();
        frmUsuario.setVisible(true);
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
        
        FrmLogin frmLogin = new FrmLogin();
        frmLogin.setVisible(true);
    }//GEN-LAST:event_btnSalirActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmMain().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
