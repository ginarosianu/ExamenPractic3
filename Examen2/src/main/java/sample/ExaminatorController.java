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
import sample.Service.ExaminatorService;

public class ExaminatorController {


    public TableView tableViewPersoana;
    public TableColumn tableColumnId;
    public TableColumn tableColumnNume;
    public TableColumn tableColumnCNP;
    public TextField txtPersoanaId;
    public TextField txtPersoanaNume;
    public TextField txtPersoanaCNP;
    public Button btnAddPersoana;
    private ExaminatorService examinatorService;

    public void setService(ExaminatorService examinatorService) {
        this.examinatorService = examinatorService;
    }


    private ObservableList < Examinator > examinatori = FXCollections.observableArrayList();

        @FXML
        private void initialize() {

            Platform.runLater( new Runnable() {
                public void run() {
                    examinatori.addAll( examinatorService.getAll() );
                    tableViewPersoana.setItems( examinatori );
                }
            } );
        }

        public void btnAddPersoana(ActionEvent actionEvent) {
            try {
                Integer id = Integer.parseInt(txtPersoanaId.getText());
                String nume = txtPersoanaNume.getText();
                String CNP = txtPersoanaCNP.getText();

                examinatorService.add(id, nume, CNP);

                examinatori.clear();
                examinatori.addAll( examinatorService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }

 }




