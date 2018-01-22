/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inw;

import java.awt.Color;

/**
 *
 * @author Lukasz Szymkowski "Simon Goatbeard"
 */
public class InwTTT extends javax.swing.JFrame {

    /**
     * Creates new form InwTTT
     */
    public boolean turnO = true;

    public InwTTT() {
        initComponents();
    }

    public void checkIT() {
        if (jToggleButton1.getText().equals("X") && jToggleButton2.getText().equals("X") && jToggleButton3.getText().equals("X")) {
            jToggleButton1.setForeground(Color.red);
            jToggleButton2.setForeground(Color.red);
            jToggleButton3.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton1.getText().equals("O") && jToggleButton2.getText().equals("O") && jToggleButton3.getText().equals("O")) {
            jToggleButton1.setForeground(Color.red);
            jToggleButton2.setForeground(Color.red);
            jToggleButton3.setForeground(Color.red);
            gameOver();
        }

        if (jToggleButton4.getText().equals("X") && jToggleButton5.getText().equals("X") && jToggleButton6.getText().equals("X")) {
            jToggleButton4.setForeground(Color.red);
            jToggleButton5.setForeground(Color.red);
            jToggleButton6.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton4.getText().equals("O") && jToggleButton5.getText().equals("O") && jToggleButton6.getText().equals("O")) {
            jToggleButton4.setForeground(Color.red);
            jToggleButton5.setForeground(Color.red);
            jToggleButton6.setForeground(Color.red);
            gameOver();
        }

        if (jToggleButton7.getText().equals("X") && jToggleButton8.getText().equals("X") && jToggleButton9.getText().equals("X")) {
            jToggleButton7.setForeground(Color.red);
            jToggleButton8.setForeground(Color.red);
            jToggleButton9.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton7.getText().equals("O") && jToggleButton8.getText().equals("O") && jToggleButton9.getText().equals("O")) {
            jToggleButton7.setForeground(Color.red);
            jToggleButton8.setForeground(Color.red);
            jToggleButton9.setForeground(Color.red);
            gameOver();
        }

        if (jToggleButton1.getText().equals("X") && jToggleButton4.getText().equals("X") && jToggleButton7.getText().equals("X")) {
            jToggleButton1.setForeground(Color.red);
            jToggleButton4.setForeground(Color.red);
            jToggleButton7.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton1.getText().equals("O") && jToggleButton4.getText().equals("O") && jToggleButton7.getText().equals("O")) {
            jToggleButton1.setForeground(Color.red);
            jToggleButton4.setForeground(Color.red);
            jToggleButton7.setForeground(Color.red);
            gameOver();
        }

        if (jToggleButton2.getText().equals("X") && jToggleButton5.getText().equals("X") && jToggleButton8.getText().equals("X")) {
            jToggleButton2.setForeground(Color.red);
            jToggleButton5.setForeground(Color.red);
            jToggleButton8.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton2.getText().equals("O") && jToggleButton5.getText().equals("O") && jToggleButton8.getText().equals("O")) {
            jToggleButton2.setForeground(Color.red);
            jToggleButton5.setForeground(Color.red);
            jToggleButton8.setForeground(Color.red);
            gameOver();
        }

        if (jToggleButton3.getText().equals("X") && jToggleButton6.getText().equals("X") && jToggleButton9.getText().equals("X")) {
            jToggleButton3.setForeground(Color.red);
            jToggleButton6.setForeground(Color.red);
            jToggleButton9.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton3.getText().equals("O") && jToggleButton6.getText().equals("O") && jToggleButton9.getText().equals("O")) {
            jToggleButton3.setForeground(Color.red);
            jToggleButton6.setForeground(Color.red);
            jToggleButton9.setForeground(Color.red);
            gameOver();
        }

        if (jToggleButton1.getText().equals("X") && jToggleButton5.getText().equals("X") && jToggleButton9.getText().equals("X")) {
            jToggleButton1.setForeground(Color.red);
            jToggleButton5.setForeground(Color.red);
            jToggleButton9.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton1.getText().equals("O") && jToggleButton5.getText().equals("O") && jToggleButton9.getText().equals("O")) {
            jToggleButton1.setForeground(Color.red);
            jToggleButton5.setForeground(Color.red);
            jToggleButton9.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton3.getText().equals("X") && jToggleButton5.getText().equals("X") && jToggleButton7.getText().equals("X")) {
            jToggleButton3.setForeground(Color.red);
            jToggleButton5.setForeground(Color.red);
            jToggleButton7.setForeground(Color.red);
            gameOver();
        }
        if (jToggleButton3.getText().equals("O") && jToggleButton5.getText().equals("O") && jToggleButton7.getText().equals("O")) {
            jToggleButton3.setForeground(Color.red);
            jToggleButton5.setForeground(Color.red);
            jToggleButton7.setForeground(Color.red);
            gameOver();
        }
    }

    public void gameOver() {
        jToggleButton1.setEnabled(false);
        jToggleButton2.setEnabled(false);
        jToggleButton3.setEnabled(false);
        jToggleButton4.setEnabled(false);
        jToggleButton5.setEnabled(false);
        jToggleButton6.setEnabled(false);
        jToggleButton7.setEnabled(false);
        jToggleButton8.setEnabled(false);
        jToggleButton9.setEnabled(false);
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
        jPanel2 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TTT");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jToggleButton2.setMaximumSize(new java.awt.Dimension(40, 40));
        jToggleButton2.setMinimumSize(new java.awt.Dimension(40, 40));
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton1.setMaximumSize(new java.awt.Dimension(40, 40));
        jToggleButton1.setMinimumSize(new java.awt.Dimension(40, 40));
        jToggleButton1.setPreferredSize(new java.awt.Dimension(40, 40));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton3.setMaximumSize(new java.awt.Dimension(40, 40));
        jToggleButton3.setMinimumSize(new java.awt.Dimension(40, 40));
        jToggleButton3.setPreferredSize(new java.awt.Dimension(40, 40));
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });

        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });

        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });

        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton1.setText("O");
            jToggleButton1.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton1.setText("X");
            jToggleButton1.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton2.setText("O");
            jToggleButton2.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton2.setText("X");
            jToggleButton2.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton3.setText("O");
            jToggleButton3.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton3.setText("X");
            jToggleButton3.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton4.setText("O");
            jToggleButton4.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton4.setText("X");
            jToggleButton4.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton5.setText("O");
            jToggleButton5.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton5.setText("X");
            jToggleButton5.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton6.setText("O");
            jToggleButton6.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton6.setText("X");
            jToggleButton6.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton7.setText("O");
            jToggleButton7.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton7.setText("X");
            jToggleButton7.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton8.setText("O");
            jToggleButton8.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton8.setText("X");
            jToggleButton8.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed
        // TODO add your handling code here:
        if (turnO == true) {
            jToggleButton9.setText("O");
            jToggleButton9.setEnabled(false);
            turnO = false;
        } else if (turnO == false) {
            jToggleButton9.setText("X");
            jToggleButton9.setEnabled(false);
            turnO = true;
        }
        checkIT();
    }//GEN-LAST:event_jToggleButton9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    // End of variables declaration//GEN-END:variables
}
