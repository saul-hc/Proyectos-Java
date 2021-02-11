package sample;

import javax.persistence.*;

@Entity
@Table(name="preguntas")
public class Pregunta {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id ;
    @Column(name = "pregunta",nullable = false)
    String pregunta;
    @Column(name = "r1",nullable = false)
    String r1;
    @Column(name = "r2",nullable = false)
    String r2;
    @Column(name = "r3",nullable = false)
    String r3;
    @Column(name = "correcta",nullable = false)
    String correcta;

    @JoinColumn(name = "FK_examen", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    Examen examen;
    /**
     * constructor
     * */
    public  Pregunta(){

    }


    public Pregunta(String pregunta, String r1, String r2, String r3, String correcta, Examen examen){
        this.pregunta=pregunta;
        this.r1=r1;
        this.r2=r2;
        this.r3=r3;
        this.correcta=correcta;
        this.examen=examen;
    }
    public Pregunta(int id,String pregunta, String r1, String r2, String r3, String correcta, Examen examen){
        this.id=id;
        this.pregunta=pregunta;
        this.r1=r1;
        this.r2=r2;
        this.r3=r3;
        this.correcta=correcta;
        this.examen=examen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * GETTERS AND SETTERS
     * */


    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }


}
