
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodolfo
 */
public class Servidor {

    ArrayList<PrintWriter> clientesStreams;
    ServerSocket serverSocket;
    public Servidor(int puerto) {
        Thread threadServidor = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    clientesStreams = new ArrayList<PrintWriter>();

                    serverSocket = new ServerSocket(puerto);
                    //System.out.println("Esperando conexiones");
                    while (true) {
                        Socket socket = serverSocket.accept();
                        //System.out.println("Nueva conexion");
                        PrintWriter printwriter = new PrintWriter(socket.getOutputStream());
                        clientesStreams.add(printwriter);

                        Thread t = new Thread(new ManejadorDeClientes(socket));
                        t.start();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        threadServidor.start();
    }



    public class ManejadorDeClientes implements Runnable {

        BufferedReader bufferedReader;
        Socket socket;

        public ManejadorDeClientes(Socket socket) {
            InputStreamReader isreader = null;
            try {
                this.socket = socket;
                isreader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(isreader);
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }

        @Override
        public void run() {
            try {
                String mensaje;
                while ((mensaje = bufferedReader.readLine()) != null) {
                    broadcast(mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void broadcast(String mensaje) {
        for (PrintWriter printWriter : clientesStreams) {
            printWriter.println(mensaje);
            printWriter.flush();
        }
    }
}
