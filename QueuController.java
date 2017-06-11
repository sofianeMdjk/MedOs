/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.GroupLayout;

/**
 * FXML Controller class
 *
 * @author sofiane
 */
public class QueuController extends MyScene implements Initializable {

    @FXML
    private JFXListView<Infos> listView;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton nextButton;
    static Queue queue;
    @FXML
    private ImageView imageView;
   
    @FXML
    private JFXButton currentPatientButton;
    @FXML
    private ImageView changeSceneID;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
        
        final QueuController qc = this;
        queue = new Queue(Doctor.id);
      queue.loadQueue();
        updateQueu(listTOArray(queue.elements),queue.currentElement);
        
        listView.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                
                @Override
                public void handle(MouseEvent click) 
                {
                    
                  Infos currentItemSelected = listView.getSelectionModel().getSelectedItem();
                  if(currentItemSelected.isHere())
                  {
                       
                      Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                      Pane root = null;
                    try {
                     root = fxmlLoader.load(
                    QueuController.class.getResource("DeleteAvailable.fxml").openStream());
                    } catch (IOException ex) {
                     Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();
                    DeleteAvailableController pdc = fxmlLoader.getController();
                    pdc.setQueuElement((QueueElement)currentItemSelected);
                    pdc.setQueueCon(qc);
                      return;
                  }
                    
                    String tab[]=Patient.getPat(((QueueElement)currentItemSelected).patID);
                     Patient patient=new Patient();
                     patient.setPatientInfo(tab);
                     PatientInfosController pi = new PatientInfosController(patient);
                     Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                Pane root = null;
        try {
            root = fxmlLoader.load(
                    QueuController.class.getResource("PatientDetails.fxml").openStream());
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();
        PatientDetailsController pdc = fxmlLoader.getController();
        pdc.setPatient(patient);
        pdc.setQueuElement((QueueElement)currentItemSelected);
        pdc.setQueueCon(qc);
                }
                }
        );
        
        
        Thread t = new Thread(new Runnable() {
          @Override
          public void run() 
          {
              while(true)
              {
                  boolean bool = queue.updateElements();
                  if(bool)
                  Platform.runLater(new Runnable() {
                      @Override
                      public void run() {
                          System.out.println(".run()111");
                        updateQueu(listTOArray(queue.elements),queue.currentElement);
                      }
                  });
                  
                  try {
                      Thread.sleep(5000);
                  } catch (InterruptedException ex) {
                      Logger.getLogger(QueuController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }
      });
        t.start();
    } 
    
    public Infos[] listTOArray(LinkedList<?> list)
    {
        Infos tab[]=new Infos[list.size()];
        for(int i=0;i<list.size();i++)
        {
            tab[i]=(Infos)list.get(i);
        }
        return tab;
    }

    @Override
    Scene getScene() {
          FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = null;
        try {
            root = fxmlLoader.load(
                    MainApp.getInstance().getClass().getResource("Queu.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        setParent(root);
        return scene;
    }
     

    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        if(event.getSource()==addButton)
        {
            queue.arrivedAtPlace(new QueueElement());
            updateQueu(listTOArray(queue.elements), queue.currentElement);
        }
        else if(event.getSource()==nextButton)
        {
            queue.passNextElement();
            updateQueu(listTOArray(queue.elements), queue.currentElement);
            
        }
        else
        {
            
            updateQueu(listTOArray(queue.elements), queue.currentElement);
        }
    }
    
    public void updateQueu(Infos []list,QueueElement current)
    {
        if(listView!=null)
        listView.getItems().clear();
       
        int size=list.length;
        for(int i=0;i<size;i++)
        {
            listView.getItems().add(list[i]);
        }
        if(current!=null)
        currentPatientButton.setText(current.getInfo());
        
    }

    @FXML
    private void handleMouse(MouseEvent event) {
        Doctor.id="failure";
        MainApp.getInstance().myWin.nextScene(new MainWindowController());
    }
    
}
