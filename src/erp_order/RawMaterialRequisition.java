/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp_order;

import erp.database.Database;
import erp.model.OrderDetail;
import erp.model.RawMatReq;
import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import org.imgscalr.Scalr;

public class RawMaterialRequisition extends javax.swing.JDialog {

    /**
     * Creates new form ProductionUpdate
     */
    private ArrayList<OrderDetail> orderList;
    private int tableSelect = -1;
    private int idSelect = -1;

    public RawMaterialRequisition(Window w) {
        super(w);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        orderList = Database.getOrder();

        initComponents();
        setTitle("Raw Material Requisition");
        btnComplete.setEnabled(false);
        productImage.setVisible(false);
        finishedProductField.setEditable(false);
        quantityField.setEditable(false);
        productCategoryField.setEditable(false);
        newOrderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = newOrderTable.getSelectedRow();
                int id = (int) ((DefaultTableModel) newOrderTable.getModel()).getValueAt(row, 0);
                if (idSelect != id) {
                    tableSelect = 1;
                    idSelect = id;
                    completedOrderTable.clearSelection();
                    btnComplete.setEnabled(true);
                    for (Iterator<OrderDetail> iterator = orderList.iterator(); iterator.hasNext();) {
                        OrderDetail next = iterator.next();
                        if (next.getId() == id) {
                            clientNameLbl.setText(next.getClientName());
                            addressLbl.setText(next.getAddress());
                            orderDateLbl.setText(next.getOrderDate());
                            delivaryDateLbl.setText(next.getDelevaryDate());
                            if (!next.getDesighUrl().equals("")) {
                                File f = new File(next.getDesighUrl());
                                if (f.exists()) {
                                    try {
                                        BufferedImage img = Scalr.resize(ImageIO.read(f), Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 134, 151, Scalr.OP_ANTIALIAS);
                                        productImage.setIcon(new ImageIcon(img));
                                        productImage.setVisible(true);
                                    } catch (IOException ex) {
                                        Logger.getLogger(RawMaterialRequisition.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            } else {
                                productImage.setVisible(false);
                            }
                            finishedProductField.setText(next.getFinishedProduct());
                            productCategoryField.setText(next.getProductCategory());
                            quantityField.setText(getCorrectNumber(next.getQuantity()));
                                                        DefaultTableModel m = new javax.swing.table.DefaultTableModel(
                                    new Object[][]{},
                                    new String[]{
                                        "Material Type", "Material Sub Type", "Req.Qty/Item", "Total Qty"
                                    }
                            ) {
                                Class[] types = new Class[]{
                                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                                };
                                boolean[] canEdit = new boolean[]{
                                    false, false, false, false
                                };

                                public Class getColumnClass(int columnIndex) {
                                    return types[columnIndex];
                                }

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit[columnIndex];
                                }
                            };
                            ArrayList<Object[]> arr = Database.getRequiredMaterials(next.getFinishedProduct(), next.getProductCategory());

                            for (int i = 0; i < arr.size(); i++) {
                                arr.get(i)[3] = getCorrectNumber(next.getQuantity() * ((double) arr.get(i)[2]));
                                arr.get(i)[2] = getCorrectNumber((double) arr.get(i)[2]);
                                m.addRow(arr.get(i));
                            }
                            materialTable.setModel(m);
                            break;
                        }

                    }

                }
            }
        });
        completedOrderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = completedOrderTable.getSelectedRow();
                int id = (int) ((DefaultTableModel) completedOrderTable.getModel()).getValueAt(row, 0);
                   
