package sample;

import javax.persistence.*;

@Entity
@Table(name="examenes")
public class Examen {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "nombre",nullable = false)
    String nombre;
    @Column(name = "materia",nullable = false)
    String materia;


    /**
     * constructor
     * */
    public Examen(){

    }

    public Examen(String nombre,String materia){
        this.nombre=nombre;
        this.materia=materia;

    }
    public Examen(int id,String nombre,String materia){
        this.id=id;
        this.nombre=nombre;
        this.materia=materia;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * GETTERS AND SETTERS
     * */


    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
    /*
     * Metodos
     * */

    public String toString(){
        return nombre;
    }
}
