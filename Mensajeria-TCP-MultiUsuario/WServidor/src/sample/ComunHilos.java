package sample;

import java.net.Socket;
import java.util.ArrayList;

public class ComunHilos {
    int conexiones; //Conexiones ocupadas en el array
    int actuales;   //Conexiones actuales
    int maximo;     //Máximo de conexiones permitidas
    Socket tabla[] = new Socket[maximo];// Sockets de clientes conectados
    ArrayList<Usuario> listaUsuarios=new ArrayList<Usuario>();
    ArrayList<Mensaje> listaMensajes =new ArrayList<Mensaje>();

    public ComunHilos(int maximo, int actuales, int conexiones, Socket[] tabla) {
        this.maximo = maximo;
        this.actuales = actuales;
        this.conexiones = conexiones;
        this.tabla = tabla;
    }

    public void addUsuario(Usuario usuario){
        listaUsuarios.add(usuario);
    }

    //Recorre la lista buscando un usuario por nombre y devuelve el usuario
    public Usuario getUsuario(String nombre){
        int i=0;
        while(i<listaUsuarios.size() && !listaUsuarios.get(i).getNombre().equals(nombre)){
            i++;
        }
        Usuario usuario=(i<listaUsuarios.size())? listaUsuarios.get(i) : null;
        return usuario;
    }

    public void addMensaje(Mensaje mensaje){
        listaMensajes.add(mensaje);
    }

    public int getMaximo() { return maximo;	}
    public void setMaximo(int maximo) { this.maximo = maximo;}

    public int getConexiones() { return conexiones;	}
    public synchronized void setConexiones(int conexiones) {
        this.conexiones = conexiones;
    }

    public int getActuales() { return actuales; }
    public synchronized void setActuales(int actuales) {
        this.actuales = actuales;
    }

    public synchronized void addTabla(Socket s, int i) {
        tabla[i] = s;
    }
    public Socket getElementoTabla(int i) { return tabla[i]; }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<Mensaje> getListaMensajes() {
        return listaMensajes;
    }
}
