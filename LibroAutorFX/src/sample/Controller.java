package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //VENTANA  AUTORES
    @FXML Label lblID;

    @FXML
    TextField txtNombreAutor;
    @FXML
    TextField txtNacionalidad;
    @FXML
    ComboBox cbPais;
    @FXML
    Button btnAltaAutores;
    @FXML
    Button btnModificarAutor;
    @FXML
    Button btnEliminarAutor;

    @FXML
    TableView tbAutor;
    @FXML
    TableColumn colIDAutor;
    @FXML
    TableColumn colNombreAutor;
    @FXML
    TableColumn colNacionalidad;
    @FXML
    TableColumn colPais;


    // VENTANA LIBROS

    @FXML Label lblIDLibro;
    @FXML
    TextField txtTitulo;
    @FXML
    ComboBox cbAutor;
    @FXML
    TextField txtEditorial;

    @FXML
    Button btnAltaLibro;
    @FXML
    Button btnModificarLibro;
    @FXML
    Button btnEliminarLibro;

    @FXML
    TableView tbLibro;
    @FXML
    TableColumn colIDLibro;
    @FXML
    TableColumn colTitulo;
    @FXML
    TableColumn colEditorial;
    @FXML
    TableColumn colAutor;

    // VENTANA CONSULTA
    @FXML TextField txtBuscarLibro;
    @FXML Button btnBuscarConsulta;
    @FXML TableView tablaConsulta;
    @FXML TableColumn colIDConsulta;
    @FXML TableColumn colTituloConsulta;
    @FXML TableColumn colEditorialConsulta;
    @FXML TableColumn colAutorConsulta;

    LibroAutorDAO dao=new LibroAutorDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //PANEL AUTOR
        colIDAutor.setCellValueFactory(new PropertyValueFactory<Autor, Integer>("id"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<Autor, String>("nacionalidad"));
        colNombreAutor.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombre"));
        colPais.setCellValueFactory(new PropertyValueFactory<Autor, Pais>("pais"));
        //PANEL LIBRO
        colIDLibro.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Libro, Autor>("autor"));
        //PANEL CONSULTA
        colIDConsulta.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("id"));
        colTituloConsulta.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
        colEditorialConsulta.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
        colAutorConsulta.setCellValueFactory(new PropertyValueFactory<Libro, Autor>("autor"));

        listaTablaAutor();
        listaTablaLibro();

        cbAutor.getItems().addAll(dao.listaAutores());
        cbPais.getItems().addAll(dao.listaPaises());

    }
    public void listaTablaAutor(){
        //selecciona todos los datos de autores y los agrega a la tabla
        tbAutor.getItems().setAll(dao.listaAutores());

    }
    public void listaTablaLibro(){
        tbLibro.getItems().setAll(dao.listaLibros());
    }


    public void callbackAltaAutor(ActionEvent actionEvent){
        dao.guardarAutor(new Autor(txtNombreAutor.getText(), txtNacionalidad.getText(), (Pais) cbPais.getValue()));
        lblID.setText("_");
        txtNombreAutor.setText("");
        txtNacionalidad.setText("");
        cbPais.setValue("");
        listaTablaAutor();
    }
    public void callbackAltaLibro(ActionEvent actionEvent){
        dao.guardarLibro(new  Libro(txtTitulo.getText(),txtEditorial.getText(), (Autor) cbAutor.getValue()));
        lblIDLibro.setText("_");
        txtTitulo.setText("");
        txtEditorial.setText("");
        cbAutor.setValue("");

        listaTablaLibro();
    }

    public  void  callBackModificarTablaAutor(){
        Autor autor=new Autor(Integer.valueOf(lblID.getText()), txtNombreAutor.getText(), txtNacionalidad.getText(), (Pais) cbPais.getValue());
        dao.modificarAutor(autor);
       listaTablaAutor();
    }
    public  void  callBackModificarTablaLibro(){
        Libro libro=new Libro(Integer.valueOf(lblIDLibro.getText()), txtTitulo.getText(), txtEditorial.getText(), (Autor) cbAutor.getValue());
        dao.modificarLibro(libro);
        listaTablaLibro();
    }

    public void callbackClicTablaAutores(javafx.scene.input.MouseEvent mouseEvent) {
        Autor autor = (Autor) tbAutor.getSelectionModel().getSelectedItem();
        if (autor != null) {
            lblID.setText(String.valueOf(autor.getId()));
            txtNombreAutor.setText(autor.getNombre());
            txtNacionalidad.setText(autor.getNacionalidad());
            cbPais.setValue(autor.getPais());
        }else{
            System.out.println("inmueble es null");
        }
    }
    public void callbackClicTablaLibro(javafx.scene.input.MouseEvent mouseEvent) {
        Libro libro = (Libro) tbLibro.getSelectionModel().getSelectedItem();
        if (libro != null) {
            lblIDLibro.setText(String.valueOf(libro.getId()));
            txtTitulo.setText(libro.getTitulo());
            txtEditorial.setText(libro.getEditorial());
            cbAutor.setValue(libro.getAutor());
        }else{
            System.out.println("inmueble es null");
        }
    }

    public void callBackEliminaLibro(){
       Libro libro=new Libro(Integer.valueOf(lblIDLibro.getText()), txtTitulo.getText(), txtEditorial.getText(), (Autor) cbAutor.getValue());
        dao.eliminarLibro(libro);
        listaTablaLibro();
    }
    public void callBackEliminaAutor(){
        Autor autor=new Autor(Integer.valueOf(lblID.getText()), txtNombreAutor.getText(), txtNacionalidad.getText(), (Pais) cbPais.getValue());
        dao.eliminarAutor(autor);
        listaTablaAutor();
    }


    public void callBackrRecargaLibros(){
        //vacía el combo box
        cbAutor.getItems().clear();
        //Consulta y muestra los items del comboBox
        cbAutor.getItems().addAll(dao.listaAutores());
    }


    /**
     * Metodos ventana consulta
     * */
    public  void callBackConsultar(ActionEvent actionEvent){
        ArrayList<Libro> consultaLibros=new ArrayList<Libro>();
        consultaLibros=dao.consultaLibro(txtBuscarLibro.getText());
        tablaConsulta.getItems().setAll(consultaLibros);

    }


}
