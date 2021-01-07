
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Profe2
 */
public class AppHotel extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException { 
        
        //Conectamos y cargamos el archivo fxml del Main y lo asociamos como elemento raíz de la ventana. Ponemos como standard o principal la MainView en el StackPane
        FXMLLoader fxmlLoader=new
            FXMLLoader(getClass().getResource("NewMainView.fxml"));
        Pane Main=fxmlLoader.load();
        
        
        Scene scene = new Scene(Main, 600, 400);
        
        primaryStage.setTitle("AppHotel");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("img/icono.png"));
        primaryStage.show();
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

