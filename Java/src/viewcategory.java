
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Main-Hall
 */
public class viewcategory extends javax.swing.JFrame {

    ArrayList<Category> al = new ArrayList<>();
    CategoryTableModel ctm = new CategoryTableModel();

    public viewcategory() {
        initComponents();
        
        jTable1.setModel(ctm);
        loaddata();
         setSize(800, 600);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x =  ((d.width) - this.getWidth())/2;
        int y =  ((d.height) - this.getHeight())/2;
        setLocation(x, y);
          setVisible(true);
    }

    void loaddata() {
        try {
            // TODO add your handling code here:
            String cuisine = cbcuisine.getSelectedItem().toString();
            HttpResponse<String> res = Unirest.get("http://localhost:8888/getcategorybycuisine")
                    .queryString("cuisine", cuisine).asString();
            String response = res.getBody();
            StringTokenizer st = new StringTokenizer(response, "~!@");
            int n = st.countTokens();
            al.clear();
            for (int i = 1; i <= n; i++) {
                String row = st.nextToken();
                StringTokenizer st2 = new StringTokenizer(row, "#$%");
                String categoryname = st2.nextToken();
                String cname = st2.nextToken();
                String desc = st2.nextToken();
                String photo = st2.nextToken();
                Category obj = new Category(categoryname, cname, desc, photo);
                al.add(obj);
            }
            jTable1.setRowHeight(100);
            jTable1.setShowGrid(true);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());

        } catch (UnirestException ex) {
            Logger.getLogger(viewcategory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbcuisine = new javax.swing.JComboBox<>();
        btgo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btdelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("View Category");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(210, 0, 230, 70);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Cuisine");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 90, 80, 30);

        cbcuisine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Indian", "Chinese", "Italian", "South Indian" }));
        getContentPane().add(cbcuisine);
        cbcuisine.setBounds(170, 100, 250, 20);

        btgo.setText("GO");
        btgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgoActionPerformed(evt);
            }
        });
        getContentPane().add(btgo);
        btgo.setBounds(447, 100, 90, 23);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Desciption", "Photo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 190, 590, 220);

        btdelete.setText("Delete");
        btdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btdelete);
        btdelete.setBounds(240, 440, 150, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btgoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgoActionPerformed
        loaddata();
    }//GEN-LAST:event_btgoActionPerformed

    BufferedImage resizephoto(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }


    private void btdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdeleteActionPerformed
        try {
            // TODO add your handling code here:
            int index = jTable1.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Please select a category first");
            } else {
                int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "Delete Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (showConfirmDialog == JOptionPane.OK_OPTION) {
                    String category = al.get(index).name;
                    HttpResponse<String> res = Unirest.get("http://localhost:8888/deletecategory")
                            .queryString("category", category).asString();
                    String response = res.getBody();
                    if (response.equals("success")) {
                        JOptionPane.showMessageDialog(rootPane, "Category Deleted");
                        loaddata();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Deletion Failed!!!");
                    }
                }
            }
        } catch (UnirestException ex) {
            Logger.getLogger(viewcategory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btdeleteActionPerformed

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
            java.util.logging.Logger.getLogger(viewcategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewcategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewcategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewcategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewcategory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btdelete;
    private javax.swing.JButton btgo;
    private javax.swing.JComboBox<String> cbcuisine;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
  class Category {

        String name, cuisine, description, photo;

        public Category(String name, String cuisine, String description, String photo) {
            this.name = name;
            this.cuisine = cuisine;
            this.description = description;
            this.photo = photo;
        }

    }

    class CategoryTableModel extends AbstractTableModel {

        String[] columns = {"Category", "Description", "Photo"};

        @Override
        public int getRowCount() {
            return al.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return al.get(rowIndex).name;
                case 1:
                    return al.get(rowIndex).description;
                case 2:
                    return al.get(rowIndex).photo;
                default:
                    return null;
            }
        }

    }

    class ImageRenderer extends DefaultTableCellRenderer {

        JLabel lbl = new JLabel();
        ImageIcon icon = new ImageIcon("");
        BufferedImage bufferedImage, newimage;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            try {
                URL url = new URL("http://localhost:8888/GetResource/" + al.get(row).photo);
                bufferedImage = ImageIO.read(url);
                newimage = resizephoto(bufferedImage, 100, 100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            icon = new ImageIcon(newimage);
            lbl.setIcon(icon);
            lbl.setBounds(0, 0, 100, 100);
            return lbl;
        }
    }
}
