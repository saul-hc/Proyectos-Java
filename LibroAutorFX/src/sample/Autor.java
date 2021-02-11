package sample;
import javax.persistence.*;


@Entity
@Table(name="autores")
public class Autor{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="nombre",nullable = false)
    private String nombre;

    @Column(name="nacionalidad",nullable = true)
    private String nacionalidad;

    @JoinColumn(name = "FK_pais", nullable = false)//muchos autores pueden tener 1 pais
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    //@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pais pais;


    public Autor(){

    }
    public Autor(String nombre ,String nacional,Pais pais){
        this.nombre=nombre;
        this.nacionalidad=nacional;
        this.pais=pais;
    }
    public Autor(int id,String nombre ,String nacional,Pais pais){
        this.id=id;
        this.nombre=nombre;
        this.nacionalidad=nacional;
        this.pais=pais;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    /**
     * To string necesario
     * */
    public String toString(){
        return nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
