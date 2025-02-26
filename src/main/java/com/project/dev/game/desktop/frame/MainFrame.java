/*
 * @fileoverview    {MainFrame}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.game.desktop.frame;

import com.project.dev.game.desktop.getter.GraphicGetter;
import com.project.dev.joystick.name.nintendo.NintendoJoystick;
import com.project.dev.joystick.name.poly.PolyJoystick;
import com.project.dev.tray.setter.TrayIconSetter;
import javax.swing.JFrame;
import javax.swing.JTextField;

import static com.project.dev.joystick.name.generic.GenericJoystick.JOYSTICK_TYPE_SERVER;

/**
 * TODO: Description of {@code MainFrame}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public class MainFrame extends JFrame {

    /*
     * Variables locales.
     */
    private final JFrame frame = this;                                  // Referencia a la ventana.

    /*
     * Variables usadas para asignar datos a los spinner.
     */
    private static final String[] JOYSTICK_NAMES = {
        PolyJoystick.JOYSTICK_NAME,
        NintendoJoystick.JOYSTICK_NAME
    };

    /**
     * TODO: Description of method {@code MainFrame}.
     *
     */
    public MainFrame() {
        initComponents();

        // Agrega titulo, icono y trayIcon al frame.
        frame.setTitle("Game");
        frame.setIconImage(GraphicGetter.getGraphic("main_icon.png").getImage());
        TrayIconSetter.setTrayIconToFrame(frame);

        // Alinea al centro los jlabel.
        message1Label.setHorizontalAlignment(JTextField.CENTER);
        message2Label.setHorizontalAlignment(JTextField.CENTER);

        // Asigna valores al spinner con los nombres del joystick.
        for (String name : JOYSTICK_NAMES)
            spnJoystickName.addItem(name);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        message1Label = new javax.swing.JLabel();
        message2Label = new javax.swing.JLabel();
        spnJoystickName = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        message1Label.setText("Indique el nombre del joystick que");

        message2Label.setText("controlará el programa");

        spnJoystickName.setFocusable(false);
        spnJoystickName.addActionListener(this::spnJoystickNameActionPerformed);

        btnCreate.setText("Iniciar");
        btnCreate.setPreferredSize(new java.awt.Dimension(85, 32));
        btnCreate.addActionListener(this::btnCreateActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(spnJoystickName, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(125, Short.MAX_VALUE))
                        .addComponent(message1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(message2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(message1Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(message2Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(spnJoystickName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * TODO: Description of method {@code btnCreateActionPerformed}.
     *
     * @param evt
     */
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed

        // Crea un frame para iniciar el frame con el joystick.
        GameFrame joystickFrame = new GameFrame();

        // Manda información al frame con el joystick.
        joystickFrame.setJoystickType(JOYSTICK_TYPE_SERVER);
        joystickFrame.setJoystickName(JOYSTICK_NAMES[spnJoystickName.getSelectedIndex()]);

        // Inicia el frame.
        joystickFrame.setVisible(true);
        joystickFrame.startFrame();
    }//GEN-LAST:event_btnCreateActionPerformed

    /**
     * TODO: Description of method {@code spnJoystickNameActionPerformed}.
     *
     * @param evt
     */
    private void spnJoystickNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spnJoystickNameActionPerformed
    }//GEN-LAST:event_spnJoystickNameActionPerformed

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JLabel message1Label;
    private javax.swing.JLabel message2Label;
    private javax.swing.JComboBox<String> spnJoystickName;
    // End of variables declaration//GEN-END:variables
}
