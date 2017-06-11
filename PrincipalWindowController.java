/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author sofiane
 */
public class PrincipalWindowController extends MyScene implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    Scene getScene() {
            FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = null;
        try {
            root = fxmlLoader.load(
                    MainApp.getInstance().getClass().getResource("PrincipalWIndow.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        setParent(root);
        return scene;
        
    }
    
}
