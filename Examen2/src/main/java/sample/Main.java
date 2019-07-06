package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Domain.*;
import sample.Repository.FileRepository;
import sample.Repository.IRepository;
import sample.Service.ExaminareService;
import sample.Service.ExaminatorService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Parent root = fxmlLoader.load();

        IValidator < Examinator > examinatorValidator = new ExaminatorValidator();
        IRepository <Examinator> examinatorIRepository= new FileRepository<>(examinatorValidator, "examinator.json", Examinator[].class);

        ExaminatorService examinatorService = new ExaminatorService(examinatorIRepository);
        examinatorService.add(1, "ionescu", "2770922011864" );
        examinatorService.add(2, "popescu", "1234567891000");
        examinatorService.add(3, "moraru", "0123456789101");

        IValidator<Examinare> examinareIValidator = new ExaminareValidator();
        IRepository<Examinare> examinareIRepository= new FileRepository<>(examinareIValidator, "examinari.json", Examinare[].class);

        ExaminareService examinareService = new ExaminareService(examinareIRepository, examinatorIRepository);
        examinareService.add(1, 1, "respins", 1);
        examinareService.add(2, 2, "respins", 3);
        examinareService.add(3,3, "respins", 5);

        examinareService.fullTextSearch("po");
        examinareService.getRaport( "5" );


        MainController controller =  fxmlLoader.getController();
        controller.setServices( examinatorService, examinareService);

        primaryStage.setTitle("Examinare");
        primaryStage.setScene(new Scene(root, 650, 500));

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

