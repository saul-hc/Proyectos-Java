package sample;

import org.hibernate.Session;

import java.util.ArrayList;

public class LibroAutorDAO {
    public LibroAutorDAO(){

    }
    /**
     * CREATE
     * */
    public void guardarAutor(Autor autor){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(autor);
        s.getTransaction().commit();
        s.close();
    }
    public void guardarLibro(Libro libro){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(libro);
        s.getTransaction().commit();
        s.close();
    }
    /**
     * read
     * */
    public ArrayList<Autor> listaAutores(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();                           //consulta a la clase //todos los objetos de la clase
        ArrayList<Autor> autores=(ArrayList<Autor>)s.createQuery("from Autor").list();
        //s.persist(autores);
        s.getTransaction().commit();
        s.close();
        return autores;
    }

    public ArrayList<Libro> listaLibros(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();                           //consulta a la clase //todos los objetos de la clase
        ArrayList<Libro> libros=(ArrayList<Libro>)s.createQuery("from Libro").list();

        s.getTransaction().commit();
        s.close();
        return libros;
    }
    public ArrayList<Pais> listaPaises(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();                           //consulta a la clase //todos los objetos de la clase
        ArrayList<Pais> paises=(ArrayList<Pais>)s.createQuery("from Pais").list();
        //s.persist(autores);
        s.getTransaction().commit();
        s.close();
        return paises;
    }
    /**
     * UPDATE
     * */
    public void modificarAutor(Autor autor){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(autor);
        s.getTransaction().commit();
        s.close();
    }
    public void modificarLibro(Libro libro){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(libro);
        s.getTransaction().commit();
        s.close();
    }
    /**
     * DELETE
     * */
    public void eliminarLibro(Libro libro){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.delete(libro);
        s.getTransaction().commit();
        s.close();
    }
    public void eliminarAutor(Autor autor){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.delete(autor);
        s.getTransaction().commit();
        s.close();
    }

    /**
     * Consulta Buscar
     * */
    public ArrayList<Libro> consultaLibro(String consulta){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        //consulta a la clase //todos los objetos de la clase
        ArrayList<Libro> libros=(ArrayList<Libro>)s.createQuery("from Libro where titulo like :titulolibro")
                .setParameter("titulolibro","%"+consulta+"%").list();

        s.getTransaction().commit();
        s.close();
        return libros;
    }

}
