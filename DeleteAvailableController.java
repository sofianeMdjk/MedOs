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
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sofiane
 */
public class DeleteAvailableController extends MyScene implements Initializable {

    @FXML
    private JFXButton patientButton;
    @FXML
    private JFXButton deleteButton;
    
    QueueElement qel;
    QueuController qc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(patientButton==null)
        System.out.println("Error");
        
            //patientButton.setText("Patient : "+qel.ID);
            
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 QueuController.queue.removeFromQueue(qel);
                    if(qc != null)
                    qc.updateQueu(qc.listTOArray(QueuController.queue.elements), QueuController.queue.currentElement);
                    Stage s2;
                    s2 = (Stage)patientButton.getScene().getWindow();
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
                    MainApp.getInstance().getClass().getResource("DeleteAvailable.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        Scene scene = new Scene(root);
        setParent(root);
        return scene;
    }

    void setQueuElement(QueueElement queueElement) {
       this.qel=queueElement;
    }

    void setQueueCon(QueuController qc) {
        this.qc=qc;
    }
    
}
