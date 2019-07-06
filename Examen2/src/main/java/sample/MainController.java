package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Service.ExaminareService;
import sample.Service.ExaminatorService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    public Button btnPersoana;
    public Button btnExaminare;
    public Button btnRaport;

    private ExaminatorService examinatorService;
    private ExaminareService examinareService;


    public void setServices(ExaminatorService examinatorService, ExaminareService examinareService) {
        this.examinatorService = examinatorService;
        this.examinareService = examinareService;
    }


    public void btnPersoanaClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation( getClass().getResource( "/Examinator.fxml" ) );

            Scene scene = new Scene( fxmlLoader.load(), 1000, 700 );
            Stage stage = new Stage();
            stage.setTitle( "Persoana" );
            stage.setScene( scene );
            stage.initModality( Modality.APPLICATION_MODAL );

            ExaminatorController controller = fxmlLoader.getController();
            controller.setService( examinatorService );

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger( getClass().getName() );
            logger.log( Level.SEVERE, "Failed to create new window: Persoana operations.", e );
        }

    }

    public void btnExaminareClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Examinare.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Examinare");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            ExaminareController controller = fxmlLoader.getController();
            controller.setService(examinareService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Examinare operations.", e);
        }
    }

    public void btnRaportClick(ActionEvent actionEvent) {
    }
}
