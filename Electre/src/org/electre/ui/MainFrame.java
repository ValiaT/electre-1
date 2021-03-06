/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on Jun 1, 2010, 9:29:10 PM
 */

package org.electre.ui;

import java.awt.Frame;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.electre.core.Atributo;
import org.electre.utils.ElectreFileFilter;
import org.electre.utils.ElectreSAXHandler;
import org.electre.utils.MyTableModel;
import org.xml.sax.InputSource;

/**
 *
 * @author william
 */
public class MainFrame extends javax.swing.JFrame {

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.title")); // NOI18N

        jDesktopPane1.setBackground(new java.awt.Color(240, 240, 240));
        jDesktopPane1.setForeground(new java.awt.Color(240, 240, 240));
        jDesktopPane1.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);

        jMenu1.setText(org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.jMenu1.text")); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText(org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.jMenuItem1.text")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText(org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.jMenuItem2.text")); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.jMenu2.text")); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ElectreDialog dialog = new ElectreDialog(this,true);
        dialog.setTitle("Nuevo programa de decisión");
        dialog.setVisible(true);
        MyTableModel tableModel = dialog.getTableModel();
        List<Atributo> atributos = dialog.getAtributos();
        ElectreInternalFrame frame = new ElectreInternalFrame();
        frame.setAtributos(atributos);
        frame.setModel(tableModel);
        this.jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        JFileChooser jc = new JFileChooser();
        jc.setFileFilter(new ElectreFileFilter());
        int c = jc.showOpenDialog(this);
        if (c == JFileChooser.APPROVE_OPTION) {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            try {
                spf.setValidating(false);
                SAXParser sp = spf.newSAXParser();
                InputSource input = new InputSource(new FileReader(jc.getSelectedFile()));
                ElectreSAXHandler handler = new ElectreSAXHandler();
                sp.parse(input, handler);
                ElectreInternalFrame frame = new ElectreInternalFrame();
                ArrayList<Atributo> atribs = (ArrayList<Atributo>) handler.getAttributes();
                frame.setAtributos(atribs);
                Vector<String> columnNames = new Vector<String>();
                columnNames.add("Alternativas");
                for (Atributo a :atribs) {
                    columnNames.add(a.getNombre());
                }
                MyTableModel m = new MyTableModel();
                m.setDataVector(handler.getMatrix(), columnNames);
                frame.setModel(m);
                this.jDesktopPane1.add(frame);
                //frame.process();
                frame.setVisible(true);
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al intentar abrir archivo", "Error",0);}



        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables

}
