/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sofiane
 */
public class PatientDetailsController extends MyScene implements Initializable {

    @FXML
    private Text familyText;
    @FXML
    private Text firstText;
    @FXML
    private Text birthdateText;
    @FXML
    private Text emailText;
    @FXML
    private Text phoneText;

    Patient patient;
    @FXML
    private JFXButton confirmButton;
    @FXML
    private JFXButton deleteButton;
    
    QueueElement QElement;
    QueuController qc;
    
    void setQueuElement(QueueElement q)
    {
        QElement=q;
    }
    
    
    void setPatient(Patient p)
    {
        patient = p;
        familyText.setText(familyText.getText().concat(p.fName));
        firstText.setText(firstText.getText().concat(p.sName));
        birthdateText.setText(birthdateText.getText().concat(p.birthDate));
        phoneText.setText(phoneText.getText().concat(p.phoneNumber));
        emailText.setText(emailText.getText().concat(p.email));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            confirmButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    QueuController.queue.arrivedAtPlace(QElement);
                    if(qc != null)
                        qc.updateQueu(qc.listTOArray(QueuController.queue.elements), QueuController.queue.currentElement);
                    Stage s =(Stage)confirmButton.getScene().getWindow();
                    s.close();
                }
            });
            
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) 
                {
                    QueuController.queue.removeFromQueue(QElement);
                    if(qc != null)
                    qc.updateQueu(qc.listTOArray(QueuController.queue.elements), QueuController.queue.currentElement);
                    Stage s2;
                    s2 = (Stage)confirmButton.getScene().getWindow();
                    s2.close();
                }
            });
            
    }  

    @Override
    Scene getScene() {
         FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = null;
        try {
            root = fxmlLoader.load(
                    MainApp.getInstance().getClass().getResource("PatientDetails.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        setParent(root);
        return scene;
    }

    void setQueueCon(QueuController aThis) 
    {
        qc = aThis;
    }
    
}
