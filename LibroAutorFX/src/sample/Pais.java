package sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paises")

public class Pais {
    @Id
    @Column(name="id")
    private int id;
    @Column(name = "iso",nullable = false)
    private String iso;
    @Column(name = "nombre",nullable = false)
    private String nombre;

    public Pais(){

    }
    /**
     * TO STRING
     * */
    public String toString(){
        return nombre+"("+iso+")";
    }

    /**
     * GETTERS AND SETTERS
     * */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
