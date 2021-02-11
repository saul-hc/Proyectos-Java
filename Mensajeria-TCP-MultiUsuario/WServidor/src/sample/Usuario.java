package sample;

import java.net.Socket;

public class Usuario {
    String nombre;
    Socket socket;

    public Usuario(String nombre, Socket socket){
        this.nombre=nombre;
        this.socket=socket;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
