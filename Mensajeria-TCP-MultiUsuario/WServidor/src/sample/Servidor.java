package sample;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.control.TableView;

public class Servidor implements Runnable{
    static final int MAXIMO = 20;//MAXIMO DE CONEXIONES PERMITIDAS
    ServerSocket servidor;
    Socket tabla[];
    ComunHilos comun;

    TableView<Mensaje> tablaMensajes;


    public Servidor(TableView<Mensaje> tb) throws IOException {
        this.tablaMensajes=tb;
        int puerto=7777;
        servidor = new ServerSocket(puerto);
        System.out.println("Servidor iniciado...");



    }

    @Override
    public void run() {
        //Crear comunhilos: conexiones, lista de usuarios
        tabla = new Socket[MAXIMO];//para controlar las conexiones
        comun = new ComunHilos(MAXIMO, 0, 0, tabla);
        System.out.println("Run de Servidor...");
        while (comun.getConexiones() < MAXIMO) {
            System.out.println("Bucle de conexiones..");
            Socket socket = new Socket(); //Creo socket para recibir al cliente
            try {
                socket = servidor.accept();// esperando cliente
            } catch (IOException e) {
                System.out.println("ERROR al aceptar el socket"+e.getStackTrace());
            }

            System.out.println("El servidor ha aceptado al cliente");

            comun.addTabla(socket, comun.getConexiones());
            comun.setActuales(comun.getActuales() + 1);
            comun.setConexiones(comun.getConexiones() + 1);

            HiloServidor hilo = new HiloServidor(socket, comun,tablaMensajes);
            hilo.start();
        }
        try {
            servidor.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el servidor"+e.getStackTrace());
        }
    }


    /**************************************************/



}
