package sample;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JTextField;
import sample.MyScene;

/**
 * FXML Controller class
 *
 * @author sofiane
 */
public class ModifyDocInfosController extends MyScene implements Initializable {

    @FXML
    private JFXButton passwordButton;
    @FXML
    private JFXButton infosButtons;
    
    JFXTextField oldPass;
    JFXTextField newPass;
    JFXTextField confirmPass;
    
    JFXTextField lname;
    JFXTextField fname;
    JFXTextField adress;
    JFXTextField description;
    JFXTextField phone;
    
    
    
    @FXML
    private GridPane gridBox;
    @FXML
    private JFXButton confirmButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()==passwordButton)
        {
            
            oldPass=new JFXTextField();
            oldPass.setPromptText("Old password");
            oldPass.setPrefWidth(471);
            oldPass.setPrefHeight(32);
            gridBox.add(oldPass, 0, 0);
            
            
            newPass=new JFXTextField();
            newPass.setPromptText("new password");
            newPass.setPrefWidth(471);
            newPass.setPrefHeight(32);
            gridBox.add(newPass, 0, 1);
            
            
            
            confirmPass=new JFXTextField();
            confirmPass.setPromptText("Confrim password");
            confirmPass.setPrefWidth(471);
            confirmPass.setPrefHeight(32);
            gridBox.add(confirmPass, 0, 2);
        }
        else if(event.getSource()==infosButtons)
        {
            String tab[]=Doctor.getDoc("1");
            
           lname=new JFXTextField();
           lname.setPromptText(tab[1]);
           lname.setPrefWidth(471);
           lname.setPrefHeight(32);
           gridBox.add(lname, 0, 0);
           
           
           fname=new JFXTextField();
           fname.setPromptText(tab[2]);
           fname.setPrefWidth(471);
           fname.setPrefHeight(32);
           gridBox.add(fname, 0, 1);
           
           adress=new JFXTextField();
           adress.setPromptText(tab[9]);
           adress.setPrefWidth(471);
           adress.setPrefHeight(32);
           gridBox.add(adress, 0, 2);
           
           description= new JFXTextField();
           description.setPromptText(tab[11]);
           description.setPrefWidth(471);
           description.setPrefHeight(32);
           gridBox.add(description, 0, 3);
           
           phone=new JFXTextField();
           phone.setPromptText(tab[5]);
           phone.setPrefWidth(471);
           phone.setPrefHeight(32);
           gridBox.add(phone, 0, 4);
           
           
        }
        else if(event.getSource()==confirmButton)
        {
            
        }
    }

    @Override
    Scene getScene() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = null;
        try {
            root = fxmlLoader.load(
                    MainApp.getInstance().getClass().getResource("ModifyDocInfos.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        Scene scene = new Scene(root);
        setParent(root);
        return scene;
    }
    
}