/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inw;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lukasz Szymkowski "Simon Goatbeard"
 */
public final class InwentaryzacjaGUI extends javax.swing.JFrame {

    Connection con;
    Statement stmt;
    ResultSet rs;
    PrintWriter zapis;

    String viewType = "All";
    String AllDevices;
    DefaultTableModel model;
    DefaultTableModel modelHistory = new DefaultTableModel();
    String[][] cellname;
    String cell;
    String Cells;
    String tekst, cat,catTable;
    ArrayList<String[]> al = new ArrayList<>();
    DefaultTableModel modelBlob = new DefaultTableModel();
    String netDevices;

    Conn myConn = new Conn();

    public void ConnectMeWithDepartments() {
        con = myConn.connectMe();
        stmt = myConn.connectStatement(con);
        Cells = "Select * from DEPARTMENT";

        try {

            rs = stmt.executeQuery(Cells);

            int i = 0;

            while (rs.next()) {
                String[] tab = new String[2];

                tab[0] = rs.getInt("DEPARTMENT_ID") + "";
                tab[1] = rs.getString("DEPNAME");
                al.add(tab);
                i++;
            }
            cellname = new String[al.size()][2];
            for (int j = 0; j < al.size(); j++) {
                cellname[j][0] = al.get(j)[0];
                cellname[j][1] = al.get(j)[1];
            }
        } catch (SQLException ex) {
            Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public InwentaryzacjaGUI() {
        initComponents();
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.getTableHeader().setReorderingAllowed(false);
        ShowFullTable();
        HistoryModel();
        BlobModel();
    }

    public void BlobModel() {
        modelBlob.addColumn("Id");
        modelBlob.addColumn("Id urządzenia");
        modelBlob.addColumn("Nazwa");
        modelBlob.addColumn("Data utworzenia");
    }

    public void search() {
        ConnectMeWithDepartments();
        model.setRowCount(0);
        tekst = jTextField1.getText();
        cat = jComboBox1.getSelectedItem().toString();
        catTable = "";
        
        switch (cat) {
            case "Id":
                catTable = "ID";
                break;
            case "Numer inwentaryzacyjny":
                catTable = "INV_NUMBER";
                break;
            case "Typ urządzenia":
                catTable = "TYPE";
                break;
            case "Producent":
                catTable = "PRODUCENT";
                break;
            case "Model":
                catTable = "MODEL";
                break;
            case "Numer seryjny":
                catTable = "SERIAL_NUMBER";
                break;
            case "Status":
                catTable = "STATUS";
                break;
            default:
                break;
        }

        if (tekst.length() != 0) {
            if (jComboBox1.getSelectedIndex() > 1) {
                jLabel3.setText("");
                String Search = "Select * from INV_DEVICES where lower(" + catTable + ") LIKE lower('%" + jTextField1.getText() + "%')";
                try {
                    rs = stmt.executeQuery(Search);
                    while (rs.next()) {

                        for (int i = 0; i < al.size(); i++) {
                            if (Integer.parseInt(cellname[i][0]) == rs.getInt("CELL")) {
                                cell = cellname[i][1];
                                break;
                            } else {
                                cell = "";
                            }
                        }
                        model.addRow(new Object[]{rs.getInt("ID"),
                            rs.getString("INV_NUMBER"),
                            rs.getString("TYPE"),
                            rs.getString("PRODUCENT"),
                            rs.getString("MODEL"),
                            rs.getString("SERIAL_NUMBER"),
                            cell,
                            rs.getString("LOCATION"),
                            rs.getString("STATUS"),});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                }
            } else {
                jLabel3.setText("BŁĄD! Wybierz kategorię!");
            }
        } else {
            jLabel3.setText("BŁĄD! Dodaj frazę wyszukiwania!");
        }
    }

    public void HistoryView(int id, String type) {
        ConnectMeWithDepartments();
        jTable1.setModel(modelHistory);
        modelHistory.setRowCount(0);

        AllDevices = "Select * from INV_DEVICES_HIS where DEV_ID=" + id + " and TYPE='" + type + "'";
        try {
            rs = stmt.executeQuery(AllDevices);
            while (rs.next()) {

                for (int i = 0; i < al.size(); i++) {
                    if (Integer.parseInt(cellname[i][0]) == rs.getInt("CELL")) {
                        cell = cellname[i][1];
                        break;
                    } else {
                        cell = "";
                    }
                }
                modelHistory.addRow(new Object[]{rs.getInt("ID"),
                    rs.getInt("DEV_ID"),
                    rs.getString("INV_NUMBER"),
                    rs.getString("TYPE"),
                    rs.getString("PRODUCENT"),
                    rs.getString("MODEL"),
                    rs.getString("SERIAL_NUMBER"),
                    cell,
                    rs.getString("LOCATION"),
                    rs.getString("STATUS"),
                    rs.getString("TIMESTAMP"),});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
    }

    public void ChangeView(String table) {
        ConnectMeWithDepartments();
        jTable1.setModel(model);
        model.setRowCount(0);
        switch (table) {
            case "All":
                AllDevices = "Select * from INV_DEVICES where STATUS!='Kasacja'";
                try {
                    rs = stmt.executeQuery(AllDevices);
                    while (rs.next()) {

                        for (int i = 0; i < al.size(); i++) {
                            if (Integer.parseInt(cellname[i][0]) == rs.getInt("CELL")) {
                                cell = cellname[i][1];
                                break;
                            } else {
                                cell = "";
                            }
                        }
                        model.addRow(new Object[]{rs.getInt("ID"),
                            rs.getString("INV_NUMBER"),
                            rs.getString("TYPE"),
                            rs.getString("PRODUCENT"),
                            rs.getString("MODEL"),
                            rs.getString("SERIAL_NUMBER"),
                            cell,//procesor
                            rs.getString("LOCATION"),
                            rs.getString("STATUS"),
                            rs.getString("IP1"),
                            rs.getString("IP2"),
                            rs.getString("IP3"),
                            rs.getString("IP4"),});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                }
                break;
            case "HistoriaAll":
                jTable1.setModel(modelHistory);
                modelHistory.setRowCount(0);
                AllDevices = "Select * from INV_DEVICES_HIS";
                try {
                    rs = stmt.executeQuery(AllDevices);
                    while (rs.next()) {

                        for (int i = 0; i < al.size(); i++) {
                            if (Integer.parseInt(cellname[i][0]) == rs.getInt("CELL")) {
                                cell = cellname[i][1];
                                break;
                            } else {
                                cell = "";
                            }
                        }

                        modelHistory.addRow(new Object[]{rs.getInt("ID"),
                            rs.getInt("DEV_ID"),
                            rs.getString("INV_NUMBER"),
                            rs.getString("TYPE"),
                            rs.getString("PRODUCENT"),
                            rs.getString("MODEL"),
                            rs.getString("SERIAL_NUMBER"),
                            cell,
                            rs.getString("LOCATION"),
                            rs.getString("STATUS"),
                            rs.getString("TIMESTAMP"),});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                }
                break;
            case "Kasacja":
                AllDevices = "Select * from INV_DEVICES where STATUS='Kasacja'";
                try {
                    rs = stmt.executeQuery(AllDevices);
                    while (rs.next()) {
                        for (int i = 0; i < al.size(); i++) {
                            if (Integer.parseInt(cellname[i][0]) == rs.getInt("CELL")) {
                                cell = cellname[i][1];
                                break;
                            } else {
                                cell = "";
                            }
                        }
                        model.addRow(new Object[]{rs.getInt("ID"),
                            rs.getString("INV_NUMBER"),
                            rs.getString("TYPE"),
                            rs.getString("PRODUCENT"),
                            rs.getString("MODEL"),
                            rs.getString("SERIAL_NUMBER"),
                            cell,
                            rs.getString("LOCATION"),
                            rs.getString("STATUS"),
                            rs.getString("IP1"),
                            rs.getString("IP2"),
                            rs.getString("IP3"),
                            rs.getString("IP4"),});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                }
                break;
            default:
                AllDevices = "Select * from INV_DEVICES where TYPE='" + table + "' and STATUS!='Kasacja'";
                try {
                    rs = stmt.executeQuery(AllDevices);
                    while (rs.next()) {

                        for (int i = 0; i < al.size(); i++) {
                            if (Integer.parseInt(cellname[i][0]) == rs.getInt("CELL")) {
                                cell = cellname[i][1];
                                break;
                            } else {
                                cell = "";
                            }
                        }
                        model.addRow(new Object[]{rs.getInt("ID"),
                            rs.getString("INV_NUMBER"),
                            rs.getString("TYPE"),
                            rs.getString("PRODUCENT"),
                            rs.getString("MODEL"),
                            rs.getString("SERIAL_NUMBER"),
                            cell,
                            rs.getString("LOCATION"),
                            rs.getString("STATUS"),
                            rs.getString("IP1"),
                            rs.getString("IP2"),
                            rs.getString("IP3"),
                            rs.getString("IP4"),});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                }
                break;
        }
    }

    public void HistoryModel() {
        modelHistory.addColumn("Id");
        modelHistory.addColumn("Id urządzenia");
        modelHistory.addColumn("Numer inwentaryzacyjny");
        modelHistory.addColumn("Typ urządzenia");
        modelHistory.addColumn("Producent");
        modelHistory.addColumn("Model");
        modelHistory.addColumn("Numer seryjny");
        modelHistory.addColumn("Komórka");
        modelHistory.addColumn("Lokalizacja");
        modelHistory.addColumn("Status");
        modelHistory.addColumn("Timestamp");
    }

    public void ShowFullTable() {
        //Wszystko
        ChangeView("All");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inwentaryzacja");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jButton1.setText("Monitory");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Komputery");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Drukarki");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Wszystkie urz.");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Przeglądaj");

        jButton8.setText("Inne");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setText("<html><center>Orzeczenie o <br> stanie technicznym</center></html>");
        jButton10.setToolTipText("");
        jButton10.setActionCommand("");
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Skasowane urz.");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Urz. sieciowe");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton14.setText("Cała historia");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(153, 153, 255));
        jButton16.setForeground(new java.awt.Color(153, 153, 255));
        jButton16.setBorder(null);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.setOpaque(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Lista orzeczeń");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton22.setText("Serwery");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("Raport Ilościowy");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jButton5.setText("Dodaj");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Edytuj");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setForeground(new java.awt.Color(102, 0, 0));
        jButton7.setText("Kasacja");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton9.setText("OK");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kategoria", "----------------", "Id", "Numer inwentaryzacyjny", "Typ urządzenia", "Producent", "Model", "Numer seryjny", "Status" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setText("Wszystkie urządzenia");

        jButton13.setText("Historia");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton15.setText("Podgląd");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton18.setText("Zapisz kopię");
        jButton18.setEnabled(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Dodaj Bloba");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("Wszystkie Bloby");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Bloby urządzenia");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15))
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton13)
                    .addComponent(jButton15)
                    .addComponent(jButton18)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19)
                    .addComponent(jButton20)
                    .addComponent(jButton21))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));
        jPanel4.setToolTipText("");

        jLabel3.setForeground(new java.awt.Color(204, 0, 0));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("© Simon Goatbeard - Łukasz Szymkowski");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nr inwentaryzacyjny", "Typ urządzenia", "Producent", "Model", "Nr seryjny", "Komórka", "Lokalizacja", "Status", "IP1", "IP2", "IP3", "IP4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setAutoscrolls(false);
        jTable1.setFillsViewportHeight(true);
        jTable1.setIntercellSpacing(new java.awt.Dimension(2, 2));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Monitory
        jLabel2.setText("Monitory");
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton10.setEnabled(true);
        jButton18.setEnabled(false);
        ChangeView("Monitor");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // Inne
        jLabel2.setText("Inne urządzenia");
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton10.setEnabled(true);
        jButton18.setEnabled(false);
        ChangeView("Inne");

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Komputery
        jLabel2.setText("Komputery");
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton10.setEnabled(true);
        jButton18.setEnabled(false);
        ChangeView("Komputer");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Drukarki
        jLabel2.setText("Drukarki");
        viewType = "Printers";
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton10.setEnabled(true);
        jButton18.setEnabled(false);
        ChangeView("Drukarka");

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        search();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Dodawanie sprzętu
        InwDodaj inwD = new InwDodaj();
        inwD.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Edycja sprzętu
        if (jTable1.getSelectedRow() != -1) {
            InwEdycja inwE = new InwEdycja((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            inwE.setVisible(true);
        }

        if (jTable1.getSelectedRow() == -1) {
            jLabel3.setText("BŁĄD! Przed edycją wybierz pozycję do edytowania!");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // Usuń sprzet  
        if (jTable1.getSelectedRow() != -1) {
            InwUsun inwU = new InwUsun((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0), jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            inwU.setVisible(true);
        }
        if (jTable1.getSelectedRow() == -1) {
            jLabel3.setText("BŁĄD! Wybierz pozycję do kasacji!");
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Wszystko
        jLabel2.setText("Wszystkie urządzenia");
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton10.setEnabled(true);
        jButton18.setEnabled(false);
        ChangeView("All");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // Orzeczenie o stanie technicznym urządzenia
        if (jTable1.getSelectedRow() != -1) {
            InwOrzeczenie inwOrz = new InwOrzeczenie((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            inwOrz.setVisible(true);
        }
        if (jTable1.getSelectedRow() == -1) {
            jLabel3.setText("BŁĄD! Wybierz pozycję do kasacji!");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // Skasowane:
        jLabel2.setText("Skasowane urządzenia");
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton10.setEnabled(true);
        jButton18.setEnabled(false);
        ChangeView("Kasacja");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // Urządzenia sieciowe:
        jLabel2.setText("Urządzenia Sieciowe");
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton10.setEnabled(true);
        jButton18.setEnabled(false);
        ChangeView("Sieciowe");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // Cała historia
        jLabel2.setText("Cała historia");
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jButton10.setEnabled(false);
        jButton18.setEnabled(false);
        ChangeView("HistoriaAll");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        //Podgląd
        if (jTable1.getModel().equals(modelHistory) && jTable1.getSelectedRow() != -1) {
            InwPodglad inwP = new InwPodglad((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0), "H");
            inwP.setVisible(true);
        } else if (jTable1.getModel().equals(model) && jTable1.getSelectedRow() != -1) {
            InwPodglad inwP = new InwPodglad((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0), "N");
            inwP.setVisible(true);
        } else if (jTable1.getModel().equals(modelBlob) && jTable1.getSelectedRow() != -1) {
            InwPodgladBlob inwPB = new InwPodgladBlob(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            inwPB.setVisible(true);
        }
        if (jTable1.getSelectedRow() == -1) {
            jLabel3.setText("BŁĄD! Przed edycją wybierz pozycję do podglądu!");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // Historia:
        jLabel2.setText("Historia urządzenia");
        if (jTable1.getSelectedRow() != -1) {
            jButton5.setEnabled(false);
            jButton6.setEnabled(false);
            jButton7.setEnabled(false);
            jButton10.setEnabled(false);

            int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            String type = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
            if (jTable1.getSelectedRow() != -1) {
                HistoryView(id, type);
            } else if (jTable1.getSelectedRow() == -1) {
                jLabel3.setText("BŁĄD! Wybierz jedną pozycję!");
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        InwTTT inwTTT = new InwTTT();
        inwTTT.setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // Lista orzeczeń
        ConnectMeWithDepartments();
        jLabel2.setText("Lista orzeczeń");
        jButton18.setEnabled(true);
        //Model widoku w tabeli dla urządzeń sieciowych
        DefaultTableModel modelDecision = new DefaultTableModel();
        modelDecision.addColumn("Id");
        modelDecision.addColumn("Id urządzenia");
        modelDecision.addColumn("Numer inwentaryzacyjny");
        modelDecision.addColumn("Typ urządzenia");
        modelDecision.addColumn("Producent");
        modelDecision.addColumn("Model");
        modelDecision.addColumn("Decyzja");
        modelDecision.addColumn("Data wystawienia");
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.setModel(modelDecision);
        netDevices = "Select * from INV_DECISIONS";
        try {
            rs = stmt.executeQuery(netDevices);
            while (rs.next()) {
                Blob blob = rs.getBlob("DECISION");

                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                String s = new String(blobAsBytes);
                //release the blob and free up memory. (since JDBC 4.0)
                blob.free();

                modelDecision.addRow(new Object[]{rs.getInt("DECISION_ID"), rs.getInt("DEV_ID"), rs.getString("DEV_INV_NUM"), rs.getString("DEV_TYPE"), rs.getString("DEV_PRODUCENT"), rs.getString("DEV_MODEL"), s, rs.getDate("DECISION_DATE")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        ConnectMeWithDepartments();
        String s = "";
        Date currentDate = new Date();
        SimpleDateFormat dateFormatFile = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        String dateString = dateFormatFile.format(currentDate);
        String id = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String oldDate = "";
        String findDate = "Select * from INV_DECISIONS where DECISION_ID=" + id;
        try {
            rs = stmt.executeQuery(findDate);
            while (rs.next()) {
                oldDate = rs.getDate("DECISION_DATE").toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            zapis = new PrintWriter("Orzeczenie " + oldDate + " - Kopia " + dateString + ".doc", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        String findBlob = "Select * from INV_DECISIONS where DECISION_ID=" + id;
        try {
            rs = stmt.executeQuery(findBlob);
            while (rs.next()) {
                Blob blob = rs.getBlob("DECISION");

                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                s = new String(blobAsBytes);
                //release the blob and free up memory. (since JDBC 4.0)
                blob.free();
            }
        } catch (SQLException ex) {
            Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        zapis.println(s);
        zapis.close();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        InwDodajBlob inwDodajBlob = new InwDodajBlob(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        inwDodajBlob.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        ConnectMeWithDepartments();
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.setModel(modelBlob);

        String allBlob = "Select * from INV_BLOB";
        try {
            rs = stmt.executeQuery(allBlob);
            while (rs.next()) {
                modelBlob.addRow(new Object[]{rs.getInt("ID"), rs.getInt("DEV_ID"), rs.getString("NAME"), rs.getString("BLOB_DATE")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        ConnectMeWithDepartments();

        if (jTable1.getSelectedRow() != -1) {
            String id = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            jTable1.setDefaultEditor(Object.class, null);
            jTable1.setModel(modelBlob);

            String allBlob = "Select * from INV_BLOB where DEV_ID=" + id;
            try {
                rs = stmt.executeQuery(allBlob);
                while (rs.next()) {
                    modelBlob.addRow(new Object[]{rs.getInt("ID"), rs.getInt("DEV_ID"), rs.getString("NAME"), rs.getString("BLOB_DATE")});
                }
            } catch (SQLException ex) {
                Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
            }
        }
        if (jTable1.getSelectedRow() == -1) {
            jLabel3.setText("BŁĄD! Przed edycją wybierz pozycję do podglądu!");
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        jLabel2.setText("Serwery");
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton10.setEnabled(true);
        jButton18.setEnabled(false);
        ChangeView("Serwer");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        InwRaport rap = new InwRaport();
        rap.inwRaport();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            JTable target = (JTable) evt.getSource();
            InwEdycja inwE = new InwEdycja((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            inwE.setVisible(true);
            
}
    }//GEN-LAST:event_jTable1MouseClicked

    
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InwentaryzacjaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
