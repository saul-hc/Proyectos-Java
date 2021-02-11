package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
       HibernateUtil.inicia();
       /* Autor autor=new Autor("Elli Felipe","Desconocida");

        Libro libro=new Libro();
        libro.setTitulo("Gatitos Felices");
        libro.setEditorial("Bosco");
        libro.setAutor(autor);

        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.persist(autor);
        s.getTransaction().commit();
        s.close();*/


        launch(args);
    }
}
