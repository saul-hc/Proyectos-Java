package sample;

import java.io.Serializable;
import java.sql.Timestamp;

public class Mensaje implements Serializable {
    String receptor;
    String emisor;
    String mensaje;
    Timestamp fecha;
    long momento;

    public Mensaje(){

    }
    public Mensaje(String receptor, String emisor, String mensaje){
        this.receptor=receptor;
        this.emisor=emisor;
        this.mensaje=mensaje;
       // this.fecha=new Timestamp(0);
       // this.momento=this.fecha.getTime();
    }

    public void setMomento(){
        momento=fecha.getTime();
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String toString(){
        return "Emisor: " + emisor + ", Receptor: " + receptor + ", Contenido: " + mensaje;
    }

    public String toStringDatosMensaje(){
        return receptor+";"+emisor+";"+mensaje;
    }
}

