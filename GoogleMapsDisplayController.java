/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sofiane
 */
public class GoogleMapsDisplayController extends MyScene implements Initializable {

     Scene s;
     static String idLoc;
    
    
    @FXML
    private void handleMouseClick(MouseEvent event) {
      Stage stage=(Stage)  anchor.getScene().getWindow();
      String urlCor="http://192.168.8.105/medos/localisation/patient.php";
      idLoc=QueryUtils.makeHttpPostRequest(urlCor, new HashMap<>());
        System.out.println(idLoc);
        
      stage.close();
    }

    
    class MyBrowser extends Pane {


        WebView webView = new WebView();
           
        WebEngine webEngine = webView.getEngine();

        public MyBrowser() {

            final URL urlGoogleMaps = getClass().getResource("demo.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
                @Override
                public void handle(WebEvent<String> e) {
                    System.out.println(e.toString());
                }
            });

            getChildren().add(webView);

            final TextField latitude = new TextField("" + 35.857908 * 1.00007);
            final TextField longitude = new TextField("" + 10.598997 * 1.00007);
            Button update = new Button("Update");
            update.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    double lat = Double.parseDouble(latitude.getText());
                    double lon = Double.parseDouble(longitude.getText());

                    System.out.printf("%.2f %.2f%n", lat, lon);

                    webEngine.executeScript("" +
                        "window.lat = " + lat + ";" +
                        "window.lon = " + lon + ";" +
                        "document.goToLocation(window.lat, window.lon);"
                    );
                }
            });

        }
    }
   
    @FXML
    private AnchorPane anchor;

    
    public void initialize(URL url, ResourceBundle rb) {
     
            WebView web=new WebView();
            
            WebEngine webEngine= web.getEngine();
            webEngine.load("http://192.168.8.105/medos/localisation/maps.html");
            anchor.getChildren().add(web);
    }

   
        @Override
    Scene getScene() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = null;
        try {
            root = fxmlLoader.load(
                    MainApp.getInstance().getClass().getResource("GoogleMapsDisplay.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        setParent(root);
        s=scene;
        return scene;
    }
    
}
