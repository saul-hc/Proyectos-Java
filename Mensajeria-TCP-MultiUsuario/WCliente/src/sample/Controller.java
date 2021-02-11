package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private int puerto =7777;
    private String host= "localhost";
    private Socket s;
    private Cliente cliente;
    private String usuario="";
    private String usuarioDestino="";

    private static final String CODIGO_PEDIR_CONTACTOS="GATO777";

    @FXML
    TextField txtMensaje;
    @FXML TextField txtNombreMio;

    @FXML
    Button btnEnviarMensaje;
    @FXML
    Button btnEnviarNombre;
    @FXML
    TextArea txtArea;

    @FXML
    Label lblUsuario;
    @FXML
    Label lblContacto;
    @FXML
    TableColumn colContactos;
    @FXML
    TableView<Contacto> tablaContactos;

    @FXML
    AnchorPane panel1;
    @FXML
    AnchorPane panel2;
    @FXML
            AnchorPane panelPrincipal;


    ArrayList<Mensaje> listaM;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtArea.setText("");
        txtArea.setEditable(false);
        

        colContactos.setCellValueFactory(new PropertyValueFactory<Contacto,String>("nombre"));




    }

    /**
     * Inicia el usuario con el que se quiere conectar
     * */
    public void inicia(){

        try {
            s= new Socket(host,puerto);
            //Inicia el usuario y se pone a la escucha de nuevos mensajes
            cliente = new Cliente(s,txtNombreMio.getText(),txtArea,tablaContactos);
            new Thread(cliente).start();
           usuario=txtNombreMio.getText();

            lblUsuario.setText(" Nombre: "+usuario);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                    "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
        }
        panel1.setVisible(false);
        panel2.setVisible(true);
    }

    public void EnviarMensaje(){

        if (usuarioDestino.equals("")){
            JOptionPane.showMessageDialog(null, "No selecciono ningun Contacto");

        }else{
            try {
                Mensaje msj= new Mensaje(usuarioDestino,usuario,txtMensaje.getText());
                // System.out.println(msj.getReceptor() +" "+msj.getEmisor()+"  "+msj.getMensaje());
                cliente.getSalida().writeUTF(msj.toStringDatosMensaje());
                System.out.println("Mensaje enviado "+msj.getMensaje());
                //Agregamos a la lista de mensajes y al textArea
                cliente.getListaMensajes().add(msj);
                txtArea.appendText(msj.getEmisor()+": "+msj.getMensaje()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Restablece el texto del mensaje y le debuelve el foco
        txtMensaje.setText("");
        txtMensaje.requestFocus();

    }
    public void pideContactos(){

        try {

            Mensaje msj= new Mensaje(usuarioDestino,usuario,CODIGO_PEDIR_CONTACTOS);
           cliente.getSalida().writeUTF(msj.toStringDatosMensaje());

            System.out.println("Pedido Contactos realizado con exito "+msj.getMensaje());

        } catch (IOException e) {
            e.printStackTrace();
        }



    }



    /**
     * Selecciona un Contacto de la tabla de la tabla
     * */
    //mouse pressed
    public void callbackClicTablaContactos(javafx.scene.input.MouseEvent mouseEvent) {
        //Guardamos la infomacion del contaxto existente
        Contacto c = (Contacto) tablaContactos.getSelectionModel().getSelectedItem();
        //carga todos los mensajes que tenemos con ese usuario
        cargaListaMensajes(c.getNombre());
        if (c != null) {

            usuarioDestino=c.getNombre();
            lblContacto.setText(usuarioDestino);

        }else{
            System.out.println("examen es null");
        }
    }

    public void cargaListaMensajes(String nombre){
        txtArea.setText("");
        listaM= cliente.getListaMensajes();
        for (Mensaje m:listaM){
            if(m.getEmisor().equals(nombre)||m.getReceptor().equals(nombre)){
                txtArea.appendText(m.emisor+": "+m.mensaje+"\n");
            }
        }

    }





}
