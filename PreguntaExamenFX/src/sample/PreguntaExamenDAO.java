package sample;

import org.hibernate.Session;

import java.util.ArrayList;

public class PreguntaExamenDAO {

    public PreguntaExamenDAO(){

    }
    /**
     * CREATE
     * */
    public void guardarExamen(Examen examen){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(examen);
        s.getTransaction().commit();
        s.close();
    }
    public void guardarPregunta(Pregunta pregunta){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(pregunta);
        s.getTransaction().commit();
        s.close();
    }
    /**
     * read
     * */
    public ArrayList<Pregunta> listaPreguntas(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();                           //consulta a la clase //todos los objetos de la clase
        ArrayList<Pregunta> preguntas=(ArrayList<Pregunta>)s.createQuery("from Pregunta").list();
        //s.persist(autores);
        s.getTransaction().commit();
        s.close();
        return preguntas;
    }

    public ArrayList<Examen> listaExamen(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();                           //consulta a la clase //todos los objetos de la clase
        ArrayList<Examen> examenes=(ArrayList<Examen>)s.createQuery("from Examen").list();

        s.getTransaction().commit();
        s.close();
        return examenes;
    }

    /**
     * UPDATE
     * */
    public void modificarExamen(Examen examen){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(examen);
        s.getTransaction().commit();
        s.close();
    }
    public void modificarPregunta(Pregunta pregunta){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(pregunta);
        s.getTransaction().commit();
        s.close();
    }
    /**
     * DELETE
     * */
    public void eliminarExamen(Examen examen){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.delete(examen);
        s.getTransaction().commit();
        s.close();
    }
    public void eliminarPregunta(Pregunta pregunta){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.delete(pregunta);
        s.getTransaction().commit();
        s.close();
    }

    /**
     * Consulta Buscar
     * segun materia
     * */
    public ArrayList<Pregunta> consultaPreguntas(Examen examen){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        //consulta a la clase //todos los objetos de la clase
        ArrayList<Pregunta> preguntas=(ArrayList<Pregunta>)s.createQuery("from Pregunta where examen = :miexamen")
                .setParameter("miexamen",examen).list();

        s.getTransaction().commit();
        s.close();
        return preguntas;
    }

}
