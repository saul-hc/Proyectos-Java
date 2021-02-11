package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //VENTANA  Examen
    @FXML Label lblIDExamen;

    @FXML TextField txtNombreExamen;
    @FXML TextField txtMateria;

    @FXML Button btnAltaExamen;
    @FXML Button btnModificarExamen;
    @FXML Button btnEliminarExamen;

    @FXML
    TableView tablaExamen;
    @FXML
    TableColumn colIDExamen;
    @FXML
    TableColumn colNombreExamen;
    @FXML
    TableColumn colMateria;



    // VENTANA PREGUNTAS

    @FXML Label lblIDPregunta;
    @FXML TextField txtPregunta;
    @FXML TextField txtR1;
    @FXML TextField txtR2;
    @FXML TextField txtR3;
    @FXML TextField txtCorrecta;

    @FXML ComboBox comboExamen;

    @FXML Button btnAltaPregunta;
    @FXML Button btnModificarPregunta;
    @FXML Button btnEliminarPregunta;

    @FXML TableView tablaPreguntas;
    @FXML TableColumn colIDPreguntas;
    @FXML TableColumn colPregunta;
    @FXML TableColumn colR1;
    @FXML TableColumn colR2;
    @FXML TableColumn colR3;
    @FXML TableColumn colCorrecta;
    @FXML TableColumn colExamen;

    // VENTANA REALIZAR EXAMEN

    @FXML Label lblPreguntaExamenRealizar;
    @FXML RadioButton radio1;
    @FXML RadioButton radio2;
    @FXML RadioButton radio3;
    @FXML ComboBox comboElejirExamen;

    @FXML Label lblCorrecto;
    @FXML Label lblIncorrecta;

    @FXML Label lblContadorRespuestas;

    String correcta="";
    //Contador pregunta
    int numPregunta;
    ArrayList<Pregunta> consultaPreguntas;
    //Contador respuestas
    int buenas;
    int malas;


    ToggleGroup group;
   // ButtonGroup group;
    PreguntaExamenDAO dao=new PreguntaExamenDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //PANEL Examen
        colIDExamen.setCellValueFactory(new PropertyValueFactory<Examen, Integer>("id"));
        colNombreExamen.setCellValueFactory(new PropertyValueFactory<Examen, String>("nombre"));
        colMateria.setCellValueFactory(new PropertyValueFactory<Examen, String>("materia"));

        //PANEL Preguntas
        colIDPreguntas.setCellValueFactory(new PropertyValueFactory<Pregunta, Integer>("id"));
        colPregunta.setCellValueFactory(new PropertyValueFactory<Pregunta, String>("pregunta"));
        colR1.setCellValueFactory(new PropertyValueFactory<Pregunta, String>("r1"));
        colR2.setCellValueFactory(new PropertyValueFactory<Pregunta, String>("r2"));
        colR3.setCellValueFactory(new PropertyValueFactory<Pregunta, String>("r3"));
        colCorrecta.setCellValueFactory(new PropertyValueFactory<Pregunta, String>("correcta"));
        colExamen.setCellValueFactory(new PropertyValueFactory<Pregunta, Examen>("examen"));


        listaTablaExamen();
        listaTablaPregunta();

        //asignamos el contenido a los combo box
        comboExamen.getItems().addAll(dao.listaExamen());
        comboElejirExamen.getItems().addAll(dao.listaExamen());

        //Agrupamos los radio butons para que solo pueda ser seleccionado uno a la vez
        group = new ToggleGroup();
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);


    }

    public void listaTablaExamen(){
        //selecciona todos los datos de autores y los agrega a la tabla
        tablaExamen.getItems().setAll(dao.listaExamen());

    }
    public void listaTablaPregunta(){
        tablaPreguntas.getItems().setAll(dao.listaPreguntas());
    }

    /**
     * CREATE
     * */

    public void callbackAltaExamen(ActionEvent actionEvent){
        dao.guardarExamen(new Examen(txtNombreExamen.getText(),txtMateria.getText()  ));
        lblIDExamen.setText("_");
        txtNombreExamen.setText("");
        txtMateria.setText("");

        listaTablaExamen();
    }
    public void callbackAltaPregunta(ActionEvent actionEvent){
        //Pregunta(String enunciado, String r1, String r2, String r3, String correcta, Examen examen)
        dao.guardarPregunta(new Pregunta(txtPregunta.getText(),txtR1.getText(),txtR2.getText()
                ,txtR3.getText(),txtCorrecta.getText(), (Examen) comboExamen.getValue()));
        lblIDPregunta.setText("_");
        txtPregunta.setText("");
        txtR1.setText("");
        txtR2.setText("");
        txtR3.setText("");
        txtCorrecta.setText("");

        //comboExamen.setValue("Examen");

        listaTablaPregunta();
    }
    /**
     * UPDATE
     * */

    public  void  callBackModificarTablaExamen(){
        Examen examen=new Examen(Integer.valueOf(lblIDExamen.getText()),txtNombreExamen.getText(), txtMateria.getText());
        dao.modificarExamen(examen);
        lblIDExamen.setText("_");
        txtMateria.setText("");
        txtNombreExamen.setText("");
        listaTablaExamen();
    }
    public  void  callBackModificarTablaPregunta(){
        //Pregunta(String enunciado, String r1, String r2, String r3, String correcta, Examen examen)
        Pregunta pregunta=new Pregunta(Integer.valueOf(lblIDPregunta.getText()), txtPregunta.getText(),txtR1.getText(),txtR2.getText(),txtR3.getText(),
                txtCorrecta.getText(), (Examen) comboExamen.getValue());
        dao.modificarPregunta(pregunta);
        listaTablaPregunta();
    }
    /**
     * Selecciona un elemento de la tabla
     * */
    //mouse pressed
    public void callbackClicTablaExamen(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("sad");
        Examen examen = (Examen) tablaExamen.getSelectionModel().getSelectedItem();
        if (examen != null) {
            System.out.println("nada");
            lblIDExamen.setText(String.valueOf(examen.getId()));
            txtMateria.setText(examen.getMateria());
            txtNombreExamen.setText(examen.getNombre());

        }else{
            System.out.println("examen es null");
        }
    }

    public void callbackClicTablaPregunta(javafx.scene.input.MouseEvent mouseEvent) {
        Pregunta pregunta = (Pregunta)tablaPreguntas.getSelectionModel().getSelectedItem();
        if (pregunta != null) {
            lblIDPregunta.setText(String.valueOf(pregunta.getId()));
            txtPregunta.setText(pregunta.getPregunta());
            txtR1.setText(pregunta.getR1());
            txtR2.setText(pregunta.getR2());
            txtR3.setText(pregunta.getR3());
            txtCorrecta.setText(pregunta.getCorrecta());

            comboExamen.setValue(pregunta.getExamen());
        }else{
            System.out.println("inmueble es null");
        }
    }
    /**
     * DELETE
     * */
    public void callBackEliminaExamen(){
       Examen examen=new Examen(Integer.valueOf(lblIDExamen.getText()), txtNombreExamen.getText(), txtMateria.getText());
        dao.eliminarExamen(examen);
        lblIDExamen.setText("_");
        txtMateria.setText("");
        txtNombreExamen.setText("");
        listaTablaExamen();
    }
    public void callBackEliminaPregunta(){
        Pregunta pregunta=new Pregunta(Integer.valueOf(lblIDPregunta.getText()), txtPregunta.getText(),txtR1.getText(),txtR2.getText(),txtR3.getText(),
                txtCorrecta.getText(), (Examen) comboExamen.getValue());
      dao.eliminarPregunta(pregunta);
        listaTablaPregunta();
    }

    /**
     * Recarga los combo box al cambiar de página
     * */
    public void callBackRecargaExamen(){
        //vacía el combo box
        comboExamen.getItems().clear();
        //Consulta y muestra los items del comboBox
        comboExamen.getItems().addAll(dao.listaExamen());
    }
    public void callBackRecargaExamenRealizar(){
        //vacía el combo box
        comboElejirExamen.getItems().clear();
        //Consulta y muestra los items del comboBox
        comboElejirExamen.getItems().addAll(dao.listaExamen());
    }


    /**
     * Metodos ventana consulta
     * */
    public  void callBackConsultarPreguntas(ActionEvent actionEvent){



        Examen examen=(Examen)comboElejirExamen.getValue();
        //System.out.println(examen.getMateria()+""+examen.getNombre());
       consultaPreguntas=dao.consultaPreguntas(examen);

        if(consultaPreguntas.size()==0){
            JOptionPane.showMessageDialog(null, "El examen no tiene preguntas");
        }else{
            numPregunta=0;//inicializo el numero de pregunta

            vaciaCampos();
            responder(consultaPreguntas.get(numPregunta));
        }



    }
    public void responder(Pregunta p){

        lblPreguntaExamenRealizar.setText(p.getPregunta());
        radio1.setText(p.getR1());
        radio2.setText(p.getR2());
        radio3.setText(p.getR3());
        correcta=p.getCorrecta();
        numPregunta++;
    }

    public void responderExamen(){


        if(radio1.isSelected() && radio1.getText().equals(correcta) ){
            lblIncorrecta.setText("");
            lblCorrecto.setText("CORRECTO");
            buenas++;
        }else if(radio2.isSelected() && radio2.getText().equals(correcta) ){
            lblIncorrecta.setText("");
            lblCorrecto.setText("CORRECTO");
            buenas++;
        }else if(radio3.isSelected() && radio3.getText().equals(correcta) ){
            lblIncorrecta.setText("");
            lblCorrecto.setText("CORRECTO");
            buenas++;
        }else{
            lblCorrecto.setText("");
            lblIncorrecta.setText("INCORRECTO");
            malas++;
        }


        if(numPregunta<consultaPreguntas.size()){
            responder(consultaPreguntas.get(numPregunta));
        }else{

            JOptionPane.showMessageDialog(null, "El examen a finalizado correctamente \n " +
                    "Correctas:"+buenas+
                    "\nIncorrrectas"+malas+
                    "\nDe "+ (buenas+malas)+" preguntas");
        }



        lblContadorRespuestas.setText("Correctas: "+buenas+"                       Incorrectas:"+ malas  +"              Total: "+(buenas+malas));

    }

    public  void vaciaCampos(){
        lblPreguntaExamenRealizar.setText("");
        radio1.setText("");
        radio2.setText("");
        radio3.setText("");
        correcta="";
        lblCorrecto.setText("");
        lblIncorrecta.setText("");

    }


}

