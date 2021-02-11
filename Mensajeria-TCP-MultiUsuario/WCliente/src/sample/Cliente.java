package sample;

import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Cliente implements Runnable{
    //private static final long serialVersionUID = 1L;
    private Socket socket = null;
    private DataInputStream entrada;
    private DataOutputStream salida;

    private String nombre;
    private ArrayList<Mensaje> listaMensajes =new ArrayList<Mensaje>();
    private ArrayList<Contacto> listaContactos = new ArrayList<>();

    private TextArea txtArea;
    private TableView<Contacto> tablaContacto;

    private static final String CODIGO_RECIBIR_CONTACTOS="GATO777";


    public Cliente(Socket socket, String nombre, TextArea txtArea, TableView<Contacto> tbc){

        System.out.println("entra a cliente");
        this.socket=socket;
        this.nombre=nombre;
        this.txtArea=txtArea;
        this.tablaContacto=tbc;

        try {

            //Abro flujo salida de texto
            salida=new DataOutputStream(socket.getOutputStream());

            //Envío el nombre al servidor
            salida.writeUTF(nombre);
            System.out.println("nombre enviado");

            System.out.println("nombre enviado");
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }



    }

    public void run(){

        //inicializo el  flujo de netrada dew mensajes
        try {
            this.entrada=new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        boolean repetir=true;
        while (repetir) {
            try {
                System.out.println("-----------------------------");
                System.out.println("Hilo cliente a la escucha");

                //Se lee el objeto mensaje que envía el servidor
                String m = entrada.readUTF();
                if(m.equals(CODIGO_RECIBIR_CONTACTOS)){
                    //Recibimos la lista de usuarios del servidor
                    String mLista=entrada.readUTF();
                    System.out.println("Lista de contactos recibida "+mLista);
                    String[] extraer = mLista.split(",");
                    for (int i=0;i< extraer.length;i++){
                        listaContactos.add(new Contacto(extraer[i]));
                        System.out.println(extraer[i]+" ah sido agregado");
                    }
                    /**
                     * AQUI
                     * daba error por llamar a un obserbable list de java fx en un un hilo externo
                     *  El uso de Platform.runLater(...)retrasos su modificación hasta que el evento actual se haya manejado por completo.
                     * */

                    Platform.runLater(() -> {
                        //modifica los contactos en la tabla
                        tablaContacto.getItems().setAll(listaContactos);
                           });

                }else {
                    Mensaje mensaje=decifraMensaje(m);

                    /**
                     * AQUI
                     * daba error por llamar a un obserbable list de java fx en un un hilo externo
                     *  El uso de Platform.runLater(...)retrasos su modificación hasta que el evento actual se haya manejado por completo.
                     * */
                    Platform.runLater(() -> {
                        txtArea.appendText(mensaje.emisor+": "+mensaje.mensaje+"\n");
                    });

                    //Lo mostramos y lo añadimos a la lista de mensajes
                    listaMensajes.add(mensaje);
                    System.out.println("Mensaje recibido "+mensaje.getMensaje());

                }



            } catch (IOException e) {
                System.out.println("No se puede conectar con el servidor"+e.getMessage());
                repetir = false;
            }
        } // while

        try {
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNombre(){
        return nombre;
    }



    public Socket getSocket() {
        return socket;
    }

    public ArrayList<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    public ArrayList<Contacto> getListaContactos() {
        return listaContactos;
    }

    private Mensaje decifraMensaje(String m) {
        //receptor ,emisor, mensaje
        String[] extraer = m.split(";");


        Mensaje men= new Mensaje(extraer[0],extraer[1],extraer[2]);
        return men;
    }

}
