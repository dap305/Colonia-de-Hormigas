/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolony_tsp;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author wilfredojose
 */
public class VentanaGuardar extends javax.swing.JFrame {
    
    JFileChooser seleccionado = new JFileChooser();//atributos para guardar y abrir archivos txt
    File archivo;
    Archivos gestion = new Archivos();
    static String txtNovo = "";

    /**
     * Creates new form VentanaGuardar
     */
    public VentanaGuardar(String txt) { //constructor al que le paso por parametros un string, que sera realmente
                                        //el metodo de la clase conversiones de texto que te convierte los datos de una simulacion en string
        initComponents();
        this.setLocationRelativeTo(null);
         txtNovo = txt;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btmGuardarTxt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setRows(5);
        txtArea.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        txtArea.setEnabled(false);
        jScrollPane1.setViewportView(txtArea);

        btmGuardarTxt.setText("Guardar");
        btmGuardarTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmGuardarTxtActionPerformed(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("Nota: Recuerde guardar el archivo en \nformato txt, de otra forma arrojará \nerror.");
        jTextArea2.setAutoscrolls(false);
        jTextArea2.setBorder(null);
        jTextArea2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea2.setEnabled(false);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(btmGuardarTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btmGuardarTxt)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmGuardarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmGuardarTxtActionPerformed
        //parte en la que se guarda el string
        Gráfica grafica = new Gráfica(0);
        txtArea.setText(txtNovo);
        
        if(seleccionado.showDialog(null, "Guardar archivo") == JFileChooser.APPROVE_OPTION){
            archivo = seleccionado.getSelectedFile();
            if(archivo.getName().endsWith("txt")){
                    String contenido = txtArea.getText();
                    String respuesta = gestion.guardarTexto(archivo, contenido);
                    if(respuesta!=null){
                        JOptionPane.showMessageDialog(null, respuesta);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al guardar el archivo.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
            }else{
                JOptionPane.showMessageDialog(null, "El archivo debe ser en formato txt.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        grafica.show();
        this.dispose();

    }//GEN-LAST:event_btmGuardarTxtActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaGuardar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaGuardar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaGuardar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaGuardar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaGuardar(txtNovo).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmGuardarTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
