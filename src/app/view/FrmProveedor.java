package app.view;

import app.controller.ProveedorDaoImp;
import app.entity.*;
import javax.swing.table.DefaultTableModel;

public class FrmProveedor extends javax.swing.JFrame {
    
    ProveedorDaoImp dao = new ProveedorDaoImp();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmProveedor.class.getName());

    public FrmProveedor() {
        initComponents();
        tabla();
    }

    public void tabla(){
        DefaultTableModel tabla = (DefaultTableModel) tablaPro.getModel();
        tabla.setRowCount(0);
        
        int anchos [] = {10,60,40,70,100,50,50,30,50,50};
        
        for (int i =0; i< tablaPro.getColumnCount(); i++){
            tablaPro.getColumnModel().getColumn(1).setPreferredWidth(anchos[i]);
        }
        
        dao.construirTabla(tabla);
    }
    
    public void limpiarCampos(){
        txtNombre.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtContacto.setText("");
        txtWeb.setText("");
        txtSaldo.setText("");
        txtClabe.setText("");
    }
    
    public String obtenerHorario(){
        int horaInicio = (Integer) spPrimero.getValue();
        int horaFin = (Integer) spSegundo.getValue();
        return String.format("%02d:00 - %02d:00", horaInicio, horaFin);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtWeb = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtContacto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        spPrimero = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        spSegundo = new javax.swing.JSpinner();
        comboTipoPro = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        txtClabe = new javax.swing.JTextField();
        comboBoxTipoCuenta = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        comboBoxBanco = new javax.swing.JComboBox<>();
        comboBoxEstatusCuenta = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtIdPro = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPro = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedores"));

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel2.setText("Telefono");

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel3.setText("Sitio Web");

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel4.setText("Correo");

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel5.setText("Direccion");

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel6.setText("Horario de Atencion:");

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel7.setText("Tipo");

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel8.setText("Persona de Contacto");

        spPrimero.setModel(new javax.swing.SpinnerNumberModel(7, 0, 24, 1));

        jLabel9.setText("a");

        spSegundo.setModel(new javax.swing.SpinnerNumberModel(15, 0, 24, 1));

        comboTipoPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distribuidor Mayorista", "Laboratorios", "Servicios", "Logistica", "Parafarmacia" }));

        jLabel10.setText("De");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuenta"));

        jLabel11.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel11.setText("Saldo");

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel12.setText("Clabe");

        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });

        comboBoxTipoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empresarial", "Nomina" }));
        comboBoxTipoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoCuentaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel13.setText("Tipo");

        jLabel15.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel15.setText("Estatus");

        comboBoxBanco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HSBC", "BBVA", "Banorte", "Santander", "NU", "Banamex" }));
        comboBoxBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBancoActionPerformed(evt);
            }
        });

        comboBoxEstatusCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        jLabel14.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel14.setText("Banco");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtClabe))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboBoxBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboBoxEstatusCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtClabe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(comboBoxBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(comboBoxEstatusCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(comboBoxTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnLimpiar.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnLimpiar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaPro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Correo", "Direccion", "Telefono", "Contacto", "Web", "Horario", "Tipo", "Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPro);
        if (tablaPro.getColumnModel().getColumnCount() > 0) {
            tablaPro.getColumnModel().getColumn(0).setResizable(false);
            tablaPro.getColumnModel().getColumn(1).setResizable(false);
            tablaPro.getColumnModel().getColumn(2).setResizable(false);
            tablaPro.getColumnModel().getColumn(3).setResizable(false);
            tablaPro.getColumnModel().getColumn(4).setResizable(false);
            tablaPro.getColumnModel().getColumn(5).setResizable(false);
            tablaPro.getColumnModel().getColumn(6).setResizable(false);
            tablaPro.getColumnModel().getColumn(7).setResizable(false);
            tablaPro.getColumnModel().getColumn(8).setResizable(false);
            tablaPro.getColumnModel().getColumn(9).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTelefono))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtCorreo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtWeb))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboTipoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(spPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10)
                                            .addComponent(spSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(comboTipoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxBancoActionPerformed

    private void txtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActionPerformed

    private void comboBoxTipoCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoCuentaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int id = Integer.parseInt(txtIdPro.getText());

        dao.eliminarProveedor(id);
        limpiarCampos();
        tabla();

        javax.swing.JOptionPane.showMessageDialog(this, "Proveedor eliminado!!");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String seleccionTipoPro = (String) comboTipoPro.getSelectedItem();
        String seleccionTipoCuenta = (String) comboBoxTipoCuenta.getSelectedItem();
        String seleccionBanco = (String) comboBoxBanco.getSelectedItem();
        String seleccionStatusCue = (String) comboBoxEstatusCuenta.getSelectedItem();

        int id = Integer.parseInt(txtIdPro.getText());
        int telefono = Integer.parseInt(txtTelefono.getText());
        double saldo = Double.parseDouble(txtSaldo.getText());

        Cuenta cuenta = new Cuenta (seleccionTipoCuenta, txtClabe.getText(), seleccionBanco, seleccionStatusCue, saldo);
        Proveedor proveedor = new Proveedor(txtNombre.getText(), txtCorreo.getText(), txtDireccion.getText(), seleccionTipoPro, 
                obtenerHorario(), txtWeb.getText(), txtContacto.getText(), telefono, cuenta);

        dao.modificarProveedor(id, proveedor);
        limpiarCampos();
        tabla();

        javax.swing.JOptionPane.showMessageDialog(this, "Proveedor modificado!!");
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String seleccionTipoPro = (String) comboTipoPro.getSelectedItem();
        String seleccionTipoCuenta = (String) comboBoxTipoCuenta.getSelectedItem();
        String seleccionBanco = (String) comboBoxBanco.getSelectedItem();
        String seleccionStatusCue = (String) comboBoxEstatusCuenta.getSelectedItem();

        int telefono = Integer.parseInt(txtTelefono.getText());
        double saldo = Double.parseDouble(txtSaldo.getText());

        Cuenta cuenta = new Cuenta (seleccionTipoCuenta, txtClabe.getText(), seleccionBanco, seleccionStatusCue, saldo);
        Proveedor proveedor = new Proveedor(txtNombre.getText(), txtCorreo.getText(), txtDireccion.getText(), seleccionTipoPro, 
                obtenerHorario(), txtWeb.getText(), txtContacto.getText(), telefono, cuenta);

        dao.insertProveedor(proveedor);
        limpiarCampos();
        tabla();

        javax.swing.JOptionPane.showMessageDialog(this, "Proveedor Insertado!!");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tablaProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProMouseClicked
        int fila = tablaPro.getSelectedRow();
        int id = Integer.parseInt(tablaPro.getValueAt(fila, 0).toString());
        
        Proveedor proveedor = dao.consultarProveedor(id);
        
        txtIdPro.setText(String.valueOf(id));
        txtNombre.setText(proveedor.getNombre());
        txtSaldo.setText(String.valueOf(proveedor.getCuenta().getSaldo()));
        txtCorreo.setText(proveedor.getCorreo());
        txtClabe.setText(proveedor.getCuenta().getClabe());
        txtDireccion.setText(proveedor.getDireccion());
        txtTelefono.setText(String.valueOf(proveedor.getTelefono()));
        txtWeb.setText(proveedor.getSitoWeb());
        txtContacto.setText(proveedor.getPersonaContacto());
        comboTipoPro.setSelectedItem(proveedor.getTipo());
        comboBoxTipoCuenta.setSelectedItem(proveedor.getCuenta().getTipo());
        comboBoxBanco.setSelectedItem(proveedor.getCuenta().getBanco());
        comboBoxEstatusCuenta.setSelectedItem(proveedor.getCuenta().getEstatus());
        
        // Parsear horario en formato "HH:00 - HH:00"
        String horario = proveedor.getHorario();
        if (horario != null && !horario.isEmpty()) {
            String[] partes = horario.split(" - ");
            if (partes.length == 2) {
                int horaInicio = Integer.parseInt(partes[0].split(":")[0]);
                int horaFin = Integer.parseInt(partes[1].split(":")[0]);
                spPrimero.setValue(horaInicio);
                spSegundo.setValue(horaFin);
            }
        }
    }//GEN-LAST:event_tablaProMouseClicked

    /**
     * @param args the command line arguments
     */
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
        java.awt.EventQueue.invokeLater(() -> new FrmProveedor().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> comboBoxBanco;
    private javax.swing.JComboBox<String> comboBoxEstatusCuenta;
    private javax.swing.JComboBox<String> comboBoxTipoCuenta;
    private javax.swing.JComboBox<String> comboTipoPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spPrimero;
    private javax.swing.JSpinner spSegundo;
    private javax.swing.JTable tablaPro;
    private javax.swing.JTextField txtClabe;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdPro;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtWeb;
    // End of variables declaration//GEN-END:variables
}