                if (idSelect != id) {
                    btnComplete.setEnabled(false);
                    tableSelect = 2;
                    idSelect = id;
                    newOrderTable.clearSelection();
                    for (Iterator<OrderDetail> iterator = orderList.iterator(); iterator.hasNext();) {
                        OrderDetail next = iterator.next();
                        if (next.getId() == id) {
                            clientNameLbl.setText(next.getClientName());
                            addressLbl.setText(next.getAddress());
                            orderDateLbl.setText(next.getOrderDate());
                            delivaryDateLbl.setText(next.getDelevaryDate());
                            if (!next.getDesighUrl().equals("")) {
                                File f = new File(next.getDesighUrl());
                                if (f.exists()) {
                                    try {
                                        BufferedImage img = Scalr.resize(ImageIO.read(f), Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 134, 151, Scalr.OP_ANTIALIAS);
                                        productImage.setIcon(new ImageIcon(img));
                                        productImage.setVisible(true);
                                    } catch (IOException ex) {
                                        Logger.getLogger(RawMaterialRequisition.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            } else {
                                productImage.setVisible(false);
                            }
                            finishedProductField.setText(next.getFinishedProduct());
                            productCategoryField.setText(next.getProductCategory());

                            quantityField.setText(getCorrectNumber(next.getQuantity()));
                            DefaultTableModel m = new javax.swing.table.DefaultTableModel(
                                    new Object[][]{},
                                    new String[]{
                                        "Material Type", "Material Sub Type", "Req.Qty/Item", "Total Qty"
                                    }
                            ) {
                                Class[] types = new Class[]{
                                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                                };
                                boolean[] canEdit = new boolean[]{
                                    false, false, false, false
                                };

                                public Class getColumnClass(int columnIndex) {
                                    return types[columnIndex];
                                }

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit[columnIndex];
                                }
                            };
                            ArrayList<Object[]> arr = Database.getRequiredMaterials(next.getFinishedProduct(), next.getProductCategory());

                            for (int i = 0; i < arr.size(); i++) {
                                arr.get(i)[3] = getCorrectNumber(next.getQuantity() * ((double) arr.get(i)[2]));
                                arr.get(i)[2] = getCorrectNumber((double) arr.get(i)[2]);
                                m.addRow(arr.get(i));
                            }
                            materialTable.setModel(m);
                            break;
                        }

                    }

                }
            }
        });
        setValueInNewOrderTable();
        setValueInCompletedOrderTable();

        setLocationRelativeTo(null);
    }

    private String getCorrectNumber(Double num) {
        if ((Math.abs(Math.round(num) - num)) < 0.01) {
            return Math.round(num) + "";
        } else {
            return num + "";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        newOrderTable = new javax.swing.JTable();
        btnComplete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        completedOrderTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        finishedProductField = new javax.swing.JTextField();
        productCategoryField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        materialTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        productImage = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        addressLbl = new javax.swing.JLabel();
        clientNameLbl = new javax.swing.JLabel();
        orderDateLbl = new javax.swing.JLabel();
        delivaryDateLbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Raw Material Requisition");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        newOrderTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        newOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No", "Finished Product", "P.  Category"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        newOrderTable.setName(""); // NOI18N
        newOrderTable.setRowHeight(30);
        newOrderTable.setRowMargin(0);
        newOrderTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(newOrderTable);
        newOrderTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (newOrderTable.getColumnModel().getColumnCount() > 0) {
            newOrderTable.getColumnModel().getColumn(0).setResizable(false);
            newOrderTable.getColumnModel().getColumn(1).setResizable(false);
            newOrderTable.getColumnModel().getColumn(2).setResizable(false);
        }

        btnComplete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnComplete.setText("Complete");
        btnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("New Order");

        completedOrderTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        completedOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No", "Finished Product", "P.  Category"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        completedOrderTable.setName(""); // NOI18N
        completedOrderTable.setRowHeight(30);
        completedOrderTable.setRowMargin(0);
        completedOrderTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(completedOrderTable);
        completedOrderTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (completedOrderTable.getColumnModel().getColumnCount() > 0) {
            completedOrderTable.getColumnModel().getColumn(0).setResizable(false);
            completedOrderTable.getColumnModel().getColumn(1).setResizable(false);
            completedOrderTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Completed Order");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Finished Product    :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Product Category  :  ");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Quantity: ");

        finishedProductField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        productCategoryField.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        quantityField.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        materialTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        materialTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material Type", "Material Sub Type", "Req.Qty/Item", "Total Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        materialTable.setName(""); // NOI18N
        materialTable.setRowHeight(30);
        materialTable.setRowMargin(0);
        materialTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        materialTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(materialTable);
        materialTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (materialTable.getColumnModel().getColumnCount() > 0) {
            materialTable.getColumnModel().getColumn(0).setResizable(false);
            materialTable.getColumnModel().getColumn(1).setResizable(false);
            materialTable.getColumnModel().getColumn(2).setResizable(false);
            materialTable.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(finishedProductField)
                            .addComponent(productCategoryField))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15)
                    .addComponent(finishedProductField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(productCategoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(220, 220, 220));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Client Name    :  ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Address          :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Order Date     :  ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Delivary Date  : ");

        productImage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        productImage.setOpaque(true);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Product Image");

        addressLbl.setBackground(new java.awt.Color(255, 255, 255));
        addressLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addressLbl.setOpaque(true);

        clientNameLbl.setBackground(new java.awt.Color(255, 255, 255));
        clientNameLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        clientNameLbl.setOpaque(true);

        orderDateLbl.setBackground(new java.awt.Color(255, 255, 255));
        orderDateLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        orderDateLbl.setOpaque(true);

        delivaryDateLbl.setBackground(new java.awt.Color(255, 255, 255));
        delivaryDateLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        delivaryDateLbl.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(clientNameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(delivaryDateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(orderDateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(productImage, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(clientNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(addressLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(orderDateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(delivaryDateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(productImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel11)
                .addGap(17, 17, 17))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Required Raw Material Details");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Order Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(496, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel2)
                        .addGap(368, 368, 368)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel3)
                                    .addGap(90, 90, 90)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(227, 227, 227)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(342, 342, 342)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(102, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(67, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(113, 113, 113)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(363, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
            
        int id = Database.nextRawMatId();
        DefaultTableModel model = (DefaultTableModel)materialTable.getModel();
        int rowC = (model).getRowCount();
        for(int i=0; i<rowC; i++){
            RawMatReq r = new RawMatReq(id, (String)model.getValueAt(i, 0), (String)model.getValueAt(i, 1),
                    Double.parseDouble((String)model.getValueAt(i, 3)), false);
            try {
                Database.saveReq(r);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RawMaterialRequisition.class.getName()).log(Level.SEVERE, null, ex);
                return;
            } catch (Exception ex) {
                Logger.getLogger(RawMaterialRequisition.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        
    
       int selectedRow = newOrderTable.getSelectedRow();
       
       Object[] o = new Object[3];    try {
            Database.compteteReq((int)newOrderTable.getValueAt(selectedRow, 0));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RawMaterialRequisition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RawMaterialRequisition.class.getName()).log(Level.SEVERE, null, ex);
        }
       for(int i=0; i<3; i++){
           o[i] = newOrderTable.getValueAt(selectedRow, i);
       }
       ((DefaultTableModel)newOrderTable.getModel()).removeRow(selectedRow);
       ((DefaultTableModel)completedOrderTable.getModel()).addRow(o);
    }//GEN-LAST:event_btnCompleteActionPerformed

    public void setValueInNewOrderTable() {
        DefaultTableModel tm = (DefaultTableModel) newOrderTable.getModel();
        for (Iterator<OrderDetail> iterator = orderList.iterator(); iterator.hasNext();) {
            OrderDetail next = iterator.next();
            if (!next.isCompleted()) {
                tm.addRow(new Object[]{next.getId(), next.getFinishedProduct(), next.getProductCategory()});
            }

        }
    }

    public void setValueInCompletedOrderTable() {
        DefaultTableModel tm = (DefaultTableModel) completedOrderTable.getModel();
        for (Iterator<OrderDetail> iterator = orderList.iterator(); iterator.hasNext();) {
            OrderDetail next = iterator.next();
            if (next.isCompleted()) {
                tm.addRow(new Object[]{next.getId(), next.getFinishedProduct(), next.getProductCategory()});
            }

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLbl;
    private javax.swing.JButton btnComplete;
    private javax.swing.JLabel clientNameLbl;
    private javax.swing.JTable completedOrderTable;
    private javax.swing.JLabel delivaryDateLbl;
    private javax.swing.JTextField finishedProductField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable materialTable;
    private javax.swing.JTable newOrderTable;
    private javax.swing.JLabel orderDateLbl;
    private javax.swing.JTextField productCategoryField;
    private javax.swing.JLabel productImage;
    private javax.swing.JTextField quantityField;
    // End of variables declaration//GEN-END:variables
}
