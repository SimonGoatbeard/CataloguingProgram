/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inw;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lszymkowski
 */
public final class InwUsun extends javax.swing.JFrame {

    Connection con;
    Statement stmt;
    ResultSet rs;
    int dev_id;
    Conn myConn = new Conn();

    String type, inv_num, producent, model, prod_date, serial_number, cell, location, ver_date, status, procesor_name, clock_speed, ram, system, system_key, ip1, ip2, ip3, ip4, ip5, monitor_type, monitor_size, dev_type, function, config, notes;

    /**
     * Creates new form InwUsun
     * @param id
     * @param type
     */
    public InwUsun(int id, String type) {
        dev_id = id;
        dev_type = type;
        initComponents();
        getRecord(id);
    }

    public void getRecord(int id) {
        con=myConn.connectMe();
        stmt=myConn.connectStatement(con);
        String AllDevices = "Select * from INV_DEVICES Where ID=" + id;
        try {
            rs = stmt.executeQuery(AllDevices);
            while (rs.next()) {
                jTextArea1.setText("ID: " + rs.getString("ID") + "\n"
                        + "Typ urządzenia: " + rs.getString("TYPE") + "\n"
                        + "Nr inwentaryzacyjny: " + rs.getString("INV_NUMBER") + "\n"
                        + "Producent: " + rs.getString("PRODUCENT") + "\n"
                        + "Model: " + rs.getString("MODEL") + "\n"
                        + "Data produkcji: " + rs.getString("PROD_DATE") + "\n"
                        + "Numer seryjny: " + rs.getString("SERIAL_NUMBER") + "\n"
                        + "Komórka: " + rs.getString("CELL") + "\n"
                        + "Lokalizacja: " + rs.getString("LOCATION") + "\n"
                        + "Data weryfikacji: " + rs.getString("VER_DATE") + "\n"
                        + "Status: " + rs.getString("STATUS") + "\n"
                        + "Procesor: " + rs.getString("PROCESOR_NAME") + "\n"
                        + "Częstotliwość procesora: " + rs.getString("CLOCK_SPEED") + "\n"
                        + "RAM: " + rs.getString("RAM") + "\n"
                        + "System: " + rs.getString("SYSTEM") + "\n"
                        + "Klucz: " + rs.getString("SYSTEM_KEY") + "\n"
                        + "IP1: " + rs.getString("IP1") + "\n"
                        + "IP2: " + rs.getString("IP2") + "\n"
                        + "IP3: " + rs.getString("IP3") + "\n"
                        + "IP4: " + rs.getString("IP4") + "\n"
                        + "IP5: " + rs.getString("IP5") + "\n"
                        + "Typ monitora: " + rs.getString("MONITOR_TYPE") + "\n"
                        + "Rozmiar monitora: " + rs.getString("MONITOR_SIZE") + "\n"
                        + "Rodzaj urządzenia: " + rs.getString("DEV_TYPE") + "\n"
                        + "Funkcja: " + rs.getString("FUNCTION") + "\n"
                        + "Konfiguracja: " + rs.getString("CONFIG") + "\n"
                        + "Opis: " + rs.getString("NOTES"));
                type = rs.getString("TYPE");
                inv_num = rs.getString("INV_NUMBER");
                producent = rs.getString("PRODUCENT");
                model = rs.getString("MODEL");
                prod_date = rs.getString("PROD_DATE");
                serial_number = rs.getString("SERIAL_NUMBER");
                cell = rs.getString("CELL");
                location = rs.getString("LOCATION");
                ver_date = rs.getString("VER_DATE");
                status = rs.getString("STATUS");
                procesor_name = rs.getString("PROCESOR_NAME");
                clock_speed = rs.getString("CLOCK_SPEED");
                ram = rs.getString("RAM");
                system = rs.getString("SYSTEM");
                system_key = rs.getString("SYSTEM_KEY");
                ip1 = rs.getString("IP1");
                ip2 = rs.getString("IP2");
                ip3 = rs.getString("IP3");
                ip4 = rs.getString("IP4");
                ip5 = rs.getString("IP5");
                monitor_type = rs.getString("MONITOR_TYPE");
                monitor_size = rs.getString("MONITOR_SIZE");
                dev_type = rs.getString("DEV_TYPE");
                function = rs.getString("FUNCTION");
                config = rs.getString("CONFIG");
                notes = rs.getString("NOTES");
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuń");
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(400, 300));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kasowanie");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(153, 0, 0));
        jButton1.setText("Zatwierdź");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 153, 0));
        jButton2.setText("Anuluj");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Czy jesteś pewny, że chcesz przeznaczyć do kasacji następującą pozycję:");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 126, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("© Simon Goatbeard - Łukasz Szymkowski");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Usuń:
        con=myConn.connectMe();
        stmt=myConn.connectStatement(con);
        String update = "Update INV_DEVICES "
                + "Set STATUS='Kasacja' " + "Where ID=" + dev_id;
        String insert = "Insert into INV_DEVICES_HIS (TYPE, INV_NUMBER, PRODUCENT, MODEL, PROD_DATE, SERIAL_NUMBER, CELL, LOCATION, VER_DATE, STATUS, PROCESOR_NAME, CLOCK_SPEED, RAM, SYSTEM, SYSTEM_KEY, IP1, IP2, IP3, IP4, IP5, MONITOR_TYPE, MONITOR_SIZE, DEV_TYPE, FUNCTION, CONFIG, NOTES, TIMESTAMP, DEV_ID)"
                + " VALUES('" + type + "', '" + inv_num + "', '" + producent + "', '" + model + "', '" + prod_date + "', '" + serial_number + "', '" + cell + "', '" + location + "', '" + ver_date + "', '" + status + "', '" + procesor_name + "', '" + clock_speed + "', '" + ram + "', '" + system + "', '" + system_key + "', '" + ip1 + "', '" + ip2 + "', '" + ip3 + "', '" + ip4 + "', '" + ip5 + "', '" + monitor_type + "', '" + monitor_size + "', '" + dev_type + "', '" + function + "', '" + config + "', '" + notes + "', CURRENT_TIMESTAMP, '" + dev_id + "')";

        try {

            rs = stmt.executeQuery(insert);
            rs = stmt.executeQuery(update);

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
        InwSuccess inwS = new InwSuccess();
        inwS.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Anuluj:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
