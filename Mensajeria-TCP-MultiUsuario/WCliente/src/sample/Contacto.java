package sample;


import javafx.scene.control.TextArea;

public class Contacto {


  private String nombre;
  private TextArea area;

    public Contacto(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TextArea getArea() {
        return area;
    }

    public void setArea(TextArea area) {
        this.area = area;
    }
}


