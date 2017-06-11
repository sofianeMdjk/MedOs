/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author sofiane
 */
public class MainWindowController extends MyScene implements Initializable{

    @FXML
    private JFXTextField usernameFIeld;
    @FXML
    private JFXPasswordField passwordField;
    private JFXButton signInButton;
    @FXML
    private JFXButton signupButton;
    @FXML
    private AnchorPane signUpButton;
    @FXML
    private ImageView confirmImg;
    @FXML
    private Label wrongInfoLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleAction(ActionEvent event) {
        if(event.getSource()==signupButton)
        {
            MainApp.getInstance().myWin.switchScene(new DoctorSignUpController());
        }
        
    }

    @Override
    Scene getScene() {
         FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = null;
        try {
            root = fxmlLoader.load(
                    MainApp.getInstance().getClass().getResource("MainWindow.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        setParent(root);
        return scene;
    }

    @FXML
    private void handleMouse(MouseEvent event) {
        
            if(usernameFIeld.getText()==""||passwordField.getText()==null)
            {
                //display an output
                System.out.println("you can't sorry");
            }
            else
            {
            String idDoc=Doctor.signIn(usernameFIeld.getText(), passwordField.getText());
            if(idDoc.equals("failure"))
            {
                
                wrongInfoLabel.setText("Wrong email or password");
                return;
            }
            Doctor.id=idDoc;
            
            Doctor.getDoc(idDoc);
            MainApp.getInstance().myWin.nextScene(new QueuController());
            }
    }
    
}
