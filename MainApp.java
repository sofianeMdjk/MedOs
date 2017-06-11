/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author sofiane
 */
public class MainApp extends Application {
    
    
    public static String ip_adress;
    static MainApp  _instance = null;
    public  MainWindow myWin;
    
        
      public static MainApp getInstance()
    {
        return _instance;
    }
    public MainWindow getMyWin()
    {
           return myWin;
    }
    void init(Stage s)
    {
        myWin = new MainWindow(s);
        _instance = this;
    }
       
   
      
    
    @Override
    public void start(Stage stage) throws Exception {
        String ip="";
        init(stage);
        TextInputDialog ipDialog =new TextInputDialog();
         ipDialog.setTitle("Enter ip adress");
         Optional <String> textIn= ipDialog.showAndWait();
         if(textIn.isPresent())
         {
             ip=textIn.get();
              ip_adress=ip;
              myWin.nextScene(new MainWindowController());
         }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public static void openWindow(String name,MyScene sc)
    {
        Stage s =new Stage();
        s.setScene(sc.getScene());
        s.setTitle(name);
        s.show();
    }
    
}
