
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 *
 * @author rodolfo
 */
public class MainForm extends javax.swing.JFrame {
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Socket socket;
    String servidorip = "127.0.0.1";
    int puertoServidor;
    Servidor servidor;
    /**
     * Creates new form MainForm
     */
    public MainForm(int puerto) {
        initComponents();
        this.puertoServidor = puerto;
        btnConectar.setEnabled(false);
        btnDesconectar.setEnabled(false);
        btnEnviar.setEnabled(false);
        btnNuevoCliente.setEnabled(false);
    }
    public void iniciarThreadCliente() {
        Thread threadCliente = new Thread(new Lector());
        threadCliente.start();
       
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
        txtChat = new javax.swing.JTextArea();
        textFieldTexto = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textFieldNick = new javax.swing.JTextField();
        btnServidor = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        btnConectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente");

        txtChat.setColumns(20);
        txtChat.setRows(5);
        txtChat.setFocusable(false);
        jScrollPane1.setViewportView(txtChat);

        btnEnviar.setText("Enviar");
        btnEnviar.setEnabled(false);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnNuevoCliente.setText("Nuevo Cliente");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Nick:");

        textFieldNick.setText("anonimo1");

        btnServidor.setText("Iniciar Servidor");
        btnServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServidorActionPerformed(evt);
            }
        });

        btnDesconectar.setText("Desconectar");
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(btnEnviar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldNick, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnServidor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConectar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDesconectar)
                        .addGap(48, 48, 48)
                        .addComponent(btnNuevoCliente)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFieldNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnviar)
                    .addComponent(textFieldTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoCliente)
                    .addComponent(btnServidor)
                    .addComponent(btnDesconectar)
                    .addComponent(btnConectar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        printWriter.println(textFieldNick.getText() + ": " + textFieldTexto.getText());
        printWriter.flush();
        
        textFieldTexto.setText("");
        textFieldTexto.requestFocus();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        MainForm cliente = new MainForm(puertoServidor);
        cliente.setLocation(500, 0);
        cliente.setVisible(true);
        cliente.setIdentificador("anonimo"+ (int)(Math.random() * 100));
        cliente.btnConectar.setEnabled(true);
        cliente.btnServidor.setEnabled(false);
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServidorActionPerformed
        servidor = new Servidor(puertoServidor);
        btnServidor.setEnabled(false);
        
        btnConectar.setEnabled(true);
        btnDesconectar.setEnabled(false);
        btnNuevoCliente.setEnabled(true);
    }//GEN-LAST:event_btnServidorActionPerformed

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed
        try {
            socket.close();
            btnDesconectar.setEnabled(false);
            btnConectar.setEnabled(true);
            btnEnviar.setEnabled(false);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnDesconectarActionPerformed

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        crearSockets();
        iniciarThreadCliente();
        
        btnNuevoCliente.setEnabled(true);
        btnConectar.setEnabled(false);
        btnDesconectar.setEnabled(true);
        btnEnviar.setEnabled(true);
    }//GEN-LAST:event_btnConectarActionPerformed

    public void desconectar() {
        
    }
    public void setIdentificador(String nick) {
        this.textFieldNick.setText(nick);
    }
    public void crearSockets() {
        try {
            socket = new Socket(servidorip, puertoServidor);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(streamReader);
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnServidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textFieldNick;
    private javax.swing.JTextField textFieldTexto;
    private javax.swing.JTextArea txtChat;
    // End of variables declaration//GEN-END:variables

public class Lector implements Runnable {

        @Override
        public void run() {
            try {
                String mensaje;
                while((mensaje = bufferedReader.readLine()) != null) {
                    txtChat.append(mensaje + "\n");
                }
                
            } catch (SocketException ex) {
                
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    
}

}

