/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.Doctor.GoogleMap;

/**
 * FXML Controller class
 *
 * @author sofiane
 */
public class DoctorSignUpController extends MyScene implements Initializable {

    @FXML
    private AnchorPane signUpButton;
    @FXML
    private JFXTextField familyNameFIeld;
    @FXML
    private JFXTextField lastNameField;
    @FXML
    private JFXTextField plaqueField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField adressField;
    @FXML
    private JFXButton phoneNumbersButton;
    private JFXButton profilePicButton;
    @FXML
    private JFXTextField usernameFIeld;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXPasswordField confirmePasswordFIeld;
    private JFXButton confirmButton;
    @FXML
    private JFXButton mapButton;
    LinkedList<String> phones=new LinkedList<>();
    @FXML
    private JFXRadioButton maleRadio;
    @FXML
    private JFXRadioButton femaleRadio;
    GoogleMap temp=null; 
    int gender;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private JFXTextField descriptionField;
    
    LinkedList<String> list;
    @FXML
    private ImageView confirmImg;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list=new LinkedList<>();
        list.add("Dentist");
        list.add("Ophtalmologist");
        list.add("Surgian");
        ObservableList obList = FXCollections.observableList(list);
        choiceBox.getItems().setAll(list);
    }    
    
    
    @FXML
    private void handleAction(ActionEvent event)
    {
     if(event.getSource()==phoneNumbersButton)
     {
         TextInputDialog phoneDialog =new TextInputDialog("xxxxxxxxxx");
         phoneDialog.setTitle("Phone numbers");
         Optional <String> textIn= phoneDialog.showAndWait();
         if(textIn.isPresent())
         {
             phones.add(textIn.get());
         }
     }
     else if(event.getSource()==mapButton)
     {
         try {
             MainApp.openWindow("Maps", new GoogleMapsDisplayController());
            // MainApp.getInstance().myWin.switchScene(new GoogleMapsDisplayController());
         } catch (Exception ex) {
             Logger.getLogger(DoctorSignUpController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
    
     }
    }
    
    
    @Override
    Scene getScene() {
      FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = null;
        try {
            root = fxmlLoader.load(
                    MainApp.getInstance().getClass().getResource("DoctorSignUp.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        Scene scene = new Scene(root);
        setParent(root);
        return scene;
    }
    
    @FXML
    private void isSet(ActionEvent event) {
        if(event.getSource()==maleRadio)
        {
            maleRadio.setSelected(true);
            femaleRadio.setSelected(false);
            gender=0;
        }
        else if(event.getSource()==femaleRadio)
        {
            
            femaleRadio.setSelected(true);
            maleRadio.setSelected(false);
            gender=1;
        }
    }

    @FXML
    private void mouseClick(MouseEvent event) {
           if(passwordField.getText().compareTo(confirmePasswordFIeld.getText())==0)
         {
             //i create my doc
             
             Doctor d=new Doctor(familyNameFIeld.getText(),
                                 lastNameField.getText(),
                                 plaqueField.getText(),
                                 adressField.getText(),
                                 emailField.getText(),
                                 phones,
                                 gender,
                                 passwordField.getText(),
                                 temp,
                                descriptionField.getText(),
                                choiceBox.getTypeSelector()
                                 );
             System.out.println("Doctor fully created");
             String id=d.signUp();
             Doctor.id=Doctor.signIn(emailField.getText(), passwordField.getText());
             MainApp.getInstance().myWin.nextScene(new QueuController());
     }
    }
    
}
