package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Button btnIniciaServidor;

    @FXML
    TableView<Mensaje> tablaMensaje;
    @FXML
    TableColumn colEmisor;
    @FXML
    TableColumn colReceptor;
    @FXML
    TableColumn colMensaje;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colEmisor.setCellValueFactory(new PropertyValueFactory<Mensaje,String>("emisor"));
        colReceptor.setCellValueFactory(new PropertyValueFactory<Mensaje,String>("receptor"));
        colMensaje.setCellValueFactory(new PropertyValueFactory<Mensaje,String>("mensaje"));
    }
    public void iniciaServidor(){
        try {
            Servidor s= new Servidor(tablaMensaje);
            Thread hilo=new Thread(s);
            hilo.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
