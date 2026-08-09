
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class signup1 extends javax.swing.JFrame {

    int otp;

    public signup1() {
        initComponents();
        setSize(700, 500);
        jPanel1.setVisible(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x =  ((d.width) - this.getWidth())/2;
        int y =  ((d.height) - this.getHeight())/2;
        setLocation(x, y);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfmob = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfotp = new javax.swing.JTextField();
        btverify = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Verify Mobile");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(270, 0, 230, 60);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Mobile NO.");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(80, 90, 160, 60);
        getContentPane().add(tfmob);
        tfmob.setBounds(250, 100, 340, 30);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(320, 150, 140, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("OTP");

        tfotp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfotpActionPerformed(evt);
            }
        });

        btverify.setText("Verify");
        btverify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btverifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel3)
                .addGap(122, 122, 122)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfotp, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btverify, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)))
                .addGap(123, 123, 123))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tfotp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btverify)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 210, 610, 190);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String mobilenumber = tfmob.getText();
        if (mobilenumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Your Mobile Number");
        } else if (mobilenumber.length() != 10) {
            JOptionPane.showMessageDialog(this, "Enter Your Valid Number ");

        }
        if (mobilenumber.length() == 10) {
            jPanel1.setVisible(true);
            otp = (int) ((10000 + (9999 - 10000)) * Math.random());
            System.out.println("otp : " + otp);
            String msg = "In order to sign up please verify the otp and your otp is " + otp;
            sendsms.send(mobilenumber, msg);
            JOptionPane.showMessageDialog(this, "Check your Mobile for OTP");
            //sendsms obj = new sendsms();
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfotpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfotpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfotpActionPerformed

    private void btverifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btverifyActionPerformed
        String mobilenumber = tfmob.getText();
        int Otp = Integer.parseInt(tfotp.getText());
        if (tfotp.equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Your OTP");
        }
        if (Otp == otp) {
            JOptionPane.showMessageDialog(this, "OTP Verified");
            signup2 obj = new signup2(mobilenumber);
            

        } else {
            JOptionPane.showMessageDialog(this, "OTP is not Verified");
        }
    }//GEN-LAST:event_btverifyActionPerformed

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
            java.util.logging.Logger.getLogger(signup1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signup1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signup1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signup1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signup1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btverify;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfmob;
    private javax.swing.JTextField tfotp;
    // End of variables declaration//GEN-END:variables
}
