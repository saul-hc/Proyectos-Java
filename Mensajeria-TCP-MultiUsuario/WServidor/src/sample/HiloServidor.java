package sample;

import javafx.application.Platform;
import javafx.scene.control.TableView;

import java.io.*;
import java.net.Socket;

public class HiloServidor extends Thread{
    Socket socket;
    ComunHilos comun;
    DataInputStream entrada;
    DataOutputStream salida;
    InputStream input;
    ObjectInputStream entradaMensajes;
    ObjectOutputStream salidaMensajes;
    String nombreCliente;
    Usuario usuario;


    Socket s1;
    OutputStream out;
    ObjectOutputStream flujoSalida;

    TableView<Mensaje> tablaMensajes;

    private static final String CODIGO_ENVIAR_CONTACTOS="GATO777";

    public HiloServidor(Socket socket, ComunHilos comun,TableView<Mensaje> tablaMensajes){
        this.socket=socket;
        this.comun=comun;
       this.tablaMensajes=tablaMensajes;
        try {
            entrada=new DataInputStream(socket.getInputStream());
            salida=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        //Recibir el nombre del cliente
        try {
            nombreCliente = entrada.readUTF();
            System.out.println("Nombre cliente:" + nombreCliente);
        }catch(IOException io){
            io.printStackTrace();
        }
        //Creo un usuario con los datos que me han pasado
        usuario=new Usuario(nombreCliente, socket);
        //agrega el usuario a comun
        comun.addUsuario(usuario);


        //Bucle para recibir mensajes del cliente
        while(true){
            try {
                System.out.println("-----------------------------");
                //Lee el mensaje del cliente con el que está conectado
                Mensaje mensaje=null;

                String m= entrada.readUTF();

                System.out.println("flujo recibido");
                mensaje=decifraMensaje(m);

                if(CODIGO_ENVIAR_CONTACTOS.equals(mensaje.getMensaje())){

                    System.out.println("Mensaje recibido en hilo servidor: " + mensaje.toString());
                    String emisor=mensaje.getEmisor();

                    //Consigo el usuario al que hay que enviarlo y su socket y abro stream
                    Usuario destino=comun.getUsuario(emisor);
                    System.out.println("Usuario destino es: "+destino.getNombre());

                    Socket s1=destino.getSocket();
                    DataOutputStream out= new DataOutputStream(s1.getOutputStream());

                    //Envío el mensaje
                    String listaNombres="";
                    for(Usuario u: comun.getListaUsuarios()){
                        listaNombres+=u.getNombre()+",";
                    }
                    System.out.println("La lista de contactos es"+listaNombres);
                    out.writeUTF(CODIGO_ENVIAR_CONTACTOS);
                    out.writeUTF(listaNombres);
                    out.flush();
                }else{


                  /**
                   * Agregamos el emnsaje a la tabla de mensajes FX
                   * */
                  comun.addMensaje(mensaje);

                  /**
                   * AQUI
                   * daba error por llamar a un obserbable list de java fx en un un hilo externo
                   *  El uso de Platform.runLater(...)retrasa su modificación hasta que el evento actual se haya manejado por completo.
                   * */
                  Platform.runLater(() -> {
                      tablaMensajes.getItems().setAll(comun.getListaMensajes());
                  });


                    System.out.println("Mensaje recibido en hilo servidor: " + mensaje.toString());
                    String receptor=mensaje.getReceptor();

                    //Consigo el usuario al que hay que enviarlo y su socket y abro stream
                    Usuario destino=comun.getUsuario(receptor);
                    System.out.println("Usuario destino es: "+destino.getNombre());

                    Socket s1=destino.getSocket();
                    DataOutputStream out= new DataOutputStream(s1.getOutputStream());

                    //Envío el mensaje
                    out.writeUTF(mensaje.toStringDatosMensaje());
                    out.flush();
                }





            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }

    private Mensaje decifraMensaje(String m) {
        //receptor ,emisor, mensaje
        String[] extraer = m.split(";");


        Mensaje men= new Mensaje(extraer[0],extraer[1],extraer[2]);
        return men;
    }

}
