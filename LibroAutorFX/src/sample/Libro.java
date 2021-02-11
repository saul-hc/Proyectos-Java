package sample;

import javax.persistence.*;


@Entity
@Table(name="libros")
public class Libro{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "titulo",nullable = false)
    private String titulo;
    @Column(name="editorial",nullable = false)
    private String editorial;

    @JoinColumn(name = "FK_autor", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    //@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    public Libro(){

    }
    public  Libro(String titulo,String editorial,Autor autor){
        this.titulo=titulo;
        this.editorial=editorial;
        this.autor=autor;
    }
    public  Libro(int id,String titulo,String editorial,Autor autor){
        this.id=id;
        this.titulo=titulo;
        this.editorial=editorial;
        this.autor=autor;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
