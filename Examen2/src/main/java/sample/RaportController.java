package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Domain.Examinator;
import sample.Service.ExaminareService;
import sample.Service.ExaminatorService;

public class RaportController {
    public TableView tableViewRaport;
    public TableColumn tableColumnNrX;
    public TableColumn tableColumnZiua;
    public TextField txtData;
    public Button btnAddRaport;
    private ExaminareService examinareService;


    public void setService(ExaminareService examinatreService) {
        this.examinareService = examinareService;
    }

    private ObservableList < Examinator > examinatori = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

//        Platform.runLater( new Runnable() {
//            public void run() {
//                examinatori.addAll( examinareService.getAll() );
//                tableViewRaport.setItems( examinatori );
//            }
//        } );
    }
    public void btnAddRaport(ActionEvent actionEvent) {

    }
}
